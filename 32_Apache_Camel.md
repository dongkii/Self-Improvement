# Apache Camel

![Apache_Camel](./img/2023_10_09/apache_camel.png)

## 아파치 카멜의 개요
> 아파치 카멜은 다양한 시스템 간 통합을 수행하는 데 사용되는 오픈소스 프레임워크  
예를 들어, 엔터프라이즈 시스템 간에 데이터를 전송하거나, 웹 서비스를 호출하거나, 메시지 큐에 대한 메시지를 처리하거나, 파일 시슽메 또는 데이터베이스와 상호 작용하는등의 작업을 수행할 수 있다.

> 아파치 카멜은 또한 다양한 프로토콜 및 데이터 형식을 지원한다.  
예를 들어, HTTP, FTP, SMTP, JDBC, JMS 및 AWS 등 다양한 프로토콜을 지원하며, JSON, XML, CSV 등 다양한 데이터 형식을 처리할 수 있다.  
따라서, 시스템 간 데이터 통합이 필요한 경우, 특히 다양한 시스템 및 프로토콜을 연결하고 데이터를 변환하거나 처리해야하는 경우에 아파치 카멜을 사용할 수 있다.

> Java 응용 프로그램에 쉽게 포함될 수 있도록 최소한의 종속성을 가진 작은 라이브러리로, 매우 유연하게 조합되어 고도로 독립적인 Java 코드라도 카멜의 어떤 부분에서든 쉽고 빠르게 확장이 가능

- 어떤 종류의 Transport가 사용되는지에 관계없이 동일한 API로 작업할 수 있게 해줌
- 유효성 검사 및 배포를 위해 여러 Maven 플러그인이 제공

## 아파치 카멜 기능요약
### 통합 연계 라우팅 엔진
- Caeml은 메시지 라우팅 연계 엔진
- 다양한 프로토콜, 메시지를 통합하여 일관된 인터페이스로 처리 가능

### EIP (Enterprise Integration Patterns) 구현
- 기업 연계 패턴을 분석하여 패턴화 시킨 것이 EIP 패턴임
- Camel을 통하여 EIP 패턴에 기반하여 다양한 연계 유형을 조립하여 구성할 수 있다.

### DSL (Domain Specific Language) 지원
- 메시지 라우팅 및 메시지 프로세싱을 다양한 언어로 기술할 수 있도록 함
- Java, XML, Groovy, Kotlin 등 다양한 언어 지원

### 다양한 연계 컴포넌트 (150+), 메시지 변환 프로세서 제공
- 다양한 연계 컴포넌트, 메시지 변환 프로세스가 제작되어 포함되어 있음
- 필요시 사용자 컴포넌트를 제작하여 함께 구동시킬 수 있음

### Lightwight core 라이브러리 모듈
- Camel core 모듈은 4M 정도의 라이브러리 형태로 가벼움
- 다양한 컨테이너에 포함되어 구동될 수 있고, 마이크로 서비스 형태의 서비스가 가능함

## 아파치 카멜 구조(개념)
### Route
> 라우터는 카멜에서 메시지를 전송하는 역할을 한다. 라우터는 다양한 프로토콜(HTTP, FTP, JMS 등)과 통합할 수 있다.
- 컴포넌트, 프로세서들의 연속적인 메시지 연결의 정의다.
- 시스템 간 혹은 시스템 내부에서 정의된 메시지 연결 플로우로 메시지가 어디서/어떻게/어디로 흘러갈지 정의한다.
- Camel 라우트는 메시지 플로우가 1:1 혹은 1:N, N:1 등 다양하게 정의될 수 있다.
- 더 많은 정보는  <a href="http://camel.apache.org/routes.html">http://camel.apache.org/routes.html</a> 참조

### Component
> Endpoint URL을 가지는 Camel이 메시지를 라우팅 할 수 있는 프로그램 단위다.
- 통신 프로토콜을 구현한 컴포넌트, 파일시스템 연동을 구현한 컴포넌트 등 다양한 컴포넌트가 있다.
- Camel 내부에 미리 구현된 컴포넌트가 150여종이 있으며 사용자가 새로 만들어 끼워 넣을 수 있다.
- 더 많은 정보는 <a href="http://camel.apache.org/components.html"></a> 참조

### Processor
> 프로세서는 메시지를 가공하고 변환하는 역할을 한다. 예를 들어, 메시지를 XML에서 JSON으로 변환하거나, 메시지를 필터링하거나, 메시지에 새로운 속성을 추가하는 등의 작업을 수행할 수 있다.
- 프로세서는 Camel 내부에서 메시지를 생성, 변환, 수정, 검증 등의 작업을 하여 다른 컴포넌트로 라우팅하는 모듈이다.
- Camel은 EIP 패턴에 의한 메시지 프로세싱을 지원한다.
- 더 많은 정보는 <a href="http://camel.apache.org/processor.html">http://camel.apache.org/processor.html</a> 참조
    - EIP (Enterprise Integration Patterns) 패턴 : 기업에서 다양한 시스템 간 통합을 구현할 때 공통적으로 발생하는 문제를 해결하는 패턴의 모음이다.

### Message
- 경로로 전송되는 데이터가 포함
- 각 메시지에는 고유한 식별자가 있으며 본문, 머리글 및 첨부 파일로 구성
- 더 많은 정보는 <a href="http://camel.apache.org/message.html">http://camel.apache.org/message.html</a> 참조

### Exchage
- 메시지의 컨테이너
- 메시지가 라우팅 프로세스 중에 consumer에 의해 수신될 때 만들어짐
- 시스템간에 서로 다른 유형의 상호 작용을 허용
- 단방향 메시지 또는 요청 - 응답 메시지를 정의 가능
- 시스템 간 대화의 상태를 포함하고 있는 홀더 객체
- 더 많은 정보는 <a href="http://camel.apache.org/exchange.html"></a> 참조

### Context
- Camel의 핵심 런타임 API로 컴포넌트, 프로세서, EndPoint, 데이터 타입, 라우팅 등을 관리한다.
- CamelContext에 의해서 다양한 모듈이 로딩되고 관리된다.
- 라우트에 정의된 단계를 따라 익스체인지를 처리하는 엔진

### DSL (Domain Specific Language)
- 컴포넌트와 프로세서를 통하여 라우트 구성을 정의하기 위해서 사용하는 언어다.
- Camel은 Java, Spring XML, Scala, Groovy 등 다양한 언어 형태를 지원한다.
- DSL을 통하여 다양한 동적 라우팅 및 메시지 변환 등 프로그래밍 요소를 삽입하여 사용 가능하다.

### EndPoint
- Camel 컴포넌트의 주소를 나타내며 URI 형태로 기술한다.
- 라우팅을 기술하기 위해서 컴포넌트의 EndPoint를 기술한다.
- 더 많은 정보는 <a href="http://camel.apache.org/endpoint.html">http://camel.apache.org/endpoint.html</a> 참조

### Producer
- EndPoint 에 메시지를 생성, 전달할 수 있는 개체다.

### Consumer
- Proucer에 의해 생성된 메시지를 수신하는 개체, 수신 후 Exchage를 생성하여 라우터에 던져준다.

### Architecture
- 높은 수준에서 Camel의 아키텍쳐는 간단하다. CamelContext는 Camel 런타임 시스템을 나타내며, Route, Component 또는 EndPoint와 같은 다른 개념을 연결한다.
- 그리고 그 아래에서 Processor는 EndPoint 간의 라우팅 및 변환을 처리하고 EndPoint는 다른 시스템을 통합한다.

### Expression
- 메시지를 처리하는 인라인 코드를 라우트 내에 넣는 하나의 방법
- 표현식이 많은 EIP들에 사용되어 메시지 라우팅에 영향을 줌
- 더 많은 정보는 <a href="http://camel.apache.org/expression.html">http://camel.apache.org/expression.html</a> 참조