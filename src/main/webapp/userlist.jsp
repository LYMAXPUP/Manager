<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <title>人员配置</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="shortcut icon" href="plugins/ico/favicon2.png" type="image/x-icon">

    <link rel="stylesheet" href="plugins/bootstrap/css/bootstrap.min.css">
    <link href="plugins/css/font-awesome.min.css" rel="stylesheet" />

    <link id="beyond-link" href="plugins/css/beyond.min.css" rel="stylesheet" type="text/css" />
    <link href="plugins/css/dataTables.bootstrap.css" rel="stylesheet" />
    <link id="skin-link" href="" rel="stylesheet" type="text/css" />
    <script src="plugins/js/skins.min.js"></script>
</head>
<body>
<div class="main-container">
    <div class="page-container">
        <div class="page-sidebar" id="sidebar">
            <ul class="nav sidebar-menu">
                <li>
                    <a href="#" class="menu-dropdown">
                        <i class="menu-icon fa fa-table"></i>
                        <span class="menu-text"> 资产管理 </span>
                        <i class="menu-expand"></i>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="assetlist.jsp">
                                <span class="menu-text">资产列表</span>
                            </a>
                        </li>
                        <li>
                            <a href="myassetlist.jsp">
                                <span class="menu-text">我的申请</span>
                            </a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="#" class="menu-dropdown">
                        <i class="menu-icon glyphicon glyphicon-cog"></i>
                        <span class="menu-text"> 系统配置</span>
                        <i class="menu-expand"></i>
                    </a>
                    <ul class="submenu">
                        <li>
                            <a href="userlist.jsp">
                                <span class="menu-text">人员配置</span>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
        <div id='userpage' class="page-content">
            <!--  模态框4新增用户-->
            <div class="modal fade" id="editMo4" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" aria-label="Close" @click="hideMo4()"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModalLabel">新增用户</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="addUserId">用户名</label>
                                <input type="text" class="form-control" id="addUserId" placeholder="请输入用户名" v-model="nd.userid">
                            </div>
                            <div class="form-group">
                                <label for="addPwd">密码</label>
                                <input type="text" class="form-control" id="addPwd" placeholder="请输入密码" v-model="nd.pwd">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button class="btn btn-default" @click="hideMo4()">取消</button>
                            <button class="btn btn-primary" @click="saveMo4()">确认新增</button>
                        </div>
                    </div>
                </div>
            </div>
            <!--模态框5编辑详细信息-->
            <div class="modal fade" id="editMo5" tabindex="-1" role="dialog" aria-labelledby="myModal">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" aria-label="Close" @click="hideMo5()"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModal">详细信息</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>id</label>
                                <p>{{ed.id}}</p>
                            </div>
                            <div class="form-group">
                                <label for="editUserId">用户ID</label>
                                <input type="text" class="form-control" id="editUserId" placeholder="请输入用户名" v-model="ed.userid">
                            </div>
                            <div class="form-group">
                                <label>状态</label>
                                <p v-if="ed.state==0">禁用</p>
                                <p v-if="ed.state==1">可用</p>
                                <p v-if="ed.state==-1">注销</p>
                            </div>
                            <div class="form-group">
                                <label for="editPriority">用户优先级</label>
                                <input type="text" class="form-control" id="editPriority" placeholder="请输入优先级" v-model="ed.priority">
                            </div>
                            <div class="form-group">
                                <label for="editText">备注</label>
                                <input type="text" class="form-control" id="editText" placeholder="请输入备注" v-model="ed.text">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" @click="hideMo5()">取消</button>
                            <button type="button" class="btn btn-primary" @click="saveMo5()">确认修改</button>
                        </div>
                    </div>
                </div>
            </div>
            <!--模态框6重置密码-->
            <div class="modal fade" id="editMo6" tabindex="-1" role="dialog" aria-labelledby="Label">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" aria-label="Close" @click="hideMo6()"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="Label">重置密码</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label for="resetPwd">新密码</label>
                                <input type="text" class="form-control" id="resetPwd" placeholder="请输入新密码" v-model="npwd.pwd">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" @click="hideMo6()">取消</button>
                            <button type="button" class="btn btn-primary" @click="saveMo6()">确认重置</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="page-header position-relative">
                <div class="header-title">
                    <h1>
                        当前用户
                        <small>
                            <i class="fa fa-angle-right"></i>
                            ${sessionScope.userid}
                        </small>
                    </h1>
                </div>
            </div>
            <div class="page-body">
                <div class="row">
                    <div class="col-xs-12 col-md-12">
                        <div class="widget">
                            <div class="widget-header ">
                                <span class="widget-caption">用户列表</span>
                            </div>
                            <div class="widget-body">
                                <div class="table-toolbar">
                                    <a id="addnew" @click="openMo4()" class="btn btn-default">
                                        新增
                                    </a>
                                    <div class="btn-group">
                                        <a class="btn btn-default" href="#">用户状态</a>
                                        <a class="btn btn-default dropdown-toggle" data-toggle="dropdown" href="#"><i class="fa fa-angle-down"></i></a>
                                        <ul class="dropdown-menu dropdown-default">
                                            <li>
                                                <a href="javascript:void(0);">禁用</a>
                                            </li>
                                            <li>
                                                <a href="javascript:void(0);">可用</a>
                                            </li>
                                            <li>
                                                <a href="javascript:void(0);">注销</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <input type="text"  placeholder="输入用户名" v-model="sd.userid"/>
                                    <button type="button" class="btn btn-default" @click="getData(1)"><i class="searchicon fa fa-search"></i></button>
                                </div>

                                <table class="table table-striped table-hover table-bordered" id="usertable">
                                    <thead>
                                    <tr role="row" class="active">
                                        <th>序号</th>
                                        <th>用户名</th>
                                        <th>状态</th>
                                        <th>操作</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <tr v-for="data in dataList">
                                        <td>{{data.id}}</td>
                                        <td>{{data.userid}}</td>
                                        <td v-if="data.state==0">禁用</td>
                                        <td v-if="data.state==1">可用</td>
                                        <td v-if="data.state==-1">注销</td>
                                        <td>
                                            <a @click="edit(data)" class="btn btn-info btn-xs"><i class="fa fa-edit"></i> 编辑 </a>
                                            <a v-show='data.state==1' @click="forbidUser(data.id)" class="btn btn-primary btn-xs"><i class="glyphicon glyphicon-ban-circle"></i> 禁用 </a>
                                            <a v-show='data.state==0'@click="liftUser(data.id)" class="btn btn-success btn-xs"><i class="glyphicon glyphicon-ok"></i> 解禁 </a>
                                            <a v-show='data.state!=-1' @click="resetPwd(data)" class="btn btn-warning btn-xs"><i class="glyphicon glyphicon-asterisk"></i> 重置密码 </a>
                                            <a v-show='data.state!=-1'@click="deleteUser(data.id)" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> 注销 </a>
                                        </td>
                                    </tr>
                                </table>
                                <div class="my-page pull-right">
                                    <nav aria-label="Page navigation">
                                        <ul class="pagination">
                                            <li>
                                                <a aria-label="Previous" @click="goUp()">
                                                    <span aria-hidden="true">&laquo;</span>
                                                </a>
                                            </li>
                                            <li v-for="page in page.list">
                                                <a @click="getData(page)">{{page}}</a>
                                            </li>
                                            <li>
                                                <a aria-label="Next" @click="goDown()">
                                                    <span aria-hidden="true">&raquo;</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /Page Body -->
        </div>

    </div>

</div>

<script src="plugins/js/jquery-1.11.1.min.js"></script>
<script src="plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="plugins/js/beyond.min.js"></script>
<script src="plugins/vue/vue.js"></script>
<script>
    var vm = new Vue({
        el: '#userpage',
        data: {
            dataList: [],
            sd : {
                userid: ''
            },
            nd: {
                userid:'',
                pwd:''
            },
            npwd:{
                id:0,
                pwd:''
            },
            page: {
                index: 1,
                max: 1,
                list: [] // 分页的集合
            },
            // 编辑数据，存data的属性
            ed: {
                id:0,
                userid:'',
                pwd:'',
                state:'',
                priority:0,
                text:''
            }
        },
        methods : {
            // 上一页
            goUp: function() {
                if (this.page.index > 1) {
                    this.page.index = this.page.index - 1;
                    this.getData(this.page.index);
                }
            },
            // 下一页
            goDown: function() {
                if (this.page.index < this.page.max) {
                    this.page.index = this.page.index + 1;
                    this.getData(this.page.index);
                }
            },
            // 计算分页数
            runPage: function(max) {
                this.page.max = max;
                this.page.list = [];
                for (var i = 1; i <= max; i++) {
                    this.page.list.push(i);
                }
            },
            // 获取数据
            getData: function(nowIndex) {
                this.page.index = nowIndex;
                $.ajax({
                    url: "user.ajax",
                    type: "post",
                    data: {
                        index: nowIndex,
                        userid: this.sd.userid
                    },
                    dataType: "JSON",
                    success: function (resp) {
                        vm.dataList = resp.list;
                        vm.runPage(resp.max);
                    },
                    error: function () {
                        alert("获取数据失败！！！");
                    }
                });
            },
            openMo4: function () {
                $('#editMo4').modal({
                    keyboard: false,
                    backdrop: 'static'
                });
            },
            hideMo4: function(){
                $('#editMo4').modal('hide');
                this.nd = {
                    userid:'',
                    pwd:''
                }
            },
            saveMo4 : function () {
                $.ajax({
                    url: "register.ajax",
                    type: "post",
                    data: {
                        userid: this.nd.userid,
                        pwd: this.nd.pwd
                    },
                    dataType: "JSON",
                    success: function (resp) {
                        var code = resp.code;
                        if(code==1){
                            alert("添加成功");
                            $('#editMo4').modal('hide');
                            vm.getData(vm.page.index);
                        } if(code==-1){
                            alert("用户名/密码不能为空");
                        }
                    },
                    error: function () {
                        alert("添加失败！");
                    }
                });
            },
            // 编辑方法
            edit: function(data) {
                this.ed.id = data.id;
                this.ed.userid = data.userid;
                this.ed.pwd = data.pwd;
                this.ed.state = data.state;
                this.ed.priority = data.priority;
                this.ed.text = data.text;
                this.openMo5();
            },
            // 模态框弹出
            openMo5: function () {
                $('#editMo5').modal({
                    keyboard: false,
                    backdrop: 'static'
                });
            },
            hideMo5: function(){
                $('#editMo5').modal('hide');
                this.ed = {
                    id:0,
                    userid:'',
                    pwd:'',
                    state:'',
                    priority:0,
                    text:''
                }
            },
            saveMo5 : function () {
                $.ajax({
                    url: "editUser.ajax?action=edit",
                    type: "post",
                    data: {
                        id: this.ed.id,
                        userid: this.ed.userid,
                        priority: this.ed.priority,
                        text:this.text
                    },
                    dataType: "JSON",
                    success: function (resp) {
                        var flag = resp.flag;
                        if(flag){
                            alert("修改成功");
                            vm.hideMo5();
                            vm.getData(vm.page.index);
                        }else{
                            alert("修改失败，用户名不能为空！")
                        }
                    },
                    error: function () {
                        alert("系统发生错误！");
                    }
                });
            },
            resetPwd: function(data){
                this.npwd.id = data.id;
                this.npwd.pwd = data.pwd;
                this.openMo6();
            },
            openMo6: function(){
                $('#editMo6').modal({
                    keyboard: false,
                    backdrop: 'static'
                });
            },
            hideMo6: function(){
                $('#editMo6').modal('hide');
                this.npwd = {
                    id:0,
                    pwd:''
                }
            },
            saveMo6 : function () {
                $.ajax({
                    url: "editUser.ajax?action=resetPwd",
                    type: "post",
                    data: {
                        id: this.npwd.id,
                        pwd: this.npwd.pwd,
                    },
                    dataType: "JSON",
                    success: function (resp) {
                        var flag = resp.flag;
                        if(flag){
                            alert("重置密码成功");
                            vm.hideMo6();
                            vm.getData(vm.page.index);
                        }else{
                            alert("重置失败，新密码不能为空！");
                        }
                    },
                    error: function () {
                        alert("系统发生错误！");
                    }
                });
            },
            forbidUser: function(id){
                $.ajax({
                    url: "changeUserState.ajax?action=forbid",
                    type: "post",
                    data: {
                        id: id
                    },
                    dataType: "JSON",
                    success: function (resp) {
                        var flag = resp.flag;
                        if(flag){
                            alert("禁用成功");
                            vm.getData(vm.page.index);
                        }else{
                            alert("禁用失败");
                        }
                    },
                    error: function () {
                        alert("系统发生错误！");
                    }
                });
            },
            liftUser: function(id){
                $.ajax({
                    url: "changeUserState.ajax?action=lift",
                    type: "post",
                    data: {
                        id: id
                    },
                    dataType: "JSON",
                    success: function (resp) {
                        var flag = resp.flag;
                        if(flag){
                            alert("解禁成功");
                            vm.getData(vm.page.index);
                        }else{
                            alert("解禁失败");
                        }
                    },
                    error: function () {
                        alert("系统发生错误！");
                    }
                });
            },
            deleteUser: function(id){
                $.ajax({
                    url: "changeUserState.ajax?action=delete",
                    type: "post",
                    data: {
                        id: id
                    },
                    dataType: "JSON",
                    success: function (resp) {
                        var flag = resp.flag;
                        if(flag){
                            alert("注销成功");
                            vm.getData(vm.page.index);
                        }else{
                            alert("注销失败");
                        }
                    },
                    error: function () {
                        alert("系统发生错误！");
                    }
                });
            }

        },
    });

    vm.getData(1);
</script>


</body>
<!--  /Body -->
</html>

