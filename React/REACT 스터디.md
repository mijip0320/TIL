# REACT 스터디

## primereact 데이터 테이블 활용

#### 🍕각 행마다 버튼 클릭 시 해당 행의 정보 가지고 오기

```react
  const [testdata, setTestdata] = useState(''); 



//선택 버튼(각 칼럼마다 가져올 데이터 지정하는 부분)
  const rentalTemplate = (rowData) => {
    //setTestdata('');
    //setTestdata(rowData.modelName);
    return (
      //<Button type="button" className="p-button-rounded p-button-secondary p-button-sm" onClick={() => onClick2('displayBasic')}>선택</Button>
      <Button type="button" className="p-button-rounded p-button-secondary p-button-sm" onClick={() => getProduct(rowData)}>선택</Button>

      
    );
  }
    const getProduct = (product) => {
    setTestdata(product.modelName); //포인트! 원하는 데이터 넣기(json형태로 되어있음)
    dialogFuncMap['displayBasic'](true);
  }
    
 const dialogFuncMap = {
    'displayBasic': setDisplayBasic
  }
...

<Dialog header={testdata} visible={displayBasic} style={{ width: '50vw' }} footer={renderFooter('displayBasic')} onHide={() => onHide('displayBasic')}>
  
```

## 리액트 구조

> React 에서 Component 를 Presentational Component 와 Container Component 로 나누는 것은 재사용성과 유지보수성을 높이기 위함

component란? 상태관리, DOM 관리, 이벤트 관리 등 다양한 역할을 하는 중요한 개념

1. 프레젠테이션 컴포넌트
   1. 어떻게 보여지는 지를 책임진다.
   2. 내부에 Presentational Component 와 Container 컴포넌트 모두를 가질 수 있고, 대게 DOM 마크업 과 자체 스타일을 가진다.
   3. this.props.children 을 통해 다른 컴포넌트 를 포함 하게끔 만들 수 있다.
   4. 어플리케이션의 나머지 부분에 대해 아무런 의존성을 가지지 않는다. (예를 들면 Flux 의 Action 이나 Stroe) 즉, 단독적인 Component 가 된다.
   5. 데이터를 불러오거나 변경하는 작업은 Presentational Component 에서 작성하지 않는다.
   6. 데이터 및 데이터와 관련된 Callback 은 props 를 통해서 받는 작업만 한다.
   7. 상태(state) 는 UI 상태를 관리하기 위해서만 갖게된다.
   8. state, LifeCycle, 성능 최적화가 필요없는 경우라면 Functional Component 로 작성된다.
   9. ex: Page, Slidebar, Story, UserInfo, List, ..
2. 컨테이너 컴포넌트
   1. 어떻게 동작해야 할지를 책임진다.
   2. 내부에 Presentational Component 와 Container 컴포넌트 모두를 가질 수 있지만, 대게 전체를 감싸는 div를 제외하고 자체적인 DOM 마크업이나 스타일을 갖고 있지 않다.
   3. 데이터 및 데이터와 관련된 동작을 다른 Presentational Component 와 Container Component 에게 제공한다.
   4. Flux 의 Action 을 호출하는 작업을 Container Component 에서 작성하며, 이 Callback 들은 다른 Presentational Component 에게 넘겨준다.
   5. 주로 데이터 저장소로 활용되며 상태(state) 를 갖고 있는 경우가 많다.
   6. 직접 작성되기 보다는 HOC(Higher-Order Components) 로 부터 생성되는 경우가 많다.
   7. ex: UserPage, FollowersSlidebar, StoryContainer, FollowedUserList, ..



## 🎈state 값을 변경하고 렌더링 시키는 방법

위 코드에서 '남자 코트 추천'을 여자 코트 추천으로 변경하고 싶다.

```
function 제목바꾸기() {
  let newArray = 글제목;
  newArray[0] = '여자코트 추천';
  글제목변경( newArray );
}
```

 

지금 뭘 한거냐면..

0. 글제목이라는 state는 직접 수정할 수 없다. 그래서.. 복사본을 만들기로 한다. 

1. 글제목이라는 state의 복사본을 만들어 **newArray**라는 변수에 저장한다. 

2. newArray의 0번째 데이터를 ‘여자코트 추천’으로 변경한다.

3. 그리고 그걸 글제목변경() 함수 안에 넣어서 글제목 state를 변경한다. 

 

**하지만 동작하지 않는다.** 

이유는 코드는 잘 짠건 맞는데, state를 복사할 때 문제가 하나 있기 때문이다. 

 

**원래 자바스크립트 내에서 array나 object 자료형은 = 등호로 복사하면 각각 별개의 자료형이 생성되는게 아니라**

**값을 공유한다.**

 

**즉, 글제목 array에 완전히 독립적인 복사본을 만들어 수정 이후 함수를 통해 수정해야한다.**

```
function 제목바꾸기() {
  var newArray = [...글제목];
  newArray[0] = '여자코트 추천';
  글제목변경( newArray );
}
```

**리액트에서 state를 수정하고 싶으면 보통 이런 패턴으로 코드를 작성한다.**

1. **수정하고 싶은 state의 deep/shallow 카피본을 하나 생성한다.** 
2. **카피본을 입맛에 맞게 수정한다.** 
3. **카피본을 state변경함수()에 Pramter로 동작시킨다.** 

출처: https://tried.tistory.com/48

