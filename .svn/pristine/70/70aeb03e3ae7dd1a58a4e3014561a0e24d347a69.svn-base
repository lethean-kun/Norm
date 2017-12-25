<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    pageContext.setAttribute("norm", request.getContextPath());
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="导入测评数据">
    <meta name="author" content="高庆佳">
    <meta name="keyword" content="导入测评数据">
    <link rel="shortcut icon" href="${norm}/img/normicon.png">


    <title>导入测评数据</title>


    <!-- Bootstrap CSS -->
    <link href="${norm}/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="${norm}/css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="${norm}/css/elegant-icons-style.css" rel="stylesheet"/>
    <link href="${norm}/css/font-awesome.min.css" rel="stylesheet"/>
    <!-- Custom styles -->
    <link href="${norm}/css/style.css" rel="stylesheet">
    <%--<script src="${norm}/js/jquery-3.2.1.js"></script>--%>
    <script src="${norm}/js/jquery-3.2.1.min.js"></script>
    <script src="${norm}/js/jquery.form.js"></script>
    <script src="${norm}/js/layer.js"></script>
    <link rel="stylesheet" href="${norm}/css/layui.css" media="all">
    <link href="${norm}/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
    <script src="${norm}/js/fileinput.js" type="text/javascript"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script>
    <%@include file="common/header.jsp" %>

</head>

<body>


<!--main content start-->
<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><i class="fa fa-table"></i>导入测评数据页</h3>
                <ol class="breadcrumb">
                    <!-- 			<li><i class="fa fa-home"></i><a href="norm_list_page.jsp">主页</a></li> -->
                    <li><i class="fa fa-table"></i>导入测评数据</li>

                </ol>
            </div>
        </div>
        <!-- page start-->
        <h4 style="display: inline">导入文件:</h4>&nbsp;&nbsp;
        <%--<input type="file" style="display: inline">--%>

        <form id="forList" method="post" action="${norm}/norm/importData" enctype="multipart/form-data">

            <div class="file-loading">
                <input id="input-b1" name="file" type="file" class="file"
                       data-allowed-file-extensions='["xls", "xlsx"]'>
            </div>
            <br>
            <%--<input type="file" name ="file">--%>

            <input type="hidden" name="normVersion" id="" value="${version}"/>
            <input type="hidden" name="id" id="id" value="${id}"/>

            <div id="loading" style="display:none;" class="layui-progress layui-progress-big" lay-showpercent="true"
                 lay-filter="demo">
                <div class="layui-progress-bar layui-bg-red" lay-percent="1%"></div>
            </div>

            <br>
            <input id="button" class="btn btn-primary" value="导入" type="submit"/>
            <a onclick="figure()" class="btn btn-warning">计算</a>
            <a href="${norm}/norm/downloadTemplate" style="float: right" class="btn btn-success">导入模板下载</a>
            <br>
        </form>


        <div class="site-demo-button" style="margin-top: 20px; margin-bottom: 0;">
            <%--<button class="layui-btn site-demo-active" data-type="setPercent">设置50%</button>--%>
            <%--<button class="layui-btn site-demo-active" data-type="loading">模拟loading</button>--%>
        </div>
        <!-- page end-->
    </section>
</section>

<!--main content end-->
</body>
<script src="${norm}/js/layui.js" charset="utf-8"></script>
<script type="text/javascript">
    layui.use('element', function () {
        var $ = layui.jquery
            , element = layui.element; //Tab的切换功能，切换事件监听等，需要依赖element模块
        //触发事件
        var active = {
        };

        $('.site-demo-active').on('click', function () {
            var othis = $(this), type = $(this).data('type');
            active[type] ? active[type].call(this, othis) : '';
        });
    });

    $(document).ready(function () {


        $("#button").click(function () {
            var input = $(".file-caption-name").attr("title");
            var input2 = $("#input-b1").attr("name");
            console.log(input2);
            console.log(input);
            if (input != null) {
                var element = layui.element;
                $("#loading").show();
                var n = 0, timer = setInterval(function () {
                    n = n + Math.random() * 10 | 0;
                    if (n > 99) {
                        n = 99;
                        clearInterval(timer);
                    }
                    element.progress('demo', n + '%');
                }, 300 + Math.random() * 1000);
                var options = {
                    type: 'post',//提交方式
                    url: '${norm}/norm/importData/${id}',
                    success: function (data) {
                        data = $.parseJSON(data.replace(/<.*?>/ig, ""));

                        if (data.status == 0) {
                            //设置进度条100% 并停止
                            element.progress('demo', '100%');
                            clearInterval(timer);

                            layer.confirm("去除重复数据后共导入" + data.data + "条数据！", {
                                btn: ['开始计算', '取消'] //按钮
                            }, function () {
                                figure();
                                window.location.href = "${norm}/menu/switch/1";
                            }, function () {
                                $("#loading").hide();
                                layer.msg('取消计算', {
                                    time: 2000, //20s后自动关闭
                                    //btn: ['您已取消计算！']
                                });
                            });
                            //layer.alert("共导入"+data.data+"条数据！");
                        } else {
                            layer.alert(data.message);
                            element.progress('demo', '0%');
                            clearInterval(timer);
                            $("#loading").hide();
                        }
                    },
                    error: function () {
                        layer.alert("导入失败！！");
                        element.progress('demo', '0%');
                        clearInterval(timer);
                        $("#loading").hide();
                    }
                };
                $("#forList").ajaxSubmit(options); // jquery 表单提交
                return false;  // 必须返回false，否则表单会自己再做一次提交操作，并且页面跳转

            }else {
                layer.alert("请选择文件后导入！");
                return false;
            }
        });
    });

    //常模计算
    function figure() {
        var id = $("#id").val();
        console.log(id);
        $.post("${norm}/norm/figureNorm/" + id, function (data) {
            console.log(data);
            console.log(data.message);
            if (data.status == 0) {
                window.location.href = "${norm}/menu/switch/1";
            } else {
                layer.alert(data.message);
            }
        })
    }
</script>
</html>
