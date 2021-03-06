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

<br>

문자열을 특정 문자(,) 기준으로 split해서 하나씩 조회하기

```mssql
DECLARE @items NVARCHAR(MAX)
SET @items = '111,222,333,444,555,666,777,888,999'

DECLARE @delimiter NVARCHAR(1)
SET @delimiter = ','

DECLARE @item NVARCHAR(MAX)
SET @item = NULL

DECLARE @results TABLE (
    Item    NVARCHAR(MAX)
)

WHILE LEN(@items) > 0
BEGIN
    DECLARE @index    INT
    SET @index = PATINDEX('%' + @delimiter + '%', @items)
    IF @index > 0
    BEGIN
        SET @item = SUBSTRING(@items, 0, @index)
        SET @items = SUBSTRING(@items, LEN(@item + @delimiter) + 1, LEN(@items))

        INSERT INTO @results ( Item ) VALUES ( @item )
    END
    ELSE
    BEGIN
        SET @item = @items
        SET @items = NULL

        INSERT INTO @results ( Item ) VALUES ( @item )
    END
END

SELECT * FROM @results
```

출처: https://blog.aliencube.org/ko/2014/02/10/converting-array-like-table-from-string-in-ms-sql/

<br>



다른 테이블과 조인 시 조인 기준+where 절 사용

```mssql
SELECT DISTINCT B.dependency_mgmt_filename as dependency_filename, B.dependency_version  as dependency_version
	FROM @TARGET_MODULE A
	INNER JOIN FILE_TOTAL_MASTER2 B ON A.MGMT_FILENAME = B.mgmt_filename AND A.VERSION = B.version
	WHERE B.dependency_version IS NOT NULL
```

<br>

임시 테이블 만드는 방법

```mssql
 DECLARE @TEST_TABLE2	TABLE
	(
		IDX				INT IDENTITY(1,1) NOT NULL,
		max_id			NVARCHAR(30) NOT NULL
		
	)
	
INSERT INTO @TEST_TABLE2
select~
```

