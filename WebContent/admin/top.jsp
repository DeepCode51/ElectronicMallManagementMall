<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.entity.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
User usersession = (User)session.getAttribute("admin");
if(usersession==null){
out.print("<script>location.replace(\"/onlineshopssm/admin/login.jsp\");</script>");
}
%>
<!DOCTYPE html>
<html>
<head>
<title>电子商城</title>
</head>
<body>
<!--header start-->
<!--logo start-->
<div class="brand">
    <a href="index.do" class="logo">
        后台管理
    </a>
    <div class="sidebar-toggle-box">
        <div class="fa fa-bars"></div>
    </div>
</div>
<!--logo end-->
<div class="nav notify-row" id="top_menu">
    <!--  notification start -->
    <ul class="nav top-menu">
        <!-- settings start -->
        <!-- settings end -->
        <!-- inbox dropdown start-->
        <!-- inbox dropdown end -->
        <!-- notification dropdown start-->
        <!-- notification dropdown end -->
    </ul>
    <!--  notification end -->
</div>
<div class="top-nav clearfix">
    <!--search & user info start-->
    <ul class="nav pull-right top-menu">
    <!-- 
        <li>
            <input type="text" class="form-control search" placeholder="搜索">
        </li>
         -->
        <!-- user login dropdown start-->
        <li class="dropdown">
            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                <span class="username" style="padding-left: 5px;">${sessionScope.admin.realname}(${sessionScope.admin.usertype})</span>
                <b class="caret"></b>
            </a>
            <ul class="dropdown-menu extended logout">
            <c:if test="${sessionScope.admin.usertype eq '商家'}">
                <li><a href="grInfo.do"><i class=" fa fa-suitcase"></i>个人信息</a></li>
            </c:if>
                <li><a href="updatepwd.jsp"><i class="fa fa-cog"></i>密码修改</a></li>
                <li><a href="login.jsp"><i class="fa fa-key"></i>安全退出</a></li>
            </ul>
        </li>
    </ul>
</div>
</body>
<script type="text/javascript"></script>
</html>
