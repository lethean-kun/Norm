<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
pageContext.setAttribute("norm", request.getContextPath()) ;
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="产品使用权限设置">
    <meta name="author" content="高庆佳">
    <meta name="keyword" content="产品使用权限设置">
	<link rel="shortcut icon" href="${norm}/img/normicon.png">
    <title>产品使用权限设置</title>

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
    <link href="${norm}/css/style-responsive.css" rel="stylesheet" />

  	<%@include file="common/header.jsp"%>
  </head>

  <body>

      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-table"></i>产品使用权限设置</h3>
					<ol class="breadcrumb">
	<!-- 					<li><i class="fa fa-home"></i><a href="${norm}/menu/switch/1">主页</a></li> -->
						<li><i class="fa fa-table"></i>产品使用权限设置</li>
					</ol>
				</div>
			</div>
              <!-- page start-->
                  <form action="">
                              <div class="row">
                                  <div class="col-sm-12">
                                      <section class="panel">
                                          <table class="table table-bordered">
                                              <tbody>
                                              <tr>
                                                  <td>用户名：</td>
                                                  <td>
                                                      <select name="" id="ccc">
                                                          <option value="">1111111111</option>
                                                          <option value="">222222222</option>
                                                          <option value="">333333333</option>
                                                      </select>
                                                  </td>

                                              </tr>
                                              <tr>
                                                  <td>用户编号：</td>
                                                  <td>
                                                      <select name="" id="bbb">
                                                          <option value="">1111111111</option>
                                                          <option value="">222222222</option>
                                                          <option value="">333333333</option>
                                                      </select>
                                                  </td>
                                              </tr>
                                              <tr>
                                                  <td>产品类型：</td>
                                                  <td>
                                                      <select name="" id="aaa">
                                                          <option value="">1111111111</option>
                                                          <option value="">222222222</option>
                                                          <option value="">333333333</option>
                                                      </select>
                                                  </td>
                                              </tr>

                                              <tr>
                                                  <td>产品名称：</td>
                                                  <td>

                                                      <div>
                                                          <label><input name="" type="checkbox" value="" />xxxx</label>
                                                          <label><input name="" type="checkbox" value="" />xxxx </label>
                                                          <label><input name="" type="checkbox" value="" />xxxx</label>
                                                          <label><input name="" type="checkbox" value="" />xxxx</label>
                                                          <br>
                                                          <label><input name="" type="checkbox" value="" />xxxx</label>
                                                          <label><input name="" type="checkbox" value="" />xxxx </label>
                                                          <label><input name="" type="checkbox" value="" />xxxx</label>
                                                          <label><input name="" type="checkbox" value="" />xxxx</label>
                                                          <br>
                                                          <label><input name="" type="checkbox" value="" />xxxx</label>
                                                          <label><input name="" type="checkbox" value="" />xxxx </label>
                                                          <label><input name="" type="checkbox" value="" />xxxx</label>
                                                          <label><input name="" type="checkbox" value="" />xxxx</label>
                                                          <br>
                                                          <label><input name="" type="checkbox" value="" />xxxx</label>
                                                          <label><input name="" type="checkbox" value="" />xxxx </label>
                                                          <label><input name="" type="checkbox" value="" />xxxx</label>
                                                          <label><input name="" type="checkbox" value="" />xxxx</label>
                                                      </div>
                                                      ....
                                                  </td>
                                              </tr>
                                              </tbody>
                                          </table>
                                      </section>
                                  </div>
                              </div>
                      <div style="text-align: center">
                          <button type="button" class="btn btn-primary">确定</button>&nbsp;&nbsp;&nbsp;&nbsp;
                          <a href="${norm}/menu/switch/2" class="btn btn-default" >返回</a>
                      </div>


                  </form>

              <!-- page end-->
      </section>
      <!--main content end-->


  </body>
</html>
