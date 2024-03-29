# REST, REST API, RESTful

## **목차**
- [REST, REST API, RESTful](#rest-rest-api-restful)
  - [**목차**](#목차)
- [1. REST 란<a id="1"></a>](#1-rest-란)
  - [1-1. REST의 정의<a id="1-1"></a>](#1-1-rest의-정의)
  - [1-2. REST의 장단점<a id="1-2"></a>](#1-2-rest의-장단점)
  - [1-3. REST가 필요한이유<a id="1-3"></a>](#1-3-rest가-필요한이유)
  - [1-4. REST 구성 요소<a id="1-4"></a>](#1-4-rest-구성-요소)
  - [1-5. REST의 특징<a id="1-5"></a>](#1-5-rest의-특징)
- [2. REST API란<a id="2"></a>](#2-rest-api란)
  - [2-1 REST API의 정의<a id="2-1"></a>](#2-1-rest-api의-정의)
  - [2-2 REST API의 특징<a id="2-2"></a>](#2-2-rest-api의-특징)
  - [2-3 REST API 설계 기본 규칙<a id="2-3"></a>](#2-3-rest-api-설계-기본-규칙)
  - [2-4 REST API 설계 규칙<a id="2-4"></a>](#2-4-rest-api-설계-규칙)
  - [2-5 REST API 설계 예시](#2-5-rest-api-설계-예시)
- [3. RESTful 이란<a id="3"></a>](#3-restful-이란)
  - [3-1. RESTful의 정의<a id="3-1"></a>](#3-1-restful의-정의)
  - [3-2. RESTful의 목적<a id="3-2"></a>](#3-2-restful의-목적)
  - [3-3. RESTful 하지 못한 경우<a id="3-3"></a>](#3-3-restful-하지-못한-경우)

<br/>

# 1. REST 란<a id="1"></a>
![REST](./img/2022_03_15/REST.png)

## 1-1. REST의 정의<a id="1-1"></a>  
- **"Representational State Transfer"** 의 약자
    - 자원을 이름(자원의 표현)으로 구분하여 해당 자원의 상태(정보)를 주고 받는 모든 것을 의미한다.
    - 즉, 자원(resource)의 표현(representation)에 의한 상태 전달
        - 자원의 표현
            - 자원 : 해당 소프트웨어가 관리하는 모든 것  
            -> ex) 문서, 그림, 데이터, 해당 소프트웨어 자체 등
            - 자원의 표현 : 그 자원을 표현하기 위한 이름  
            -> ex) DB의 학생 정보가 자원일 때, 'student'를 자원의 표현으로 정한다.
        - 상태(정보) 전달
            - 데이터가 요청되어지는 시점에서 자원의 상태(정보)를 전달한다.
            - JSON 혹은 XML을 통해 데이터를 주고 받는 것이 일반적이다.
    - 월드 와이드 웹(www)과 같은 분산 하이퍼미디어 시스템을 위한 소프트웨어 개발 아키텍처의 한 형식
        - REST는 기본적으로 웹의 기술과 HTTP 프로토콜을 그대로 활용하기 때문에 **웹의 장점을 최대한 활용할 수 있는 아키텍처 스타일** 이다.
        - REST는 네트워크 상에서 Client와 Server 사이의 통신 방식 중 하나이다.  
<br/>

- REST의 구체적인 개념  
  - `HTTP URI(Uniform Resource IDentifier)를 통해 자원(Resource)을 명시하고, HTTP Method(POST, GET, PUT, DELETE)를 통해 해당 재원에 대한 CRUD Operation을 적용하는 것을 의미한다.`  
  - 즉, REST는 자원 기반의 구조(ROA, Resource Oriented Architecture) 설계의 중심에 Resource가 있고 HTTP Method를 통해 Resource를 처리하도록 설계된 아키텍쳐를 의미한다.
  - 웹 사이트의 이미지, 텍스트, DB 내용등의 모든 자원에 대한 고유한 ID인 HTTP URI를 부여한다.
  - CRUD Operation
    - Create : 생성(POST)
    - Read : 조회(GET)
    - Update : 수정(PUT)
    - Delete : 삭제(Delete)
    - HEAD : header 정보 조회(HEAD)
        
<br/>
<br/>

## 1-2. REST의 장단점<a id="1-2"></a>
- 장점
    - HTTP 프로토콜의 인프라를 그대로 사용하므로 REST API 사용을 위한 별도의 인프라를 구축할 필요가 없다.
    - HTTP 프로토콜의 표준을 최대한 활용하여 여러 추가적인 장점을 함께 가져갈 수 있게 해준다.
    - HTTP 표준 프로토콜에 따르는 모든 플랫폼에서 사용이 가능하다.
    - Hypermedia API의 기본을 충실히 지키면서 범용성을 보장한다.
    - REST API 메시지가 의도하는 바를 명확하게 나타내므로 의도하는 바를 쉽게 파악할 수 있다.
    - 여러가지 서비스 디자인에서 생길 수 있는 문제를 최소화한다.
    - 서버와 클라이언트의 역할을 명확하게 분리한다.
<br/>
<br/>

- 단점  
    - 표준이 존재하지 않는다.
    - 사용할 수 있는 메소드가 4가지 밖에 없다.
        - HTTP Method 형태가 제한적이다.
    - 브라우저를 통해 테스트할 일이 많은 서비스라면 쉽게 고칠 수 있는 URL보다 Header 값이 왠지 더 어렵게 느껴진다.
    - 구형 브라우저가 아직 제대로 지원해주지 못하는 부분이 존재한다.
        - PUT, DELETE를 사용하지 못하는 점
        - pushState를 지원하지 않는 점

<br/>
<br/>

## 1-3. REST가 필요한이유<a id="1-3"></a>
- '애플리케이션 분리 및 통합'
- '다양한 클라이언트의 등장'
- 최근의 서버 프로그램은 다양한 브라우저와 안드로이드폰, 아이폰과 같은 모바일 디바이스에서도 통신을 할 수 잇어야 한다.
- 이러한 멀티 플랫폼에 대한 지원을 위해 서비스 자원에 대한 아키텍처를 세우고 이용하는 방법을 모색한 결과, REST에 관심을 가지게 되었다.

<br/>
<br/>

## 1-4. REST 구성 요소<a id="1-4"></a>
1. 자원(Resource) : URI
    - 모든 자원에 고유한 ID가 존재하고, 이 자원은 Server에 존재한다.
    - 자원을 구별하는 ID는 '/group/:group_id'와 같은 **HTTP `URI`** 이다.
    - Client는 URI를 이용해서 자원을 지정하고 해당 자원의 상태(정보)에 대한 조작을 Server에 요청한다.
<br/><br/>

2. 행위(Verb) : HTTP Method
    - HTTP 프로토콜의 Method를 사용한다.
    - HTTP 프로토콜은 **`GET, POST, PUT, DELETE`** 와 같은 메서드를 제공한다.
<br/><br/>

3. 표현(Representation of Resource)
    - Client가 자원의 상태(정보)에 대한 조작을 요청하면 Server는 이에 적절한 응답(Representation)을 보낸다.
    - REST에서 하나의 자원은 **`JSON, XML, TEXT, RSS`** 등 여러 형태의 Representation으로 나타내어 질 수 있다.
    - JSON 혹은 XML을 통해 데이터를 주고 받는 것이 일반적이다.

<br/>
<br/>

## 1-5. REST의 특징<a id="1-5"></a>
1. Server-Client(서버-클라이언트 구조)
    - 자원이 있는 쪽이 Server, 자원을 요청하는 쪽이 Client가 된다.
        - REST server : API를 제공하고 비즈니스 로직 처리 및 저장을 책임진다.
        - Client : 사용자 인증이나 context(세션, 로그인정보) 등을 직접 관리하고 책임진다.   
    - 서로 간 의존성이 줄어든다.

<br/>

2. Stateless(무상태)
    - HTTP 프로토콜은 Stateless Protocol 이므로 REST 역시 무상태성을 갖는다.
    - Client의 context를 Server에 저정하지 않는다.
        - 즉, 세션과 쿠키와 같은 context 정보를 신경쓰지 않아도 되므로 구현이 단순해진다.
    - Server는 각각의 요청을 완전히 별개의 것으로 인식하고 처리한다.
        - 각 API 서버는 Client의 요청만을 단순 처리한다.
        - 즉, 이전 요청이 다음 요청의 처리에 연관되어서는 안된다.
        - 물론 이전 요청이 DB를 수정하여 DB에 의해 바뀌는 것은 허용한다.
        - Server의 처리 방식에 일관성을 부여하고 부담이 줄어들며, 서비스의 자유도가 높아진다.

<br/>

3. Cacheable(캐시 처리 가능)
    - 웹표준 HTTP 프로토콜을 그대로 사용하므로 웹에서 사용하는 기존의 인프라를 그대로 활용할 수 있다.
        - 즉, HTTP가 가진 가장 강력한 특징 중 하나인 캐싱 기능을 적용할 수 있다.
        - HTTP 프로토콜 표준에서 사용하는 Last-Modified 태그나 E-Tag를 이용하면 캐싱 구현이 가능하다.
        - 대량의 요청을 효율적으로 처리하기 위해 캐시가 요구된다.
        - 캐시 사용을 통해 응답시간이 빨라지고 REST Server 트랜잭션이 발생하지 않기 때문에 전체 응답시간, 성능, 서버의 자원 이용률을 향상시킬 수 있다.
    
<br/>

4. Layered System(계층화)
    - Client는 REST API Server만 호출한다.
    - REST Server는 다중 계층으로 구성될 수 있다.
        - API Server는 순수 비즈니스 로직을 수행하고 그 앞단에 보안, 로드밸런싱, 암호화, 사용자 인증 등을 추가하여 구조상의 유연성을 줄 수 있다.
        - 또한 로드밸런싱, 공유 캐시 등을 통해 확장성과 보안성을 향상시킬 수 있다.
    - PROXY, 게이트웨이 같은 네트워크 기반의 중간 매체를 사용할 수 있다.

<br/>

5. Code-On-Demand(optional)
    - Server로부터 스크립트를 받아서 Client에서 실행한다.
    - 반드시 충족할 필요는 없다.

<br/>

6. Uniform Interface(인터페이스 일관성)
    - URI로 지정한 Resource에 대한 조작을 통일되고 한정적인 인터페이스로 수행한다.
    - HTTP 표준 프로토콜에 따르는 모든 플랫폼에서 가용이 가능하다.
        - 특정 언어나 기술에 종속되지 않는다.

<br/><br/>

# 2. REST API란<a id="2"></a>

![REST_API](./img/2022_03_15/REST_API.png)

- API(Application Programming Interface)란
    - 데이터와 기능의 집합을 제공하여 컴퓨터 프로그램간 상호작용을 촉진하며, 서로 정보를 교환가능 하도록 하는 것

<br/>

## 2-1 REST API의 정의<a id="2-1"></a>  

- REST 기반으로 서비스 API를 구현한 것
- 최근 OpenAPI(누구나 사용할 수 있도록 공개된 API: 구글 맵, 공공 데이터 등), 마이크로서비스 등을 제공하는 업체 대부분은 REST API를 제공한다.

## 2-2 REST API의 특징<a id="2-2"></a>

- 사내 시스템들도 REST 기반으로 시스템을 분산해 확장성과 재사용성을 높여 유지보수 및 운용을 편리하게 할 수 있다.
- REST는 HTTP 표준을 기반으로 구현하므로, HTTP를 지원하는 프로그램 언어로 클라이언트, 서버를 구현할 수 있다.
- 즉, REST API를 제작하면 델파이 클라이언트 뿐 아니라 자바, C#, 웹 등을 이용해 클라이언트를 제작할 수 있다.

## 2-3 REST API 설계 기본 규칙<a id="2-3"></a>
 - 도큐먼트 : 객체 인스턴스나 데이터베이스 레코드와 유사한 개념
 - 컬렉션 : 서버에서 관리하는 디렉터리라는 리소스
 - 스토어 : 클라이언트에서 관리하는 리소스 저장소

 1. URI는 정보의 자원을 표현해야 한다.
 2. 자원의 대한 행위는 HTTP Method(GET, PUT, POST, DELETE 등)로 표현한다.

## 2-4 REST API 설계 규칙<a id="2-4"></a>
 1. 슬래시 구분자(/)는 계층 관계를 나타내는데 사용한다.
 2. URI 마지막 문자로 슬래시(/)를 포함하지 않는다.
 3. 하이픈(-)은 URI 가독성을 높이는데 사용
 4. 밑줄(_)은 URI에 사용하지 않는다.
 5. URI 경로에는 소문자가 적합하다.
 6. 파일확장자는 URI에 포함하지 않는다.
 7. 리소스 간에는 연관 관계가 있는 경우

## 2-5 REST API 설계 예시
![REST_API_설계_예시](./img/2022_03_15/REST_API_설계_예시.png)

응답 상태 코드  
 - 1XX : 전송 프로토콜 수준의 정보 교환
 - 2XX : 클라이언트 요청이 성공적으로 수행됨
 - 3XX : 클라이언트는 요청을 완료하기 위해 추가적인 행동을 취해야 함
 - 4XX : 클라이언트의 잘못된 요청
 - 5XX : 서버쪽 오류로 인한 상태코드

 <br/>
 <br/>

# 3. RESTful 이란<a id="3"></a>

![REST_ful](./img/2022_03_15/REST_ful.png)


<br/>

## 3-1. RESTful의 정의<a id="3-1"></a>
- RESTful은 일반적으로 REST라는 아키텍처를 구현하는 웹 서비스를 나타내기 위해 사용되는 용어이다.
    - 'REST API'를 제공하는 웹 서비스를 'RESTful'하다고 할 수 있다.
- RESTful은 REST를 REST답게 쓰기 위한 방법으로, 누군가가 공식적으로 발표한 것이 아니다.
    - 즉, REST 원리를 따르는 시스템은 RESTful이란 용어로 지칭된다.

## 3-2. RESTful의 목적<a id="3-2"></a>
- 이해하기 쉽고 사용하기 쉬운 REST API를 만드는 것
- RESTful한 API를 구현하는 근본적인 목적이 성능 향상에 있는 것이 아니라 일관적인 컨벤션을 통한 API의 이해도 및 호환성을 높이는 것이 주 동기이니, 성능이 중요한 상황에서는 굳이 RESTful한 API를 구현할 필요는 없다.

## 3-3. RESTful 하지 못한 경우<a id="3-3"></a>
1. CRUD 기능을 모두 POST로만 처리하는 API
2. route에 resource, id 외의 정보가 들어가는 경우 (/students/updateName)

<br/>
<br/>

> ### 출처 및 참고 : https://gmlwjd9405.github.io/2018/09/21/rest-and-restful.html