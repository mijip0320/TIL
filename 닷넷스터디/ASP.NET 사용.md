# ASP.NET 사용

> Post Back? 이벤트를 처리하기 위해 페이지 자체적으로 페이지를 다시 로드하여 처리하게 됨
>
> submit 같은 버튼을 통해 다시 자신에 페이지가 새로 고침이 일어나는 현상
>
>  !Page.IsPostBack 이라고 묻는것은 페이지가 처음 로드 되었는지를 확인하는 것
>
> ```c#
>   void Page_Load(object sender, EventArgs e)
>     {
>         //페이지가 처음 로드되는 경우, 이부분이 빠지면 버튼을 클릭할때
>         //textbox1에는 늘 오라클자바오라클자바커뮤니티로 초기화 된다.
>         //if (!Page.IsPostBack)
>         //{
>             textbox1.Text = “오라클자바커뮤니티오라클자바";
>         //}
>     }
> ```



```c#
<asp:ScriptManager ID="scriptManager1" runat="server" />
<asp:UpdatePanel ID="UpdatePanel3" runat="server" CssClass="floatcs">
    <Triggers>
        <asp:AsyncPostBackTrigger controlid="btnSearch1" eventname="Click" />  
    </Triggers> 
    <ContentTemplate>
         <p id="eventDate" runat="server" style="margin-bottom:0">
             <asp:Label id="lbl1" runat="server" Text="&#8226; 예심일자:"></asp:Label>
         </p>
         <p id="tryOutDate" runat="server" style="margin-bottom:0">  
             <asp:Label id="lbl2" runat="server" Text="&#8226; FIX DAY 본선: ">
             </asp:Label>
         </p>
    </ContentTemplate>
</asp:UpdatePanel>
```

**Update Panel 사용 시 무조건 상단에 Script Manager 선언해줘야함!!**

- Update Panel? 웹에서 PostBack될 때 창이 깜빡이지 않고 새로고침 해주는 컨트롤 
  - 속성 리스트:
    - UpdateMode: 언제 업데이트 시킬지
      1. Always: 모든 이벤트에 대해 새로고침
      2. Conditional: 모든 이벤트에 대해 새로고침
    - Trigger: Update할 컨트롤 지정
    - AsyncPostBackTrigger : 지정 컨트롤(ControlID)에 이벤트가 발생했을 때 실행
    - PostBackTrigger: 지정 컨트롤(ControlID)에 지정한 이번트(EventName)가 발생했을 때 실행



### GridView 선언

```c#
<asp:GridView ID="gridService" runat="server">  
</asp:GridView>  
```

```c#
<asp:GridView ID="gridService" runat="server" CssClass="EU_DataTable" AutoGenerateColumns="false" ShowFooter="true"OnRowEditing="gridService_RowEditing"            onrowcreated="gridService_RowCreated"onrowupdating="gridService_RowUpdating">  
                    <Columns>  
                        <asp:TemplateField ItemStyle-Width="30px" HeaderText="SR.NO">  
                            <ItemTemplate>  
                                <asp:Label ID="lblID" runat="server"  
                                  Text='<%#Eval("service_id")%>'></asp:Label>  
                            </ItemTemplate>  
                        </asp:TemplateField>  
                        <asp:TemplateField ItemStyle-Width="600px" HeaderText="Service">  
                            <ItemTemplate>  
                                <asp:Label ID="lblService" runat="server" Text='<%#Eval("service_name")%>'></asp:Label>  
                            </ItemTemplate>  
                            <EditItemTemplate>  
                                <asp:TextBox ID="txtService" runat="server" Text='<%#Eval("service_name")%>'></asp:TextBox>  
                            </EditItemTemplate>  
                            <FooterTemplate>  
                                <asp:TextBox ID="txtService" runat="server"></asp:TextBox>  
                            </FooterTemplate>  
                        </asp:TemplateField>  
                        <asp:TemplateField ItemStyle-Width="100px" HeaderText="Service Photo">  
                            <ItemTemplate>  
                                <img src='<%# Eval("service_image")%>' alt='<%#  
                                                Eval("service_image")%>' height="50px"  
                                    width="50px" />  
                            </ItemTemplate>  
                            <EditItemTemplate>  
                                <asp:FileUpload ID="fuService" runat="server" />  
                            </EditItemTemplate>  
                            <FooterTemplate>  
                                <asp:FileUpload ID="fuService" runat="server" />  
                            </FooterTemplate>  
                        </asp:TemplateField>  
                        <asp:TemplateField>  
                            <ItemTemplate>  
                                <asp:LinkButton ID="lnkRemove" runat="server" CommandArgument='<%#  
                                         Eval("service_id")%>'  
                                    OnClientClick="return confirm('Do you want to delete?')"  
                                  Text="Delete"></asp:LinkButton>  
                            </ItemTemplate>  
                            <FooterTemplate>  
                                <asp:Button ID="btnAdd" runat="server" Text="Add" OnClick="AddService"/>  
                            </FooterTemplate>  
                        </asp:TemplateField>  
                        <asp:CommandField ShowEditButton="True" />  
                    </Columns>  
                </asp:GridView>  
```

### GridView 바인딩

```c#
private void _BindService()  
{  
    try  
    {  
        List<BOService> service = dALService.Service.ToList();  
        if (service.Count > 0 && service != null)  
        {  
            gridService.DataSource = service;  
            gridService.DataBind();  
       }  
   }  
   catch (Exception)  
   {  
       throw;  
   }  
```

삽입/삭제/수정은 데이터베이스에 직접 접근해서 해당 기능 수행, 수행 후 다시 바인딩!

