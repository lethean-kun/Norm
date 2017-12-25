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
    <script src="${norm}/js/jquery-3.2.1.js"></script>
    <script src="${norm}/js/jquery-3.2.1.min.js"></script>
    <script src="${norm}/js/layer.js"></script> 
    <script src="${norm}/js/bootstrap-paginator.js"></script>
    <link rel="stylesheet" href="${norm}/css/bootstrap.css">
    <link rel="stylesheet" href="${norm}/css/layui.css" media="all"> 
    <script src="http://code.jquery.com/jquery-migrate-1.1.0.js"></script>
    <script>
        $(document).ready(function () {
            getNormList();
        });

		function getNormList() {
			var name = $("#normName").val();
            var status = $("#status").val();
            //先清空tbody
            $("#list tbody").html("");
            $("#msg").hide();
            $.post("${norm}/norm/getNormList", {"normname": name, "status": status,"page":0}, function (data) {
                if (data.status == 0) {
					var tbody = "";
                    $.each(data.data, function (i, norm) {
                        var status;
                        var buttons;
                        var figureTime;
                        if(norm.figureTime == null||norm.figureTime == ""){
                            figureTime =  "空";
                        }else {
                            figureTime = norm.figureTime;
                        }
                        if (norm.status == 0) {
                            status = "待计算";
                            buttons = "<a class=\"btn btn-success btn-sm\" href=\"${norm}/norm/goupdatenorm/" + norm.id + "\">更新</a>\n" +
                                "                                    <a class=\"btn btn-primary btn-sm\" href=\"${norm}/norm/rtInPage/" + norm.id + "\">导入</a>\n" +
                                "                                    <a class=\"btn btn-warning btn-sm\" onclick='figure("+norm.id+")'>计算</a>\n" +
                                "                                    <a class=\"btn btn-info btn-sm\" disabled=\"disabled\" href=\"${norm}/norm/getNormDetailed/" + norm.id + "\" >查看</a>\n" +
                                "                                    <a class=\"btn btn-default btn-sm\" disabled=\"disabled\" href=\"${norm}/norm/exportNorm/" + norm.id + "\">导出</a>\n" +
                                "                                    <a class=\"btn btn-danger btn-sm\" onclick=\"deleteNorm("+norm.id+")\" >删除</a>";
                        } else if (norm.status == 1) {
                            status = "计算中";
                            buttons = "<a class=\"btn btn-success btn-sm\" disabled=\"disabled\">更新</a>\n" +
                                "                                        <a class=\"btn btn-primary btn-sm\" disabled=\"disabled\">导入</a>\n" +
                                "                                        <a class=\"btn btn-warning btn-sm\" disabled=\"disabled\">计算</a>\n" +
                                "                                        <a class=\"btn btn-info btn-sm\" disabled=\"disabled\" href=\"${norm}/norm/getNormDetailed/" + norm.id + "\" >查看</a>\n" +
                                "                                        <a class=\"btn btn-default btn-sm\" disabled=\"disabled\" href=\"${norm}/norm/exportNorm/" + norm.id + "\">导出</a>\n" +
                                "                                        <a class=\"btn btn-danger btn-sm\" onclick=\"deleteNorm("+norm.id+")\" >删除</a>";
                        } else if (norm.status == 2) {
                            status = "已计算";
                            buttons = "<a class=\"btn btn-success btn-sm\" disabled=\"disabled\">更新</a>\n" +
                                "                                        <a class=\"btn btn-primary btn-sm\" disabled=\"disabled\">导入</a>\n" +
                                "                                        <a class=\"btn btn-warning btn-sm\" disabled=\"disabled\">计算</a>\n" +
                                "                                        <a class=\"btn btn-info btn-sm\"  href=\"${norm}/norm/getNormDetailed/" + norm.id + "\" >查看</a>\n" +
                                "                                        <a class=\"btn btn-default btn-sm\"  href=\"${norm}/norm/exportNorm/" + norm.id + "\">导出</a>\n" +
                                "                                        <a class=\"btn btn-danger btn-sm\" onclick=\"deleteNorm("+norm.id+")\" >删除</a>";
                        } else if (norm.status == 3) {
                            status = "计算失败";
                            buttons = "<a class=\"btn btn-success btn-sm\" href=\"${norm}/norm/goupdatenorm/" + norm.id + "\">更新</a>\n" +
                                "                                    <a class=\"btn btn-primary btn-sm\" href=\"${norm}/norm/rtInPage/" + norm.id + "\">导入</a>\n" +
                                "                                    <a class=\"btn btn-warning btn-sm\" onclick='figure("+norm.id+")'>计算</a>\n" +
                                "                                    <a class=\"btn btn-info btn-sm\" disabled=\"disabled\" href=\"${norm}/norm/getNormDetailed/" + norm.id + "\" >查看</a>\n" +
                                "                                    <a class=\"btn btn-default btn-sm\" disabled=\"disabled\" href=\"${norm}/norm/exportNorm/" + norm.id + "\">导出</a>\n" +
                                "                                    <a class=\"btn btn-danger btn-sm\" onclick=\"deleteNorm("+norm.id+")\" >删除</a>";
                        }


                        tbody += '<tr>\n' +
                            '                                  <td class="productName">' + norm.productName + '</td>\n' +
                            '                                  <td class="normName">' + norm.normName + '</td>\n' +
                            '                                  <td>' + norm.version + '</td>\n' +
                            '                                  <td>' + norm.sampleSize + '</td>\n' +
                            '                                  <td class="description">' + norm.description + '</td>\n' +
                            '                                  <td>' + figureTime + '</td>\n' +
                            '                                  <td>' + status + '</td>\n' +
                            '                                    <td>\n'
                            + buttons +
                            '                                    </td>\n' +
                            '                                </tr>';
                            $("#list tbody").html(tbody);
                    })
                    var pageCount = data.pageNo; //取到pageCount的值(把返回数据转成object类型)--页码
                    var currentPage = data.currentPage; //得到currentPage--当前页
                    var options = {
                        bootstrapMajorVersion: 2, //版本
                        currentPage: currentPage, //当前页数
                        totalPages: pageCount, //总页数
                        itemTexts: function (type, page, current) {
                            switch (type) {
                                case "first":
                                    return "首页";
                                case "prev":
                                    return "上一页";
                                case "next":
                                    return "下一页";
                                case "last":
                                    return "末页";
                                case "page":
                                    return page;
                            }
                        },
                        //点击事件，用于通过Ajax来刷新整个list列表
                        onPageClicked: function (event, originalEvent, type, page) {
                        	 $.post("${norm}/norm/getNormList", {"normname": name, "status": status,"page":page}, function (data1){
                                    if (data1.status == 0) {
                                       var tbody2 = "";
                                       $.each(data1.data, function (i, norm) {
					                        var status;
					                        var buttons;
					                        var figureTime;
					                        if(norm.figureTime == null||norm.figureTime == ""){
					                            figureTime =  "空";
					                        }else {
					                            figureTime = norm.figureTime;
					                        }
					                        if (norm.status == 0) {
					                            status = "待计算";
					                            buttons = "<a class=\"btn btn-success btn-sm\" href=\"${norm}/norm/goupdatenorm/" + norm.id + "\">更新</a>\n" +
					                                "                                    <a class=\"btn btn-primary btn-sm\" href=\"${norm}/norm/rtInPage/" + norm.id + "\">导入</a>\n" +
					                                "                                    <a class=\"btn btn-warning btn-sm\" onclick='figure("+norm.id+")'>计算</a>\n" +
					                                "                                    <a class=\"btn btn-info btn-sm\" disabled=\"disabled\" href=\"${norm}/norm/getNormDetailed/" + norm.id + "\" >查看</a>\n" +
					                                "                                    <a class=\"btn btn-default btn-sm\" disabled=\"disabled\" href=\"${norm}/norm/exportNorm/" + norm.id + "\">导出</a>\n" +
					                                "                                    <a class=\"btn btn-danger btn-sm\" onclick=\"deleteNorm("+norm.id+")\" >删除</a>";
					                        } else if (norm.status == 1) {
					                            status = "计算中";
					                            buttons = "<a class=\"btn btn-success btn-sm\" disabled=\"disabled\">更新</a>\n" +
					                                "                                        <a class=\"btn btn-primary btn-sm\" disabled=\"disabled\">导入</a>\n" +
					                                "                                        <a class=\"btn btn-warning btn-sm\" disabled=\"disabled\">计算</a>\n" +
					                                "                                        <a class=\"btn btn-info btn-sm\" disabled=\"disabled\" href=\"${norm}/norm/getNormDetailed/" + norm.id + "\" >查看</a>\n" +
					                                "                                        <a class=\"btn btn-default btn-sm\" disabled=\"disabled\" href=\"${norm}/norm/exportNorm/" + norm.id + "\">导出</a>\n" +
					                                "                                        <a class=\"btn btn-danger btn-sm\" onclick=\"deleteNorm("+norm.id+")\" >删除</a>";
					                        } else if (norm.status == 2) {
					                            status = "已计算";
					                            buttons = "<a class=\"btn btn-success btn-sm\" disabled=\"disabled\">更新</a>\n" +
					                                "                                        <a class=\"btn btn-primary btn-sm\" disabled=\"disabled\">导入</a>\n" +
					                                "                                        <a class=\"btn btn-warning btn-sm\" disabled=\"disabled\">计算</a>\n" +
					                                "                                        <a class=\"btn btn-info btn-sm\"  href=\"${norm}/norm/getNormDetailed/" + norm.id + "\" >查看</a>\n" +
					                                "                                        <a class=\"btn btn-default btn-sm\"  href=\"${norm}/norm/exportNorm/" + norm.id + "\">导出</a>\n" +
					                                "                                        <a class=\"btn btn-danger btn-sm\" onclick=\"deleteNorm("+norm.id+")\" >删除</a>";
					                        } else if (norm.status == 3) {
					                            status = "计算失败";
					                            buttons = "<a class=\"btn btn-success btn-sm\" href=\"${norm}/norm/goupdatenorm/" + norm.id + "\">更新</a>\n" +
					                                "                                    <a class=\"btn btn-primary btn-sm\" href=\"${norm}/norm/rtInPage/" + norm.id + "\">导入</a>\n" +
					                                "                                    <a class=\"btn btn-warning btn-sm\" onclick='figure("+norm.id+")'>计算</a>\n" +
					                                "                                    <a class=\"btn btn-info btn-sm\" disabled=\"disabled\" href=\"${norm}/norm/getNormDetailed/" + norm.id + "\" >查看</a>\n" +
					                                "                                    <a class=\"btn btn-default btn-sm\" disabled=\"disabled\" href=\"${norm}/norm/exportNorm/" + norm.id + "\">导出</a>\n" +
					                                "                                    <a class=\"btn btn-danger btn-sm\" onclick=\"deleteNorm("+norm.id+")\" >删除</a>";
					                        }

					                        tbody2 += '<tr>\n' +
					                            '                                  <td class="productName">' + norm.productName + '</td>\n' +
					                            '                                  <td class="normName">' + norm.normName + '</td>\n' +
					                            '                                  <td>' + norm.version + '</td>\n' +
					                            '                                  <td>' + norm.sampleSize + '</td>\n' +
					                            '                                  <td class="description">' + norm.description + '</td>\n' +
					                            '                                  <td>' + figureTime + '</td>\n' +
					                            '                                  <td>' + status + '</td>\n' +
					                            '                                    <td>\n'
					                            + buttons +
					                            '                                    </td>\n' +
					                            '                                </tr>';
					                           $("#list tbody").html(tbody2); 
					                           cut(8, "productName");
							                   cut(12, "description");
							                   cut(8, "normName");
					                    })
                                    }
                                })
                        }
                    }  
                    $('#normlistpaging').bootstrapPaginator(options); 
                    //对长度处理
                    cut(8, "productName");
                    cut(12, "description");
                    cut(8, "normName");
                } else {

                    $("#message").text(data.message);
                    $("#msg").show();
                }
            })
		}
		
        function cut(num, id) {

            $("." + id).each(function (i) {
                //获取td当前对象的文本,如果长度大于;
                if ($(this).text().length > num) {
                    //给td设置title属,性,并且设置td的完整值.给title属性.
                    $(this).attr("title", $(this).text());
                    //获取td的值,进行截取。赋值给text变量保存.
                    var text = $(this).text().substring(0, num) + "...";
                    //重新为td赋值;
                    $(this).text(text);
                }
            });
        }

        function deleteNorm(id){

            layer.confirm("是否删除！", {
                btn: ['删除', '取消'] //按钮
            }, function () {
                $.post("${norm}/norm/deleteNorm/"+id,function (data) {
                    layer.msg(data.message, {icon: 1});
                    getNormList();
                })
            }, function () {
                layer.msg('取消删除', {
                    time: 1000, //1s后自动关闭
                });
            });
        }
        
        
        
        //常模计算
        function figure(id){
        	console.log(id);
        	layer.confirm('常模计算可能会占用比较长的时间,计算完成会发送消息到你的邮箱,是否立即计算？', {
				  btn: ['计算','放弃'] //按钮
				}, function(){
				 $.post("${norm}/norm/figureNorm/"+id,function(data){
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
        	
        }
    </script>
</head>

<body>

<!--main content start-->
<section id="main-content">
    <section class="wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h3 class="page-header"><i class="fa fa-table"></i>常模列表页面</h3>
                <ol class="breadcrumb">
                    <!-- 					<li><i class="fa fa-home"></i><a href="norm_list_page.jsp">主页</a></li> -->
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
                            <a class="btn btn-success" href="javascript:void(0)" onclick="getNormList()">查询</a>
                            <a class="btn btn-warning" href="${norm}/menu/switch/3">添加</a>
                        </div>


                    </header>
                    <div class="table-responsive">
                        <table id="list" class="table">
                            <thead>
                            <tr>

                                <th>产品名称</th>
                                <th>常模名称</th>
                                <th>版本</th>
                                <th>样本量</th>
                                <th>描述</th>
                                <th>计算时间</th>
                                <th>状态</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>

                            </tbody>

                        </table>
                    </div>
                    <div id="normlistpaging">
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
<style type="text/css">

 	#normlistpaging ul li { 
 	display:inline;
 	list-style-type:none;
	float:left; /* 往左浮动 */
	margin-left: 10px;
	margin-right: 10px;
	font-size: 15px;
	color:#768399;
    text-align:center;/*水平居中*/
	vertical-align:bottom;/*垂直底部*/ 
	}
</style>
</html>
