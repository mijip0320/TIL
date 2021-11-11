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



