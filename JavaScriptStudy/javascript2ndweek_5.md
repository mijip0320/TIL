# 자바스크립트 정리 2주차



## 'new Function' 문법

> 새로 만들어지는 함수

```javascript
//선언방법 new Function(함수 인수, 함수 본문)
let func = new Function ([arg1, arg2, ...argN], functionBody);
```



```javascript
let sum = new Function('a', 'b', 'return a + b');

alert( sum(1, 2) ); // 3
```

```javascript
let sayHi = new Function('alert("Hello")');

sayHi(); // Hello
```

- 기존에 사용하던 방법과 `new Function`을 사용해 함수를 만드는 방법의 가장 큰 차이는 **런타임에 받은 문자열을 사용해 함수를 만들 수 있다는 점**

  - 함수 표현식과 함수 선언문은 개발자가 직접 스크립트를 작성해야만 함수를 만들 수 있었음

  - 그러나 `new Function`이라는 문법을 사용하면 어떤 문자열도 함수로 바꿀 수 있음

  - 서버에서 전달받은 문자열을 이용해 새로운 함수를 만들고 이를 실행하는 것도 가능

  - ```javascript
    let str = ... 서버에서 동적으로 전달받은 문자열(코드 형태) ...
    
    let func = new Function(str);
    func();
    ```



- [클로저 - 렉시컬 환경](https://ko.javascript.info/new-function)





## setTimeout과 setInterval을 이용한 호출 스케쥴링

> 호출 스케쥴링: 일정 시간이 지난 후에 원하는 함수를 예약 실행(호출)할 수 있게 하는 것

- `setTimeout`을 이용해 일정 시간이 지난 후에 함수를 실행하는 방법

  - ```javascript
    let timerId = setTimeout(func|code, [delay], [arg1], [arg2], ...)
    ```

  - `func|code`: 실행하고자 하는 코드로, 함수 또는 문자열 형태, 이 자리에 함수가 대부분 들어감

  - `delay`: 실행 전 대기 시간으로, 단위는 밀리초(millisecond, 1000밀리초 = 1초)이며 기본값은 0

  - ``arg1`**,** `arg2`...: 함수에 전달할 인수

  - ```javascript
    function sayHi() {
      alert('안녕하세요.');
    }
    
    setTimeout(sayHi, 1000);//1초 후에 sayHi()가 호출
    //함수를 넘길때 함수 뒤에 () 붙이면 실행 x, sayHi()를 인수로 전달하면 함수 실행 결과가 전달되어 버림
    //sayHi()엔 반환문이 없어서 호출 결과는 undefined
    ```

  - ```javascript
    function sayHi(who, phrase) {
      alert( who + ' 님, ' + phrase );
    }
    
    setTimeout(sayHi, 1000, "홍길동", "안녕하세요."); // 홍길동 님, 안녕하세요.
    ```

  - ```javascript
    let timerId = setTimeout(...);
    clearTimeout(timerId); //스케쥴링 취소
    ```

  - 

- `setInterval`을 이용해 일정 시간 간격을 두고 함수를 실행하는 방법

  - ```javascript
    let timerId = setInterval(func|code, [delay], [arg1], [arg2], ...)
    ```

  - ```javascript
    // 2초 간격으로 메시지를 보여줌
    let timerId = setInterval(() => alert('째깍'), 2000);
    
    //함수 호출을 중단하려면 clearInterval(timerId)을 사용
    // 5초 후에 정지
    setTimeout(() => { clearInterval(timerId); alert('정지'); }, 5000);
    ```

  - 



- [**중첩 setTimeout**](https://ko.javascript.info/settimeout-setinterval): 중첩 `setTimeout`을 이용하는 방법은 `setInterval`을 사용하는 방법보다 유연, 호출 결과에 따라 다음 호출을 원하는 방식으로 조정해 스케줄링 할 수 있기 때문

