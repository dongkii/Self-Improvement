# JAVA Function

- **Lambda Function**
    - **람다함수란?**
        - 람다함수는 프로그래밍 언어에서 사용되는 개념으로 **익명 함수(Anonymous function)**를 지칭하는 용어입니다.
        - 현재 사용되고 있는 람다의 근간은 수학과 기초 컴퓨터과학 분야에서의 람다 대수이다. 람다 대수는 간단히 말하자면 수학에서 사용하는 함수를 보다 **단순하게 표현하는 방법**이다.
    
    - **람다의 특징**
        - 람다 대수는 이름을 가질 필요가 없다. - **익명 함수 (Anonymous function)**
        - 두 개 이상의 입력이 있는 함수는 최종적으로 1개의 입력만 받는 람다 대수로 단순화 될 수 있다. - **커링 (Curring)**
        
    - **익명함수?**
        - 익명함수란 말 그대로 함수의 이름이 없는 함수. 익명함수들은 공통으로 **일급객체(First Class citizen)라는 특징**을 가지고 있다.
        - 이 일급 객체란 일반적으로 다른 객체들에 적용 가능한 연산을 모두 지원하는 객체를 가르킨다. 함수를 값으로 사용 할 수도 있으며, 파라미터로 전달 및 변수에 대입 하기와 같은 연산들이 가능하다.
    
    - **람다의 장단점**
        - **장점**
            1. 코드의 간결성 - 람다를 사용하면 불필요한 반복문의 삭제가 가능하며 복잡한 식을 단순하게 표현할 수 있다.
            2. 지연연산 수행 - 람다는 지연연산을 수행 함으로 써 불필요한 연산을 최소화 할 수 있다.
            3. 병렬처리 가능 - 멀티쓰레드를 활용하여 병렬처리를 사용할 수 있다.
        - **단점**
            1. 람다식의 호출이 까다롭다.
            2. 람다 stream 사용 시 단순 for문 혹은 while문 사용 시 성능이 떨어진다. (속도가 느리다)
            3. 함수 몸체가 return문으로만 구성되어 있는 경우 괄호{}를 생략할 수 없다.
        
        ```java
        // 정상적인 유형
        () -> {}
        () -> 1
        () -> { return 1; }
        
        (int x) -> x + 1
        (x) -> x + 1
        x -> x + 1
        (int x) -> { return x + 1; }
        x - > { return x + 1; }
        
        (int x, int y) -> x + y
        (x , y) -> x + y
        (x , y) -> { return x + y; }
        
        (String lam) -> lam.length();
        lam -> lam.length();
        (Thread lamT) -> { lamT.start(); }
        lamT -> { lamT.start(); }
        
        // 잘못된 유형
        // 선언된 type과 선언되지 않은 type을 같이 사용 할 수 없다.
        (x, int y) -> x + y
        (x, final y) -> x + y
        ```
        
    - 람다식 예제
        - 기존 자바 문법
        
        ```java
        new Thread(new Runnable() {
        	@Override
        	public void run() {
        		System.out.println("Welcome");
        	}
        }
        ```
        
        - 람다식 문법
        
        ```java
        new Thread() -> {
        			System.out.println("Welcome");
        }).start();
        ```
        
    
    - 함수형 인터페이스
        
        @FunctionalInterface
        
        Functional Interface는 일반적으로 **‘구현해야 할 추상 메소드가 하나만 정의된 인터페이스’**를 가리킵니다.
        
        자바 컴파일러는 이렇게 명시된 함수형 인터페이스에 두 개 이상의 메소드가 선언되면 오류를 발생시킵니다.
        
        ```java
        // 구현해야 할 메소드가 한개이므로 Functional Interface이다.
        @FunctionalInterface
        public interface Math {
        		public int int Calc(int first, int second);
        }
        
        // 구현해야 할 메소드가 두개이므로 Functional Interface가 아니다. (오류)
        @FunctionalInterface
        public interface Math {
        		public int Calc(int first, int second);
        		public int Calc2(int first, int second);
        }
        ```
        
    - 추상 메소드 구현 및 함수형 인터페이스 사용
    
    ```java
    public static void main(String[] args) {
    		Math plusLambda = (first, second) -> first + second;
    		System.out.println(plusLambda.Calc(4, 2));
    
    		Math minusLambda = (first, second) -> first - second;
    		System.out.println(plusLambda.Calc(4, 2));
    }
    ```
    
    - 실행 결과
    
    ```java
    6
    2
    ```
    
    - **Stream API**
        
        Stream이란 다양한 데이터를 표준화된 방법으로 다루기 위한 라이브러리이다. 자바 8부터 추가된 Stream API는 다음과 같이 구성된다.
        
        ```java
        example.stream().filter(x -> x < 2).count
        ```
        
        stream() : 스트림생성
        
        filter : 중간 연산 (스트림 변환) - 연속에서 수행 가능
        
        count : 최종 연산 (스트림 사용) - 마지막에 단 한 번만 사용 가능
        
        - Stream의 특징
            - Stream은 데이터를 변경하지 않는다.
            - Stream은 1회용 이다.
            - Stream은 지연 연산을 수행한다.
            - Stream은 병렬 실행이 가능하다.
        
        [Stream의 종류](https://www.notion.so/c5b73f6c37df44938a3fd9387611af16)
        
        [Stream의 중간 연산 명령어](https://www.notion.so/3b5e3ccac7714220bd5c1d2545a72332)
        
        [Stream의 최종 연산 명령어](https://www.notion.so/1217699285af45d1a0e20aecba21c549)
        
        Collector 연산
        
        - Collectors.toList()
        - Collectors.toSet()
        - Collectors.toMap()
        - Collectors.groupingBy
        - Collectors.partioningBy
        - Collectors.summarizingInt()
        
    - Stream API 간단 예제
        - 예제1
        
        ```java
        // Stream API를 이용한 간단한 짝수 판별
        IntStream.range(1, 11).filter(i-> i%2 == 0).forEach(System.out::println);
        ```
        
        - 실행경과
        
        ```java
        2
        4
        6
        8
        10
        ```
        
        - 예제2
        
        ```java
        // 0 ~ 1000까지의 값 중 500이상이며 짝수이면서 5의 배수인 수의 합을 구하라
        System.out.println(
        				IntStream.range(0, 1001)
        						.skip(500)
        						.filter(i -> i % 2 == 0)
        						.filter(i -> i % 5 == 0)
        						.sum()
        );
        ```
        
        - 실행결과
        
        ```java
        38250
        ```
        

- **Arrays.sort()** - 배열 정렬
    
    자바에서 배열이나 리스트를 정렬하고자 할 때 java.util.Arrays 클래스의 sort() 메서드를 사용하여 간편하게 정렬할 수 있다. Arrays 클래스는 배열의 복사, 항목 정렬, 검색과 같은 배열 조각 기능을 가지고 있다.
    
    **Syntax**
    
    - `public static void **sort** ( primitive type **array a** )`
        - 인자 값으로 primitive data type 인 byte[], char[], double[], long[], int[], float[] 등을 이용한다.
    - `public static void **sort** ( primitive type **array a**, **int fromIndex**, **int toIndex** )`
        - 인자 값으로 primitive data type 인 byte[], char[], double[], long[], int[], float[] 등을 이용하며, 정렬 시작 index 값 및 종료 index 값을 지정한다.
    - `public static void **sort** ( **Object[] a** )`
        - 인자 값으로 Object(객체)를 받습니다. Integer[], Double[], Character[] 등의 값을 받을 수 있다.
    - `public static void **sort** ( **Object[] a**, **int fromIndex**, **int toIndex** )`
        - 인자 값으로 Object(객체)를 받습니다. Integer[], Double[], Character[] 등의 값을 받을 수 있으며, 정렬 시작 index 값 및 종료 index 값을 지정한다.
    - `public static void **sort** ( **T[] a**, **Comparator<? super T> c** )`
        - 인자 값으로 객체를 받으며, 지정된 Comparator가 가리키는 순서에 따라 지정된 객체의 배열을 정렬한다.
    - `public static void **sort** ( **T[] a**, **int fromIndex**, **int toIndex**, **Comparator<? super T> c** )`
        - 인자 값으로 객체를 받으며, 지정된 Comparator가 가리키는 순서에 따라 지정된 객체의 배열을 정렬하며, 정렬 시작 index 값 및 종료 index 값을 지정한다.
        
- **Arrays.copyOf()**, **Arrays.copyOfRange()** - 배열 복사
    
    자바에서 배열을 복사하고자 할 때 java.util.Arrays 클래스의 copyOf() 메서드 혹은 copyOfRange() 메서드를 통해 복사 할 수 있다.
    
    **Syntax**
    
    - `public Static primitive type **array a** **copyOf** ( primitive type **array b**, **arrayLength** )`
        - 인자 값으로 primitive data type 을 이용하며, 배열 길이 값을 지정한다.
    - `public Static primitive type **array a** **copyOfRange** ( primitive type **array b**, **int fromIndex**, **int toIndex** )`
        - 인자 값으로 primitive data type 을 이용하며, 복사 시작 index 값 및 종료 index 값을 지정한다.
        
- **List 중복 제거 2가지 방법**
    
    Java에서 List는 중복된 데이터를 가질 수 있습니다.
    
    List에 중복된 데이터가 있을 경우, 중복된 데이터를 제거하는 2가지 방법
    
    1. Set 이용하기
    2. Stream 이용하기 - Java 8 이상
    
    1. **Set 이용하기**
        
        Set은 중복을 허용하지 않는 객체이다.
        
        따라서, List를 Set으로 변경하면 자동으로 중복이 제거된다.
        
        **코드**
        
        ```java
        import java.util.ArrayList;
        import java.util.Arrays;
        import java.util.HashSet;
        import java.util.List;
        import java.util.Set;
        
        public Class RemoveListDuplication {
        	public static void main(String[] args) {
        		
        		// List 준비
        		List<String> list = Arrays.asList("A", "B", "C", "A");
        
        		// List를 Set으로 변경
        		Set<String> set = new HashSet<String>(list);
        
        		// Set을 List로 변경
        		List<String> newList = new ArrayList<String>(set);
        
        		// 결과 출력
        		System.out.println(newList);
        	}
        }
        ```
        
        **결과**
        
        ```java
        [A, B, C]
        ```
        
        위 코드는 중복을 제거하기 위해 List → Set → List 순서로 데이터를 변환 하였다.
        
    2. Stream 이용하기 - Java 8 이상
        
        Java 8 이상에서는 Stream의 distinct()를 이용해서 List의 중복을 제거할 수 있다.
        
        **코드**
        
        ```java
        import java.util.Arrays;
        import java.util.List;
        import java.util.stream.Collectors;
        public class RemoveListDuplication {
        	public static void main(String[] args) {
        		// List 준비
        		List<String> list = Arrays.asList("A", "B", "C", "A");
        
        		// 중복 제거
        		List<String> newList = list.stream().distinct().collect(Collectors.toList());
        
        		// 결과 출력
        		System.out.println(newList);
        	}
        }
        ```
        
        **결과**
        
        ```java
        [A, B, C]
        ```
        
- **Math 클래스의 메소드**
    
    Math 클래스는 수학에서 자주 사용하는 상수들과 함수들을 미리 구현해놓은 클래스
    
    Math 클래스의 모든 메소드는 클래스 메소드(static method)이므로, 객체를 생성하지 않고도 바로 사용 가능
    
    **java.lang.Math**
    
    - **Math.E와 Math.PI**
        1. **Math.E** : 오일러의 수라 불리며, 자연로그(natural logarithms)의 밑(base) 값으로 약 2.718을 의미
        2. **Math.PI** : 원의 원주를 지름으로 나눈 비율(원주율) 값으로 약 3.14159를 의미
    
    - **random()** 메소드
        - random() 메소드는 0.0 이상 1.0 미만의 범위에서 임의의 double형 값을 하나 생성하여 반환
        - 이 메소드는 내부적으로 java.util 패키지의 Random 클래스를 사용한 의사 난수 발생기(pseudorandom-number generator)를 사용하여 임의의 수를 생성
        
        예제
        
        ```java
        (int)(Math.random() *100); // 0 ~ 99
        
        Random ran = new Random();
        ran.nextInt(100); // 0 ~ 99
        
        (int)(Math.random() * 6); // 0 ~ 5
        ((int)(Math.random() * 6) + 1); // 1 ~ 6
        ((int)(Math.random() * 6) + 3); // 3 ~ 8
        ```
        
    - **abs()** 메소드
        - abs() 메소드는 전달된 값이 음수이면 그 값의 절댓값을 반환하며, 전달된 값이 양수이면 전달된 값을 그대로 반환
        
        예제
        
        ```java
        Math.abs(10); // 10
        Math.abs(-10); // 10
        Math.abs(-3.14); // 3.14
        ```
        
    - **floor()**, **ceil()**, **round()** 메소드
        - floor() 메소드는 인수로 전달받은 값과 같거나 작은 수 중에서 가장 큰 정수를 반환
        - ceil() 메소드는 인수로 전달받은 값과 같거나 큰 수 중에서 가장 작은 정수를 반환
        - round() 메소드는 전달받은 실수를 소수점 첫째 자리에서 반올림한 정수를 반환
        
        예제
        
        ```java
        Math.floor(10.0); // 10.0
        Math.floor(10.9); // 10.0
        
        Math.ceil(10.0); // 10.0
        Math.ceil(10.1); // 11.0
        Math.ceil(10.0000001); // 11.0
        
        Math.round(10.0); // 10
        Math.round(10.4); // 10
        Math.round(10.5); // 11
        ```
        
    - **max()**, **min()** 메소드
        - max() 메소드는 전달된 두 값을 비교하여 그중 큰값을 반환
        - min() 메소드는 전달된 두 값을 비교하여 그중 작은값을 반환
        
        예제
        
        ```java
        Math.max(3.14, 3.14159); // 3.14159
        Math.max(-10, -11); // -10
        
        Math.min(3.14, 3.14159); // 3.14
        Math.min(-10, -11); // -11
        ```
        
    - **pow()**, **sqrt()** 메소드
        - pow() 메소드는 전달된 두 개의 double형 값을 가지고 제곱 연산을 수행
        - sqrt() 메소드는 전달된 double형 값의 제곱근을 반환
        
        예제
        
        ```java
        (int)Math.pow(5, 2); // 25
        (int)Math.sqrt(25); // 5
        ```
        
    - **sin()**, **cos()**, **tan()** 메소드
        - sin() 메소드는 전달된 double형 값의 사인값
        - cos() 메소드는 전달된 double형 값의 코사인값
        - tan() 메소드는 전달된 double형 값의 탄젠트값
        
        예제
        
        ```java
        Math.sin(Math.toRadians(30));  // 0.49999999999999994
        Math.sin(Math.PI / 6);         // 0.49999999999999994
        
        Math.tan(Math.toRadians(45));  // 0.9999999999999999
        Math.tan(Math.PI / 4);         // 0.9999999999999999
        
        Math.cos(Math.toRadians(60));  // 0.5000000000000001
        Math.cos(Math.PI / 3);         // 0.5000000000000001
        ```
        
    
    **대표적인 Math 메소드**
    
    | 메소드 | 설명 |
    | --- | --- |
    | static double random() | 0.0 이상 1.0 미만의 범위에서 임의의 double형 값을 하나 생성하여 반환함. |
    | static double abs(double a)
    static double abs(float a)
    static double abs(int a)
    static double abs(long a) | 전달된 값이 음수이면 그 값의 절댓값을 반환하며, 전달된 값이 양수이면 인수를 그대로 반환함. |
    | static double toRaidans(double angdeg) | 육십분법의 각도 값을 대략적인 호도법의 라디안 값으로 변환함. |
    | static double toDegrees(double angrad) | 호도법의 라디안 값을 대략적인 육십분법의 각도 값으로 변환함. |
    | static double sin(double a)
    static double cos(double a)
    static double tan(double a) | 전달된 double형 값에 해당하는 각각의 삼각 함숫값을 반환함. |
    | static double sqrt(double a) | 전달된 double형 값의 제곱근 값을 반환함. |
    | static double pow(double a, double b) | 전달된 두 개의 double형 값을 가지고 제곱 연산을 수행하여, ab을 반환함. |
    | static double min(double a, double b)
    static float min(float a, float b)
    static long min(long a, long b)
    static int min(int a, int b) | 전달된 두 값을 비교하여 작은 값을 반환함. |
    | static double max(double a, double b)
    static float max(float a, float b)
    static long max(long a, long b)
    static int max(int a, int b) | 전달된 두 값을 비교하여 큰 값을 반환함. |
    | static double rint(double a) | 전달된 double형 값과 가장 가까운 정수값을 double형으로 반환함. |
    | static long round(double a)
    static int round(float a) | 전달된 값을 소수점 첫째 자리에서 반올림한 정수를 반환함. |
    | static double floor(double a) | 전달된 double형 값의 소수 부분이 존재하면 소수 부분을 무조건 버리고 반환함. |
    | static double ceil(double a) | 전달된 double형 값의 소수 부분이 존재하면 소수 부분을 무조건 올리고 반환함. |