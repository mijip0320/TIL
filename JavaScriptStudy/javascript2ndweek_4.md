# 자바스크립트 정리 2주차



## 전역 객체

- 브라우저 환경에선 전역 객체를 `window`

- ```javascript
  alert("Hello");
  // 위와 동일하게 동작합니다.
  window.alert("Hello");
  ```
  
- 브라우저에서 `let`이나 `const`가 아닌 `var`로 선언한 전역 함수나 전역 변수는 전역 객체의 프로퍼티가 됨(let으로 선언 시 undefined 출력)

- ```javascript
  //중요한 변수라서 모든 곳에서 사용할 수 있게 하려면, 아래와 같이 전역 객체에 직접 프로퍼티를 추가
  // 모든 스크립트에서 현재 사용자(current user)에 접근할 수 있게 이를 전역 객체에 추가함
  window.currentUser = {
    name: "John"
  };
  
  // 아래와 같은 방법으로 모든 스크립트에서 currentUser에 접근할 수 있음
  alert(currentUser.name);  // John
  
  // 지역 변수 'currentUser'가 있다면
  // 지역 변수와 충돌 없이 전역 객체 window에서 이를 명시적으로 가져올 수 있음
  alert(window.currentUser.name); // John
  ```



## 객체로서의 함수와 기명 함수 표현식

- **'name'** property: 함수 이름을 가져올 수 있음

  - ```javascript
    let sayHi = function() {
      alert("Hi");
    };
    
    alert(sayHi.name); // sayHi (익명 함수이지만 이름이 있네요!)
    ```

- **'length'** property: 함수 매개변수의 개수를 반환

  - ```javascript
    function f1(a) {}
    function f2(a, b) {}
    function many(a, b, ...more) {}
    
    alert(f1.length); // 1
    alert(f2.length); // 2
    alert(many.length); // 2
    ```

- **custom** property: 함수에 자체적으로 만든 프로퍼티를 추가

  - ```javascript
    function sayHi() {
      alert("Hi");
    
      // 함수를 몇 번 호출했는지 
      sayHi.counter++;
    }
    sayHi.counter = 0; // 초깃값
    
    sayHi(); // Hi
    sayHi(); // Hi
    
    alert( `호출 횟수: ${sayHi.counter}회` ); // 호출 횟수: 2회
    
    //property != 변수
    //`sayHi.counter = 0`와 같이 함수에 프로퍼티를 할당해도 함수 내에 지역변수 `counter`가 만들어지지 않음. `counter` 프로퍼티와 변수 `let counter`는 전혀 관계가 없음.
    ```



- **기명 함수 표현식**: 기명 함수 표현식(Named Function Expression, NFE)은 이름이 있는 함수 표현식을 나타내는 용어

  - ```javascript
    //일반함수
    let sayHi = function(who) {
      alert(`Hello, ${who}`);
    };
    ```

  - ```javascript
    //함수에 func이라는 이름이 붙음
    let sayHi = function func(who) {
      alert(`Hello, ${who}`);
    };
    
    //이름을 붙이게 되면:
    //1. 이름을 사용해 함수 표현식 내부에서 자기 자신을 참조할 수 있음.
    //2. 기명 함수 표현식 외부에선 그 이름을 사용할 수 없음.
    ```

  - ```javascript
    let sayHi = function func(who) {
      if (who) {
        alert(`Hello, ${who}`);
      } else {
        func("Guest"); // func를 사용해서 자신을 호출.
        //sayHi를 호출하지 않고 func을 사용한 이유: 외부 코드에 의해 sayHi가 변경될 수 있다는 문제가 생김, 함수 지역 수준(function-local)에 존재
      }
    };
    
    sayHi(); // Hello, Guest
    
    // 하지만 아래와 같이 func를 호출하는 건 불가능.
    func(); // Error, func is not defined (기명 함수 표현식 밖에서는 그 이름에 -접근할 수 없습니다.)
    ```
    
  - ```javascript
    let sayHi = function func(who) {
      if (who) {
        alert(`Hello, ${who}`);
      } else {
        func("Guest"); 
      }
    };
    
    let welcome = sayHi;
    sayHi = null;
    
    welcome(); // Hello, Guest (중첩 호출이 제대로 동작함)
    ```
  
  - 
  

