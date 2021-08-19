<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <meta charset="utf-8" />
        <title>我的申请</title>

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

        <div id='myassetpage' class="page-content">
            <!--模态框3编辑详细信息-->
            <div class="modal fade" id="editMo3" tabindex="-1" role="dialog" aria-labelledby="myModal">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" aria-label="Close" @click="hideMo3()"><span aria-hidden="true">&times;</span></button>
                            <h4 class="modal-title" id="myModal">详细信息</h4>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>资产ID</label>
                                <p>{{ed.assetId}}</p>
                            </div>
                            <div class="form-group">
                                <label>产品名称</label>
                                <input type="text" class="form-control"  placeholder="请输入资产名称" v-model="ed.assetName">
                            </div>
                            <div class="form-group">
                                <label>所购部门</label>
                                <input type="text" class="form-control"  placeholder="请输入部门" v-model="ed.department">
                            </div>
                            <div class="form-group">
                                <label>产品型号</label>
                                <input type="text" class="form-control"  placeholder="请输入产品型号" v-model="ed.model">
                            </div>
                            <div class="form-group">
                                <label>产品单价</label>
                                <input type="text" class="form-control"  placeholder="请输入产品单价" v-model="ed.price">
                            </div>
                            <div class="form-group">
                                <label>购买时间</label>
                                <input type="text" class="form-control"  placeholder="请输入购买时间" v-model="ed.buyTime">
                            </div>
                            <div class="form-group">
                                <label>入账人</label>
                                <p>{{accountName}}</p>
                            </div>
                            <div class="form-group">
                                <label>凭证号</label>
                                <p>{{ed.voucher}}</p>
                            </div>
                            <div class="form-group">
                                <label>入账时间</label>
                                <p>{{ed.accountTime}}</p>
                            </div>
                            <div class="form-group">
                                <label>财务状态</label>
                                <p v-if="ed.financeState==1">入账</p>
                                <p v-if="ed.financeState==0">销账</p>
                            </div>
                            <div class="form-group">
                                <label>保管人</label>
                                <p>{{ed.keeperName}}</p>
                            </div>
                            <div class="form-group">
                                <label>使用人</label>
                                <p>{{ed.userName}}</p>
                            </div>
                            <div class="form-group">
                                <label>资产状态</label>
                                <p v-if="ed.assetState==0">空闲</p>
                                <p v-if="ed.assetState==1">使用中</p>
                                <p v-if="ed.assetState==-1">报废</p>
                            </div>
                            <div class="form-group">
                                <label>备注</label>
                                <input type="text" class="form-control"  placeholder="请输入产品备注" v-model="ed.text">
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" @click="hideMo3()">取消</button>
                            <button type="button" class="btn btn-primary" @click="saveMo3()">确认修改</button>
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
            <!-- Page Body -->
            <div class="page-body">
                <div class="row">
                    <div class="col-xs-12 col-md-12">
                        <div class="widget">
                            <div class="widget-header ">
                                <span class="widget-caption">我的资产</span>
                            </div>
                            <div class="widget-body">
                                <div class="table-toolbar">
                                    <input type="text"  placeholder="输入资产名称" v-model="sd.assetName"/>
                                    <button type="button" class="btn btn-default" @click="getData(1)"><i class="searchicon fa fa-search"></i></button>
                                </div>
                                <table class="table table-striped table-hover table-bordered" id="assettable">
                                    <thead>
                                    <tr role="row">
                                        <th>序号</th>
                                        <th>资产名称</th>
                                        <th>类型</th>
                                        <th>状态</th>
                                        <th>  </th>
                                    </tr>
                                    </thead>

                                    <tbody>
                                    <tr v-for="data in dataList">
                                        <td >{{data.assetId}}</td>
                                        <td>{{data.assetName}}</td>
                                        <td>{{data.type}}</td>
                                        <td v-if="data.assetState==0">空闲</td>
                                        <td v-if="data.assetState==1">使用中</td>
                                        <td v-if="data.assetState==-1">报废</td>
                                        <td>
                                            <a @click="edit(data)" class="btn btn-info btn-xs"><i class="fa fa-edit"></i> 编辑 </a>
                                            <a v-show='data.assetState==1'@click="giveBackAsset(data.assetId)" class="btn btn-warning btn-xs"><i class="glyphicon glyphicon-ok"></i> 归还 </a>
                                            <a v-show='data.assetState!=-1'@click="deleteAsset(data.assetId)" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> 报废 </a>
                                        </td>
                                    </tr>
                                </table>
                                <div class="pull-right">
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
        el: '#myassetpage',
        data: {
            dataList: [],
            sd : {
                assetName: ''
            },
            page: {
                index: 1,
                max: 1,
                list: []
            },
            ed: {
                accountName:'',
                voucher:'',
                accountTime:'',
                financeState:0,
                assetId:0,
                assetName:'',
                type:'',
                department:'',
                model:'',
                price:0,
                buyTime:'',
                keeperName:'',
                userName:'',
                assetState:0,
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
                    url: "asset.ajax?action=my",
                    type: "post",
                    data: {
                        index: nowIndex,
                        assetName: this.sd.assetName
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
            edit: function(data) {
                this.ed.accountName = data.accountName;
                this.ed.voucher = data.voucher;
                this.ed.accountTime = data.accountTime;
                this.ed.financeState = data.financeState;
                this.ed.assetId = data.assetId;
                this.ed.assetName = data.assetName;
                this.ed.type = data.type;
                this.ed.department = data.department;
                this.ed.model = data.model;
                this.ed.price = data.price;
                this.ed.buyTime = data.buyTime;
                this.ed.keeperName = data.keeperName;
                this.ed.userName = data.userName;
                this.ed.assetState = data.assetState;
                this.ed.text = data.text;
                this.openMo3();
            },
            // 模态框弹出
            openMo3: function () {
                $('#editMo3').modal({
                    keyboard: false,
                    backdrop: 'static'
                });
            },
            hideMo3: function(){
                $('#editMo3').modal('hide');
                this.ed = {
                    accountName:'',
                    voucher:'',
                    accountTime:'',
                    financeState:0,
                    assetId:0,
                    assetName:'',
                    type:'',
                    department:'',
                    model:'',
                    price:0,
                    buyTime:'',
                    keeperName:'',
                    userName:'',
                    assetState:0,
                    text:''
                }
            },
            saveMo3 : function () {
                $.ajax({
                    url: "editAsset.ajax",
                    type: "post",
                    data: {
                        assetId: this.ed.assetId,
                        assetName: this.ed.assetName,
                        department: this.ed.department,
                        model:this.ed.model,
                        price: this.ed.price,
                        buyTime: this.ed.buyTime,
                        text: this.ed.text
                    },
                    dataType: "JSON",
                    success: function (resp) {
                        var flag = resp.flag;
                        if(flag){
                            alert("修改成功");
                            vm.hideMo3();
                            vm.getData(vm.page.index);
                        }else{
                            alert("修改失败，除备注信息外不能为空！")
                        }
                    },
                    error: function () {
                        alert("系统发生错误！");
                    }
                });
            },
            deleteAsset: function(id){
                $.ajax({
                    url: "changeAssetState.ajax?action=delete",
                    type: "post",
                    data: {
                        assetId: id
                    },
                    dataType: "JSON",
                    success: function (resp) {
                        var flag = resp.flag;
                        if(flag){
                            alert("报废成功");
                            vm.getData(vm.page.index);
                        }else{
                            alert("报废失败");
                        }
                    },
                    error: function () {
                        alert("系统发生错误！");
                    }
                });
            },
            giveBackAsset: function (id) {
                $.ajax({
                    url: "changeAssetState.ajax?action=giveBack",
                    type: "post",
                    data: {
                        assetId: id
                    },
                    dataType: "JSON",
                    success: function (resp) {
                        var flag = resp.flag;
                        if(flag){
                            alert("归还成功");
                            vm.getData(vm.page.index);
                        }else{
                            alert("归还失败");
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
