<html>
<head>
    <title>详情界面</title>

    <link rel="stylesheet" href="../bootstrap.min.css">
    <script type="text/javascript" src="../jquery-3.2.1.js"></script>
    <script type="text/javascript" src="../bootstrap.min.js"></script>
    <script type="text/javascript" src="../WdatePicker.js"></script>


</head>
<body>
<div align="center" style="margin-top:100px">
    <form>
        编号:<input type="text" readonly="readonly" name="id" value="${user.id}"><br>
        姓名<input type="text" readonly="readonly" name="name" value="${user.name}"><br>
        年龄<input type="text" readonly="readonly" name="age" value="${user.age}"><br>
        邮箱<input type="email" readonly="readonly" name="email" value="${user.email}"><br>
        生日<input type="text" readonly="readonly" onclick="WdatePicker()" name="birth" value="<#if user.birth??>${user.birth?date}</#if>"><br>
        所在部门<input type="text" readonly="readonly" name="dname" value="${user.dname}">

    </form>
</div>



</body>

</html>