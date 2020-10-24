# 자바스크립트 정리 2주차



## 나머지 매개변수와 전개 문법

- 나머지 매개변수: `...`(=나머지 매개변수들을 한데 모아 배열에 집어넣음), 비교적 최신 문법

  - ```javascript
    function sum(a, b) {
      return a + b;
    }
    
    alert( sum(1, 2, 3, 4, 5) );
    //함수를 정의할 땐 인수를 두 개만 받도록 하고, 실제 함수를 호출할 땐 이보다 더 많은 ‘여분의’ 인수를 전달했지만, 에러가 발생하지 않음. 다만 반환 값은 처음 두 개의 인수만을 사용해 계산됨
    ```

  - 여분의 매개변수는 그 값들을 담을 배열 이름을 마침표 세 개 `...`뒤에 붙여주면 함수 선언부에 포함시킬 수 있음 

  - ```javascript
    function sumAll(...args) { // args는 배열의 이름입니다.
      let sum = 0;
    
      for (let arg of args) sum += arg;
    
      return sum;
    }
    
    alert( sumAll(1) ); // 1
    alert( sumAll(1, 2) ); // 3
    alert( sumAll(1, 2, 3) ); // 6
    ```

  - ```javascript
    function showName(firstName, lastName, ...titles) { //...titles = Consul, Imperator
      alert( firstName + ' ' + lastName ); // Julius Caesar
    
      // 나머지 인수들은 배열 titles의 요소가 됩니다.
      // titles = ["Consul", "Imperator"]
      alert( titles[0] ); // Consul
      alert( titles[1] ); // Imperator
      alert( titles.length ); // 2
    }
    
    showName("Julius", "Caesar", "Consul", "Imperator");
    ```



- **'argruments'** 변수

  - `arguements`라는 특별한 유사 배열 객체(array-like object)를 이용하면 인덱스를 사용해 모든 인수에 접근할 수 있음

  - ```javascript
    function showName() {
      alert( arguments.length );
      alert( arguments[0] );
      alert( arguments[1] );
    
      // arguments는 이터러블 객체이기 때문에
      // for(let arg of arguments) alert(arg); 를 사용해 인수를 나열할 수 있습니다.
    }
    
    // 2, Julius, Caesar가 출력됨
    showName("Julius", "Caesar");
    
    // 1, Bora, undefined가 출력됨(두 번째 인수는 없음)
    showName("Bora");
    ```

  - 나머지 매개변수가 있기 이전 함수의 인수 전체를 얻어내는 방법이 `arguments`를 사용하는 것밖에 없었음. 지금도 사용 가능함

  - `arguments`는 유사 배열 객체이면서 이터러블(반복 가능한) 객체

  - **화살표 함수에는** `'arguments\'`**가 없음**: 화살표 함수에서 `arguments` 객체에 접근하면, 외부에 있는 ‘일반’ 함수의 arguments 객체를 가져옴

    - 화살표 함수에 `this`를 가지지 않는 것처럼 `arguments` 객체도 존재하지 않음




- **spread 문법**

  - ```javascript
    let arr = [3, 5, 1];
    
    alert( Math.max(arr) ); // NaN-->Math.max는 배열이 아닌 숫자 목록을 인수로 받기 때문
    
    alert( Math.max(3, 5, 1) ); // 5 -->제대로 동작하지만 배열 길이를 알 수 없을 때는 이마저도 불가능
    ```

  - *전개 문법(spread syntax)* 은 `...`를 사용하기 때문에 나머지 매개변수와 비슷해 보이지만, 나머지 매개변수와 **반대**의 역할

  - 함수를 호출할 때 `... arr`를 사용하면, 이터러블 객체 `arr`이 인수 목록으로 '확장’됨

  - ```javascript
    let arr = [3, 5, 1];
    
    alert( Math.max(...arr) ); // 5 (전개 문법이 배열을 인수 목록으로 바꿔줌.)
    ```

  - ```javascript
    let arr1 = [1, -2, 3, 4];
    let arr2 = [8, 3, -8, 1];
    
    alert( Math.max(...arr1, ...arr2) ); // 8
    ```

  - ```javascript
    let arr = [3, 5, 1];
    let arr2 = [8, 9, 15];
    
    let merged = [0, ...arr, 2, ...arr2];
    
    alert(merged); // 0,3,5,1,2,8,9,15 (0, arr, 2, arr2 순서로 합쳐짐.)
    ```

  - ```javascript
    let str = "Hello";
    
    alert( [...str] ); // H,e,l,l,o -->전개 문법을 사용해 문자열을 문자 배열로 변환
    ```

  - ```javascript
    let str = "Hello";
    
    // Array.from은 이터러블을 배열로 바꿔줌.
    alert( Array.from(str) ); // H,e,l,l,o
    ```

  - 

