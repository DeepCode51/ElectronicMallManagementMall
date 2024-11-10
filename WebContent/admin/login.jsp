<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
session.removeAttribute("admin");
%>
<!DOCTYPE html>
<html>
<head>
<title>电子商城</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Visitors Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- bootstrap-css -->
<link rel="stylesheet" href="css/bootstrap.min.css" >
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href="css/style-responsive.css" rel="stylesheet"/>
<!-- font CSS -->
<!-- font-awesome icons -->
<link rel="stylesheet" href="css/font.css" type="text/css"/>
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<script src="js/jquery2.0.3.min.js"></script>
	<script type="text/javascript" src="<%=path %>/layer/layer.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=path %>/layer/layui.css">
</head>
<body>
<div class="log-w3">
<div class="w3layouts-main">
	<h2>电子商城</h2>
		<form action="<%=path%>/admin/login.do" method="post" name="userlogin" id="userlogin" >
			<input type="text" class="ggg" placeholder="账号" name="username" id="username" required oninvalid="setCustomValidity('账号不能为空')" oninput="setCustomValidity('');">
			<input type="password" class="ggg"  placeholder="密码" name="userpassword" id="userpassword" required oninvalid="setCustomValidity('密码不能为空')" oninput="setCustomValidity('');">
			<h6><a href="#"></a></h6>
				<div class="clearfix"></div>
				<input type="submit" value="登录" >
		</form>
</div>
</div>
<script src="js/bootstrap.js"></script>
<script src="js/jquery.dcjqaccordion.2.7.js"></script>
<script src="js/scripts.js"></script>
<script src="js/jquery.slimscroll.js"></script>
<script src="js/jquery.nicescroll.js"></script>
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="js/flot-chart/excanvas.min.js"></script><![endif]-->
<script src="js/jquery.scrollTo.js"></script>
<script type="text/javascript">
<%
String error = (String)request.getAttribute("error");
if(error!=null){
%>
layer.msg("用户名或密码错误");
<%}%>
</script>
</body>
</html>
