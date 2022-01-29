# IntelliJ setting

# 1. IntelliJ 세팅

**1.1 gradle 프로젝트를 spring boot 프로젝트로 변경**

- build.gradle 파일을 열어 맨앞에 아래와 같이 추가
    - gradle 버전 없으로 수정 필요

```java
plugins {// (1)
    id 'org.springframework.boot' version '2.4.1'
    id 'io.spring.dependency-management' version '1.0.10.RELEASE'
    id 'java'
}

group 'com.jojoldu.book'
version '1.0.4-SNAPSHOT-'+new Date().format("yyyyMMddHHmmss")
sourceCompatibility = 1.8

repositories {
    mavenCentral()
    jcenter()
}

// for Junit 5
test {// (2)
    useJUnitPlatform()
}

dependencies {
//(3)
    implementation('org.springframework.boot:spring-boot-starter-web')
    implementation('org.springframework.boot:spring-boot-starter-mustache')

// lombok
    compileOnly('org.projectlombok:lombok')
    annotationProcessor('org.projectlombok:lombok')
    testImplementation('org.projectlombok:lombok')
    testAnnotationProcessor('org.projectlombok:lombok')

    implementation('org.springframework.boot:spring-boot-starter-data-jpa')
    implementation("org.mariadb.jdbc:mariadb-java-client")
    implementation('com.h2database:h2')

//implementation('org.springframework.boot:spring-boot-starter-oauth2-client') // 권한 관련//implementation('org.springframework.session:spring-session-jdbc') // 권한 관련

    testImplementation('org.springframework.boot:spring-boot-starter-test')
//testImplementation("org.springframework.security:spring-security-test") // 권한 관련
}

```

**1.2 Version Check**

jdk - 11

gradle - 6.7.1

JUnit - 5

IntelliJ - 2021.3.2

- JDK 버전 변경 방법
    1. Ctrl + Alt + S 입력 후 Build, Execution, Deployment > Build Tools > Gradle
    2. Gradle projects 에서 Run tests using → IntelliJ IDEA 로 변경
    3. Gradle 항목에서 Gradle → SDK 11로 변경

## IntelliJ 단축키

[Untitled](https://www.notion.so/adf644ddc65346b5899015d3ccd55948)