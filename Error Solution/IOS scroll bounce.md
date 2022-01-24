> ## IOS scroll bounce

IOS 사파리 브라우저에서 스크롤 했을 때 윗부분 혹은 아래부분이 div 영역보다 더 많이 스크롤 되면서 Scroll 펑션이 두번 실행되어 scroll 이벤트가 정상적으로 실행되지 않는 현상이 발생한다.

예를들면 스크롤이 아래로 내려갈 때는 Header 부분을 숨겼다가, 올라올때 보여지게 한다면,
IOS에서 스크롤을 내리는경우 div 범위 바깥으로 나갔다가 자동으로 div 최하단에 맞춰지면서 스스로 올라오는 경우가 발생한다.   
이때 scroll 이벤트는 스크롤이 올라오는것으로 판단하여 Header 부분이 보여지게 된다.

이를 해결하기위해 다음과 같은 방법을 사용했다.

### **`1. css로 처리`**
    ```css
    html, body {
        position: fixed;
        width: 100%;
        overflow: hidden;
        transform: translate3d(0, 0, 0);
        -webkit-transform: translateZ(0);
    }
    ```
- 크롬브라우저 모바일모드에선 스크롤이 적용되지 않는 문제가 있음
---

### **`2. css로 처리2`**
```css
#swipe { -webkit-overflow-scrolling: touch;}
```
```javascript
function preventDefault(event){
    event.preventDefault();
}

document.addEventListener("touchmove", preventDefault, false);
document.removeEventListener("touchmove", preventDefault, false);
```
- 크롬브라우저 모바일모드에선 스크롤이 이상없으나, IOS에선 바운스현상 그대로

---

### **`3. 스크립트로 처리`**
scrollTop 최대치보다 커질경우 스크롤 이벤트가 동작하지 않도록 처리