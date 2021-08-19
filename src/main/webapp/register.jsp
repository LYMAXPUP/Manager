<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Rgister</title>

    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="plugins/css/form-elements.css">
    <link rel="stylesheet" href="plugins/css/style.css">

    <link rel="shortcut icon" href="plugins/ico/favicon.png">
</head>

<body>
<div class="top-content">
    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>用户注册</h3>
                            <p>开启资产管理新旅程...</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-lock"></i>
                        </div>
                    </div>

                    <div class="form-bottom">
                        <form role="form" action="" method="post" class="login-form">
                            <div class="form-group">
                                <label for="form-username">
                                    <span class="glyphicon glyphicon-user"></span> 用户名
                                </label>
                                <input type="text" name="form-username" placeholder="请输入用户名" class="form-username form-control" id="form-username" v-model="uname"/>
                            </div>
                            <div class="form-group">
                                <label for="form-password">
                                    <span class="glyphicon glyphicon-lock"></span> 密码
                                </label>
                                <input type="password" name="form-password" class="form-password form-control" id="form-password" placeholder="请输入密码" v-model="upwd"/>
                            </div>
                            <button type="button" style="background: #de995e" class="btn" @click="doRegister()">立即注册
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="plugins/js/jquery-1.11.1.min.js"></script>
<script src="plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="plugins/js/jquery.backstretch.min.js"></script>
<script src="plugins/js/scripts.js"></script>
<script src="plugins/vue/vue.js"></script>
<script>
    var vm = new Vue({
        el: '.top-content',
        data: {
            uname:'',
            upwd:'',
        },
        methods:{
            doRegister:function () {
                $.ajax({
                    url:"register.ajax",
                    type:"post",
                    dataType:"JSON",
                    data:{
                        userid: this.uname,
                        pwd: this.upwd
                    },
                    success:function (resp) {
                        var code = resp.code;
                        if(code==1){
                            alert("注册成功");
                            location.href = "login.jsp";
                        }else if(code==-1){
                            alert("注册失败，用户名/密码不能为空");
                        }else if(code==0){
                            alert("注册失败，用户名已存在");
                        }
                    },
                    error: function () {
                        alert("注册失败");
                    }
                })
            }
        }
    });
</script>

</body>

</html>
