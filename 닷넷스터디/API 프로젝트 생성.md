# API 프로젝트 생성

POST 방식 관련 함수 생성

```c#
[Route("api/서비스이름/기능이름")]

public JObject Post함수이름([FromUri]string 변수1, [FromUri]string 변수2){
    ~ 처리방식
    return dbconnection.커넥션이름(변수1, 변수2)
}
```



DB 연결 부분

```c#
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Http;
using System.Net;
using System.Net.Http;
using System.Net.Http.Headers;
using System.Data;
using System.Data.SqlClient;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using 프로젝트명.Models;
using System.Configuration;
using System.IO;
using Dapper;

public class UserDBAccess
    {
        //human db 연결
        protected static string 스트링 = System.Configuration.ConfigurationManager.ConnectionStrings["디비커넥션스트링값"].ConnectionString;
    


        public JObject 함수명(string 매개변수)
        {
            using (SqlConnection con = new SqlConnection(스트링))
            {
                var result = con.Query<User>("프로시저명"
                    , new { 디비입력값 = 매개변수 }, commandType: CommandType.StoredProcedure);
                string resultjson = JsonConvert.SerializeObject(new { items(표현하고자 하는 데이터 이름) = result });
                JObject jo = JObject.Parse(resultjson);

                return jo;
            }

        }

    }
```

> postman에서 해당 url과 파라미터 입력해서 테스트 가능/크롬에서 json 확장자 다운받아서 결과 확인 가능
