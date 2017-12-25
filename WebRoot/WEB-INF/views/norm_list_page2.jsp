<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    pageContext.setAttribute("norm", request.getContextPath());
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <!-- <meta charset="utf-8"> -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="常模列表模板,列表,常模列表">
    <meta name="author" content="高庆佳">
    <meta name="keyword" content="常模列表模板,列表,常模列表">
    <link rel="shortcut icon" href="${norm}/img/favicon.png">

    <title>常模列表</title>
    <%@include file="common/header.jsp" %>
    <link rel="stylesheet" href="${norm}/css/bootstrap.css">
    <script src="${norm}/js/jquery-3.2.1.js"></script>
    <script src="${norm}/js/jquery-3.2.1.min.js"></script>
    <script src="${norm}/js/layer.js"></script>
    <script src="${norm}/js/layui.js"></script>
    <link rel="stylesheet" href="${norm}/css/layui.css" media="all">
</head>
<body>

<!--main content start-->
<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><i class="fa fa-table"></i>常模列表页面</h3>
                <ol class="breadcrumb">
                    <li><i class="fa fa-table"></i>常模列表</li>
                </ol>
            </div>
        </div>
        <!-- page start-->


        <div class="row">
            <div class="col-lg-12">
                <section class="panel">
                    <header class="panel-heading">
                        <p style="display: inline;">常模名称：</p>
                        <input type="text" id="normName" style="height: 33px;">
                        <!--  search form end -->
                        <p style="display: inline;padding-left: 60px;">状态：</p>

                        <!-- Single button -->
                        <select name="" id="status" style="height: 30px;color: #688a7e;">
                            <option value="">全部</option>
                            <option value="0">待计算</option>
                            <option value="1">计算中</option>
                            <option value="2">已计算</option>
                            <option value="3">计算失败</option>
                        </select>
                        <!-- Single button -->
                        <div style="display:inline;float: right">
                            <a class="layui-btn layui-btn-xs layui-btn-radius" onclick="reload()">查询</a>
                            <a class="layui-btn layui-btn-normal layui-btn-xs layui-btn-radius" href="${norm}/menu/switch/3">添加</a>
                        </div>


                    </header>
                    <div class="table-responsive">
                        <table id="list" lay-filter="normListTable">
                          
                        </table>
                    </div>
                    <div id="msg" style="text-align:center;margin-top: 100px;height: 250px;">
                        <span id="message" style="font-size:50px;color:lightgrey;">
                        </span>
                    </div>

                </section>
            </div>
        </div>
        <!-- page end-->
    </section>
</section>
<!--main content end-->

</body>
<!-- 操作按钮 -->
<script type="text/html" id="barOpt">
  <!-- 这里同样支持 laytpl 语法，如： -->
  <!-- 3计算失败，允许操作：编辑、导入、计算、删除 -->
  <!-- 2计算成功，允许操作：查看、导出、删除 -->
  <!-- 1计算中，不允许操作-->
  <!-- 0未计算，允许操作：编辑、导入、计算、删除-->
  {{#  if(d.status == 3){ }}
    <button class="layui-btn layui-btn-warm layui-btn-mini layui-btn-radius" lay-event="edit">编辑</button>
    <button class="layui-btn layui-btn-mini layui-btn-radius " lay-event="import">导入</button>
	<button class="layui-btn layui-btn-primary layui-btn-mini layui-btn-radius" lay-event="figure">计算</button>
    <button class="layui-btn layui-btn-warm layui-btn-mini layui-btn-radius layui-btn-disabled" disabled="disabled" lay-event="detail">查看</button>
    <button class="layui-btn layui-btn-normal layui-btn-mini layui-btn-radius layui-btn-disabled" disabled="disabled" lay-event="export">导出</button>
    <button class="layui-btn layui-btn-danger layui-btn-mini layui-btn-radius" lay-event="del">删除</button>
  {{#   }else if(d.status == 2){ }}
	<button class="layui-btn layui-btn-warm layui-btn-mini layui-btn-radius layui-btn-disabled" disabled="disabled" lay-event="edit">编辑</button>
	<button class="layui-btn layui-btn-mini layui-btn-radius layui-btn-disabled" disabled="disabled" lay-event="import">导入</button>
    <button class="layui-btn layui-btn-primary layui-btn-mini layui-btn-radius layui-btn-disabled" disabled="disabled" lay-event="figure">计算</button>
    <button class="layui-btn layui-btn-warm layui-btn-mini layui-btn-radius" lay-event="detail">查看</button>
    <button class="layui-btn layui-btn-normal  layui-btn-mini layui-btn-radius" lay-event="export">导出</button>
  	<button class="layui-btn layui-btn-danger layui-btn-mini layui-btn-radius" lay-event="del">删除</button>
  {{#  }else if(d.status == 1){ }}
	<button class="layui-btn layui-btn-warm layui-btn-mini layui-btn-radius layui-btn-disabled" disabled="disabled" lay-event="edit">编辑</button>
	<button class="layui-btn layui-btn-mini layui-btn-radius layui-btn-disabled" disabled="disabled" lay-event="import">导入</button>
    <button class="layui-btn layui-btn-primary layui-btn-mini layui-btn-radius layui-btn-disabled" disabled="disabled" lay-event="figure">计算</button>
    <button class="layui-btn layui-btn-warm layui-btn-mini layui-btn-radius layui-btn-disabled" disabled="disabled" lay-event="detail">查看</button>
    <button class="layui-btn layui-btn-normal  layui-btn-mini layui-btn-radius layui-btn-disabled" disabled="disabled" lay-event="export">导出</button>
  	<button class="layui-btn layui-btn-danger layui-btn-mini layui-btn-radius layui-btn-disabled" disabled="disabled" lay-event="del">删除</button> 
  {{#  }else if(d.status == 0){ }}
	<button class="layui-btn layui-btn-warm layui-btn-mini layui-btn-radius" lay-event="edit">编辑</button>
	<button class="layui-btn layui-btn-mini layui-btn-radius" lay-event="import">导入</button>
    <button class="layui-btn layui-btn-primary layui-btn-mini layui-btn-radius" lay-event="figure">计算</button>
    <button class="layui-btn layui-btn-warm layui-btn-mini layui-btn-radius layui-btn-disabled" disabled="disabled" lay-event="detail">查看</button>
    <button class="layui-btn layui-btn-normal  layui-btn-mini layui-btn-radius layui-btn-disabled"disabled="disabled" lay-event="export">导出</button>
  	<button class="layui-btn layui-btn-danger layui-btn-mini layui-btn-radius" lay-event="del">删除</button>
  {{#  } }} 

</script>

<script type="text/html" id="barSta">
	{{#  if(d.status == 0){ }}
		<span>待计算</span>
 	{{#  }else if(d.status == 1){ }} 
		<span>计算中</span>
	{{#  }else if(d.status == 2){ }} 
		<span>计算完成</span>
	{{#  }else if(d.status == 3){ }} 
		<span>计算失败</span>
	{{#  } }} 
</script>

<script type="text/javascript">
	//页面加载
	$(document).ready(function () {
        reload();
    });
	//查询
	function reload(){
	  layui.use('table', function(){
	  var table = layui.table;
	 //执行一个 table 实例
	  var tableObj = table.render({
	    elem: '#list'//指定表格
	    ,even: true //开启隔行背景
	    ,url: '${norm}/norm/getNormListLayer'//请求路径
	    ,where:{'normname': $("#normName").val(), 'status': $("#status").val()} //参数
	    ,page: true //开启分页--默认包含参数page,limit
	    ,cols: [[ //表头
	      {field: 'id', title: 'ID', width:80,sort:true}
		  ,{field: 'productName', title: '产品名称', width:160}
	      ,{field: 'normName', title: '常模名称', width:160}
	      ,{field: 'version', title: '常模版本', width:160} 
	      ,{field: 'sampleSize', title: '样本量', width: 120}
	      ,{field: 'description', title: '描述', width: 160}
	      ,{field: 'figureTime', title: '计算时间', width: 120, sort: true}
	      ,{field: 'status', title: '状态', width: 160,toolbar: '#barSta'}
	      ,{fixed: 'right', title: '操作',width:300, align:'left', toolbar: '#barOpt'}
	    ]]
	  });
	  table.set(tableObj);//设置全局变量
	  table.on('tool(normListTable)',function(obj) {	
		var data = obj.data;
		 var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
	  	if(layEvent === 'edit'){//编辑
	  		window.location.href="${norm}/norm/goupdatenorm/" + data.id;
	  	}else if(layEvent === 'import'){//导入
	  	    window.location.href="${norm}/norm/rtInPage/" + data.id;
	  	}else if(layEvent === 'figure'){//计算
	  		layer.confirm('常模计算可能会占用比较长的时间,计算完成会发送消息到你的邮箱,是否立即计算？', {
				  btn: ['计算','放弃'] //按钮
				}, function(){
				 $.post("${norm}/norm/figureNorm/"+data.id,function(data){
	        		console.log(data);
	        		console.log(data.message);
	        		if(data.status == 0){
	        			window.location.href="${norm}/menu/switch/1";
	        		}else{
	        			layer.alert(data.message);
	        		}
	        	  })
				}, function(){
				  return;
				});
	  	}else if(layEvent === 'detail'){//查看
	  		window.location.href="${norm}/norm/getNormDetailed/" + data.id;
	  	}else if(layEvent === 'export'){//导出
	  		window.location.href="${norm}/norm/exportNorm/"+ data.id;
	  	}else if(layEvent === 'del'){//删除
	  		 layer.confirm("是否删除！", {
                btn: ['删除', '取消'] //按钮
            }, function () {
                $.post("${norm}/norm/deleteNorm/"+data.id,function (data) {
                    layer.msg(data.message, {icon: 1});
					//TODO 删除后刷新页面
  					tableObj.reload({
  						where:{'normname': $("#normName").val(), 'status': $("#status").val()}
  					}); 
                })
            }, function () {
                layer.msg('取消删除', {
                    time: 1000, //1s后自动关闭
                });
            });
	  	}else if(layEvent === 'query'){//查询
	  		tableObj.reload({
  				where:{'normname': $("#normName").val(), 'status': $("#status").val()}
  			});
	  	}
	  })
	});
	} 
</script>
</html>
