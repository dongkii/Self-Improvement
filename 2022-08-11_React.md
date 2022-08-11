# React

![React](./img/2022_08_11/react.png)

## **목차**
- [1. React란](#1)
- [2. React의 필요성](#2)
- [3. React의 특징](#3)

## **1. Reack란?** <a id="1"></a>
> `React`는 웹 프레임워크로, 자바스크립트 라이브러리의 하나로서 사용자 인터페이스를 만들기 위해 사용된다.  
> <br/>
> 출처 : <a href="https://ko.wikipedia.org/wiki/%EB%A6%AC%EC%95%A1%ED%8A%B8_(%EC%9E%90%EB%B0%94%EC%8A%A4%ED%81%AC%EB%A6%BD%ED%8A%B8_%EB%9D%BC%EC%9D%B4%EB%B8%8C%EB%9F%AC%EB%A6%AC)">위키백과</a>

- `React`는 facebook에서 제공해주는 프론트엔드 라이브러리라고 볼 수 있다.
- 싱글 페이지 애플리케이션이나 모바일 애플리케이션의 개발 시 토대로 사용될 수 있다.

즉, 현재 많이 활용되고 있는 웹/앱의 View를 개발할 수 있도록 하는 인기있는 라이브러리라고 볼 수 있다.

<br/>

## **2. React의 필요성** <a id="2"></a>
react를 사용하지 않아도, html과 css, javascript를 이용해서 웹페이지를 만들 수 있지만, react를 이용해 사용자와 상호작용할 수 있는 동적인 UI를 쉽게 만들 수 있기 때문에 많이 이용한다.

<br/>

## **3. React의 특징** <a id="3"></a>
### **`1. Data Flow`**
React는 데이터의 흐름이 한 방향으로만 흐르는 단방향 데이터 흐름을 가진다.  
<br/>
Augular.js와 같은 양방향 데이터 바인딩은 규모가 커질수록(대규모 애플리케이션의 경우) 데이터의 흐름을 추적하기가 힘들고 복잡해지는 경향이 있어, 복잡한 앱에서도 데이터 흐름에서 일어나는 변화를 보다 예측 가능할 수 있도록 단방향 흐름을 가지도록 
했다고 한다.

<br/>

### **`2. Component 기반 구조`**
> Component는 독립적인 단위의 소프트웨어 모듈을 말한다.  
> 즉, 소프트웨어를 독립적인 하나의 부품으로 만드는 방법이라고 볼 수 있다.

React는 UI(View)를 여러 컴포넌트(component)를 쪼개서 만든다.  
한 페이지 내에서도 여러 각 부분을 독립된 컴포넌트로 만들고, 이 컴포넌트를 조립해 화면을 구성한다.  

컴포넌트 단위로 쪼개져 있기 때문에, 전체 코드를 파악하기가 상대적으로 쉽다. 이렇게 기능 단위, UI 단위로 캡슐화 시켜서 코드를 관리하기 때문에 재사용성이 높다. 따라서 코드는 반복해 입력할 필요 없이, 컴포넌트만 import해 사용하면 된다는 간편함이 있으며, 애플리케이션이 복잡해지더라도 코드의 유지보수, 관리가 용이해지는 장점을 가진다.

```javascript
class App extends Component{
    render() {
        return (
            <Layout>
                <Header />
                <Navigation />
                <Content>
                    <Sidebar></Sidebar>
                    <Router />
                </Content>
                <Footer></Footer>
            </Layout>
        );
    }
}
```
위와 같이 Header, Footer 같은 구조 등을 컴포넌트로 만들고, 이를 조합해서 root component를 만드는 방식이다.

<br/>

### **`3. Virtual DOM`**
먼저, DOM은 Document Object Model의 약자이다.  
DOM은 html, xml, CSS 등을 트리 구조로 인식하고, 데이터를 객체로 간주하고 관리한다.

React는 이 DOM Tree 구조와 같은 구조체를 Virtual DOM으로 가지고 있다.

> Virtual DOM은 가상의 Document Object Model을 말한다.

이벤트가 발생할 때마다 Virtual Dom을 만들고, 다시 그릴 때마다 실제 DOM과 비교하고 전후 상태를 비교해, 변경이 필요한 최소한의 변경사항만 실제 DOM에 반영해, 앱의 효율성과 속도를 개선할 수 있다고 한다.

<br/>

### **`4. Props and State`**
- Props  
    Props란 부모 컴포넌트에서 자식 컴포넌트로 전달해 주는 데이터를 말한다.  
    쉽게 읽기 전용 데이터라고 생각하면 된다. 자식 컴포넌트에서 전달받은 props는 변경이 불가능하고, props를 전달해준 최상위 부모 컴포넌트만 props를 변경할 수 있다.

- State
    State는 컴포넌트 내부에서 선언하며 내부에서 값을 변경할 수 있다. state는 동적인 데이터를 다룰 때 사용하며, 사용자와의 상호작용을 통해 데이터를 동적으로 변경할 때 사용한다. 클래스형 컴포넌트에서만 사용할 수 있고, 각각의 state는 독립적이다.

<br/>

### **`JSX`**
React에서 JSX 사용이 필수는 아니지만, React에서 사용되기 때문에 공식문서의 설명을 첨부해보았다

```html
const element = <h1>Hello, world!</h1>
```

위와 같은 변수도 JSX문법이다.

> JSX란 ?  
> Javascript를 확장한 문법이다.
> <br/>
> 
> React에서는 이벤트가 처리되는 방식, 시간에 따라 state가 변하는 방식, 화면에 표시하기 위해 데이터가 준비되는 방식 등 렌더링 로직이 본질적으로 다른 UI 로직과 연결된다는 사실을 받아들인다.  
>
> React는 별도의 파일에 마크업과 로직을 넣어 기술을 인위적으로 분리하는 대신, 둘 다 포함하는 "컴포넌트"라고 부르는 느슨하게 연결된 유닛으로 관심사를 분리한다. 이후 섹션에서는 다시 컴포넌트로 돌아오겠지만, JS에 마크업을 넣는 게 익숙해지지 않는다면 이 이야기가 확신을 줄 것이다.  
>
> React는 JSX 사용이 필수가 아니지만, 대부분의 사람은 JavaScript 코드 안에서 UI 관련 작업을 할 때 시각적으로 더 도움이 된다고 생각한다. 또한 React가 더욱 도움이 되는 에러 및 경고 메시지를 표시할 수 있게 해준다.  
> <a href="https://ko.reactjs.org/docs/introducing-jsx.html">React 공식 문서</a>

<br/>
<br/>

> 출처 및 참고 : https://velog.io/@jini_eun/React-React.js%EB%9E%80-%EA%B0%84%EB%8B%A8-%EC%A0%95%EB%A6%AC