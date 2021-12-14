# REACT REDUX VS MOBX

MOBX:

- state관리 라이브러리, 객체지향 느낌이 강함
- Component와 State를 연결하는**(Redux와 달리)** 번잡한 보일러플레이트 코드들을 데코레이터(애노테이션)제공으로 깔끔하게 해결
- 관찰 대상을 observable 데코레이터로 정해서 state값이 변경될때 마다 렌더링 진행
- 객체지향적이기 때문에 스프링 프레임워크 사용 시 적합
- **Mobx Configuration 설정**으로 **State를 오직 메소드**를 통하여 변경할 수 있도록 **Private**하게 관리 할 수 있음
- 데이터를 AJAX로 가져옴

```react
class RiderRepository {
  URL = "/v1/api/riders";

  constructor(url) {
    this.URL = url || this.URL;
  }

  findAll(params) {
    return axios.get(`$`, );
  }

  findOne(riderAccountId) {
    return axios.get(`$/$`);
  }
}

// 싱글톤으로 리턴 (매번 새로운 객체를 생성 할 필요가 없다면 처음 부터 싱글톤으로 export)
export default new RiderRepository();
```



REDUX:

- reducer - 실제 비즈니스 로직 담당
- Redux의 경우 ACTION 타입을 정의하고, ACTION을 생성하는 행위들이 추가로 들어가야 된다는 차이가 있음







![mobx-redux-layer-table](https://techblog.woowahan.com/wp-content/uploads/img/2019-01-02/mobx-redux-layer.png)

출처: https://techblog.woowahan.com/2599/



## REDUX

1. 하나의 애플리케이션 안에는 하나의 스토어가 존재

2. 상태는 읽기 전용

   1. state 업데이트 시 setState를 사용하고, 배열을 업데이트 해야 할 때는 배열 자체에 push를 직접하지 않고, concat같은 함수를 사용하여 **기존의 배열을 수정하지 않고 새로운 배열을 만들어서 교체하는 방식으로** 업데이트 진행(Object.assign이나 spread 연산자(`...`)를 사용해서 업데이트)

   2. spread 연산자?

      1. 배열의 복제

      ```react
      var array = [1,2,3,4,5];
      var newArray = [];
      for(var i=0; i<array.length; i++){
      	newArray[i] = array[i];
      }
      
      //위 로직을 아래 spread연산자를 이용해 복제 표현 가능
      const newArray = [...array];
      ```

      2. 배열 병합

      ```react
      const arrayA = [1,2,3];
      const arrayB = [4,5,6];
      
      const arrayC = [...arrayA, ...arrayB];
      console.log(arrayC);//[1,2,3,4,5,6];
      ```

      3. 객체에서도 동일

      ```react
      const objA= {a:1, b:2};
      const objB = {...objA};
      
      console.log(objB);// {a:1, b:2}
      ```

      	4. 특정 값만 변경

      ```react
      //주로 리액트에서 state 관리할 때 불변성을 유지하며 객체 중 일부만 update를 해야할 때 사용
      const objA = {a:1, b:2, c:3};
      const objB = {...objA, b:4}; //objA 복제 후, b 속성만 덮어씀
      console.log(objB); //{a:1, b:4, c:3}
      ```

      ```react
      const object = {
          a: {
              b: {
                  target:123,
                  c:"cc"
              },
              d:"dd"
          },
          e:"ee"
      }
      
      //a,b,c,d,e는 그대로 두고 target만 변경
      const newObject = {
          ...object,
          a:{
              ...object.a,
              b:{
                  ...object.a.b,
                  target:456
              }
          }
      };
      ```

   3.  변화를 일으키는 함수 리듀서는 순수한 함수여야 함

      1. 순수한 함수란? 
         1. 리듀서 함수는 이전상태와 액션 객체를 파라미터로 받음
         2. 이전 상태는 절대 건들이지 않고 변화를 일으킨 새로운 상태 객체를 만들어서 반환
         3. 똑같은 파라미터로 호출된 리듀서 함수는 **언제나** 똑같은 결과값을 반환
      2. 순수한 함수 x?
         1. new Date() 사용, 랜던숫자 생성, 네트워크 요청...
         2. 이럴 때 리덕스 미들웨어 사용
      3. 

