## mssql 이론노트

프로시저 등록 시 <br>

```
BEGIN

select~~~~
CASE WHEN <1번 상황일때> THEN <1번 상황 처리> [WHEN 으로 또 더 조건 추가 가능] END AS 해당 칼럼
.....


END
```

형식으로 지정 가능

<br>



닷넷과 연결 시, Web.config 파일에

```xml
<connectionStrings>
	<add name="이름지정" connectionString="server=서버이름;Initial Catalog=디비이름;User ID= ;Password= ; Connection Timeout=600" providerName="System.Data.SqlClient" />
</connectionStrings>
```

형식으로 지정

<br>



변수 선언 및 사용방법

```mssql
DECLARE @변수 INT
SET @변수 = (원하는 쿼리문 또는 값)

추후 @변수로 다른 쿼리문에서도 사용 가능
```



<br>

외래키 걸려있는 디비 Truncate 하는 방법

```mssql
  DELETE FROM [TableName]
-- Set current ID to "1"
-- If table already contains data, use "0"
-- If table is empty and never insert data, use "1"
-- Use SP https://github.com/reduardo7/TableTruncate
DBCC CHECKIDENT ([TableName], RESEED, 0)
```

