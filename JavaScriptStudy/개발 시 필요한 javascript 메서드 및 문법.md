# 개발 시 필요한 javascript 메서드 및 문법



### 🍕해당 태그의 id값 가져오기

```javascript
변수 = document.getElementById("설정한 id값")
```



### 🍕해당 태그의 value값 가져오기

```javascript
아이디.value
```



### 🍕해당 태그의 value의 타입 가져오기

```javascript
typeof(id값)
```



### 🍕Null Check

```javascript
if( !아이디값 ){
	//해당 내용 작성
}
```



### 🍔select 태그에서 동적으로 추가할때 중복 체크

```javascript
var moduleversion = $(button).data('id'); //version 받아옴

        var resultString = modulename + "|" + moduleversion;

        //var results = [];
        //results.push(resultString);

        var thevalue = resultString;
        //var exists = 0 != $('#selectDependency option[value=' + thevalue + ']').length;

        //중복제거
        if ($('#selectDependency option').length == 0) {
            $('#selectDependency').append('<option value="' + thevalue + '">' + thevalue + '</option>');
        } else {
            var exists = false;
            $('#selectDependency option').each(function () {
                if ($(this).text() == thevalue) {
                    exists = true;
                    alert("해당 모듈은 이미 추가되었습니다");
                    return false;
                }
            });

           //중복이 아니라면 추가됨
            if (exists == false) {
                $('#selectDependency').append('<option value="' + thevalue + '">' + thevalue + '</option>');

            }

        }
```

### 🍳select 태그에서 동적으로 제거

```javascript
 var chosen = $("#selectDependency").val(); //선택된 항목
        $("#selectDependency option[value= '"+chosen+"']").remove(); //항목 제거

        
```

