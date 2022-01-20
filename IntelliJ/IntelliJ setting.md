> # 1. IntelliJ 세팅
1.1 gradle 프로젝트를 spring boot 프로젝트로 변경
- build.gradle 파일을 열어 맨앞에 아래와 같이 추가
    - gradle 버전 없으로 수정 필요
```gradle
plugins {
    id 'org.springframework.boot' version '2.4.1' // RELEASE 삭제
    id 'io.spring.dependency-management' version '1.0.10.RELEASE'
    id 'java'
}

group 'com.jojoldu.book'
version '1.0.4-SNAPSHOT-'+new Date().format("yyyyMMddHHmmss")
sourceCompatibility = 1.8

repositories {      // 각종 의존성(라이브러리)들을 어떤 원격 저장소에서 받을지 지정
    mavenCentral()  
    jcenter()       // 라이브러리 업로드 난이도 때문에 jcenter 도 많이 이용
}

dependencies {
    implementation('org.springframework.boot:spring-boot-starter-web')
    testImplementation('org.springframework.boot:spring-boot-starter-test')
}
```


1.2 



> ## IntelliJ 단축키
|동작|단축키(윈도우)|단축키(맥)|
|:---------:|:------:|:------:|
|자동완성|Ctrl + Space|Ctrl + Space|
|Action 검색창|Ctrl + Shift + A|Command + Shift + A|
|프로젝트 우클릭|Alt + Insert|Command + N|
|설정 창|Ctrl + Alt + S|
|git commit 창|Ctrl + K|Command + K|
|git push 창|Ctrl + Shift + K|Command + Shift + K|
