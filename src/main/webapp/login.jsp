<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login</title>

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
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>资产管理系统</strong></h1>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top">
                        <div class="form-top-left">
                            <h3>Login to our site</h3>
                            <p style="color:red" v-show="code==2">
                                <span class="glyphicon glyphicon-remove"></span> 用户名/密码错误
                            </p>
                            <p style="color:red" v-show="code==1">
                                <span class="glyphicon glyphicon-remove"></span> 此用户已被禁用或注销
                            </p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
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
                            <button type="button" class="btn" @click="doLogin()">
                                <span class="glyphicon glyphicon-log-in"></span> 登陆
                            </button>
                            <a href="register.jsp">注册新账号</a>
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
            code:-10
        },
        methods:{
            doLogin:function () {
                $.ajax({
                    url:"login.ajax",
                    type:"post",
                    dataType:"JSON",
                    data:{
                        un: this.uname,
                        up: this.upwd
                    },
                    success:function (resp) {
                        vm.code = resp.code;
                        if(vm.code==0){
                            location.href = "assetlist.jsp";
                        } if(vm.code==-1){
                            alert("账号密码不能为空");
                        }
                    },
                    error: function () {
                        alert("登陆失败");
                    }
                })
            }
        }
    });
</script>

</body>
</html>
