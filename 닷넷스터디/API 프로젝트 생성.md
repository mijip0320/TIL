# API 프로젝트 생성

POST 방식 관련 함수 생성

```c#
[Route("api/서비스이름/기능이름")]

public JObject Post함수이름([FromUri]string 변수1, [FromUri]string 변수2){
    ~ 처리방식
    return dbconnection.커넥션이름(변수1, 변수2)
}
```

