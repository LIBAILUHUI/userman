<html>
<head>

    <title>欢迎</title>
    <link rel="stylesheet" href="../bootstrap.min.css" type="text/css">
    <script type="text/javascript" src="../jquery-3.2.1.js"></script>
    <script type="text/javascript" src="../bootstrap.min.js"></script>
    <script type="text/javascript" src="../WdatePicker.js"></script>
</head>
<body>
<table class="table">
    <tr>
        <td colspan="10">
            <input type="button" class="btn btn-success" value="增加" onclick="add()">
            <input type="button" class="btn btn-danger" value="批量删除" onclick="delBatch()">
        </td>
    </tr>

    <tr>
        <td colspan="10">
            <form action="/user/list" method="post" id="queryForm">
                姓名<input type="text" name="name" value="${users.name!""}">
                <#--生日<input type="text" name="birth" onclick="WdatePicker()" value="<#if users.birth??>${users.birth?string("yyyy-MM-dd")}</#if>">-->
                <input type="hidden" name="current" id="current">
                生日范围：
                <input type="text" name="startDate" onclick="WdatePicker()" value="<#if users.startDate??>${users.startDate?string("yyyy-MM-dd")}</#if>">
                <input type="text" name="endDate" onclick="WdatePicker()" value="<#if users.endDate??>${users.endDate?string("yyyy-MM-dd")}</#if>">
                所在部门
                <select name="did">
                    <option value="">--请选择部门--</option>
                    <#list dlist as dept>
                        <option value="${dept.did}" <#if users.did??><#if users.did==dept.did>selected</#if></#if>>${dept.dname}</option>
                    </#list>
                </select>
                <input type="submit" value="查询" class="btn btn-dark">
            </form>

        </td>
    </tr>
    <tr>
        <th>
            <input type="checkbox" id="checkAll">
        </th>
        <th>序号</th>
        <th>名称</th>
        <th>年龄</th>
        <th>邮箱</th>
        <th>生日</th>
        <th>头像</th>
        <th>所在部门</th>
        <th>操作</th>
    </tr>
    <#list info.records as user>
        <tr>
            <td>
                <input type="checkbox" class="userC" value="${user.id?c}">
            </td>
            <td>

                ${user.id?c}</td>
            <td>${user.name!""}</td>
            <td>${user.age!""}</td>
            <td>${user.email!""}</td>
            <td>
                <#if user.birth??>
                    ${user.birth?string("yyyy-MM-dd")}
                </#if>
            </td>
            <td>
                <img src="${user.headImg}" height="100px" width="100px">
            </td>
            <td>
                ${user.dname!""}
            </td>
            <td>
                <a href="/user/detail?id=${user.id?c}">详情</a>
                <a href="/user/update?id=${user.id?c}">修改</a>
                <a href="javascript:void(0)" onclick="del(${user.id?c})">删除</a>
            </td>
        </tr>


    </#list>
    <tr>
        <td colspan="100">
            共${info.total}项  当前第${info.current}页/${pages}页
            <input type="button" onclick="goPage(1)" value="首页" class="btn btn-info">
            <#if info.current==1>
                <input type="button" onclick="goPage(1)" value="上一页" class="btn btn-info">
                <#else>
                    <input type="button" onclick="goPage(${info.current-1})" value="上一页" class="btn btn-info">
            </#if>

            <#if info.current==pages>
                <input type="button" onclick="goPage(${pages})" value="下一页" class="btn btn-info">
                <#else>
                    <input type="button" onclick="goPage(${info.current+1})" value="下一页" class="btn btn-info">
            </#if>

            <input type="button" onclick="goPage(${pages})" value="尾页" class="btn btn-info">

        </td>

    </tr>
</table>



    <script type="text/javascript">

        function goPage(current){
            $("#current").val(current);
            $("#queryForm").submit();
        }


        $("#checkAll").click(function(){
            $(".userC").each(function(){
                $(this).prop("checked",$("#checkAll").prop("checked"));
            });
        });



        function delBatch(){
            var ids = $(".userC:checked").map(function(){
                return this.value;
            }).get().join();
            //alert(ids);
            if($(".userC:checked").length){
                if(confirm("确认删除")){
                    $.post("/user/delBatch",{ids:ids},function(data){
                        if(data){
                            alert("删除成功");
                            location.reload();
                        }else{
                            alert("删除失败");
                        }
                    });
                }
            }else{
                alert("请勾选要删除的数据");
            }
        }

        function add() {
            location = "/user/add";
        }
        function del(id){

            if(confirm("确认删除?")){
                $.ajax({
                    url:"/user/delete",
                    type:"post",
                    data:{id:id},
                    success:function(data){
                        if(data){
                            alert("删除成功");
                            location = "/user/list";
                        }else{
                            alert("删除失败");
                        }
                    },
                    error:function(){
                        alert("未知错误，请联系管理员");
                    }
                });
            }
        }

    </script>

</body>
</html>