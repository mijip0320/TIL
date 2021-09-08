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

<br>

year, month, day를 따로따로 뽑을 때

```mssql
SELECT DATEPART(yy, GETDATE()) --년

SELECT DATEPART(mm, GETDATE()) --월

SELECT DATEPART(dd, GETDATE()) --일
```

<br>

문자열 SPLIT 함수 만들기(테이블을 리턴함)

```mssql
CREATE FUNCTION [dbo].[FN_SPLIT2]
(
    @StrValue NVARCHAR(MAX),   -- 분리할 문자열
    @SplitChar VARCHAR(1)         -- 구분할 문자
) 
RETURNS @SPLIT_TEMP TABLE  ( IDX INT, VALUE VARCHAR(50) )
AS 
BEGIN   
    DECLARE @oPos INT, @nPos INT
    DECLARE @TmpVar VARCHAR(1000) -- 분리된 문자열 임시 저장변수
	DECLARE @I INT
	
	SET @I = 0
 

    SET @oPos = 1 -- 구분문자 검색을 시작할 위치
    SET @nPos = 1 -- 구분문자 위치

 

    WHILE (@nPos > 0)
    BEGIN 

        SET @nPos = CHARINDEX(@SplitChar, @StrValue, @oPos ) 

 

        IF @nPos = 0 
            SET @TmpVar = RIGHT(@StrValue, LEN(@StrValue)-@oPos+1 )
        ELSE
            SET @TmpVar = SUBSTRING(@StrValue, @oPos, @nPos-@oPos)

 

        IF LEN(@TmpVar)>0
        BEGIN
            INSERT INTO @SPLIT_TEMP VALUES( @I, @TmpVar )
            SET @I = @I + 1
        END


        SET @oPos = @nPos +1 
    END 


   RETURN 
END


GO

--사용시 (FN_SPLIT2가 테이블이기 때문)
select IDX , VALUE from dbo.FN_SPLIT2
```

<br>

string 값 리턴하는 함수

```mssql
CREATE FUNCTION [dbo].[GET_VERSION_FROM_COMP](@VERSION_COMP NVARCHAR(25))
	RETURNS NVARCHAR(25)
AS
BEGIN
	DECLARE @RETVAL	NVARCHAR(25)

	--IF(CHARINDEX('-', @VERSION_COMP, 0) > 0 )
	--.이 들어가지 않았을때 AND 숫자가 포함되어 있지 않을때
	IF(CHARINDEX('.', @VERSION_COMP, 0) = 0 AND ISNUMERIC(@VERSION_COMP) = 0)
	BEGIN
		SET @RETVAL = '0';
	END
	ELSE
	SET @RETVAL = CONVERT(VARCHAR(5), CONVERT(INT,SUBSTRING(@VERSION_COMP, 1, 5))) + '.'
				+ CONVERT(VARCHAR(5), CONVERT(INT,SUBSTRING(@VERSION_COMP, 7, 5))) + '.'
				+ CONVERT(VARCHAR(5), CONVERT(INT,SUBSTRING(@VERSION_COMP, 13, 5))) + '.'
				+ CONVERT(VARCHAR(5), CONVERT(INT,SUBSTRING(@VERSION_COMP, 19, 5)))
	
	return @RETVAL
END
```



<br>

mmsql 공백, 띄어쓰기, 탭 제거

```mssql
REPLACE(REPLACE(REPLACE(REPLACE([칼럼명], CHAR(13), ''), CHAR(10), ''), CHAR(9),''),' ','')
```

<br>

mssql 조건 주기(case when이 잘 안될때 )

```mssql
# WHERE 절에 명시한 조건에 충족하는 결과가 없기 때문!!

# 결과 값이 0개 이기 때문에 0개의 행이 출력!!

# 일반적으로 MSSQL에서 조건에 해당되는 값이 없을때 처리 방법!!

 

       IF EXISTS

       (

             SELECT 컬럼명

             FROM 테이블명

             WHERE 조건

       )

       BEGIN

             해당조건의 값이 있을 경우처리

       END

      

       ELSE

            

       BEGIN

            해당조건의 값이 없을 경우처리

       END



출처: https://rohsstory.tistory.com/409 [디버깅하는 남자]
```

<br>

mssql 교집합, 차집합 구하기

```mssql
1. INTERSECT

 

- SELECT * FROM TableA INTERSET SELECT * FROM TableB

  ( TableA 와 TableB 가 일치하는 결과를 리턴 )

- 교집합 개념

 

2. EXCEPT

 

- SELECT * FROM TableA EXCEPT SELECT * FROM TableB

  ( TableA  Row 내용 중 TableB와 Row 내용이 같지 않거나

   TableA 에는 있는데 TableB에는 없는 데이터를 리턴 )

- 차집합 개념

 

3. 주의 사항

 

- TableA 와 TableB 의 컬럼의 갯수와 순서가 동일해야 함

- 각 상호 비교되는 컬럼들의 데이터 형식이 호환되어야 함



출처: https://rocabilly.tistory.com/50 [프로그램이 좋다]
```

<br>

중첩 CASE 사용

```mssql
CASE WHEN B.expired_date is not null AND B.requester is not null AND B.approver is null THEN '0' --승인 대기 중
				WHEN B.expired_date is null or status != 'TEST모듈' THEN 
					CASE WHEN B.requester is not null THEN '0' --승인 대기 중
					ELSE '1' --제한 없음
					END
				ELSE CONVERT(CHAR(20),B.expired_date,120) end as expired_date --만료일
```

<br>

그룹 중 가장 큰 값(id) 별로 선택

```mssql
--각 file_id별로 제일 큰 id 값들만 선택
SELECT id, file_id from(
select ROW_NUMBER() OVER (PARTITION BY file_id order by id desc) as RankNO, id, file_id, expired_date, requester, approver FROM DOWNLOAD_DATE_MANAGE
) T
where RankNo = 1
```

<br>

같지 않다 표현

```mssql
 Select * From subject Where CURRICULUM_CODE <> 1

Select * From subject Where CURRICULUM_CODE != 1
```

<br>

사용자 지정 순서 지정

```mssql
ORDER BY  
   CASE WHEN dbo.FIND_FILE_CAT(file_cat) = '관리자용삭제툴' THEN 1 
   WHEN dbo.FIND_FILE_CAT(file_cat) = '고객사전용툴' THEN 2
   WHEN dbo.FIND_FILE_CAT(file_cat) = 'TEST모듈' THEN 3 
   WHEN dbo.FIND_FILE_CAT(file_cat) = '고객사전용모듈' THEN 4
   WHEN dbo.FIND_FILE_CAT(file_cat) = '통합SSO연동.ps1/.exe' OR dbo.FIND_FILE_CAT(file_cat) = '통합SSO연동.py모듈' THEN 5 END ASC
```

<br>

조회되지 않는 데이터를 조건문으로 걸면 결과가 아무것도 안나옴 -> 결과가 안나오는 쿼리문도 미리 처리해야함

```mssql
--조건
DECLARE @max_date nvarchar(50)
	SET @max_date = CASE WHEN (SELECT top 1 reg_date from FILE_MASTER where customer = @customer and dbo.FIND_FILE_CAT(file_cat) = '고객사전용툴' order by reg_date desc) is null or (SELECT top 1 reg_date from FILE_MASTER where customer = '(사)대한간호협회' and dbo.FIND_FILE_CAT(file_cat) = '고객사전용툴' order by reg_date desc) = '' THEN '' --null값 처리
	ELSE (SELECT top 1 CONVERT(CHAR(23),reg_date,21) from FILE_MASTER where customer = @customer and dbo.FIND_FILE_CAT(file_cat) = '고객사전용툴' order by reg_date desc) END --그 외 해당 값 출력
	
...

CASE WHEN dbo.FIND_FILE_CAT(file_cat)= ''관리자용삭제툴'' THEN 1 
				   WHEN dbo.FIND_FILE_CAT(file_cat) = ''TEST모듈'' THEN 3 
				   WHEN dbo.FIND_FILE_CAT(file_cat) = ''고객사전용모듈'' THEN 4
				   WHEN dbo.FIND_FILE_CAT(file_cat) = ''통합SSO연동.ps1/.exe'' OR dbo.FIND_FILE_CAT(file_cat) = ''통합SSO연동.py모듈'' THEN 5 
				   --조건문 사용
					WHEN '''+@max_date+''' <> '''' THEN 
						CASE WHEN CONVERT(CHAR(23),reg_date,21) ='''+@max_date+''' AND dbo.FIND_FILE_CAT(file_cat) = ''고객사전용툴'' THEN 2
						ELSE 6 END 
				   END as ROW_ORDER
```



