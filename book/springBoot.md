> # 스프링 부트와 AWS로 혼자 구현하는 웹 서비스

## **목차**
- [용어정리](#1)
- [Chapter 03. 스프링 부트에서 JPA로 데이터베이스 다뤄보자](#03)
- [Chapter 04. 머스테치로 화면 구성하기](#04)
- [Chapter 05. 스프링 시큐리티와 OAuth2.0으로 로그인 기능 구현하기](#05)

> ### 용어 정리

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

<a id="03"></a>

> ## Chapter 03. 스프링 부트에서 JPA로 데이터베이스 다뤄보자
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

    ```java
    public class Posts {
        ...

        public void update(String title, String content) {
            this.title = title;
            this.content = content;
        }
    }

    /*
    해당 업데이트 메서드는 Entity 클래스에 작성되며, Setter처럼 생성자의 값을 변경할 경우 DB접근이 일어나게 된다. 이때문에 Entity 클래스에 Setter를 작성하지 않는 이유가 되며, 필드의 값 변경이 필요하면 명확히 그목적과 의도를 나타낼 수 있는 메서드를 만들어야 함
    */
    ```
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

<a id="04"></a>

> ## Chapter 04. 머스테치로 화면 구성하기  
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

<br/>
<br/>

<a id="05"></a>

> ## Chapter 05. 스프링 시큐리티와 OAuth2.0으로 로그인 기능 구현하기  
- 스프링 `시큐리티(Spring Security)`는 `막강한 인증(Authentication)`과 `인가(Authorization)` 기능을 가진 프레임워크이다.  
- 스프링 시큐리티는 확장성을 고려한 프레임워크이다 보니 `다양한 요구사항을 손쉽게 추가 및 변경` 가능


> 코드설명 P.178
```md
- 1. @Enumerated(EnumType.STRING)
    - JPA로 데이터베이스로 저장할 떄 Enum 값을 어떤 형태로 저장할지를 결정한다.
    - 기본적으로 int로 된 숫자가 저장된다.
    - 숫자로 저장되면 데이터베이스로 확인할 때 그 값이 무슨 코드를 의미하는지 알 수가 없다.
    - 그래서 문자열 (EnumType.STRING)로 저장될 수 있도록 선언
```
<br/>

> 코드설명 P.179
```md
## UserRepository ##
- 1. findByEmail
    - 소셜 로그인으로 반환되는 기본 값 중 email을 통해 이미 생성된 사용자인지 처음 가입하는 사용자인지를 판단하기 위한 메소드

## build.gradle에 의존성 추가 ##
- 2. spring-boot-starter-oauth2-client 
    - 소셜 로그인 등 
```
<br/>

> 코드설명 P.181
```md
## SecurityConfig ##
- 1. @EnableWebSecurity
    - Spring Security 설정들을 활성화

- 2. csrf().disable().headers().frameOptions().disable()
    - h2-console 화면을 사용하기 위해 해당 옵션들을 disable 한다.

- 3. authorizeRequests
    - URL별 권한 관리를 설정하는 옵션의 시작점
    - authorizeRequests가 선언되어야만 antMatchers 옵션을 사용할 수 있다.

- 4. antMatchers
    - 권한 관리 대상을 지정하는 옵션
    - URL, HTTP 메소드별로 관리가 가능하다.
    - "/"등 지정된 URL들은 permitAll() 옵션을 통해 전체 열람 권한을 주었다.
    - "/api/v1/**" 주소를 가진 API는 USER 권한을 가진 사람만 가능하도록 처리

- 5. anyRequest
    - 설정된 값들 이외 나머지 URL들을 나타낸다.
    - 여기서는 authenticated()을 추가하여 나머지 URL들은 모두 인증된 사용자들에게만 허용하게 한다.
    - 인증된 사용자 즉, 로그인한 사용자들을 말한다.

- 6. logout().logoutSuccessUrl("/")
    - 로그아웃 기능에 대한 여러 설정의 진입점이다.
    - 로그아웃 성공 시 / 주소로 이동한다.

- 7. oauth2Login
    - OAuth 2 로그인 기능에 대한 여러 설정의 진입점

- 8. userInfoEndpoint
    - OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당

- 9. UserService
    - 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
    - 리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다.
```
<br/>

> 코드설명 P.184
```md
## CustomOAuth2UserService ##
- 1. registrationId
    - 현재 로그인 진행 중인 서비스를 구분하는 코드
    - 지금은 구글만 사용하는 불필요한 값이지만, 이후 네이버 로그인 연동 시에 네이버로 로그인인지, 구글 로그인인지 구분하기 위해 사용

- 2. userNameAttributeName
    - OAuth2 로그인 진행 시 키가 되는 필드값을 이야기한다. Primary Key와 같은 의미
    - 구글의 경우 기본적으로 코드를 지원하지만, 네이버 카카오 등은 기본 지원하지 않는다. 구글의 기본코드는 "sub"
    - 이후 네이버 로그인과 구글 로그인을 동시 지원할 때 사용

- 3. OAuthAttributes
    - OAuth2UserService를 통해 가져온 OAuth2User의 attribute를 담을 클래스
    - 이후 네이버 등 다른 소셜 로그인도 이 클래스를 사용

- 4. SessionUser
    - 세션에 사용자 정보를 저장하기 위한 Dto 클래스
```
- SessionUser에 대한 추가 설명
    - 세션에 저장을 위해 `User 클래스(Entity)`를 사용하지 않는 이유는 `직렬화를 구현하지 않았다`라는 에러 때문
    - 에러를 해결하기 위해 User에 직렬화코드(Serializable)를 넣을 경우 다른 엔티니와 관계가 형성될지도 모름
    - 예를들어 User 엔티티가 @OneToMany, @ManyToMany 등 자식 엔티티를 가지고 있으면 직렬화 대상에 자식들까지 포함되니 `성능이슈`, `부수 효과`가 발생할 확률이 높다.
    - 위의 이슈들 때문에 `직렬화 기능을 가진 세션 Dto`를 하나 추가로 만드는것이 유지보수에 유리하다.


<br/>

> 코드설명 P.186
```md
## OAuthAttributes ##
- 1. of()
    - OAuth2User에서 반환하는 사용자 정보는 Map이기 때문에 값 하나하나를 변환해야만 한다.

- 2. toEntity()
    - User 엔티티를 생성
    - OAuthAttributes에서 엔티티를 생성하는 시점은 처음 가입할 때 이다.
    - 가입할 때의 기본 권한을 GUEST로 주기 위해서 role 빌더값에는 Role.GUEST를 사용
```
<br/>


> 코드설명 P.189
```md
## index.mustache ##
- 1. {{#userName}}
    - 머스테치는 다른 언어와 같은 if문(if userName != null 등)을 제공하지 않습니다.
    - true/false 여부만 판단할 뿐입니다.
    - 그래서 머스테치에서는 항상 최종값을 넘겨줘야 합니다.
    - 여기서도 역시 userName이 있다면 userName을 노출시키도록 구성  

- 2. a href="/logout"
    - 스프링 시큐리티에서 기본적으로 제공하는 로그아웃 URL
    - 개발자가 별도로 저 URL에 해당하는 컨트롤러를 만들필요가 없다.
    - SecurityConfig 클래스에서 URL을 변경할 순 있지만 기본 URL을 사용해도 충분하니 그대로 사용

- 3. {{^userName}}
    - 머스테치에서 해당 값이 존재하지 않는 경우에는 ^를 사용
    - 여기서는 userName이 없다면 로그인 버튼을 노출시키도록 구성
 
- 4. a href="/oauth2/authorization/google"
    - 스프링 시큐리티에서 기본적으로 제공하는 로그인 URL
    - 로그아웃 URL과 마찬가지로 개발자가 별도의 컨트롤러를 생설할 필요가 없다.
```
<br/>

> 코드설명 P.191
```md
## IndexController ##
- 1. (SessionUser) httpSession.getAttribute("user")
    - 앞서 작성된 CustomOAuth2UserService에서 로그인 성공 시 세션에 SessionUser를 저장하도록 구성
    - 즉, 로그인 성공 시 httpSession.getAttribute("user")에서 값을 가져올 수 있다.

- 2. if(user != null)
    - 세션에 저장된 값이 있을 때만 model에 userName으로 등록한다.
    - 세션에 저장된 값이 없으면 model엔 아무런 값이 없는 상태이니 로그인 버튼이 보이게 된다.
```
<br/>

> 코드설명 P.196
```md
## @LoginUser ##
- 1. @Target(ElementType.PARAMETER)
    - 이 어노테이션이 생성될 수 있는 위치를 지정
    - PARAMETER로 지정했으니 메소드의 파라미터로 선언된 객체에서만 사용할 수 있다.
    - 이 외에도 클래스 선언문에 쓸 수 있는 TYPE 등이 있다.

- 2. @interface
    - 이 파일을 어노테이션 클래스로 지정
    - LoginUser라는 이름을 가진 어노테이션이 생성되었다고 보면 된다.
```
<br/>

> 코드설명 P.197
```md
## LoginUserArgumentResolver ##
- 1. supportsParameter()
    - 컨트롤러 메소드의 특정 파라미터를 지원하는지 판단
    - 여기서는 파라미터에 @LoginUser 어노테이션이 붙어있고, 파라미터 클래스 타입이 SessionUser.class인 경우 true를 반환

- 2. resolverArgument()
    - 파라미터에 전달할 객체를 생성
    - 여기서는 세션에서 객체를 가져온다
```
<br/>


> 코드설명 P.207
```md
## application-oauth.properties ##
- 1. user_name_attribute=response
    - 기준이 되는 user_name의 이름을 네이버에서는 response로 해야한다.
    - 이유는 네이버의 회원 조회 시 반환되는 JSON 형태기 때문
```
<br/>

> 코드설명 P.209
```md
## index.mustache ##
- 1. /oauth2/authorization/naver
    - 네이버 로그인 URL은 application-oauth.properties에 등록한 redirect-uri 값에 맞춰 자동으로 등록된다.
    - /oauth2/authorization/ 까지는 고정이고 마지막 Path만 각 소셜 로그인 코드를 사용하면 된다.
    - 여기서는 naver가 마지막 Path가 된다.
```
<br/>

>코드설명 P.216
```md
## PostsApiControllerTest
- 1. @WithMockUser(roles="USER")
    - 인증된 모의(가짜) 사용자를 만들어서 사용
    - roles에 권한을 추가할 수 있다.
    - 즉, 이 어노테이션으로 인해 ROLE_USER 권한을 가진 사용자가 API를 요청하는 것과 동일한 효과를 가지게 된다.
```
<br/>


> 카카오 로그인 참고자료 : https://loosie.tistory.com/302

<br/>
<br/>

> 코드설명 P.301
```md
## depoly.sh
- 1. REPOSITORY=/home/ec2-user/app/step1
    - 프로젝트 디렉토리 주소는 스크립트 내에서 자주 사용하는 값이기 때문에 이를 변수로 저장한다.
    - 마찬가지로 PROJECT_NAME=freelec-springboot2-webservice도 동일하게 변수로 저장한다.
    - 쉘에서는 타입 없이 선언하여 저장
    - 쉘에서는 $ 변수명으로 변수를 사용할 수 있다.

- 2. cd $REPOSITORY/$PROJECT_NAME/
    - 제일 처음 git clone 받았던 디렉토리로 이동한다.
    - 바로 위의 쉘 변수 설명에 따라 /home/ec2-user/app/step1/freelec-springboot2-webservice 주소로 이동한다.

- 3. git pull
    - 디렉토리로 이동 후, master 브랜치의 최신 내용을 받는다.

- 4. ./gradlew build
    - 프로젝트 내부의 gradlew로 build를 수행한다.

- 5. cp ./build/libs/*.jar $REPOSITORY/
    - build의 결과물인 jar 파일을 복사해 jar파일을 모아둔 위치로 복사한다.

- 6. CURRENT_PID=$(pgrep -f ${PROJECT_NAME}.*.jar)
    - 기존에 수행 중이던 스프링 부트 애플리케이션을 종료
    - pgrep은 process id만 추출하는 명령어이다.
    - -f 옵션은 프로세스 이름으로 찾는다.

- 7. if ~ else ~ fi
    - 현재 구동 중인 프로세스가 있는지 없는지를 판단해서 기능을 수행
    - process id 값을 보고 프로세스가 있으면 해당 프로세스를 종료한다.

- 8. JAR_NAME=$(ls -tr $REPOSITORY/ | grep jar | tail -n 1)
    - 새로 실행할 jar 파일명을 찾느다.
    - 여러 jar파일이 생기기 때문에 tail -n로 가장 나중의 jar파일(최신 파일)을 변수에 저장

- 9. nohup java -jar $REPOSITORY/$JAR_NAME 2>&1 &
    - 찾은 jar 파일명으로 해당 jar 파일을 nohup으로 실행
    - 스프링 부트의 장점으로 특별히 외장 톰캣을 설치할 필요가 없다.
    - 내장 톰캣을 사용해서 jar 파일만 있으면 바로 웹 어플리케이션 서버를 실행할 수 있다.
    - 일반적으로 자바를 실행할 때는 java -jar라는 명령어를 사용하지만, 이렇게 하면 사용자가 터미널 접속을 끊을 때 애플리케이션도 같이 종료된다.
    - 애플리케이션 실행자가 터미널을 종료해도 애플리케이션은 계속 구동될 수 있도록 nohup 명령어를 사용한다.
```

<br/>

> 코드설명 P.310
```md
## ~/app/application-real-db.properties
- 1. spring.jpa.hibernate.ddl-auto=none
    - JPA로 테이블이 자동 생성되는 옵션을 None(생성하지않음)으로 지정한다.
    - RDS에는 실제 운영으로 사용될 테이블이니 절대 스프링 부트에서 새로 만들지 않도록 해야 한다.
    - 이 옵션을 하지 않으면 자칫 테이블이 모두 새로 생성될 수 있다.
    - 주의해야 하는 옵션이다...

## ~/app/step1/deploy.sh
- 2. -Dspring.profiles.active=real
    - application-real.properties를 활성화 시킨다.
    - application-real.properties의 spring.profiles.include=oauth,real-db 옵션 때문에 real-db 역시 함께 활성화 대상에 포함된다.
```

<br/>

> 코드설명 P.326
```md
## /.travis.yml
- 1. branches
    - Travis CI를 어느 브랜치가 푸시될 때 수행할지 지정한다.
    - 현재 옵션은 오직 master 브랜치에 push될 때만 수행한다.

- 2. cache
    - 그레이들을 통해 의존성을 바덱 되면 이를 해당 디렉토리에 캐시하여, 같은 의존성은 다음 배포 때부터 다ㅣㅅ 받지 않도록 설정한다.

- 3. script
    - master 브랜치에 푸시 되었을 때 수행하는 명령어이다.
    - 여기서는 프로젝트 내부에 둔 gradlew을 통해 clean & build를 수행한다.

- 4. notifications
    - Travis CI 실행 완료 시 자동으로 알람이 가도록 설정한다.
```

<br/>

> 코드설명 P.339
```md
## /.travis.yml
- 1. before_deploy
    - deploy 명령어가 실행되기 전에 수행된다.
    - CodeDeploy는 Jar 파일은 인식하지 못하므로 jar+기타 설정 파일듣ㄹ을 모아 압축(zip) 한다.

- 2. zip -r freelec-springboot2-webservice
    - 현재 위치의 모든 파일을 freelec-springboot2-webservice 이름으로 압축(zip) 한다.
    - 명령어의 마지막 위치는 본인의 프로젝트 이름이어야 한다.

- 3. mkdir -p deploy
    - deploy 라는 디렉토리를 Travis CI 가 실행중인 위치에서 생성한다.

- 4. mv freelec-springboot2-webservice.zip deploy/freelec-springboot2-webservice.zip
    - freelec-springboot2-webservice.zip 파일을 deploy/freelec-springboot2-webservice.zip으로 이동 시킨다.

- 5. deploy
    - S3로 파일 업로드 혹은 CodeDeploy로 배포 등 외부 서비스와 연동될 행위들을 선언한다.

- 6. local_dir: deploy
    - 앞에서 생성한 deploy 디렉토리를 지정한다.
    - 해당 위치의 파일들만 S3로 전송한다.
```

<br/>

> 코드설명 P.354
```md
## /appspec.yml
- 1. version: 0.0
    - CodeDeploy 버전을 이야기한다.
    - 프로젝트 버전이 아니므로 0.0 외에 다른 버전을 사용하면 오류가 발생한다.

- 2. source
    - CodeDeploy에서 전달해 준 파일 중 destination으로 이동시킬 대상을 지정한다.
    - 루트 경로(/)를 지정하면 전체 파일을 이야기한다.

- 3. destination
    - source에서 지정된 파일을 받을 위치이다.
    - 이후 Jar를 실행하는 등은 destination에서 옮긴 파일들로 진행된다.

- 4. overwrite
    - 기존에 파일들이 있으면 덮어쓸지를 결정한다.
    - 현재 yes라고 했으니 파일들을 덮어쓰게 된다.
```

<br/>

> 코드설명 P.360
```md
## /script/deploy.sh
- 1. CURRENT_PID
    - 현재 수행 중인 스프링 부트 애플리케이션의 프로세스 ID를 찾는다.
    - 실행 중이면 종료하기 위해서이다.
    - 스프링 부트 애플리케이션 이름(freelec-springboot2-webservice)으로 된 다른 프로그램들이 있을 수 있어 freelec-springboot2-webservice로 된 jar(pgrep -fl freelec-springboot2-webservice | grep jar) 프로세스를 찾은 뒤 ID를 찾는다.(| awk '{print $1}').
    
- 2. chmod +x $JAR_NAME
    - Jar 파일은 실행 권한이 없는 상태이다.
    - nohup으로 실행할 수 있게 실행 권한을 부여한다.

- 3. $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
    - nohup 실행 시 CodeDeploy는 무한 대기한다.
    - 이 이슈를 해결하기 위해 nohup.out 파일을 표준 입출력용으로 별도로 사용한다.
    - 이렇게 하지 않으면 nohup.out 파일이 생기지 않고, CodeDeploy 로그에 표준 입출력이 출력된다.
    - nohup이 끝나기 전까지 CodeDeploy도 끝나지 않으니 꼭 이렇게 해야 한다.
```

<br/>

> 코드설명 P.361
```md
## /.travis.yml
- 1. Travis CI는 S3 로 특정 파일만 업로드가 안된다.
    - 디렉토리 단위로만 업로드할 수 있기 때문에 before-deploy 디렉토리는 항상 생성한다.

- 2. before-deploy에는 zip 파일에 포함시킬 파일들을 저장한다.

- 3. zip -r 명령어를 통해 before-deploy 디렉토리 전체 파일을 압축한다.
```

<br/>

> 코드설명 P.362
```md
## /appspec.yml
- 1. permissions
    - CodeDeploy에서 EC2 서버로 넘겨준 파일들을 모두 ec2-user 권한을 갖도록 한다.

- 2. hooks
    - CodeDeploy 배포 단계에서 실행할 명령어를 지정한다.
    - ApplicationStart라는 단계에서 deploy.sh를 ec2-user 권한으로 실행하게 한다.
    - timeout: 60으로 스크립트 실행 60초 이상 수행되면 실패가 된다.
```