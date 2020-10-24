# 자바스크립트 정리 2주차



## 변수의 유효범위와 클로저

- **코드 블록**

  - ```javascript
    {
      // 지역 변수에 몇 가지 조작을 하면, 그 결과를 밖에선 볼 수 없습니다.
    
      let message = "Hello"; // 블록 내에서만 변숫값을 얻을 수 있습니다.
    
      alert(message); // Hello
    }
    
    alert(message); // ReferenceError: message is not defined
    //코드 블록 {...} 안에서 선언한 변수는 블록 안에서만 사용할 수 있음
    ```

  - ```javascript
    {
      // 메시지 출력
      let message = "안녕하세요.";
      alert(message);
    }
    
    {
      // 또 다른 메시지 출력
      let message = "안녕히 가세요.";
      alert(message);
    }
    
    //블록이 없으면 에러 발생
    ```

  - ```javascript
    //if, for, while 등에서도 마찬가지로 {...} 안에서 선언한 변수는 오직 블록 안에서만 접근 가능
    if (true) {
      let phrase = "안녕하세요!";
    
      alert(phrase); // 안녕하세요!
    }
    
    alert(phrase); // ReferenceError: phrase is not defined
    ```

  - 



- **중첩 함수**
  - 함수 내부에서 선언한 함수는 *‘중첩(nested)’* 함수라고 부름

  - ```javascript
    function sayHiBye(firstName, lastName) {
    
      // 헬퍼(helper) 중첩 함수
      function getFullName() {
        return firstName + " " + lastName;
      }
    
      alert( "Hello, " + getFullName() );
      alert( "Bye, " + getFullName() );
    
    }
    
    //새로운 객체의 프로퍼티 형태로나 중첩 함수 그 자체로 반환
    //반환된 함수는 어디서든 호출해 사용할 수 있음
    ```



- [렉시컬 환경, 가비지 컬렉션](https://ko.javascript.info/closure)
- **클로저**: 외부 변수를 기억하고 이 외부 변수에 접근할 수 있는 함수를 의미
  - 자바스크립트에선 모든 함수가 자연스럽게 클로저가 됨
  - 자바스크립트의 함수는 숨김 프로퍼티인 `[[Environment]]`를 이용해 자신이 어디서 만들어졌는지를 기억
  - 함수 내부의 코드는 `[[Environment]]`를 사용해 외부 변수에 접근