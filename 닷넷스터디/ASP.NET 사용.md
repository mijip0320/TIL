# ASP.NET 사용

> Post Back? 이벤트를 처리하기 위해 페이지 자체적으로 페이지를 다시 로드하여 처리하게 됨
>
> submit 같은 버튼을 통해 다시 자신에 페이지가 새로 고침이 일어나는 현상
>
>  !Page.IsPostBack 이라고 묻는것은 페이지가 처음 로드 되었는지를 확인하는 것



```c#
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

- Update Panel? 웹에서 PostBack될 때 창이 깜빡이지 않고 새로고침 해주는 컨트롤 

