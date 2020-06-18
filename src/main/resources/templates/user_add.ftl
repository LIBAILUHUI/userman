<html>
<head>
    <title>添加界面</title>

    <link rel="stylesheet" href="../bootstrap.min.css">
    <script type="text/javascript" src="../jquery-3.2.1.js"></script>
    <script type="text/javascript" src="../bootstrap.min.js"></script>
    <script type="text/javascript" src="../WdatePicker.js"></script>

</head>
<body>
    <div align="center" style="margin-top:100px">
    <form id="addForm">
        姓名<input type="text" name="name"><br>
        年龄<input type="text" name="age"><br>
        邮箱<input type="email" name="email"><br>
        生日<input type="text" name="birth" onclick="WdatePicker()"><br>
        头像<input type="file" name="myFile">
        <br>
        所在部门
        <select name="did">
            <option value="">--请选择部门--</option>
            <#list dlist as dept>
                <option value="${dept.did}">${dept.dname}</option>
            </#list>
        </select>
        <input type="button" value="添加" onclick="add()">
    </form>
    </div>
    <script type="text/javascript">
        function add(){
            var formData = new FormData($("#addForm")[0]);

            $.ajax({
                url:"/user/add",
                type:"post",
                data:formData,
                processData:false,
                contentType:false,
                success:function(data){
                    if(data){
                        alert("添加成功");
                        location = "/user/list";
                    }else{
                        alert("添加失败");
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