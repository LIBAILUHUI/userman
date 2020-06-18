<html>
<head>

    <title>欢迎</title>
    <link rel="stylesheet" href="../bootstrap.min.css" type="text/css">
    <script type="text/javascript" src="../jquery-3.2.1.js"></script>
    <script type="text/javascript" src="../bootstrap.min.js"></script>
</head>
<body>




    <div style="margin-top:50px">
        <form id="addForm">
            上传头像
            <input type="file" name="file">
            <br>
            <button type="button" onclick="addFile()">ajax文件上传</button>
        </form>

    </div>


    <script type="text/javascript">
        function addFile(){
            var formData = new FormData($("#addForm")[0]);
            $.ajax({
                url:"/user/uploadajax",
                type:"post",
                data:formData,
                contentType:false,
                processData:false,

                success:function(flag){
                    if(flag){
                        alert("上传成功");
                    }else{
                        alert("上传失败");
                    }
                }
            });
        }
    </script>
</body>
</html>