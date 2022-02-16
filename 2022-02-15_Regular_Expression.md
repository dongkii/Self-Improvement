# Regular Expression (정규표현식)

## **목차**
- [1. 정규표현식(Regular Expression) 이란 ?](#1)
- [2. 자주 사용하는 정규표현식](#2)
- [3. 정규표현식 작성 방법](#3)
    - [3-1. Pattern 클래스](#3-1)
    - [3-2. Matcher 클래스](#3-2)
- [4. 유효성 검사](#4)
- [5. 정규표현식 문법](#5)

<br/>
<br/>

## 1. 정규표현식(Regular Expression) 이란?<a id="1"></a>  

> `정규표현식(Regular Expression)`이란 컴퓨터 과학의 정규언어로부터 유래한 것으로 특정한 규칙을 가진 문자열의 집합을 표현하기 위해 쓰이는 언어 형식. 개발을 하다보면 전화번호, 주민등록번호, 이메일등과 같이 정해져있는 형식이 있고 사용자가 그 형식대로 제대로 입력했는지 검증을 해야하는 경우가 종종 있다. 이런 입력값을 정해진 형식에 맞는지 검증해야 할 때에는 정규표현식을 사용하면 쉽게 구현할 수 있다.

<br/>
<br/>

## 2. 자주 사용하는 정규표현식<a id="2"></a>  

|정규 표현식|설명|
|---|---|
|^[0-9]*$|숫자|
|^[a-zA-Z]*$|영문자|
|^[가-힣]*$|한글|
|\\w+@\\w+\\.\\w+(\\.\\w+)?|E-Mail|
|^\d{2,3}-\d{3,4}-\d{4}$|전화번호|
|^01(?:0\|1\|[6-9])-(?:\d{3}\|\d{4})-\d{4}$|핸드폰번호|
|\d{6} \- [1-4]\d{6}|주민등록번호|
|^\d{3}-\d{2}$|우편번호|

<br/>
<br/>

## 3. 정규표현식 작성 방법<a id="3"></a>  

> 정규표현식을 작성하는 방법은 자바 API `java.util.regex` 패키지를 사용해야 한다. 자바에서 정규표현식을 사용할 때에는 `java.util.regex 패키지 안에 있는 Pattern 클래스와 Matcher클래스`를 주로 사용  

<br/>

- ### 3-1. Pattern 클래스<a id="3-1"></a>  

    정규표현식에 대상 문자열을 검증하는 기능은 `java.util.regex.Pattern 클래스`의 `matchers()` 메소드를 활용하여 검증할 수 있다. matches() 메서드의 첫번째 매개값은 정규표현식이고 두번째 매개값은 검증 대상 문자열이다. 검증 후 대상 문자열이 정규표현식과 일치하면 true, 그렇지 않다면 false 값을 리턴

    ```java
    import java.util.regex.Pattern;

    public class RegexExample {
        public static void main(String[] args) {
            String pattern = "^[0-9]*$";    // 숫자만
            String val = "123456789";

            boolean regex = Pattern.matches(pattern, val);
            System.out.println(regex);
        }
    }
    ```
    ```
    true
    ```

    
    > `Pattern 클래스 주요 메서드`  
    compile(String regex) : 주어진 정규표현식으로부터 패턴을 만듭니다.  
    matcher(CharSequence input) : 대상 문자열이 패턴과 일치할 경우 true를 반환합니다.  
    asPredicate() : 문자열을 일치시키는 데 사용할 수있는 술어를 작성합니다.  
    pattern() : 컴파일된 정규표현식을 String 형태로 반환합니다.  
    split(CharSequence input) : 문자열을 주어진 인자값 CharSequence 패턴에 따라 분리합니다.  

    > `Parttern 플래그 값 사용(상수)`  
    Pattern.CANON_EQ : None표준화된 매칭 모드를 활성화합니다.  
    Pattern.CASE_INSENSITIVE : 대소문자를 구분하지 않습니다.  
    Pattern.COMMENTS : 공백과 #으로 시작하는 주석이 무시됩니다. (라인의 끝까지).  
    Pattern.MULTILINE : 수식 ‘^’ 는 라인의 시작과 '\$'는 라인의 끝과 match 됩니다.  
    Pattern.DOTALL : 수식 ‘.’과 모든 문자와 match 되고 ‘\n’ 도 match 에 포함됩니다.  
    Pattern.UNICODE_CASE : 유니코드를 기준으로 대소문자 구분 없이 match 시킵니다.   
    Pattert.UNIX_LINES : 수식 ‘.’ 과 ‘^’ 및 ‘$’의 match시에 한 라인의 끝을 의미하는 ‘\n’만 인식됩니다.  

<br/>

- ### 3-2. Matcher 클래스<a id="3-2"></a>
    Matcher 클래스는 대상 문자열의 패턴을 해석하고 주어진 패턴과 일치하는지 판별할 때 주로 사용된다. Matcher 클래스의 입력값으로는 CharSequence라는 새로운 인터페이스가 사용되는데, 이를 통해 다양한 형태의 입력 데이터로부터 문자 단위의 매칭 기능을 지원 받을 수 있습니다. Matcher 객체는 Pattern 객체의 matcher() 메소르를 호출하여 받아 올 수 있다.

    ```java
    import java.util.regex.Matcher;
    import java.util.regex.Pattern;

    public class RegexExample {
        public static void main(String[] args) {
            Pattern = pattern = Pattern.compile("^[a-zA-Z]*$");     // 영문자만
            String val = "abcdefg";     // 대상 문자열

            Matcher matcher = pattern.matcher(val);
            System.out.println(matcher.find());
        }
    }
    ```
    ```
    true
    ```

    > `Matcher 클래스 주요 메서드`  
    matches() : 대상 문자열과 패턴이 일치할 경우 true 반환합니다.  
    find() : 대상 문자열과 패턴이 일치하는 경우 true를 반환하고, 그 위치로 이동합니다.  
    find(int start) : start위치 이후부터 매칭검색을 수행합니다.  
    start() : 매칭되는 문자열 시작위치 반환합니다.  
    start(int group) : 지정된 그룹이 매칭되는 시작위치 반환합니다.  
    end() : 매칭되는  문자열 끝 다음 문자위치 반환합니다.  
    end(int group) : 지정되 그룹이 매칭되는 끝 다음 문자위치 반환합니다.  
    group() : 매칭된 부분을 반환합니다.  
    group(int group) : 매칭된 부분중 group번 그룹핑 매칭부분 반환합니다.  
    groupCount() : 패턴내 그룹핑한(괄호지정) 전체 갯수를 반환합니다.  

<br/>
<br/>

## 4. 유효성 검사<a id="4"></a>

정규 표현식은 유효성 검사 코드 작성 시 가장 효율적인 방법이다.

```java
import java.util.regex.Pattern;

public class RegexExample {
	public static void main(String[] args)  {
          String name = "홍길동";
          String tel = "010-1234-5678";
          String email = "test@naver.com";
         
          //유효성 검사
          boolean name_check = Pattern.matches("^[가-힣]*$", name);
          boolean tel_check = Pattern.matches("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", tel);
          boolean email_check = Pattern.matches("\\w+@\\w+\\.\\w+(\\.\\w+)?", email);

          //출력
          System.out.println("이름 : " + name_check);
          System.out.println("전화번호 : " + tel_check);
          System.out.println("이메일 : " + email_check);
    }
}
```
```
이름 : true
전화번호 : true
이메일 : true
```

<br/>
<br/>

## 5. 정규표현식 문법<a id="5"></a>

|정규표현식|설  명|
|:---:|---|
|^|문자열 시작|
|$|문자열 종료|
|.|임의의 한 문자(단 \은 넣을 수 없음)|
|*|앞 문자가 없을 수도 무한정 많을 수도 있음|
|+|앞 문자가 하나 이상|
|?|앞 문자가 없거나 하나 있음|
|[ ]|문자의 집합이나 범위를 나타내며 두 문자 사이는 - 기호로 범위를 나타냅니다. [] 내에서 ^ 가 선행하여 존재하면 not을 나타낸다.|
|{ }|횟수 또는 범위를 나타냅니다.|
|( )|소괄호 안의 문자를 하나의 문자로 인식|
|\||패턴 안에서 or 연산을 수행할 때 사용|
|\ |정규 표현식 역슬래시(\)는 확장문자 (역슬래시 다음에 일반 문자가 오면 특수문자로 |취급하고 역슬래시 다음에 특수문자가 오면 그 문자 자체를 의미)
|\b|단어의 경계|
|\B|단어가 아닌것에 대한 경계|
|\A|입력의 시작 부분|
|\G|이전 매치의 끝|
|\Z|입력의 끝이지만 종결자가 있는 경우|
|\z|입력의 끝|
|\s|공백 문자|
|\S|공백 문자가 아닌 나머지 문자|
|\w|알파벳이나 숫자|
|\W|알파벳이나 숫자를 제외한 문자|
|\d|숫자 [0-9]와 동일|
|\D|숫자를 제외한 모든 문자|
|(?i)|앞 부분에 (?!)라는 옵션을 넣어주게 되면 대소문자는 구분하지 않습니다.|