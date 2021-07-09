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
