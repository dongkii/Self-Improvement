> ## 용어 정리

- JPA (Java Persistence API)  
    - 자바 ORM 기술에 대한 표준 명세로, `JAVA`에서 제공하는 API이다. 스프링에서 제공하는 것이 아님!  
    - 자바 어플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스 이다.
        - 여기서 중요하게 여겨야 할 부분은, JPA는 말 그대로 인터페이스라는 점이다. JPA는 특정 기능을 하는 라이브러리가 아니다. 스프링의 PSA에 의해서(POJO를 사용하면서 특정 기술을 사용하기 위해) 표준 인터페이스를 정해두었는데, 그중 ORM을 사용하기 위해 만든 인터페이스가 JPA이다.
    - 기존 EJB에서 제공되던 Entity Bean을 대체하는 기술이다.
    - ORM이기 때문에 자바 클래스와 **`DB테이블`** 을 매핑한다.(sql을 매핑하지 않는다.)
    - 지향하는 바가 다른 2개의 영역(객체지향 프로그래밍 언어와 관계형 데이터베이스)을 **중간에서 패러다임 일치**를 시켜주기 위한 기술  
<br/>

- ORM (Object-Relation Mapping/객체-관계 매핑)  
    - `DB 데이터` ← mapping → Object 필드
        - 객체를 통해 간접적으로 DB데이터를 다룬다.
    - 객체와 DB의 데이터를 자동으로 매핑해준다.
        - SQL 쿼리가 아니라 **메서드로 데이터를 조작할 수 있다.**
        - 객체간 관계를 바탕으로 **SQL을 자동으로 생성** 한다.
    - Persistant API라고 할 수 있다.
    - JPA, Hibernate  
<br/>

- Spring 웹 계층  
    - Web Layer
        - 흔히 사용하는 컨트롤러(@Controller)와 JSP/Freemarker 등의 뷰 템플릿 영역
        - 이외에도 필터(@Filter), 인터셉터, 컨트롤러 어드바이스(@ControllerAdvice)등 **외부요청과 응답**에 대한 전반적인 영역을 이야기한다.  
    <br/>

    - Service Layer
        - @Service에 사용되는 서비스 영역
        - 일반적으로 Controller와 Dao의 중간 영역에서 사용
        - @Transactional이 사용되어야 하는 영역이기도 하다.  
    <br/>
    - Repository Layer
        - Database와 같이 데이터 저장소에 접근하는 영역이다.
        - 기존에 개발하셨던 분들이라면 Dao(Data Access Object) 영역으로 이해하면 된다.  
    <br/>
    - Dtos
        - Dto(Data Transfer Object)는 **`계층 간에 데이터 교환을 위한 객체`** 를 이야기하며 Dtos는 이들의 영역을 얘기한다.
        - 예를 들어 뷰 템플릿 엔진에서 사용될 객체나 Repository Layer에서 결과로 넘겨준 객체 등이 이들을 이야기한다.  
    <br/>
    - Domaimn Model - **`비즈니스 처리 담당`**
        - 도메인이라 불리는 개발 대상을 모든 사람이 동일한 관점에서 이해할 수 있고 공유할 수 있도록 단순화시킨 것을 도메인 모델이라고 한다.
        - 이를테면 택시 앱이라고 하면 배차, 탑승, 요금 등이 모두 도메인이 될 수 있다.
        - @Entity를 사용해보신 분들은 @Entity가 사용된 영역 역시 도메인 모델이라고 이해하면 된다.
        - 다만, 무조건 데이터베이스의 테이블과 관계가 있어야만 하는 것은 아니다.
        - VO처럼 값 객체들도 이 영역에 해당하기 때문  
    <br/>
- 패러다임 불일치
    - 관계형 데이터베이스는 **어떻게 데이터를 저장**할지에 초점이 맞춰진 기술
    - 객체지향 프로그래밍 언어는 메시지를 기반으로 **기능과 속성을 한곳에서 관리하는 기술**
    - 서로 반대의 기술을 일치하는 것이 개발자의 몫, 관계형 데이터베이스에서는 상속에 대한 표현을 할 수 없음  
    <br/>


> ## JPA
- 인터페이스인 JPA를 사용하기 위해서는 구현체가 필요.  
    - 대표적으로는 Hibernate, EclipseLink 등이 있다.  
- 구현체를 좀 더 쉽게 사용하고자 추상화 시킨 **`Spring Data JPA`** 라는 모듈을 사용  
    - JPA <- Hibernate <- Spring Data JPA  
- Hibernate와 Spring Data JPA를 쓰는 것 사이에는 큰 차이가 없으나 이를 사용 하는 이유는 두가지가 있다.  
    1. 구현체 교체의 용이성 - **Hibernate 외에 다른 구현체로 쉽게 교체하기 위함**
    2. 저장소 교체의 용이성 - **관계형 데이터베이스 외에 다른 저장소로 쉽게 교체하기 위함**
- JPA를 사용해서 얻는 보상중 하나는 CRUD 쿼리를 직접 작성할 필요가 없다.  
<br/>
> 게시판 만들기 예제
- 게시판의 요구사항
```
- 게시판 기능
    - 게시글 조회
    - 게시글 등록
    - 게시글 수정
    - 게시글 삭제

- 회원 기능
    - 구글/네이버 로그인
    - 로그인한 사용자 글 작성 권한
    - 본인 작성 글에 대한 권한 관리
```

<br/>  

> JPA 구조
```
domain (package)  
    ㄴ posts (package)
        ㄴ Posts (class) DB와 매핑될 클래스, Entity 클래스라고도 함
        ㄴ PostsRepository (interface)  Posts 클래스로 DB를 접근하게 해줄 JpaRepository 를 생성 Dao라고 불리는 DB Layer 접근자
    ㄴ BaseTimeEntity 모든 Entity 클래스의 상위클래스가 되어 createdDate, modifiedDate를 자동으로 관리
service
    ㄴ PostsService
web
    ㄴ dto
        ㄴ PostsSaveRequestDto  controller와 service에서 사용할 dto, Request/Response용 dto, 자주변경 필요
    ㄴ PostsApiController   service와의 연결은 생성자로 주입, @RequiredArgsContructor 를 이용 final이 선언된 모든 필드를 생성자로 만듬
```  
- Entity 클래스에는 절대 Setter 메소드를 만들지 않는다.
- 생성자를 통해 최종값을 채운 후 DB에 삽입 하는것이며, 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출하여 변경하는 것을 전제로 해야함
- 생성자 대신 @Builder를 통해 제공되는 빌더 클래스를 사용  
<br/>

> ### 각 클래스의 역할
- Controller - 비지니스 로직 처리
- Service - 트랜잭션과 도메인 순서 보장
- Dto - Entity의 필드 중 일부만 사용, Entity를 받아 처리, Response/Request가 자주 일어나는 클래스, 결과값으로 여러 테이블 조인이 필요할 떄 사용됨
- Entity - Entity의 수정시 모든 클래스 수정이 필요함, DB와 매핑될 클래스, Entity는 **`DB와 맞닿은 핵심 클래스`**, Entity를 기준으로 스키마가 변경 됨
- JpaRepository - Enitiy와 DB를 연결해주는 DB Layer 접근자, Dao라고 불림
- BaseTimeEntity - 모든 Entity클래스의 슈퍼클래스가 되며, 해당 클래스의 필드가 모든 필드에 적용

<br/>

> ex) new Example(b, a) 처럼 a와 b의 위치를 변경해도 코드를 실행하기 전까지는 문제를 찾을 수 없는경우
```java
public Example(String a, String b) {
    this.a = a;
    this.b = b;
}
```
<br/>

> ex) 빌더를 사용하게 될 경우  

```java
Example.builder()
    .a(a)
    .b(b)
    .build();
```
- 위와 같이 **어느 필드에 어떤 값을 채워야 할지** 명확하게 인지할 수 있다.

- Service 에서는 비지니스 로직을 처리하는 것이 아니라 **`트랜잭션, 도메인 간 순서 보장 역할`** 만 한다.

- update 기능에서 DB 쿼리를 날리는 부분이 없는 이유? - **JPA의 영속성 컨텍스트** 때문
    - 영속성 컨텍스트란, **엔티티를 영구 저장**하는 환경
    - 일종의 논리적 개념, JPA의 핵심 내용은 **엔티티가 영속성 컨텍스트에 포함 되어있냐 아니냐**로 갈린다.
    - JPA의 엔티티 매니저가 활성화된 상태로(Spring Darta Jpa를 쓴다면 기본옵션) **트랜잭션 안에서 데이터베이스에서 데이터를 가져오면** 영속성 컨텍스트가 유지된 상태이다.
    - 해당 상태에서 데이터의 값을 변경하면 **트랜잭션이 끝나는 시점에 해당 테이블에 변경분을 반영** 한다.
    - 위 개념을 **`더티 체킹(dirty checking)`** 이라고 한다  
<br/>
<br/>

> ## 머스테치로 화면 구성하기  
- 템플릿 엔진이란, **지정된 템플릿 양식과 데이터** 가 합쳐져 HTML문서를 출력하는 소프트웨어
- 서버 템플릿 엔진 - JSP, Freemaker
- 클라이언트 템플릿 엔진 - 리액트(React), 뷰(Vue)
- 머스테치(mustache)는 수많은 언어를 지원하는 가장 심플한 템플릿엔진
    - 루비, 자바스크립트, 파이썬, PHP, 자바, 펄, Go, ASP등 현존하는 대부분의 언어 지원
- 다른 템플릿 엔진들의 단점
    - JSP, Velocity : 스프링 부트에서는 권장하지 않는 템플릿 엔진
    - Freemaker - 템플릿 엔진으로는 너무 과하게 많은 기능을 지원한다. 높은 자유도로 인해 숙련도가 낮을수록 Freemaker 안에 비즈니스 로직이 추가될 확률이 높다.
    - Thymeleaf - 스프링 진영에서 적극적으로 밀고 있지만 문법이 어렵다. HTML태그에 속성으로 템플릿 기능을 사용하는 방식이 기존 개발자한테 높은 허들로 느껴지는 경우가 많다.  
<br/>
- 머스테치의 장점
    - 문법이 다른 템플릿 엔진보다 심플
    - 로직 코드를 사용할 수 없어 View의 역할과 서버의 역할이 명확하게 분리된다.
    - Mustache.js와 Mustache.java 2가지가 다 있어, 하나의 문법으로 클라이언트/서버 템플릿을 모두 사용 가능  
<br/>
- 로딩속도를 높이기 위해 css는 header에 js는 footer에서 받아 처리
    - head가 실행되고서야 body가 실행되기 때문에 head가 불러지기 전까지는 백지화면만 노출
    - 부트스트랩의 경우 제이쿼리가 꼭 있어야하므로, 부트스트랩보다 앞에 제이쿼리를 불러옴  
<br/>

- {{>layout/header}} - {{>}}는 현재 머스테치 파일(index.mustache)을 기준으로 다른 파일을 가져온다.
- index.js 의 첫문장에 var main = {} 을 쓰는 이유는 index객체 안에서만 function 이 유효하도록 처리하기 위함
