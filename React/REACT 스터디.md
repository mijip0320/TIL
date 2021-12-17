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
