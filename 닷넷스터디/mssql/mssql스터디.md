mssql 이론노트

프로시저 등록 시 <br>

```
BEGIN

select~~~~
CASE WHEN <1번 상황일때> THEN <1번 상황 처리> [WHEN 으로 또 더 조건 추가 가능] END AS 해당 칼럼
.....


END
```

형식으로 지정 가능