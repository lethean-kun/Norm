<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
pageContext.setAttribute("norm", request.getContextPath());
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="常模更新">
    <meta name="author" content="高庆佳">
    <meta name="keyword" content="常模更新">
    <link rel="shortcut icon" href="${norm}/img/normicon.png">

    <title>常模更新</title>

    <!-- Bootstrap CSS -->    
    <link href="${norm}/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="${norm}/css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="${norm}/css/elegant-icons-style.css" rel="stylesheet" />
    <link href="${norm}/css/font-awesome.min.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="${norm}/css/style.css" rel="stylesheet">
    <%@include file="common/header.jsp"%>
  </head>

  <body>
  <!-- container section start -->

      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-table"></i>常模更新</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-home"></i><a href="${norm}/menu/switch/1">主页</a></li>
						<li><i class="fa fa-table"></i>常模更新</li>
					</ol>
				</div>
			</div>
              <!-- page start-->

              <div class="row">
                  <form action="">
                      <div class="col-sm-12">
                          <section class="panel">
                              <table class="table table-bordered">
                                  <tbody>
                                  <tr>
                                      <td>常模名称：</td>
                                      <td>
                                      	  <input type="hidden" id="normid" value="${normId}">
                                      	  <input type="hidden" id="proid" value="${proid}">
                                          <input type="text" name="normname" id="normName" required="required" value="${normName}">
                                      </td>
                                  </tr>
                                  <tr>
                                      <td>版本：</td>
                                      <td>
                                          <input type="text" name="version" id="version" required="required" value="${version}">
                                      </td>
                                  </tr>
                                  <tr>
                                      <td>描述：</td>
                                      <td> 
                                      	<textarea rows="3" cols="50" id="description" name="description" required="required">${description}</textarea>
                                      </td>
                                  </tr>
                                  <tr>
                                      <td>计算项：</td>
                                      <td>
										<textarea type="text" value="" id="SelectedModuleNames" name="moduleNames"  readonly="readonly" style="border:none;overflow-x:hidden;width:60%" >${moduleName}</textarea>
                                      </td>
                                  </tr>
                                  <tr style="text-align: center">
                                      <td colspan="2">
                                          <a onclick="modifyNorm()" class="btn btn-primary">保存</a>
                                          <a href="${norm}/menu/switch/1" class="btn btn-primary">返回</a>
                                      </td>
                                  </tr>
                                  </tbody>
                              </table>
                          </section>
                      </div>
                  </form>
              </div>
          </section>
      </section>
  </body>
  <!-- javascripts -->
  <script src="${norm}/js/jquery-3.2.1.min.js"></script>
  <script src="${norm}/js/jquery-2.1.1.js"></script>
  <script src="${norm}/js/bootstrap.min.js"></script>
  <script src="${norm}/js/bootstrap.js"></script>
  <script src="${norm}/js/layer.js"></script>
  <script type="text/javascript">
  	function modifyNorm(){
  		var normid =$("#normid").val();
  		var proid = $("#proid").val();
  		var normName=$("#normName").val();
  		var version=$("#version").val();
  		var description=$("#description").val();
  		console.log("proid"+proid);
  		if(normName==null || normName ==""){
  			layer.alert("常模名称不能为空！");
  			return;
  		}
  		if(version==null || version ==""){
  			layer.alert("常模版本不能为空！");
  			return;
  		}
  		if(description==null || description ==""){
  			description=null;
  		}	
  		$.ajax({
  			url:"${norm}/norm/doupdatenorm/"+normid,
  			type:"post",
  			dataType:"json",
  			data:{proid:proid,normName:normName,version:version,description:description},
  			success:function(data){
  				if(data.status==0){
  					layer.alert('更新成功', function(){
					  window.location.href="${norm}/menu/switch/1";
					})
  				}else{
  					layer.alert(data.message);
  				}	
  			},
  			error:function(){
  				layer.alert("服务器开小差中0.0");
  			}
  		})
  	}
  </script>
</html>
