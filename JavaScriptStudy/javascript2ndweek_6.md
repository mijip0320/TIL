# 자바스크립트 정리 2주차



## 함수 바인딩

- 객체 메서드가 객체 내부가 아닌 다른 곳에 전달되어 호출되면 `this`가 사라짐

- ```javascript
  let user = {
    firstName: "John",
    sayHi() {
      alert(`Hello, ${this.firstName}!`);
    }
  };
  
  setTimeout(user.sayHi, 1000); // Hello, undefined!
  //let f = user.sayHi; setTimeout(f, 1000); // user 컨텍스트를 잃어버림
  //setTimeout에 객체에서 분리된 함수인 user.sayHi가 전달되기 때문
  ```

- `setTimeout` 메서드는 인수로 전달받은 함수를 호출할 때, `this`에 `window`를 할당

- `this.firstName`은 `window.firstName`가 되는데, `window` 객체엔 `firstName`이 없으므로 `undefined`가 출력

- **방법1: 레퍼**: 

  - ```javascript
    let user = {
      firstName: "John",
      sayHi() {
        alert(`Hello, ${this.firstName}!`);
      }
    };
    
    setTimeout(() => user.sayHi(), 1000);
    
    // 1초가 지나기 전에 user의 값이 바뀜
    user = { sayHi() { alert("또 다른 사용자!"); } };
    
    // setTimeout에 또 다른 사용자!
    //하지만 setTimeout이 트리거 되기 전에(1초가 지나기 전에) user가 변경되면, 변경된 객체의 메서드를 호출
    ```

- **방법2: bind:**

  - ```javascript
    let boundFunc = func.bind(context);
    ```

  - `func.bind(context)`는 함수처럼 호출 가능한 '특수 객체(exotic object)'를 반환 

  - 이 객체를 호출하면 `this`가 `context`로 고정된 함수 `func`가 반환

  - 따라서 `boundFunc`를 호출하면 `this`가 고정된 `func`를 호출하는 것과 동일한 효과

  - ```javascript
    //funcUser에는 this가 user로 고정된 func이 할당
    let user = {
      firstName: "John"
    };
    
    function func() {
      alert(this.firstName);
    }
    
    let funcUser = func.bind(user);
    funcUser(); // John
    ```

  - ```javascript
    let user = {
      firstName: "John"
    };
    
    function func(phrase) {
      alert(phrase + ', ' + this.firstName);
    }
    
    // this를 user로 바인딩합니다.
    let funcUser = func.bind(user);
    
    funcUser("Hello"); // Hello, John (인수 "Hello"가 넘겨지고 this는 user로 고정됩니다.)
    ```



- [**부분 적용**, **컨텍스트 없는 부분적용**](https://ko.javascript.info/bind)



## [프로퍼티 플래그와 설명자](https://ko.javascript.info/property-descriptors)

> 객체 프로퍼티는 **`값(value)`** 과 함께 플래그(flag)라 불리는 특별한 속성 세 가지를 갖음
>
> 1. **`writable`** – `true`이면 값을 수정할 수 있음. 그렇지 않다면 읽기만 가능.
> 2. **`enumerable`** – `true`이면 반복문을 사용해 나열할 수 있음. 그렇지 않다면 반복문을 사용해 나열할 수 없음.
> 3. **`configurable`** – `true`이면 프로퍼티 삭제나 플래그 수정이 가능. 그렇지 않다면 프로퍼티 삭제와 플래그 수정이 불가능.





