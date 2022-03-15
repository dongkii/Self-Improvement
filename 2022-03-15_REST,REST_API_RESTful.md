# REST, REST API, RESTful

## **목차**
- [1. REST 란](#1)
    - [1-1. REST의 정의](#1-1)
    - [1-2. REST의 장단점](#1-2)
    - [1-3. REST가 필요한이유](#1-3)
    - [1-4. REST 구성 요소](#1-4)
    - [1-5. REST의 특징](#1-5)
- [2. REST API란](#2)
    - [2-1. REST API의 정의](#2-1)
    - [2-2. REST API의 특징](#2-2)
    - [2-3. REST API 설계 기본 규칙](#2-3)
    - [2-4. REST API 설계 규칙](#2-4)
    - [2-5. REST API 설계 예시](#2-5)
- [3. RESTful 이란](#3)
    - [3-1. RESTful의 정의](#3-1)
    - [3-2. RESTful의 목적](#3-2)
    - [3-3. RESTful 하지 못하는 경우](#3-3)

<br/>

# 1. REST 란<a id="1"></a>

## 1-1. REST의 정의<a id="1-1"></a>  
- "Representational State Transfer"** 의 약자
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
