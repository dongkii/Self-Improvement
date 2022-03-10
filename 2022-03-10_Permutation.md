# Permutation [JAVA]

## **목차**
- [1. 순열이란?](#1)
- [2. Swap을 이용한 순열](#2)
- [3. Visited 배열을 이용한 순열](#3)

## 1. 순열이란?<a id="1"></a>  
> 순열이란 `n` 개의 값 중에서 `r` 개의 숫자를 모든 순서대로 뽑는 경우를 말한다.

예를 들어 `[1, 2, 3]` 이라는 세개의 원소를 가진 배열에서 2개의 숫자를 뽑는 경우는

```
[1, 2]
[1, 3]
[2, 1]
[2, 3]
[3, 1]
[3, 2]
```

위와 같이 6개의 경우이다.

<br/>

## 2. Swap을 이용한 순열<a id="2"></a>  
첫번 째는 `Swap` 함수를 만들어서 배열들의 값을 직접 바꾸는 방법이다. 

배열의 첫 값부터 순서대로 하나씩 바꾸며 모든 값을 한번씩 `swap` 한다.

`depth` 를 기준 인덱스로 하여 `depth` 보다 인덱스가 작은 값들은 그대로 고정하고  

`depth` 보다 인덱스가 큰 값들만 가지고 다시 `swap` 을 진행한다.

![swap을 이용한 순열](./img/2022_03_10/swap을_이용한_순열.png)

간단하고 코드도 깔끔하게 나오지만 순열들의 순서가 보장되지 않는다.

예를 들어 3개의 숫자 중 3개의 값을 뽑는 순열을 만들 때

```
[3, 1, 2]
[3, 2, 1]
```

위 순서대로 나와야 하는데

```
[3, 2, 1]
[3, 1, 2]
```

위처럼 나오게 된다.

## Java Source
```java
// 순서 없이 n 개 중에서 r개를 뽑는 경우
// 사용 예시 : permutation(arr, 0, n, 4);
static void permutation(int[] arr, int depth, int n, int r) {
    if(depth == r) {
        print(arr, r);
        return;
    }

    for(int i = depth; i < n; i++) {
        swap(arr, depth, i);
        permutation(arr, depth + 1, n, r);
        swap(arr, depth, i);
    }
}

static void swap(int[] arr, int depth, int i) {
    int temp = arr[depth];
    arr[depth] = arr[i];
    arr[i] = temp;
}
```

## Result
```java
1 2 3
1 3 2
2 1 3
2 3 1
3 2 1
3 1 2
```

<br/>

## 3. Visited 배열을 이용한 순열<a id="3"></a>  
두번째로는 `visited` 배열을 이용하는 방법이다.  

1번과 달리 사전식으로 순열을 구현할 수 있다.  

|변수|설명|
|---|---|
|`arr`|`r` 개를 뽑기 위한 `n` 개의 값|
|`output`|뽑힌 `r` 개의 값|
|`visited`|중복해서 뽑지 않기 위해 체크하는 값|

위 3개의 값이 포인트이다.

DFS를 돌면서 모든 인덱스를 방문하여 `output` 에 값을 넣는다.

이미 들어간 값은 `visited` 값을 `true` 로 바꾸어 중복하여 넣지 않도록 한다.

`depth` 값은 `output` 에 들어간 숫자의 길이라고 생각하면 된다.

`depth` 의 값이 `r` 만큼 되면 `output` 에 들어있는 값을 출력하면 된다.

![visited_배열을_이용한_순열](./img/2022_03_10/visited_배열을_이용한_순열.png)

## Java Source
```java
// 순서를 지키면서 n 개 중에서 r 개를 뽑는 경우
// 사용 예시 : perm(arr, output, visited, 0, n , 3);
static void perm(int[] arr, int[] output, boolean[] visited, int depth, int n, int r) {
    if(depth == r) {
        print(output, r);
        return;
    }

    for(int i = 0; i < n; i++) {
        if(visited[i] != true) {
            visited[i] = true;
            output[depth] = arr[i];
            perm(arr, output, visited, depth + 1, n , r);
            output[depth] = 0;
            visited[i] = false;
        }
    }
}
```

## Result
```
1 2 3
1 3 2
2 1 3
2 3 1
3 1 2
3 2 1
```

<br/>
<br/>

> 출처 및 참고 : https://bcp0109.tistory.com/14