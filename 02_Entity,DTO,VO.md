# Entity, DTO, VO 바로알기

## **목차**
- [1. Entity 란?](#1)
    - [1.1 Entity, DTO Class 분리 이유](#1_1)
    - [1.2 Entity Setter 금지 및 생성자, 접근 제어](#1_2)
    - [1.3 Entity 예제](#1_3)
- [2. VO(Value Object)](#2)
    - [2.1 VO 예제](#2_1)
- [3. DTO (Data Transfer Object)](#3)
    - [3.1 DTO 예제](#3_1)
    - [3.2 VO와 DTO의 차이점](#3_2)
- [최종](#end)
<br/>

## 1. Entity 란 ?<a id="1"></a>
> `Entity Class`는 실제 BataBase의 테이블과 1 : 1 로 Mapping 되는 class로,  
DB의 테이블내에 존재하는 컬럼만을 속성(필드)로 가져야한다.  
`Entity Class`는 상속을 받거나 구현체여서는 안되며, 테이블내에 존재하지 않는 컬럼을 가져서도 안된다.

`Entity Class` 또는 `가장 Core한 Class` 라고 부른다.

최대한 외부에서 `Entity Class`의 Data Field에 함부로 접근하지 못하도록 제한하여,  
해당 Class 안에서 접근을 허용할 데이터들을 제한하며 login method를 구현 해야하고,  
`Domain Login`만 가지며  Presentation Logic을 가지고 있어서는 안된다.

Spring 3 Tier 인 `Persistence`, `Business`, `Presentation` Tier 중 `Persistence Tier`에서 사용

구현 method는 주로 Service Layer에서 사용한다.  
Entity를 `Persistence Tier`에서 받아와 DTO로 변환하여 `Presentation Tier`에 전달하는 것이 `Business Tier`, `Service` 단에서의 역할

## 1.1 Entity, DTO Class 분리 이유<a id="1_1"></a>
`Entity`와 `DTO`를 분리해서 관리해야 하는 이유는 `DB Layer`와 `View Layer` 사이의 역할을 분리하기 위해서다.

`DB Layer` = `Persistence Tier`  
`View Layer` = `Presentation Tier`

`Entity`는 **실제 테이블과 매핑**되어 만일 **변경되게 되면 여러 다른 Class에 영향**을 끼치고,  
`DTO`는 View와 통신하며 **자주 변경되므로 분리** 해주어야 한다.  
결국 DTO는 Domain Model 객체(`Entity`)를 그대로 두고 복사하여,  
다양한 Presentation Logic을 추가한 정도로 사용하며 Domain Model 객체(`Entity`)는 `Persistent`만을 위해서 사용해야한다.

## 1.2 Entity Setter 금지 및 생성자, 접근 제어  <a id="1_2"></a>
`Entity`를 작설할 때 Setter를 무분별하게 사용하면 객체(`Entity`)의 값을 쉽게 변경할 수 있으므로, 객체의 일관성을 보장할 수 없다.  
객체의 일관성을 유지할 수 있어야 유지 보수성이 올라가기 때문에 Setter 사용을 최대한 지양해야하며, 객체의 생성자에 값들을 넣어줌으로써 Setter 사용을 줄일 수 있다.

```java
public Member(String userName, String passWord, String name) {
    this.username = userName;
    this.password = passWord;
    this.name = name;
}
```

### Builder Patten 으로 객체 생성 제한
여기에 더 나아가 `Builder Pattern` 을 사용하면 해당 `Entity` 객체를 생성할 때 그 목적과 함께 필요 용도에 따라, 객체 생성을 제한할 수 있어 명시적이고 안전한 Core Data 접근이 가능하다.

```java
@ToString(callSuper = true)
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true, of = "seq")
@Table(name = "TB_COMMENT")
@Entity
public class CommentEntity extends CommonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "C_SEQ")
    private long seq;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "P_SEQ")
    private long pSeq;

    @Column(name = "VIEW")
    private boolean view;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "U_SEQ")
    private UserEntity user;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "O_SEQ")
    private OrderEntity order;

    public void setUser(UserEntity userEntity) {
        if(user != null) {
            user.getComments().remove(this);
        }
        user = userEntity;
        if(!user.getComments().contains(this)) {
            user.getComments().add(this);
        }
    }

    public void setOrder(OrderEntity orderEntity) {
        if(order != null) {
            order.getComments().remove(this);
        }
        order = orderEntity;
        if(!order.getComments().contains(this)) {
            order.getComments().add(this);
        }
    }

    // 객체 생성시에 사용할 Builder Method의 이름 지정
    // Builder 사용시에도 생성자를 통해 객체를 생성하지만
    // 더욱 명시적으로 객체 생성시에 이유를 알 수 있다.
    @Builder(
            builderClassName = "init"
            , builderMethodName = "initComment"
    )
    private CommentEntity(CommentDTO dto) {
        seq = dto.getSeq();
        comment = dto.getComment();
        if(dto.getView() != null) view = dto.getView().booleanValue();
        
        // 해당 구문 처럼 Entity와 DTO간 변환시에도 목적을 명확히 할 수 있다.
        if(dto.getUser() != null) {
            user = UserEntity
                    .initUser()
                    .seq(dto.getUser().getSeq())
                    .build();
        }
        if(dto.getOrder() != null) {
            order = OrderEntity
                    .initOrderSeq()
                    .seq(dto.getOrder().getSeq())
                    .build();
        }
        if(dto.getParentComment() != null) {
            pSeq = dto.getParentComment().getSeq();
        }
    }
}
```

아래와 같이 기본 생성자 접근 제한자를 protected로 변경하면 new Member() 사용을 제한해 Entity의 일관성을 더 유지할 수 있다.

```java
// Member Entity
@Entity
@Getter
@EqualsAndHashCode(of = "seq")
@Table(name = "member")
public class Member {
    // 기본 생성자 protected로 접근 제한(기본 생성자 접근 제한자는 protected 까지 허용
    // 기본 생성자의 접근 제한자를 private으로 걸면, 추후에 Lazy Loading 사용 시 Proxy 관련 예외가 발생)
    protected Member() {};
    
    ...
}

// @NoArgsconstructor 어노테이션을 통한 protected 접근 제어
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@EqualsAndHashCode(of = "seq")
@Table(name = "member")
public class Member {
    ...
}
```

## 1.3 Entity 예제<a id="1_3"></a>
```java
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
@Table(name = "user")
@Entity
public class User {
    
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    @Column(nullable = true)
    private LocalDateTime create_date;

    private LocalDateTime modify_date;

    @Builder(
            builderClassName = "init",
            builderMethodName = "initUser"
    )

    private User(UserDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        ...
    }
}
```  

- `@Entity`  
    테이블과 링크될 클래스임을 나타낸다.  
    기본값으로 카멜케이스 이름을 언더 스코어 네이밍(_)으로 테이블 이름을 매핑한다.  
    ex) SalesManager.java -> sales_manager table

- `@GeneratedValue`  
    PK 생성 규칙  
    스프링 부트 2.0에서는 GenerationType.IDENTITY 옵션을 추가해야만 auto_increment가 된다.

- `@Id`  
    해당 테이블의 PK (Primary Key) 필드를 가리킨다.

- `@Column`  
    테이블의 컬럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 컬럼이다.  
    사용하는 이유는, 기본값 외에 추가로 변경이 필요한 옵션이 있으면 사용한다.

- `@Builder`  
    해당 클래스의 빌더 패턴 클래스를 생성  
    생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함

<br/>
<br/>
<br/>

## 2. VO(Value Object)<a id="2"></a>

> `VO(Value Object)`는 말 그대로 값 객체라는 의미를 가지고 있다.  
`VO`의 핵심 역할은 equals()와 hashcode()를 오버라이딩 하는 것이다.  
`VO` 내부에 선언된 속성(Field)의 모든 값들이 VO 객체마다 값이 같아야, 똑같은 객체라고 판별한다.  

VO는 Getter와 Setter를 가질 수 있으며,  
VO는 테이블 내에 있는 속성 외에 추가적인 속성을 가질 수 있으며,  
여러 테이블(A, B, C)에 대한 공통 속성을 모아서 만든 BaseVO 클래스를 상속받아서 사용할 수도 있다.  

## 2.1 VO 예제 <a id="2_1"></a>  

```java
@Getter
@Setter
@Alias("article")
class ArticleVO {
    private Long id;
    private String title;
    private String contents;

    @Override
    public boolean equals(Object o) {
        if (this == 0) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
```

<br/>
<br/>
<br/>

## 3. DTO (Data Transfer Object)<a id="3"></a>
> `DTO(Data Transfer Object)`는 데이터 전송(이동) 객체라는 의미를 가지고 있다.  
DTO는 주로 비동기 처리를 할 때 사용한다.

`DTO`는 계층간 데이터 교환을 위한 객체이다.  
DB의 데이터를 Service나 Controller 등으로 보낼 때 사용하는 객체를 말한다.  
즉, DB의 데이터가 `Presentation Logic Tier`로 넘어올 때는 DTO로 변환되어 오고가는 것이다.  
로직을 갖고 있지 않는 순수한 데이터 객체이며, `getter/setter` 메서드만을 갖는다.  
또한 `Controller Layer(ViewLayer)`에서 Response DTO 형태로 Client에 전달한다.

## 3.1 DTO 예제 <a id="3_1"></a>

```java
@Getter
@Setter
class ArticleDTO {
    private String title;
    private String content;
    private String writer;
}
```

## 3.2 VO와 DTO의 차이점 <a id="3_2"></a>

`VO`는 `DTO`와 Data를 전달하는 객체로 동일한 개념이지만,  
`VO`는 조금 더 특정한 Business Logic의 결과 값을 담는 객체이다.  

`equals`, `hashCode` Method를 구현하여 특정 중요한 Data를 전달할 때는  
`VO`를 생성하여 이를 동일한 객체 비교까지 필요한 Logic내에서 주로 사용하게 된다.  

`DTO`는 Layer간의 단순 통신 용도로 오고가는 Data 전달 객체이다.  
조금 더 포괄적으로 제한없이 사용할 수 있는 객체이므로,  
민감하지 않거나 해당 객체안의 값들을 통해 동일한 객체임을 비교하는 로직에 사용되지 않을 때는 단순하게 `DTO`를 사용하면 된다.

# 최종 <a id="end"></a>

마지막으로 `Entity`, `VO`, `DTO`를 아래 표를 통해 비교하며 정리하였다.

해당 내용들을 상기하며 Data를 담는 객체 생성시에 그 목적에 따라 달리 생성하며 사용해보자.

||Entity|VO|DTO|
|-|---|---|----|
|생성목적|JPA사용시 DB Table과 직접적으로 매핑하여 DB에 접근할 때 사용|객체안의 값을 통해서도 비교해야하는 중요 로직에서 사용할 Data를 담기위해 사용|단순 Data 송,수신 용도|
|Getter 사용|O|O|O|
|Setter 사용|X|O|O|
|equals & hashCode 구현|O|O|X|
|데이터 민감도|100|80|0|
|Sptirng Tier 사용 범위|Persistence(DAO, Repository) <-> Business(Service)|Business(Service) <-> Presentation(Controller)|Business(Service) <-> Presentation(Controller)|


<br/>
<br/>
<br/>


> 출처 및 참고 : https://velog.io/@gillog/Entity-DTO-VO-%EB%B0%94%EB%A1%9C-%EC%95%8C%EA%B8%B0