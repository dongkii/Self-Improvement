# IaC (Infrastructure as Code)

## **목차**
- [1. IaC 정의](#1)
- [2. IaC 장점](#2)

<br/>

# 1. IaC(Infrastructure as Code)의 정의<a id="1"></a>
> 시스템을 수동으로 관리하는 대신 스크립트를 사용하여 컴퓨팅 인프라를 구성하는 방식  

- IaC는 유연성이 떨어지는 스크립팅이나 수동 프로세스 대신 `코드를 이용해 시스템을 자동으로 구축, 관리, 프로비저닝` 하는 IT 인프라 프로비저닝 프로세스의 일종이다.

- 당연히 코드가 정확하면 빠르고, 인적 실수를 없앨 수 있다.

AWS 서비스를 사용해보았다면 콘솔웹에서 인프라를 관리해보았을 것이다.  
Storage의 mount를 놓친다던가, dev, stg, prd 환경 별로 다른 설정을 적용한다던가,  
방화벽 이슈 등 `인적 장애`로 발생할 확률이 높아진다.

인프라 구성을 소프트웨어 프로그래밍처럼 처리하므로, 애플리케이션 구성과의 경계가 좁아졌다.  
인프라 프로비저닝과 어플리케이션 배폴르 동시에 할 수 있다.  
이 부분은 `DevOps`가 중요해지면서 각광받고 있다.  

Insfrastructure as Code는 IaaS를 지원한다.  
그러므로 `소프트웨어 기반 관리 툴(ansible, chef, puppet 등)을 통합`하여 서비스로 판매, 사용할 수 있다.  

대표적으로는 hashcorp에서 오픈소스로도 제공되는 `terraform`, AWS의 `Cloud formation`등이 있다.

<br/>

# 2. IaC의 장점<a id="2"></a>
- 개별 하드웨어를 수동으로 변경할 필요가 없다.
    - 예를 들어 EC2 instance의 10EA의 type을 c3.large에서 c3.xlarge로 type을 변경할 때 콘솔웹에서 일일히 변경하지 않고 code로 간단히 변경할 수 있다.
    - 이로써 `빠른 속도`, `비용 절감`의 장점을 제공한다.

<br/>

> ### 출처 및 참고 : https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=ssdyka&logNo=221355367790