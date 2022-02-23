# Stream [JAVA]

## **목차**
- [1. 스트림(Stream) 이란?](#1)
- [2. 생성하기](#2)
    - [2-1. 배열 / 컬렉션 / 빈 스트림](#2-1)
    - [2-2. Stream.builder() / Stream.generate() / Stream.iterate()](#2-2)
    - [2-3. 기본 타입형 / String / 파일스트림](#2-3)
    - [2-4. 병렬 스트림 / 스트림 연결하기](#2-4)
- [3. 가공하기](#3)
    - [3-1. Filtering](#3-1)
    - [3-2. Mapping](#3-2)
    - [3-3. Sorting](#3-3)
    - [3-4. Iterating](#3-4)
- [4. 결과 만들기](#4)
    - [4-1. Calulating](#4-1)
    - [4-2. Reduction](#4-2)
    - [4-3. Collecting](#4-3)
    - [4-4. Matching](#4-4)
    - [4-5. Iterating](#4-5)


## 1. 스트림(Stream) 이란?<a id="1"></a>
> 자바 8에서 추가한 `스트림(Stream)`은 람다를 활용할 수 있는 기술 중 하나이다. 자바 8이전에는 배열 또는 컬렉션 인스턴스를 다루는 방법은 `for` 또는 `foreach` 문을 돌면서 요소 하나씩을 꺼내서 다루는 방법이었다. 간단한 경우라면 상관없지만 로직이 복잡해질수록 코드의 양이 많아져 여러 로직이 섞이게 되고, 메소드를 나눌 경우 루프를 여러번 도는 경우가 발생한다.

스트림은 `'데이터의 흐름'`이다. 배열 또는 컬렉션 인스턴스에 함수 여러 개를 조합해서 원하는 결과를 필터링하고 가공된 결과를 얻을 수 있다. 또한 람다를 이용해서 코드의 양을 줄이고 간결하게 표현할 수 있다. 즉, 배열과 컬렉션을 함수형으로 처리할 수 있다.

또 하나의 장점은 간단하게 `병렬처리(multi-threading)`가 가능하다는 점이다. 하나의 작업을 둘 이상의 작업으로 잘게 나눠서 동시에 진행하는 것을 `병렬처리(parallel processing)`라고 한다. 즉 쓰레드를 이용해 많은 요소들을 빠르게 처리할 수 있다.

스트림에 대한 내용은 크게 세 가지로 나눌 수 있다.
1. `생성하기` : 스트림 인스턴스 생성
2. `가공하기` : 필터링(filtering)및 맵핑(mapping)등 원하는 결과를 만들어가는 중간 작업(intermediate operations)
3. `결과 만들기` : 최종적으로 결과를 만들어내는 작업(terminal operations)

> 전체 -> 맵핑 -> 필터링1 -> 필터링2 -> 결과만들기 -> 결과물

<br/>
<br/>

## 2. 생성하기 <a id="2"></a>  
> 보통 배열과 컬렉션을 이용해서 스트림을 만들지만 이 외에도 다양한 방법으로 스트림을 만들 수 있다.
## 2-1. 배열 / 컬렉션 / 빈 스트림 <a id="2-1"></a>

- 배열 스트림  
    스트림을 이용하기 위해서는 먼저 생성을 해야 한다. 스트림은 배열 또는 컬렉션 인스턴스를 이용해서 생성할 수 있다. 배열은 다음과 같이 `Arrays.stream` 메소드를 사용

    ```java
    String[] arr = new String[]{"a", "b", "c"};
    Stream<String> stream = Arrays.stream(arr);
    Stream<String> streamOfArrayPart =
        Arrays.stream(arr, 1, 3); // 1~2 요소 [b, c]
    ```

- 컬렉션 스트림  
    컬렉션 타입(Collection, List, Set)의 경우 인터페이스에 추가된 디폴트 메소드 `stream`을 이용해서 스트림을 만들 수 있다.
    ```java
    public interface Collection<E> extends Iterable<E> {
        default Stream<E> stream() {
            return StreamSupport.stream(spliterator(), false);
        }
    }
    ```
    그러면 다음과 같이 생성할 수 있다.
    ```java
    List<String> list = Arrays.asList("a", "b", "c");
    Stream<String> stream = list.stream();
    Stream<String> parallelStream = list.parallelStream(); // 병렬 처리 스트림
    ```

- 비어 있는 스트림  
    비어있는 스트림(empty streams)도 생성할 수 있다. 언제 빈 스트림이 필요한가? 빈 스트림은 요소가 없을 때 null 대신 사용할 수 있다.
    ```java
    public Stream<String> streamOf(List<String> list) {
        return list == null || list.isEmpty() ? Stream.empty() : list.stream();
    }
    ```

<br/>
<br/>

## 2-2. Stream.builder() / Stream.generate() / Stream.iterate() <a id="2-2"></a>

- Stream.builder()  
    빌더(Builder)를 사용하면 스트림에 직접적으로 원하는 값을 넣을 수 있다. 마지막에 build 메소드로 스트림을 리턴한다.
    ```java
    Stream<String> builderStream =
        Stream.builder()
        .add("Eric").add("Elena").add("java")
        .build();       // [Eric, Elena, Java]
    ```

- Stream.generate()  
    `generate` 메소드를 이용하면 `Supplier<T>`에 해당하는 람다로 값을 넣을 수 있다. `Supplier<T>`는 인자는 없고 리턴값만 있는 함수형 인터페이스이다. 람다에서 리턴하는 값이 들어간다.
    ```java
    public static<T> Stream<T> generate(Supplier<T> s) { ... }
    ```

    이때 생성되는 스트림은 크기가 정해져있지 않고 무한(infinite)하기 때문에 특정 사이즈로 최대 크기를 제한해야 한다.

    ```java
    Stream<String> generatedStream = 
        Stream.generate(() -> "gen").limit(5);  // [el, el, el, el, el]
    ```

    5개의 "gen" 이 들어간 스트림이 생성된다.

- Stream.iterate()  
    `iterate` 메소드를 이용하면 초기값과 해당 값을 다루는 람다를 이용해서 스트림에 들어갈 요소를 만든다. 다음 예제에서는 30이 초기값이고 값이 2씩 증가하는 값들이 들어가게 된다. 즉 요소가 다음 요소의 인풋으로 들어간다. 이 방법도 스트림의 사이즈가 무한하기 때문에 특정 사이즈로 제한해야 한다.

    ```java
    Stream<Integer> iteratedStream =
        Stream.iterate(30, n -> n + 2).limit(5); // [30, 32, 34, 36, 38]
    ```

<br/>
<br/>

## 2-3. 기본 타입형 / String / 파일스트림 <a id="2-3"></a>

- 기본 타입형 스트림  
    물론 제네릭을 사용하면 리스트나 배열을 이용해서 기본 타입(int, long, double) 스트림을 생성할 수 있다.
    하지만 제네릭을 사용하지 않고 직접적으로 해당 타입의 스트림을 다룰 수도 있다. `range`와 `rangeClosed`는 범위의 차이이다. 두 번째 인자인 종료 지점이 포함되느냐 안되느냐의 차이

    ```java
    IntStream intStream = IntStream.range(1, 5); // [1, 2, 3, 4]
    LongStream longStream = LongStream.rangeClosed(1, 5); // [1, 2, 3, 4, 5]
    ```

    제네릭을 사용하지 않기 때문에 불필요한 `오토박싱(auto-boxing)`이 일어나지 않는다. 필요한 경우 boxed 메소드를 사용해 `박싱(boxing)` 가능

    ```java
    Stream<Integer> boxedIntStream = IntStream.range(1, 5).boxed();
    ```
    
    Java 8 의 Random 클래스는 난수를 가지고 세 가지 타입의 스트림(IntStream, LongStream, DoubleStream)을 만들어낼 수 있다. 쉽게 난수 스트림을 생성해서 여러가지 후속 작업을 취할 수 있어 유용하다.

    ```java
    DoubleStream doubles = new Random().doubles(3); // 난수 3개 생성
    ```

- 문자열 스트림  
    문자열을 이용해서 스트림을 생성할 수도 있다. 다음은 문자열의 각 문자(char)를 `IntStream`으로 변환한 예제이다. `char`는 문자이지만 본질적으로는 숫자이기 때문에 가능하다.

    ```java
    IntStream charsStream = 
        "Stream".chars();   // [83, 116, 114, 101, 97,109]
    ```

    다음은 정규표현식(RegEx)을 이용해서 문자열을 자르고, 각 요소들로 스트림을 만든 예제이다.

    ```java
    Stream<String> stringStream =
        Pattern.compile(", ").splitAsStream("Eric, Elena, Java");
        // [Eric, Elena, Java]
    ```

- 파일 스트림  
    자바 NIO의 `Files` 클래스의 `lines` 메소드는 해당 파일의 각 라인을 문자열 타입의 스트림으로 만들어 준다.

    ```java
    Stream<String> lineStream =
        Files.lines(Paths.get("file.txt"),
                    Charset.forName("UTF-8"));
    ```

<br/>
<br/>

## 2-4. 병렬 스트림 / 스트림 연결하기 <a id="2-4"></a>

- 병렬 스트림 (Parallel Stream)  
    스트림 생성 시 사용하는 `stream` 대신 `parallelStream` 메소드를 사용해서 병렬 스트림을 쉽게 생성할 수 있다. 내부적으로는 쓰레드를 처리하기 위해 자바 7부터 도입된 Fork/Join framework 를 사용한다.

    ```java
    // 병렬 스트림 생성
    Stream<Product> parallelStream = productList.parallelStream();

    // 병렬 여부 확인
    boolean isParallel = parallelStream.isParallel();
    ```

    따라서 다음 코드는 각 작업을 쓰레드를 이용해 병렬 처리된다.

    ```java
    boolean isMany = parallelStream
            .map(product -> product.getAmount() * 10)
            .anyMatch(amount -> amount > 200);
    ```

    다음은 배열을 이용해서 병렬 스트림을 생성하는 경우이다.

    ```java
    Arrays.stream(arr).parallel();
    ```

    컬렉션과 배열이 아닌 경우는 다음과 같이 `parallel` 메소드를 이용해서 처리한다.

    ```java
    IntStream intStream = IntStream.range(1, 150).parallel();
    boolean isParallel = intStream.isParallel();
    ```

    다시 시퀀셜(sequential) 모드로 돌리고 싶다면 다음처럼 `sequential` 메소드를 사용한다. 뒤에서 한번 더 다루겠지만 반드시 병렬 스트림이 좋은 것은 아니다.

    ```java
    IntStream intStream = intStream.sequential();
    boolean isParallel = intStream.isParallel();
    ```

- 스트림 연결하기  
    `Stream.concat` 메소드를 이용해 두 개의 스트림을 연결해서 새로운 스트림을 만들어낼 수 있다.

    ```java
    Stream<String> stream1 = Stream.of("Java", "Scala", "Groovy");
    Stream<String> stream2 = Stream.of("Python", "Go", "Swift");
    Stream<String> concat = Stream.concat(stream1, stream2);
    // [Java, Scala, Groovy, Python, Go, Swift]
    ```

<br/>
<br/>

## 3. 가공하기 <a id="3"></a>
전체 요소 중에서 다음과 같은 API를 이용해서 내가 원하는 것만 뽑아낼 수 있다. 이러한 가공 단계를 `중간 작업(intermediate operations)`이라고 하는데, 이러한 작업은 스트림을 리턴하기 때문에 여러 작업을 이어 붙여서(chaining) 작성할 수 있다.

```java
List<String> names = Arrays.asList("Eric", "Elena", "Java");
```

아래 나오는 예제 코드는 위와 같은 리스트를 대상으로 한다.

## 3-1. Filtering <a id="3-1"></a>

 - 필터(filter)는 스트림 내 요소들을 하나씩 평가해서 걸러내는 작업이다. 인자로 받는 Predicate 는 boolean을 리턴하는 함수형 인터페이스로 평가식이 들어가게 된다.

```java
Stream<T> filter(Predicate<? super T> predicate);
```

아래는 간단한 예제이다.

```java
Stream<String> stream = names.stream()
                                .filter(name -> name.contains("a"));
                                // [Elena, Java]
```

스트림의 각 요소에 대해서 평가식을 실행하게 되고 'a'가 들어간 이름만 들어간 스트림이 리턴된다.

<br/>
<br/>

## 3-2. Mapping <a id="3-2"></a>

`맵(map)`은 스트림 내 요소들을 하나씩 특정 값으로 변환해준다. 이 때 값을 변환하기 위한 람다를 인자로 받는다.

```java
<R> Stream<R> map(Function<? super T, ? extends R> mapper);
```

스트림에 들어가 있는 값이 input이 되어서 특정 로직을 거친 후 output이 되어 (리턴되는) 새로운 스트림에 담기게 된다. 이러한 작업을 `맵핑(mapping)`이라고 한다.

아래는 간단한 예제이다. 스트림 내 String 의 `toUpperCase`메소드를 실행해서 대문자로 변환한 값들이 담긴 스트림을 리턴한다.

```java
Stream<String> stream = names.stream()
                .map(String::toUpperCase);
                // [ERIC, ELENA, JAVA]
```

다음처럼 요소 내 들어있는 Product 개체의 수량을 꺼내올 수도 있다. 각 '상품'을 '상품의 수량'으로 매핑한다.

```java
Stream<Integer> stream = productList.stream()
                 .map(Product::getAmount);
                 // [23, 14, 13, 23, 13]
```

`map` 이외에도 조금 더 복잡한 `flatMap` 메소드도 있다.
```java
<R> Stream<R> flatMap(Function<? super T, ? extends Stream<? extends R>> mapper);
```

인자로 mapper를 받고 있는데, 리턴 타입이 Stream이다. 즉, 새로운 스트림을 생성해서 리턴하는 람다를 넘겨야 한다. `flatMap`은 중첩 구조를 한 단계 제거하고 단일 컬렉션으로 만들어주는 역할을 한다. 이러한 작업을 `플래트닝(flattening)`이라고 한다.

아래와 같은 중첩된 리스트가 있을 경우
```java
List<List<String>> list = Arrays.asList(Arrays.asList("a"), Arrays.asList("b"));

// [[a], [b]]
```

이를 `flatMap`을 사용해서 중첩 구조를 제거한 후 작업할 수 있다.

```java
List<String> flatList = list.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
                // [a, b]
```

이번엔 객체에 적용

```java
students.stream()
    .flatMapToInt(student ->
                  IntStream.of(student.getKor(),
                               student.getEng(),
                               student.getMath()))
    .average().ifPresent(avg ->
                         System.out.println(Math.round(avg * 10)/10.0));
```

위 예제에서는 학생 객체를 가진 스트림에서 학생의 국영수 점수를 뽑아 새로운 스트림을 만들어 평균을 구하는 코드이다.
이는 `map` 메소드 자체만으로는 한번에 할 수 없는 기능이다.

<br/>
<br/>

## 3-3. Sorting <a id="3-3"></a>

정렬의 방법은 다른 정렬과 마찬가지로 Comparator를 이용한다.

```java
Stream<T> sorted();
Stream<T> sorted(Comparator<? super T> comparator);
```

인자 없이 그냥 호출할 경우 `오름차순`으로 정렬한다.

```java
IntStream.of(14, 11, 20, 39, 23)
    .sorted()
    .boxed()
    .collect(Collectors.toList());

// [11, 14, 20, 23, 39]
```

인자를 넘기는 경우와 비교해보면, 스트링 리스트에서 알파벳 순으로 정렬한 코드와 Comparator를 넘겨서 역순으로 정렬한 코드이다.

```java
List<String> lang = Arrays.asList("Java", "Scala", "Groovy", "Python", "Go", "Swift");

lang.stream()
    .sorted()
    .collect(Collectors.toList());
// [Go, Groovy, Java, Python, Scala, Swift]

lang.stream()
    .sorted(Comparator.reverseOrder())
    .collect(Collectors.toList());
// [Swift, Scala, Python, Java, Groovy, Go]
```

Comparator의 `compare` 메소드는 두 인자를 비교해서 값을 리턴한다.

```java
int compare(T o1, T o2)
```

기본적으로 Comparator 사용법과 동일하다. 이를 이용해서 문자열 길이를 기준으로 정렬하면,

```java
lang.stream()
    .sorted(Comparator.comparingInt(String::length))
    .collect(Collectors.toList());
// [Go, Java, Scala, Swift, Groovy, Python]

lang.stream()
    .sorted((s1, s2) -> s2.length() - s1.length())
    .collect(Collectors.toList());
// [Groovy, Python, Scala, Swift, Java, Go]
```

<br/>
<br/>

## 3-4. Iterating <a id="3-4"></a>

스트림 내 요소들 각각을 대상으로 특정 연산을 수행하는 메소드로는 `peek` 메소드가 있다. 'peek'는 그냥 확인해본다는 단어 뜻처럼 특정 결과를 반환하지 않는 함수형 인터페이스 Consumer를 인자로 받는다.

```java
Stream<T> peek(Consumer<? super T> action);
```

따라서 스트림 내 요소들 각각에 특정 작업을 수행할 뿐 결과에 영향을 미치지 않는다. 다음처럼 작업을 처리하는 중간에 결과를 확인해볼 때 사용할 수 있다.

```java
int sum = IntStream.of(1, 3, 5, 7, 9)
            .peek(System.out::println)
            .sum();
```

<br/>
<br/>

## 4. 결과 만들기 <a id="4"></a>

가공한 스트림을 가지고 내가 사용할 결과값으로 만들어내는 단계이다. 따라서 스트림을 끝내는 `최종 작업(terminal operations)`이다.

## 4-1. Calulating <a id="4-1"></a>

스트림 API는 다양한 종료 작업을 제공한다. 최소, 최대, 합, 평균 등 기본형 타입으로 결과를 만들어낼 수 있다.

```java
long count = IntStream.of(1, 3, 5, 7, 9).count();
long sum = IntStream.of(1, 3, 5, 7, 9).sum();
```

만약 스트림이 비어 있는 경우 `count` 와 `sum` 은 0을 출력하면 된다. 하지만 평균, 최소, 최대의 경우에는 표현할 수가 없기 때문에 `Optional`을 이용해 리턴한다.

```java
OptionalInt min = IntStream.of(1, 3, 5, 7, 9).min();
OptionalInt max = IntStream.of(1, 3, 5, 7, 9).max();
```

스트림에서 바로 `ifPresent` 메소드를 이용해서 Optional을 처리할 수 있다.

```java
DoubleStream.of(1.1, 2.2, 3.3, 4.4, 5.5)
            .average()
            .ifPresent(System.out::println);
```

이 외에도 사용자가 원하는대로 결과를 만들어내기 위해 `reduce`와 `collect` 메소드를 제공한다.

<br/>

## 4-2. Reduction <a id="4-2"></a>

스트림은 `reduce`라는 메소드를 이용해서 결과를 만들어낸다. 람다 예제에서 살펴봤듯 스트림에 있는 여러 요소의 총합을 낼 수도 있다.

다음은 `reduce` 메소드는 총 세가지의 파라미터를 받을 수 있다.  
- `accumulator` : 각 요소를 처리하는 계산 로직. 각 요소가 올 때마다 중간 결과를 생성하는 로직.
- `identity` : 계산을 위한 초기값으로 스트림이 비어서 계산할 내용이 없더라도 이 값은 리턴.
- `combiner` : 병렬(parallel) 스트림에서 나눠 계산한 결과를 하나로 합치는 동작의 로직.

```java
// 1개 (accumulator)
Optional<T> reduce(BinaryOperator<T> accumulator);

// 2개 (identity)
T reduce(T identity, BinaryOperator<T> accumulator);

// 3개 (combiner)
<U> U reduce(U identity, 
            BiFunction<U, ? super T, U> accumulator, 
            BinaryOperator<U> conbiner);
```

먼저 인자가 하나만 있는 경우이다. 여기서 `BinaryOperator<T>`는 같은 타입의 인자 두 개를 받아 같은 타입의 결과를 반환하는 함수형 인터페이스이다. 다음 예제에서는 두 값을 더하는 람다를 넘겨주고 있다. 따라서 결과는 6(1+2+3)이 된다.

```java
OptionalInt reduced = IntStream.range(1, 4) // [1, 2, 3]
                            .reduce((a, b) -> {
                                return Integer.sum(a, b);
                            });
```

이번엔 두 개의 인자를 받는 경우이다. 여기서 10은 초기값이고, 스트림 내 값을 더해서 결과는 16(10+1+2+3)이 된다. 여기서 람다는 `메소드 참조(method reference)`를 이용해서 넘길 수 있다.

```java
int reducedTwoParams = IntStream.range(1, 4) // [1, 2, 3]
                            .reduce(10, Integer::sum);
```

마지막으로 세 개의 인자를 받는 경우이다. Combiner가 하는 역할을 설명만 봤을 때는 잘 이해가 안갈 수 있는데, 코드를 한번 살펴보기 바란다. 그런데 다음 코드를 실행해보면 이상하게 마지막 인자인 Combiner는 실행되지 않는다.

```java
Integer reducedParams = Stream.of(1, 2, 3)
                            .reduce(10, // identity
                            Integer::sum,   // accumulator
                            (a, b) -> {
                                System.out.println("combiner was called");
                                return a + b;
                            });
```

Combiner는 병렬 처리 시 각자 다른 쓰레드에서 실행한 결과를 마지막에 합치는 단계이다. 따라서 병렬스트림에서만 동작한다.

```java
Integer reducedParallel = Arrays.asList(1, 2, 3)
                                .parallelStream()
                                .reduce(10,
                                    Integer::sum,
                                    (a, b) -> {
                                        System.out.println("combiner was called")
                                        return a + b;
                                    });
```

결과는 다음과 같이 36이 나온다. 먼저 accumulator는 총 세 번 동작한다. 초기값 10에 각 스트림을 더한 세 개의 값(10+1=11, 10+2=12, 10+3=13)을 계산한다. Combiner는 identity와 accumulator를 가지고 여러 쓰레드에서 나눠 계산한 결과를 합치는 역할이다. (12 + 13 = 25, 25 + 11 = 36) 이렇게 두 번 호출된다.

```
combiner was called
combiner was called
36
```

병렬 스트림이 무조건 시퀀셜보다 좋은 것은 아니다. 오히려 간단한 경우에는 이렇게 부가적인 처리가 필요하기 때문에 오히려 느릴 수도 있다.

<br/>

## 4-3. Collecting <a id="4-3"></a>

`collect` 메소드는 또 다른 종료 작업이다. `Collector` 타입의 인자를 받아서 처리를 하는데, 자주 사용하는 작업은 `Collectors` 객체에서 제공하고 있다.

이번 예제에서는 다음과 같이 간단한 리스트를 사용한다. Product 객체는 수량(amount)과 이름(name)을 가지고 있다.

```java
List<Product> productList = 
                    Arrays.asList(new Product(23, "potatoes"),
                                  new Product(14, "orange"),
                                  new Product(13, "lemon"),
                                  new Product(23, "bread"),
                                  new Product(13, "sugar"));
```

- Collectors.toList()  
    스트림에서 작업한 결과를 담은 리스트로 반환한다. 다음 예제에서는 map 으로 각 요소의 이름을 가져온 후 `Collectors.toList` 를 이용해서 리스트로 결과를 가져온다.

    ```java
    List<String> collectorCollection = 
                    productList.stream()
                        .map(Product::getName)
                        .collect(Collectiors.toList());
    
    // [potatoes, orange, lemon, bread, sugar]
    ```

- Collectors.joining()  
    스트림에서 작업한 결과를 하나의 문자열로 이어 붙일 수 있다.

    ```java
    String listToString = 
                productList.stream()
                    .map(Product::getName)
                    .collect(Collectors.joining());

    // potatoesorangelemonbreadsugar
    ```

    `Collectors.joining`은 세 개의 인자를 받을 수 있다. 이를 이용하면 간단하게 문자열을 조합할 수 있다.  
    - `delimiter` : 각 요소 중간에 들어가 요소를 구분시켜주는 구분자
    - `prefix` : 결과 맨 앞에 붙는 문자
    - `suffix` : 결과 맨 뒤에 붙는 문자

    ```java
    String listToString =
                productList.stream()
                    .map(Product::getName)
                    .collect(Collectors.joining(", ", "<", ">"));

    // <potatoes, orange, lemon, bread, sugar>
    ```

- Collectors.averagingInt()  
    숫자 값(Integer value)의 평균(arithmetic mean)을 낸다.

    ```java
    Double averageAmount = 
                productList.stream()
                    .collect(Collectors.averagingInt(Product::getAmount));
    // 17.2
    ```

- Collectors.summingInt()  
    숫자값의 합(sum)을 낸다.

    ```java
    Integer summingAmount = 
                productList.stream()
                    .collect(Collectors.summingInt(Product::getAmount));
    // 86
    ```

    IntStream 으로 바꿔주는 `mapToInt` 메소드를 사용해서 좀 더 간단하게 표현할 수 있다.

    ```java
    Integer summingAmount =
                productList.stream()
                    .mapToInt(Product::getAmount)
                    .sum(); // 86
    ```

- Collectors.summarizingInt()  
    만약 합계와 평균 모두 필요하다면 스트림을 두 번 생성해야 되는가? 이런 정보를 한번에 얻을 수 있는 방법으로는 `summarizingInt` 메소드가 있다.

    ```java
    IntSummaryStatistics statistics = 
                            productList.stream()
                                .collect(Collectors.summarizingInt(Product::getAmount));
    ```

    이렇게 받아온 IntSummaryStatistics 객체에는 다음과 같은 정보가 담겨 있다.

    ```java
    IntSummaryStatistics {count=5, sum=86, min=13, average=17.200000, max=23}
    ```

    - 개수 getCount()
    - 합계 getSum()
    - 평균 getAverage()
    - 최소 getMin()
    - 최대 getMax()

    이를 이용하면 `collect`전에 이런 통계 작업을 위한 `map`을 호출할 필요가 없게된다. 위에서 살펴본 averaging, summing, summarizing 메소드는 각 기본 타입(int, long, double)별로 제공된다.

- Collectors.groupingBy()  
    특정 조건으로 요소들을 그룹지을 수 있다. 수량을 기준으로 그룹핑을하면 아래와 같다. 여기서 받는 인자는 함수형 인터페이스 Function이다.

    ```java
    Map<Integer, List<Product>> collectorMapOfLists =
        productList.stream()
            .collect(Collectors.groupingBy(Product::getAmount));
    ```

    결과는 Map 타입으로 나오는데, 같은 수량이면 리스트로 묶어서 보여준다.

    ```java
    {23=[Product{amount=23, name='potatoes'},
         Product{amount=23, name='bread'}],
     13=[Product{amount=13, name='lemon'},
         Product{amount=13, name='sugar'}],
     14=[Product{amount=14, name='orage'}]}
    ```

- Collectors.partitioningBy()  
    위의 `groupingBy` 함수형 인터페이스 Function을 이용해서 특정 값을 기준으로 스트림내 요소들을 묶었다면, `partitioningBy`은 함수형 인터페이스 Predicate를 받는다. Predicate는 인자를 받아서 boolean 값을 리턴한다.

    ```java
    Map<Boolean, List<Product>> mapPartitioned = 
        productList.stream()
            .collect(Collectors.partitioningBy(el -> el.getAmount() > 15));
    ```

    따라서 평가를 하는 함수를 통해서 스트림 내 요소들을 true 와 false 두 가지로 나눌 수 있다.

    ```java
    {false=[Product{amount=14, name='orage'},
            Product{amount=13, name='lemon'},
            Product{amount=13, name='sugar'}],
     true=[Product{amount=23, name='potatoes'},
           Product{amount=23, name='bread'}]}
    ```

- Collectors.collectingAndThen()  
    특정 타입으로 결과를 `collect`한 이후에 추가 작업이 필요한 경우에 사용할 수 있다. 이 메소드의 시그니쳐는 다음과 같다. `finisher`가 추가된 모양인데, 이 피니셔는 collect를 한 후에 실행할 작업을 의미한다.

    ```java
    public static<T,A,R,RR> Collector<T,A,RR> collectingAndThen(
            Collector<T,A,R> downstream,
            Function<R,RR> finisher) { ... }
    )
    ```

    다음 예제는 `Collectors.toSet`을 이용해서 결과를 Set으로 collect 한 후 수정불가한 Set으로 변환하는 작업을 추가로 실행하는 코드이다.

    ```java
    Set<Product> unmodifiableSet = 
        productList.stream()
            .collect(Collectors.collectingAndThen(Collectors.toSet(),
                                                  Collections::unmodifiableSet));
    ```

- Collector.of()  
    여러가지 상황에서 사용할 수 있는 메소드들을 살펴봤는데, 이 외에 필요한 로직이 있다면 직접 collector를 만들 수도 있다. accumulator 와 combiner 는 `reduce`에서 살펴본 내용과 동일하다.

    ```java
    public static<T, R> Collector<T, R, R> of(
        Supplier<R> supplier,   // new collector 생성
        BiConsumer<R, T> accumulator,   // 두 값을 가지고 계산
        BinaryOperator<R> combiner,     // 계산한 결과를 수집하는 함수
        Characteristics ... characteristics) { ... }
    ```

    위 내용을 코드로 보면 아래와 같다. 다음 코드에서는 collector를 하나 생성한다. 컬렉터를 생성하는 supplier에 linkedList의 생성자를 넘겨준다. 그리고 accumulator에는 리스트에 추가하는 add 메소드를 넘겨주고 있다. 따라서 이 컬렉터는 스트림의 각 요소에 대해서 LinkedList를 만들고 요소를 추가하게 된다. 마지막으로 combiner를 이용해 결과를 조합하는데, 생성된 리스트들을 하나의 리스트로 합치고 있다.

    ```java
    Collector<Product, ?, LinkedList<Product>> toLinkedList =
        Collector.of(LinkedList::new,
                     LinkedList::add,
                     (first, second) -> {
                        first.addAll(second);
                        return first;
                     });
    ```

    따라서 다음과 같이 collect 메소드에 우리가 만든 커스텀 컬렉터를 넘겨줄 수 있고, 결과가 담긴 LinkedList가 반환된다.

    ```java
    LinkedList<Product> linkedListOfPersons = 
        productList.stream()
            .collect(toLinkedList);
    ```

<br/>
<br/>

## 4-4. Matching <a id="4-4"></a>

매칭은 조건식 람다 Predicate를 받아서 해당 조건을 만족하는 요소가 있는지 체크한 결과를 리턴한다. 다음과 같은 세 가지 메소드가 있다.

- 하나라도 조건을 만족하는 요소가 있는지(anyMatch)
- 모두 조건을 만족하는지(allMatch)
- 모두 조건을 만족하지 않는지(noneMatch)

```java
boolean anyMatch(Predicate<? super T> predicate);
boolean allMatch(Predicate<? super T> predicate);
boolean noneMatch(Predicate<? super T> predicate);
```

간단한 예제입니다. 다음 매칭 결과는 모두 `true`이다.

```java
List<String> names = Arrays.asList("Eric", "Elena", "Java");

boolean anyMatch = names.stream()
    .anyMatch(name -> name.contains("a"));

boolean allMatch = names.stream()
    .allMatch(name -> name.length() > 3);

booelan noneMatch = names.stream()
    .noneMatch(name -> name.endsWith("s"));
```

<br/>
<br/>

## 4-5. Iterating <a id="4-5"></a>

`foreach`는 요소를 돌면서 실행되는 최종 작업이다. 보통 `System.out.println` 메소드를 넘겨서 결과를 출력할 때 사용하곤 한다. 앞서 살펴본 `peek`과는 중간 작업과 최종 작업의 차이가 있다.

```java
names.stream().forEach(System.out::println);
```

<br/>
<br/>
<br/>

> 출처 및 참고 : https://futurecreator.github.io/2018/08/26/java-8-streams/