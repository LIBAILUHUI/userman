<html>
<head>
    <title>修改界面</title>

    <link rel="stylesheet" href="../bootstrap.min.css">
    <script type="text/javascript" src="../jquery-3.2.1.js"></script>
    <script type="text/javascript" src="../bootstrap.min.js"></script>
    <script type="text/javascript" src="../WdatePicker.js"></script>

</head>
<body>
<div align="center" style="margin-top:100px">
    <form id="updateForm">
        编号:<input type="text" readonly="readonly" name="id" value="${user.id}"><br>
        姓名<input type="text" name="name" value="${user.name}"><br>
        年龄<input type="text" name="age" value="${user.age}"><br>
        邮箱<input type="email" name="email" value="${user.email}"><br>
        生日<input type="text" name="birth" onclick="WdatePicker()" value="<#if user.birth??>${user.birth?string("yyyy-MM-dd")}</#if>"><br>
        头像<input type="file" name="myFile">
        <input type="hidden" value="${user.headImg}">
        所在部门
        <select name="did">
            <option value="">--请选择部门--</option>
            <#list dlist as dept>
                <option value="${dept.did}" <#if user.did??><#if user.did==dept.did>selected</#if></#if>>${dept.dname}</option>
            </#list>
        </select>
        <br>
        <input type="button" value="修改" onclick="update()">
    </form>
</div>

    <script type="text/javascript">
        function update(){
            var formData = new FormData($("#updateForm")[0]);
            $.ajax({
                url:"/user/update",
                type:"post",
                data:formData,
                success:function(data){
                    if(data){
                        alert("修改成功");
                        location = "/user/list";
                    }else{
                        alert("修改失败");
                    }
                },
                error:function(){
                    alert("未知错误，请联系管理员");
                }
            });
        }

    </script>

</body>

</html>