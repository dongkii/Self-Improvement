# Redirect, Fowad 취약점

## 검증되지 않은 Redirect / Forward 취약점 대응방안

### **`1. 신뢰되지 않는 URL 주소로 자동 접속 연결이란?`**
사용자의 입력값 등 외부 입력값이 링크 표현에 사용되고, 이 링크를 이용하여 악의적인 사이트로 리다이렉트(redirect)되는 보안 약점

<br/>

### **`2. 공격 시나리오`**
1) 사용자 인증을 필요로 하는 페이지에 접근을 하면, 사이트는 로그인 페이지로 이동시킨 뒤 로그인에 성공하면 다시 이전 페이지로 돌아가기 위해 Redirect 페이지 주소를 받게된다. 악의적인 사용자가 Redirect되는 페이지의 주소를 피싱 사이트로 변조하여 사용자에게 전송한다.

2) 만약 어플리케이션이 "url" 이라는 하나의 파라미터를 가진 "redirect.jsp" 페이지를 갖고 있다면, 공격자는 피싱이나 악성코드를 설치하는 악성 사이트로 사용자를 리다이렉트 시키는 악성 URL을 만들 수 있다.

```
http://www.example.com/redirect.jsp?url=evil.com
```

<br/>

### **`3. Redirect를 이용한 Phishing 예제`**
아래와 같이 오리지널 사이트의 주소를 이용해서 사용자를 유인한다.

http://original_site.com/redirect.html?<span style="color: red;">q=http://external_site.com/external_page.html</span>  
http://original_site.com/redirect.html?<span style="color: red;">q=http://evil.com/evil_page.html</span>  
http://original_site.com/redirect.html?<span style="color: red;">q=http://%65%76%69%6c%2e%63%6f%6d/evil_page.html</span>

<br/>

### **`4. Redirect에 사용자가 자주 속는 이유는?`**
제공된 링크 주소가 평소 알고 있던 원래 사이트 주소이기 때문에 안심하고 링크를 클릭하게 된다.

<br/>
<br/>

## 해결방안
1. 화이트리스트 작성으로 redirect url 검증 절차 필요
    - 법인몰일 경우 interparkb2b.co.kr 체크? (서비스몰에 등록된 URL과 같은지 체크)
    - 제휴사의 경우??
2. CR/LF(Carriage Return/Line Feed) 문자 포함의 경우 에러페이지 리턴