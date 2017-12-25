<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("norm", request.getContextPath()) ;
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->    
    <link href="${norm}/css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="${norm}/css/bootstrap-theme.css" rel="stylesheet">
    <!-- font icon -->
    <link href="${norm}/css/elegant-icons-style.css" rel="stylesheet" />
    <link href="${norm}/css/font-awesome.min.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="${norm}/css/style.css" rel="stylesheet">

    <script>
        function show(){
            $("#logout").toggle(500);
        }
        function logout() {
            $.post("${norm}/userLogin/logout",function (data) {

                if(data.status == 0){
                    location.href = "${norm}";
                }
            })
        }
    </script>

  </head>

  <body>
  <!-- container section start -->
  <section id="container" class="">
      <!--header start-->
      <header class="header dark-bg">
            <div class="toggle-nav">
                <div class="icon-reorder tooltips" data-original-title="Toggle Navigation" data-placement="bottom"></div>
            </div>

            <!--logo start-->
            <a href="#" class="logo" style="color: #1dd388">智鼎优源 <span class="lite"></span></a>
            <!--logo end-->
            <div class="top-nav notification-row">                
                <!-- notificatoin dropdown start-->
                <ul class="nav pull-right top-menu">
                    

                    <!-- user login dropdown start-->
                    <li class="dropdown">
                        <a data-toggle="dropdown" class="dropdown-toggle" onclick="show()" href="#">
  <!--                           <span class="profile-ava">
                                <img alt="" src="img/avatar1_small.jpg">
                            </span> -->
                            <span class="username">${u.name}</span>
                            <b class="caret"></b>
                        </a>
                        <ul class="dropdown-menu extended logout" id="logout">
                            <div class="log-arrow-up"></div>

                            <li>
                                <a href="javascript:void(0)" onclick="logout()" id="out"><i class="icon_key_alt"></i>退出</a>
                            </li>
                        </ul>
                    </li>
                    <!-- user login dropdown end -->
                </ul>
                <!-- notificatoin dropdown end-->
            </div>
      </header>      
      <!--header end-->

      <!--sidebar start-->

      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu">
                  <li>
                      <a class="" href="${norm}/menu/switch/1">
                          <i class="icon_piechart"></i>
                          <span>常模列表</span>
                      </a>

                  </li>

                 <%--  <li class="sub-menu">
                      <a href="${norm}/menu/switch?menu=2" class="">
                          <i class="icon_table"></i>
                          <span>常模明细</span>
                      </a>

                  </li> --%>
                  <li class="sub-menu">
                      <a href="${norm}/menu/switch/2" class="">
                          <i class="icon_table"></i>
                          <span>产品负责情况</span>
                      </a>

                  </li>
                 <!--  <li class="sub-menu">
                      <a href="import_evaluationData_page.jsp" class="">
                          <i class="icon_table"></i>
                          <span>导入测评数据</span>
                      </a>
                  </li>
                  <li class="sub-menu">
                      <a href="productUse__rightsSetting_page.jsp" class="">
                          <i class="icon_table"></i>
                          <span>产品使用权限设置</span>
                      </a>
                  </li> -->
              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
	</section>
  </body>
</html>
