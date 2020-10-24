# 자바스크립트 정리 2주차



## 재귀

> 재귀: 큰 목표 작업 하나를 동일하면서 간단한 작업 여러 개로 나눌 수 있을 때 유용한 프로그래밍 패턴
>
>   - 함수가 *자기 자신*을 호출

- 두 가지 ver.

  ```javascript
  //for-loop 사용
  function pow(x, n) {
    let result = 1;
  
    // 반복문을 돌면서 x를 n번 곱함
    for (let i = 0; i < n; i++) {
      result *= x;
    }
  
    return result;
  }
  
  alert( pow(2, 3) ); // 8
  ```

  ```javascript
  //재귀 사용
  function pow(x, n) {
    if (n == 1) {
      return x;
    } else {
      return x * pow(x, n - 1);
      //1. pow(2,3) = 2*pow(2,2) --> 서브 호출인 pow(2,2)을 만들어 들어감
      //2. pow(2,2) = 2*pow(2,1); --> 서브 호출인 pow(2,1)을 만들어 들어감
      //3. pow(2,1) = 2 -->if문의 조건을 만족, 더 호출해야 할 중첩 호출이 없어 해당 실행 컨텍스트는 메모리에서 삭제(a), 스택 맨 위엔 이전의 실행 컨텍스가 위치(b가 올라옴)
        
        //실행 컨텍스트 스택:
  //    a.Context: { x: 2, n: 1, 첫 번째 줄 } call: pow(2, 1)
  //	  b.Context: { x: 2, n: 2, 다섯 번째 줄 } call: pow(2, 2)
  //	  c.Context: { x: 2, n: 3, 다섯 번째 줄 } call: pow(2, 3)
    }
  }
  
  alert( pow(2, 3) ); // 8
  ```



- 자바스크립트 엔진은 최대 재귀 깊이를 제한. 

  -  이런 제한을 완화하려고 엔진 내부에서 자동으로 'tail calls optimization’라는 최적화를 수행하긴 하지만, 모든 곳에 적용되는 것은 아니고 간단한 경우에만 적용
  - 재귀 깊이 제한 때문에 재귀를 실제 적용하는데 제약이 있긴 하지만, 재귀는 여전히 광범위하게 사용됨
  -  재귀를 사용하면, 간결하고 유지보수가 쉬운 코드를 만들 수 있기 때문
  - **재귀를 이용해 작성한 코드는 반복문을 사용한 코드로 다시 작성할 수 있음**
  - **반복문을 사용하면 대개 함수 호출의 비용(메모리 사용)이 절약**

  

- **실행 컨텍스트**: 함수 실행에 대한 세부 정보를 담고 있는 내부 데이터 구조

  - 제어 흐름의 현재 위치, 변수의 현재 값, `this`의 값 등 상세 내부 정보가 실행 컨텍스트에 저장
  - 함수 1호출: 1실행 컨텍스트 생성
  - 함수 내부 중첩 호출이 있을 때:
    1. 현재 함수의 실행이 일시 중지
    2. 중지된 함수와 연관된 실행 컨텍스트는 *실행 컨텍스트 스택(execution context stack)* 이라는 특별한 자료 구조에 저장
    3. 중첩 호출이 실행
    4. 중첩 호출 실행이 끝난 이후 실행 컨텍스트 스택에서 일시 중단한 함수의 실행 컨텍스트를 꺼내오고, 중단한 함수의 실행을 다시 이어감



* **재귀적 순회**:

  * ```javascript
    let company = {
      sales: [{
        name: 'John',
        salary: 1000
      }, {
        name: 'Alice',
        salary: 1600
      }],
    
      development: {
        sites: [{
          name: 'Peter',
          salary: 2000
        }, {
          name: 'Alex',
          salary: 1800
        }],
    
        internals: [{
          name: 'Jack',
          salary: 1300
        }]
      }
    };
    
    //임직원의 급여를 더한 값을 구해야 함
    //순회방법: company를 대상으로 동작하는 for 반복문을 만들고 한 단계 아래의 부서에 중첩 반복문를 돌리는 것 -->이렇게 하면 sites 같은 두 단계 아래의 부서에 속한 임직원의 급여를 뽑아낼 때 또 다른 중첩 반복문이 필요
    ```

  * ```javascript
    let company = { // 동일한 객체(간결성을 위해 약간 압축함)
      sales: [{name: 'John', salary: 1000}, {name: 'Alice', salary: 1600 }],
      development: {
        sites: [{name: 'Peter', salary: 2000}, {name: 'Alex', salary: 1800 }],
        internals: [{name: 'Jack', salary: 1300}]
      }
    };
    
    // 급여 합계를 구해주는 함수
    function sumSalaries(department) {
      if (Array.isArray(department)) { // 첫 번째 경우
        return department.reduce((prev, current) => prev + current.salary, 0); // 배열의 요소를 합함
      } else { // 두 번째 경우
        let sum = 0;
        for (let subdep of Object.values(department)) { //Object.values는 프로퍼티의 값이 담긴 배열을 반환
          sum += sumSalaries(subdep); // 재귀 호출로 각 하위 부서 임직원의 급여 총합을 구함
        }
        return sum;
      }
    }
    
    alert(sumSalaries(company)); // 7700
    ```



- **재귀적 구조** 
  - 재귀적 자료 구조는 자기 자신의 일부를 복제하는 형태의 자료 구조 -->다양한 곳에서 재귀적으로 정의된 자료구조가 쓰임(연결리스트)