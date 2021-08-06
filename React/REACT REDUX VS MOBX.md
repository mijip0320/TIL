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