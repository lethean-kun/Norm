<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("norm", request.getContextPath()) ;
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="常模明细页面,明细,常模明细">
    <meta name="author" content="高庆佳">
    <meta name="keyword" content="常模明细页面,明细,常模明细">
  	<link rel="shortcut icon" href="${norm}/img/normicon.png">
    <title>常模明细页面</title>

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

      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
		  <div class="row">
				<div class="col-lg-12">
					<h3 class="page-header"><i class="fa fa-table"></i>常模明细页面</h3>
					<ol class="breadcrumb">
						<li><i class="fa fa-table"></i>常模明细</li>
					</ol>
				</div>
			</div>
              <!-- page start-->

              <div class="row">
              <div class="col-sm-12">
                  <section class="panel">
                      <header class="panel-heading no-border">
                          基本信息：
                      </header>
                      <table class="table table-bordered">
                          <tbody>
                          <tr>
                              <td>常模名称：${Norm.normName}</td>

                          </tr>
                          <tr>
                              <td>版本：${Norm.version}</td>

                          </tr>
                          <tr>
                              <td>描述：${Norm.description}</td>

                          </tr>
                          </tbody>
                      </table>
                  </section>
              </div>
          </div>
              <div class="row">
                  <div class="col-sm-12">
                      <section class="panel">
                          <header class="panel-heading no-border">
                              计算项：
                          </header>
                          <table class="table table-bordered">
                              <tbody>
                              <tr>
                                  <td>名称：</td>
                                  <td>平均值</td>
                                  <td>标准差</td>
                              </tr>
                                <c:forEach items="${normDetailed}" var="ndList">
                                    <tr>
                                        <td class="warning" colspan="3">${ndList.key}</td>
                                    </tr>
                                    <c:forEach items="${ndList.value}" var="nd">
                                        <tr>
                                            <td>${nd.module.modulename}</td>
                                            <td>
                                            <fmt:formatNumber type="number" value="${nd.average}" pattern="0.0000000000"/>
                                            </td>
                                            <td>
                                            <fmt:formatNumber type="number" value="${nd.standardScore}" pattern="0.0000000000"/>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:forEach>


                              </tbody>
                          </table>
                          <div style="text-align: center;padding-top: 20px">
                              <a href="${norm}/menu/switch/1" class="btn btn-danger">关闭</a>
                          </div>
                      </section>
                  </div>
              </div>
              <!-- page end-->
          </section>
      </section>


  </body>
</html>
