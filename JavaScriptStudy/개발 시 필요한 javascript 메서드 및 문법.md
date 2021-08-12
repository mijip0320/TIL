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

