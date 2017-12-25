<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	pageContext.setAttribute("norm", request.getContextPath()) ;
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="用户登录页面">
    <meta name="author" content="gaoqj">
    <meta name="keyword" content="用户登录">
    <link rel="shortcut icon" href="img/normicon.png">

    <title>常模登录</title>

    <!-- Bootstrap CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- bootstrap theme -->
    <link href="css/bootstrap-theme.css" rel="stylesheet">
    <!--external css-->
    <!-- font icon -->
    <link href="css/elegant-icons-style.css" rel="stylesheet" />
    <link href="css/font-awesome.css" rel="stylesheet" />
    <!-- Custom styles -->
    <link href="css/style.css" rel="stylesheet">
    <script src="js/jquery-3.2.1.js"></script>
    <script src="js/jquery-3.2.1.min.js"></script>
    <script src="js/jquery.form.js"></script>

</head>

<body class="login-img3-body">

<div class="container">


    <form class="login-form" action="${norm}/userLogin/login">
        <div class="login-wrap">
            <p class="login-img"><i class="icon_lock_alt"></i></p>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_profile"></i></span>
                <input type="text" class="form-control" placeholder="用户名" autofocus required="required" name="username">
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_key_alt"></i></span>
                <input type="password" class="form-control" placeholder="密码" required="required" name="password">
            </div>
            <!--<label class="checkbox">-->
            <!--<input type="checkbox" value="remember-me">记住我-->
            <!--<span class="pull-right"> <a href="#">忘记密码</a></span>-->
            <!--</label>-->

            <button id="login" class="btn btn-primary btn-lg btn-block" type="submit">登录</button>
            <!--<button class="btn btn-info btn-lg btn-block" type="submit">Signup</button>-->
            <span id="error" style="color:red;text-align:center"></span>
        </div>
    </form>
</div>


</body>
<script type="text/javascript">
    $(document).ready(function() {
        $(".login-form").ajaxForm(function(data){
            if(data.status == 0){
                window.location.href="${norm}/menu/switch/1";
            }else{
                $("#error").text(data.message);
            }
        });
    });
</script>
</html>
