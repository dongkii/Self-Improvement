> ## 용어 정리

- JPA (Java Persistence API)  
    - 자바 ORM 기술에 대한 표준 명세로, `JAVA`에서 제공하는 API이다. 스프링에서 제공하는 것이 아님!  
    - 자바 어플리케이션에서 관계형 데이터베이스를 사용하는 방식을 정의한 인터페이스 이다.
        - 여기서 중요하게 여겨야 할 부분은, JPA는 말 그대로 인터페이스라는 점이다. JPA는 특정 기능을 하는 라이브러리가 아니다. 스프링의 PSA에 의해서(POJO를 사용하면서 특정 기술을 사용하기 위해) 표준 인터페이스를 정해두었는데, 그중 ORM을 사용하기 위해 만든 인터페이스가 JPA이다.
    - 기존 EJB에서 제공되던 Entity Bean을 대체하는 기술이다.
    - ORM이기 때문에 자바 클래스와 **`DB테이블`** 을 매핑한다.(sql을 매핑하지 않는다.)  
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
    