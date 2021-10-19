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

//일반 추가
  $.ajax({
            type: "POST",
            url: '@Url.Action("customerList", "Search")',
            success: function (result) {
                var list = result.data;
                for (var i = 0; i < list.length; i++) {
                    $('#customer').append('<option value="' + list[i] + '">' + list[i] + '</option>');
                }
                if (customerInput != "") {
                    $("#customer option[value='" + customerInput + "']").attr('selected', true);
                }
            }, error: function (e) {
                alert("error");
            }
```

### 🍳select 태그에서 동적으로 제거

```javascript
 var chosen = $("#selectDependency").val(); //선택된 항목
        $("#selectDependency option[value= '"+chosen+"']").remove(); //항목 제거

        
```



### 🧀Button과 input type=button 차이

페이지를 코딩해서 링크를 걸 때 아무거나 <a> 링크로 만드는 것이 아니라 **용도에 맞게 사용하는 것이 중요**하다.

- **<a>** 페이지 링크를 걸 때 문서간의 url을 **'연결'**하기 위해 주로 사용되는 일반적인 방식
- **<button>** url을 연결하지않고 단순하게 사용자 인터페이스만을 **'조작'**하기 위한 버튼. type을 명시하지않으면 submit 으로 실행하기때문에 반드시 **type="button"으로 지정**해야한다.
- **<input>** 사용자의 입력을 서버로 '**전송'** 하기 위한 버튼으로 **전달하는값을 받거나 전달할 때** 사용하는 마크업

------

폼(form)을 전송 기능을 하는 <button>과 <input type="submit / nutton / reset" />요소와 **동일한 기능을 수행**한다.

기본적으로 button 요소는 type 속성을 명시하지 않으면 submit 기능을 수행하기때문에 알맞는 속성을 지정한다.

 

- **<button type="submit">** 폼(form)을 **전송하는 기능**을 담당 [default]
- **<button type="reset">** 폼(form) 입력한 **내용을 초기화**하는 기능
- **<button type="button">** 자바스크립트의 도움을 받아 **기능 구현 인터페이스를 조작**하는 기능 

디자인적인 관점에서 input 요소와 달리 button은 매우 자유롭다.

<button>사이에 다른 태그들을 삽입 가능하며 최근 라이브러리에서

button 요소들에 대한 꾸밈을 적용한 CSS를 배포로 간단히 클래스명을 이용해 적용할 수 있다.

출처: https://chlolisher.tistory.com/72

> 버튼으로 엔터/클릭시 화면이 자꾸 리프레시가 되면 e.preventDefault(); 추가!





 ### 🍚 다른 컨트롤러/페이지로 이동하고 싶을 때

```javascript
window.location.href = "./Search/SearchVersion?PatchVersion=" + 보낼 정보;
```



### 🥗 버튼 bootstrap

색깔별로 다양하게 줄 수 있음

```javascript
<button type="button" class="btn btn-primary">Primary</button>
<button type="button" class="btn btn-secondary">Secondary</button>
<button type="button" class="btn btn-success">Success</button>
<button type="button" class="btn btn-danger">Danger</button>
<button type="button" class="btn btn-warning">Warning</button>
<button type="button" class="btn btn-info">Info</button>
<button type="button" class="btn btn-light">Light</button>
<button type="button" class="btn btn-dark">Dark</button>

<button type="button" class="btn btn-link">Link</button>
```

크기

```javascript
<button type="button" class="btn btn-primary btn-lg">Large button</button>
<button type="button" class="btn btn-secondary btn-lg">Large button</button>

<button type="button" class="btn btn-primary btn-sm">Small button</button>
<button type="button" class="btn btn-secondary btn-sm">Small button</button>
```

출처: https://getbootstrap.com/docs/4.0/components/buttons/



### 🥘popover tooltip 예시

```html
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css" integrity="sha384-Zug+QiDoJOrZ5t4lssLdxGhVrurbmBWopoEl+M6BdEfwnCJZtKxi1KgxUyJq13dy" crossorigin="anonymous">

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.6/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
  <h3>Bootstrap 4 Table Demo</h2>
 
<table class="table table-hover">
  <thead>
    <tr>
      <th>Month</th>
      <th>Number of Sales</th>
      <th>Amount</th>
    </tr>
  </thead>
  <tbody>
    <tr data-toggle="popover" data-trigger="hover" title="Popover in Table Demo"  data-content="Popover in row number 1">
      <th scope="row">Jan</th>
      <td>105</td>
      <td>$15,000.00</td>
    </tr>
    <tr>
      <th scope="row">Feb</th>
      <td>95</td>
      <td>$12,000.00</td>
    </tr>
    <tr data-toggle="popover" data-trigger="hover" title="Popover in Table Demo"  data-content="Popover in row number 3">
      <th scope="row">Mar</th>
      <td>150</td>
      <td>$20,000.00</td>
    </tr>
    <tr>
      <th scope="row">Apr</th>
      <td>50</td>
      <td>$30,000.00</td>
    </tr>
    <tr data-toggle="popover" data-trigger="hover" title="Popover in Table Demo"  data-content="Popover in row number 5">
      <th scope="row">May</th>
      <td>80</td>
      <td>$15,000.00</td>
    </tr>
    <tr>
      <th scope="row">Jun</th>
      <td>110</td>
      <td>$22,000.00</td>
    </tr>            
  </tbody>
</table>
</div>


<script>
$(document).ready(function(){
$('[data-toggle="popover"]').popover({
})    
});
</script>

</body>
</html>


```

출처: https://www.jquery-az.com/bootstrap4/demo.php?ex=84.0_6



### 🍵Modal close 구현 시

해당 버튼에 속성으로 data-dismiss="modal"이 있어야 제대로 닫힘

```html
<button type="button" id="cancel" class="btn btn-secondary" data-dismiss="modal" onclick="downList = []; modal_close('download_modal'); ">취소</button>@*data-dismiss 꼭 필요!*@

```

### 🍧Checkbox를 편리하게 설정하는 방법

해당 체크박스 label을 눌러도 체크가 되게끔 설정

```html
<input type="checkbox" id="c1" name="cc" />
<label for="c1">Check Box 1</label>
```

label을 통해 input과 상호작용하기 위해서는 input과 label이 서로 연결되어야 함.  `for=""`과 input의 ID를 사용하면 연결.



### 🍣엔터로 검색 기능 활성화

```javascript
//엔터로 검색 모듈명 검색
$("#텍스트아이디").keydown(function (e) { if (e.keyCode == 13) $("#버튼아이디").click(); });
//keydown : This event occurs when a keyboard key is pressed.
//keyup: This event occurs when a keyboard key is released.

//OR


```

### 🍺onclick parameter에 공백이 포함될 때

```javascript
RenderValueAs(data => "<button type='button' class='btn btn-secondary btn-sm' onclick=\"selectRow('" + data+"')\">선택</button>");
```

onclick 맨 앞에 \ 추가, 맨 뒤에 \ 추가함으로써 공백을 포함시킨다.



### 🍐로딩 추가

```javascript
//div(로딩을 넣고싶은) 태그 안에 로딩 생성
$(".loader").html('<div class="loading"> </div>');
//로딩 css 실행
document.getElementsByClassName("loader")[0].style.display = "block";

//..작업 완료 후
//추후 로딩 remove 필요
$(".loading").remove();
```

- 데이터가 얼마 없으면 모달 띄우기도 전에 데이터가 불러와짐 -> 순서를: 버튼 클릭->모달이 띄워지면 그제서야 데이터 부르기



### 🍤다른 페이지 이동시...

```javascript
window.location.href = "./Search/SearchVersion?PatchVersion=" + version
```

- 자바스크립트에서 window.location.href를 이용해 파라미터와 함께 넘김

  - 문제점: 파라미터를 GET방식으로 보내는데 모든 내용들이 URL에 노출이 됨

- 해결방법

  ```javascript
  $.ajax({
              type: "POST",
              url: '@Url.Action("SearchVersion", "Search")',
              dataType: "json",
              data: { PatchVersion: version },
              success: function (data) {
                  window.location.href = "./Search/SearchVersion?PatchVersion";
                  //window.location.href = "./Search/SearchVersion?PatchVersion=" + version;
              }
          });
  ```

  - POST방식으로 바꿔야함

### 🍖Nullable<DateTime>과 DateTime 차이

- nullable: datetime에 null 값이 허용됨(초기값)
- Datetime에선 초기값이 0001년01월01일

Datetime가지고 파싱(parse, parseexact) 등 형식을 가지고 데이터를 수정하려면 nullable이 아닌 일반 Datetime 가지고 해야 함(nullable은 파싱이 적용안됨)



### 🍰POPOVER content 부분 표시할때...왜 이럴까 증맬 알수없다 자스~!!!

인터넷에선

```javascript
$("#aaa").popover({ //팝오버 하고자 하는 태그 설정 시
    html: true, 
	content: function() {
          return $('#popover-content').html(); //-> 콘텐츠.html()을 하라고 하지만 잘 안됨(일반 string 값은 잘 가져옴)
        }
    //content: "string 값" <-원래 방식
});
```

대체방법:

```javascript
$('#moreinfo').popover({
            trigger: 'hover',
            html: true,
            //content: function () {
            //    var data = $('#directRegister').html();
            //    return data;
            //    //$('#directRegister').html();
            //}
            content: function () { //clone을 함으로써 div 컨텐츠를 가져온다
                var clone = $("#directRegister")
                   .clone(true)
                   .html($("#directRegister").clone(true).html()
                   );
                clone.removeClass('hide');

                return clone;
            }
            //content: data
        })
```



### 🥭해당 변수가 undefined인지 확인할 때

```javascript
if (typeof(해당변수) !== 'undefined')
```



### 🍜선택한 값의 value를 가져올때

```javascript
$('input:radio[name="file_cat"]:checked').val()
//(input:타입[name=네임속성이름]:checked).val()
```



### 🥙 파일명에서 확장자 가져올 때

```javascript
 //파일명에서 파일 확장자명 추출
        var fileName = document.getElementById('ex_file').value;
        var fileName_len = fileName.length;
        var _lastDot = fileName.lastIndexOf('.');
        var file_ext = fileName.substring(_lastDot + 1, fileName_len); //_lastDot까지 하면 .exe 출력, _lastDot+1까지 하면 exe 출력
        
        if ($('input:radio[name="file_cat"]:checked').val() == "2") {
            if (file_ext != "ps1" && file_ext != "exe") {
                $('#modal_text').html(" <p >'통합 SSO 연동 .ps1/ .exe' 파일 형태는 .ps1 또는 .exe 파일만 가능합니다. 다시 확인해주세요. </p> ");
                $('#registerClick').modal('show');
            } else {
                addfileList(file);//올바른 파일이면 add
            }
        } else if ($('input:radio[name="file_cat"]:checked').val() == "5") {
            if (file_ext != "py") {
                $('#modal_text').html(" <p >'통합 SSO 연동 .py' 파일 형태는 .py 파일만 가능합니다. 다시 확인해주세요. </p> ");
                $('#registerClick').modal('show');
            } else {
                addfileList(file); //올바른 파일이면 add
            }
        } else {
            addfileList(file);//올바른 파일이면 add
        }
```

### 🍤필요한 html 정보 해당 id 태그 아래 넣기

```html
<div id="fileTotalSize" style="display: inline;">원하는 내용 또는 태그가 대입됨 </div>
```

```javascript
 $('#fileTotalSize').html(원하는 내용 또는 태그);
```



### 🧆Date를 가져와서 toISOString값으로 반환할때 주의

```javascript
new Date().toISOString.substring(0,10) //2021-08-24 형태로 출력되지만 toISOString함수는 UTC시간을 기준으로 반환하기 때문인데, 그 시간이 한국과 9시간차이의 오프셋을 가짐!

//수정
var timezoneOffset = new Date().getTimezoneOffset() * 60000;
var timezoneDate = new Date(Date.now() - timezoneOffset);
timezoneDate.toISOString().substring(0, 10); //한국시간 기준으로 날짜가 출력됨
```



### 🍑String에서 Datetime으로 변환할 때

```mssql
select CONVERT(DATETIME, @expire_date2,20)
```



### 🍺특정 문자열 포함 여부 체크

```javascript
var text = "문자열"
var findString = "포함여부를 따질 문자열"
if(text.indexOf(findString) != -1) { //포함될 때

    alert("성공!");

    }

else { //포함 안됨

    alert("실패!");

}
```



### 🥟테이블 안에 버튼을 특정 위치로 고정하고 싶을 때

```html
div#father {
    position: relative;
}
div#son1 {
    position: absolute;
    /* put your coords here */
}
div#son2 {
    position: absolute;
    /* put your coords here */
}
```

- 부모 태그가 relative, 자식태그가 absolute로 고정되어야 원하는 위치에 태그를 넣을 수 있음



### 🧅 SELECT 태그 multiple 속성 추가

```html
<select name="order[]" multiple>
    <option value="americano">아메리카노</option>
    <option value="caffe latte">카페라테</option>
    <option value="cafe au lait">카페오레</option>
    <option value="espresso">에스프레소</option>
</select>
```

- 윈도우즈(Windows) 운영체제에서는 CTRL이나 SHIFT키를 사용하여 여러 옵션을 선택할 수 있으며, 맥(Mac) 운영체제에서는 COMMAND키를 사용하여 여러 옵션을 선택
- multiple은 불리언 속성 ->명시하지 않으면 false, 명시하면 true값을 가지게 됨



### 🥩체크박스가 체크 되었는지 확인

```javascript
if($(선택자).is(":checked") == true){
    console.log('체크된 상태');
}
 
if($(선택자).is(":checked") == false){
    console.log('체크 안 된 상태');
}
```



### 🍚jquery로 배열에 있는 중복 값 제거

```javascript
var origin_data = deptNameList.substring(0, deptNameList.length - 1).split(",");
var final_data = [];

$.each(origin_data, function (i, value) {
     if (final_data.indexOf(value) == -1) final_data.push(value);
});

```

### 🍍Async: false 일때

```javascript
$.ajax({
                //url: "../MeetingService.asmx/cus_calendar_list",
                url: "../MeetingService.asmx/check_user",
                type: "post",
                contentType: "application/json; charset=utf-8",
                data: JSON.stringify(data),
                dataType: "json",
                async: false,
                success: function (result) {
                    for (var i = 0; i < result.d.length; i++) {
                        if (result.d[i] == userNow) {
                            userFlag = true;
                            break;
                        } else {
                            continue;
                        }
                    }
                },
                error: function (result) {
                    alert("사용자 권한 체크 error:" + result);
                }
```

ajax로 백엔드로 정보를 보낼 때 결과값을 가지고 오기도 전에 다른 함수나 기능이 실행 될 수 도 있음

- 이를 방지하려면 async: false로 설정하면 결과를 받기 전까지(success 또는 error) 다른 기능 실행 X



### 🧁에러 출력

```javascript
 ajax({ .....
 error: function (result) {
                    alert("error: "+result.responseText); //해당 에러 출력해줌
                }
```

### 🍂배열 내용 remove

```javascript
var arr = [1, 2, 3, 4, 5, 6];

Array.prototype.remove =
  Array.prototype.remove ||
  function() {
    this.splice(0, this.length);
  };

arr.remove();
console.log(arr);

//출력: []
```

### 🍢해당 태그 값 실시간 변화 감지

```javascript
 $("#아이디값").on("propertychange change keyup paste input", function () {

          //값의  변화에 따른 act 설정

        });
```

### 🍳자스 변수

```javascript
var a = "";
a = 1; 
//초기화를 스트링 타입으로 했어도 다른 값을 넣으면 자동으로 형변환 일어남!!!
```

### 🍠배열에 json 정보 추가하기

```javascript
var data = {
                    "mgmt_filename": mgmt_data,
                    "discontinued": dis_data,
                    "version": version,

                    //추가
                    "description": description,
                    "status": status,
                    "releated_customer": releated_customer,
                    "deveploper": deveploper,
                    "dev_date": dev_date,
                    "issue_no": issue_no
                };

var dataList = []; 

dataList.push(data);
//'[{"customer":"ㄴㄴㄴㄴ","fix_type":"hdgdf"}]' 형태로 들어감
```

- **추가 시 주의할 점**
  - push 안쓰고 그냥 concat 쓰면 덧붙여지는 형태, json이 되지 않음 -> dataList는 형변환이 일어날 수도 있음
  - '{"customer":"ㄴㄴㄴㄴ","fix_type":"hdgdf"},{"customer":"ㄴㄴㄴㄴ","fix_type":"hdgdf"}'

### 🍣jquery html 기능 빈칸 추가

```javascript
$("#아이디값").html("<p>a b c d</p>"); //빈칸 인식 X, abcd 로 출력 됨
$("#아이디값").html("<p>a&nbsp b&nbsp c&nbsp d</p>"); //&nbsp 추가하면 a b c d로 출력됨
```

### 🥝DataTables 사용법

```javascript
//테이블 적용
<script type="text/javascript">
jQuery(function($){ 
	$("#foo-table").DataTable(); 
}); 
</script>
```

```javascript
//필요없는 기능들 숨기기
$("#foo-table").DataTable({
	// 표시 건수기능 숨기기
	lengthChange: false,
	// 검색 기능 숨기기
	searching: false,
	// 정렬 기능 숨기기
	ordering: false,
	// 정보 표시 숨기기
	info: false,
	// 페이징 기능 숨기기
	paging: false
});
```

```javascript
//정렬
$("#foo-table").DataTable({
	// 2번째 항목을 오름 차순 
	// order : [ [ 열 번호, 정렬 순서 ], ... ]
	order: [ [ 1, "asc" ] ]
});

// 초기 표시에 경우 정렬 안함
//	order: []
```

- order는 [ [ 열 번호, 정렬 순서 ], ... ] 형식으로 설정
  - 0부터 시작
  - 1번째 항목은 오름 차순 + 2번째 항목은 내림 차순 = `order:[ [ 0, "asc" ], [ 1, "desc"] ]`

```javascript
//스크롤바 추가
$("#foo-table").DataTable({
	// 가로 스크롤바를 표시
	// 설정 값은 true 또는 false
	scrollX: true,

	// 세로 스크롤바를 표시
	// 설정 값은 px단위
	scrollY: 200
});
```

```javascript
//넓이 설정
$("#foo-table").DataTable({
	// 스크롤바 설정
	scrollX: true,
	scrollY: 200,

	// 열 넓이 설정
	columnDefs: [
		// 2번째 항목 넓이를 100px로 설정
		{ targets: 1, width: 100 }
	]
});
```

```javascript
//컬럼 항목 숨기기
$("#foo-table").DataTable({
	// 스크롤바
	scrollX: true,
	scrollY: 200,

	// 열 설정
	columnDefs: [
		// 1번재 항목 열을 숨김
		{ targets: 0, visible: false },
	
		// 2번째 항목의 넓이를 100px로 설정
		{ targets: 1, width: 100 }
	]
});
```

```javascript
//표시 건수 설정
$("#foo-table").DataTable({
	// 표시 건수를 10건 단위로 설정
	lengthMenu: [ 10, 20, 30, 40, 50 ],

	// 기본 표시 건수를 50건으로 설정
	displayLength: 50, 
	scrollX: true,
	scrollY: 200,
	columnDefs: [
		{ targets: 0, visible: false },
		{ targets: 1, width: 100 }
	]
});
```

```javascript
//페이지 수, 표시 건수, 정렬 상태를 보존하기(stateSave)

//일람 페이지에서 다른 페이지로 이동한 뒤, 다시 원래의 일람 페이지로 돌아오는 경우에 보고 있었던 페이지나 정렬 상태, 표시 건수들을 저장해뒀다가 다시 보여줄 수 있도록 해줍니다.

$("#foo-table").DataTable({
	// 현재 상태를 보존
	stateSave: true,
	scrollX: true,
	scrollY: 200,
	columnDefs: [
		{ targets: 0, visible: false },
		{ targets: 1, width: 100 }
	]
});
```

#### 기본 정보

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=http%3A%2F%2Fcfile2.uf.tistory.com%2Fimage%2F991290365C8D0E671EE84A)

| **번호** | **항목**  | **내용**                                    |
| -------- | --------- | ------------------------------------------- |
| 1        | 표시 건수 | 10, 25, 50, 100건 단위로 표시 가능          |
| 2        | 검색      | 출력된 표에서 검색 가능                     |
| 3        | 정렬      | 클릭하면 열을 오름 차순, 내림 차순으로 정렬 |
| 4        | 정보 표시 | 표시 건수 등 여러 정보를 표시               |
| 5        | 페이징    | 1페이지, 2페이지... 등 페이지를 나눠서 표시 |

https://devmoony.tistory.com/93

https://ponyozzang.tistory.com/220





### 🍈테이블 행 삭제

```javascript
<script>
    function SomeDeleteRowFunction(o) {
     //no clue what to put here?
     var p=o.parentNode.parentNode;
         p.parentNode.removeChild(p);
    }
    </script>

    <table>
       <tr>
           <td><input type="button" value="Delete Row" onclick="SomeDeleteRowFunction(this)"></td>
       </tr>
       <tr>
           <td><input type="button" value="Delete Row" onclick="SomeDeleteRowFunction(this)"></td>
       </tr>
       <tr>
           <td><input type="button" value="Delete Row" onclick="SomeDeleteRowFunction(this)"></td>
       </tr>
    </table>
```

