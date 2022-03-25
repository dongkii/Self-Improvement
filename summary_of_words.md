# Summary of words

- [MSA](#MSA)
- [Docker](#Docker)
- [Kubernetes(k8s, kube)](#kubernetes)
- [Apache Kafka](#kafka)
- [Elasticsearch](#elasticsearch)
- [Iac(Infrastructure as Code)](#iac)
- [Provisioning](#provisioning)

- [SaaS(Software-as-a-Service)](#saas)
- [PaaS(Platform-as-a-Service)](#paas)
- [IaaS(Infrastructure-as-a-Service)](#iaas)
- [TDD](#tdd)
- [BDD](#bdd)
- [Agile](#agile)ß
- [Redis](#redis)
- ELB
- VPC
- EC2
- ECS
- ECR
- S3
- AWS Lambda
- NoSQL
- DynamoDB
- React
- node.js


# MSA(Microservice Architecture)<a id="MSA"></a>
- 정의 및 특징
    - 객체 조합으로 소프트웨어를 완성하며 객체 자체의 기능과 데이터베이스를 갖춘 작은 서비스(Micro-Service)들로 구성된 아키텍처
    - 각 서비스가 독립적으로 구성되며 배포가 가능하다.
    - 각각의 서비스는 API를 통해 통신하며, 각각 서비스의 의존성이 최소화되어야 한다.

<br/>

- 장점
    1. 배포(deployment) 관점
        - 서비스 별 개별 배포 가능 (배포 시 전체 서비스의 중단이 없다.)
        - 요구사항을 신속하게 반영하여 빠르게 배포할 수 있다.
    2. 확장(scaling) 관점
        - 특정 서비스에 대한 확장성이 용이햐다
        - 클라우드 사용에 적합한 아키텍처
    3. 장애(failure) 관점
        - 장애가 전체 서비스로 확장될 가능성이 적다.
        - 부분적 장애에 대한 격리가 수월하다.
    4. 신기술 적용이 유연하고, 서비스를 polyglot하게 개발/운영 할 수 있다.

<br/>

- 단점
    1. 프로젝트 규모가 커짐에 따라 복잡도가 기하급수적으로 증가한다.
    2. 성능 - 서비스간 호출 시 API를 사용하기 때문에, 통신 비용이나 Latency가 증가한다.
    3. 테스트/트랜잭션 - 서비스가 분산되어 있기 때문에 테스트와 트랜잭션 복잡도가 증가하고 많은 자원을 필요로 한다.
    4. 데이터 관리 - 데이터가 여러 서비스에 걸쳐 분산되기 때문에 한번에 조회하기 어렵고, 데이터의 정합성 또한 관리하기 어렵다.

<br/>

# Docker<a id="Docker"></a>
-  정의 및 특징
    - Docker : 컨테이너 기반의 오픈소스 가상화 플랫폼이다.
    - Images : 컨테이너 실행에 필요한 파일과 설정값 등을 포함하는 것으로, 상태값을 가지지 않고 변하지 않는다.
    - Container : 이미지를 실행한 상태이며 추가되거나 변하는 값은 컨테이너에 저장된다.

<br/>

- 장점
    1. 실행속도가 빠르다. OS를 실행하지 않고도 모든 프로세스에 대한 컨테이너를 실행할 수 있기때문
    2. 안전하다. Container 끼리는 분리되어있기 때문에 보안상 이점이 있다.
    3. 실행환경 구성이 빠르고 간편하다. 프로젝트 개발 및 실행환경을 강제화 가능
    4. 비용절감 효과. 컨테이너의 크기가 매우 작고, 하나의 물리적 서버에 다수의 컨테이너를 가동시킬 수 있다.

<br/>

- 단점
    1. Docker는 플랫폼 의존적이다. windows와 mac에서는 linux 가상머신 위에서 동작한다.
    2. Docker는 bare-mental 방식보다 느리다. 도커가 기존의 가상화방식(이미 OS가 설치된 하드웨어 위에 Hypervisor OS를 설치하는 경우)보다 overhead가 적긴하지만, 여전히 bare-mental 방식의 속도에 비하면 느리다.
    3. Docker위에서 GUI 앱을 구동하기 불편하다. 도커는 command line에서 동작하는 앱을 호스팅하기 위해 디자인되었다. 도커 컨테이너 내에서 GUI앱을 실행 하는 것은 가능은 하지만(X11 forwarding을 이용) 불편하다.

<br/>

# Kubernetes(k8s, kube)<a id="kubernetes"></a>
- 정의 및 특징
    - 컨테이너로 구성된 애플리케이션을 효율적으로 배포하며 운영하기 위해 사용되는 오픈소스 플랫폼
    - Pod 단위로 배포, 한개이상의 컨테이너와 스토리지, 네트워크 속성을 가진다.
    - ReplicaSet을 통해 Pod를 한 개 이상 복제하여 관리
    - 오브젝트 명세 SPEC은 YAML로 정의하고 오브젝트의 종류와 원하는 상태를 입력

<br/>

- 장점
    1. Load Balance 처리 가능, 컨테이너나 서버가 다운되게 되면 ReplicaSet을 통해 컨테이너가 재생성 됨(가장 강력한 쿠버네티스의 기능)
    2. 애플리케이션 생산성 향상
        - 버그 발견시 이를 수정하기 위한 롤백과 롤아웃 기능을 제공
    3. 신뢰성 있는 서비스 제공
        - 배포 및 통합을 위한 서버의 정지 없이 계속하여 운영하게 해줌으로 써 유저들에게 신뢰성 있는 서비스를 제공
    4. 유연한 리소스 활용(오토스케일링)
        - 빠르게 서버를 확장하거나 축소할 수 있으며, 사용자에 따라 리소스를 낭비하지 않으므로 비용 절감
    5. 다양한 지원 및 지속 발전
        - CNCF 및 다른 클라우드 운영 도구들과 쉽게 연동되므로 확장성이 높다.
    
<br/>

- 단점
    1. 다른 도구보다 훨씬 다양한 지식이 요구됨
    2. 쿠버네티스 자체의 관리가 어려울 수도 있음.
    3. 구조가 복잡하고 사용방법이 다양하여 쿠버네티스 학습 비용 또한 고려대상임

<br/>

# Apache Kafka<a id="kafka"></a>
- 정의 및 특징
    - 대용량, 대규모 메시지 데이터를 빠르게 처리하도록 개발된 분산 메시징 플랫폼
    - 어플리케이션간에 메시지를 교환하기 위해 사용되는 메세징 시스템
    - Pub/Sub(Publish/Subscribe) 시스템, 생산자와 소비자 사이에서 중재자 역할을 하는 브로커로 구성된 Loosely Coupled 시스템

<br/>

- 장점
    1. Low Latency : 10ms 까지 low latency value를 제공
    2. High Throughput : low latency 덕분에 Kafka는 메시지들을 더 많이, 빠르게 전달 가능
    3. Fault Tolerance : Kafka는 cluster 내에 있는 node나 machine의 실패에 대처하는 방법을 가지고 있다.
    4. Durability : replication 기능을 제공, 클러스터내에 다른 노드에게 데이터를 복사할 수 있다.
    5. 생산자와 소비자 통합 : 생산자는 카프카에 데이터를 쓰고, 소비자는 카프카로 데이터를 읽을 수 있다.
    6. Easily Accessible : 데이터가 Kafka에 저장되므로, 모두가 쉽게 데이터에 접근할 수 있다.
    7. Distributed System(Scalability) : distributed architecture를 포함하고 있으므로 scalable하다.
    8. Real-Time Handling : 실시간 데이터 파이프라인을 다룰 수 있다.

<br/>

- 단점
    1. 관리와 모니터링 툴들이 부족, 장기적으로 신뢰가 어렵다는 시각이 있다.
    2. 메시지의 수정이 필요하다면 퍼포먼스가 급격하게 감소, 메시지가 바뀔필요가 없을 때 가장 잘 동작
    3. wild card topic selection 을 지원하지 않는다. 한번에 하나의 topic만 선택이 가능
    4. 생산자가 데이터를 압축하고 소비자가 데이터를 압축해제하는 과정에서 성능저하가 일어난다.

<br/>

# Elasticsearch<a id="elasticsearch"></a>
- 정의 및 특징
    - Apache Lucene 기반의 Java 오픈소스 분산 검색엔진
    - 검색을 위해 단독으로 사용되기도 하며, ELK(Elasticsearch/Logstash/Kibana) 스택으로 사용되기도 한다.
        - Logstash
            - 다양한 소스(DB, csv파일 등)의 로그 또는 트랜잭션 데이터를 수집, 집계 파싱하여 Elasticsearch로 전달
        - Elasticsearch
            - Logstash 로부터 받은 데이터를 검색 및 집계하여 필요한 관심 있는 정보를 획득
        - Kibana
            - Elasticsearch의 빠른 검색을 통해 데이터를 시각화 및 모니터링
    
    - 분산/확장성/병렬처리
        - 3개 이상의 노드를 클러스터로 구성하며, 데이터를 샤드(shard)로 저장 시 클러스터 내 다른 호스트에 복사본(replica)을 저장해 놓기 때문에 하나의 노드가 죽거나 샤드가 깨져도 복제되어 있는 다른 샤드를 활용하기 때문에 안정성을 보장한다.

    - 고가용성
        - 동작중에 죽은 노드를 감지하고 삭제하며 사용자의 데이터를 안전하고 접근 가능하도록 유지하기 때문에 일부 노드에 문제가 생기더라도 문제 없이 서비스를 제공

    - 멀티 테넌시
        - 클러스터는 여러개의 인덱스(RDBMS의 데이터베이스와 유사)들을 저장하고 관리하며, 하나의 쿼리나 그룹 쿼리로 여러 인덱스의 데이터를 검색할 수 있다.
    
    - 문서(Document) 중심 & 스키마(Schema) 미존재
        - 현실세계의 요소들을 구조화된 JSON 문서 형식으로 저장. 모든 필드는 기본적으로 인덱싱되며, 모든 인덱스들을 단일쿼리로 빠르게 검색 및 활용 가능
        - 스키마 개념이 없으며, 사용자의 데이터가 어떻게 인덱싱 될 것인지를 사용자가 커스터마이징 가능
    
    - 플러그인 형태로 구현
        - 검색엔진을 직접 수행하지 않고, 필요한 기능에 대한 플러그인을 적용하여 기능을 확장할 수 있다.