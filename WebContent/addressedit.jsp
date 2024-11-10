<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" "http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1">
    <title>毛绒玩具电商平台</title>
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <link href="css/public.css" type="text/css" rel="stylesheet"/>
    <link href="css/liebiao.css" type="text/css" rel="stylesheet"/>
    <link href="css/reg.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript" src="js/slide.js"></script>
</head>
<script>
    $(function(){
        $('.nav ul li').hover(function(){
            $(this).children(".details").show();
        },function(){
            $(this).children(".details").hide();
        });
        $('#my').hover(function(){
            $(this).find("div").show();
        },function(){
            $(this).find("div").hide();
        });
    });
</script>

<body>
<!------------顶部---------------->
<!--------------logo搜索------------->
<!--------------导航------------------>
<jsp:include page="top.jsp"></jsp:include>
<!---------------------导航完--------------------->
<div class="w1200" style="margin-bottom: 60px;">
	<div class="position"><a href="#">首页</a> > <a href="#">会员中心</a> > <a href="#">编辑收货地址</a></div>
    
    <div class="m_d">
        <div class="right" style="float: left;width: 100%">
            <dl class="dl02">
            	<dt>
                	<span>编辑收货地址</span>
                </dt>
                <dd>
                <form action="addressEdit.do" method="post">
                <input type="hidden" name="id" value="${address.id }"/>
                <input type="hidden" name="orderid" value="${orderid }"/>
                	<div class="item"><span>姓名：</span><input type="text" class="txt" name="name" value="${address.name }" style="padding-left:5px;" placeholder="姓名" required oninvalid="setCustomValidity('请输入姓名')" oninput="setCustomValidity('');"/></div>
                    <div class="item"><span>联系电话：</span><input type="text" class="txt" name="tel" value="${address.tel }"  style="padding-left:5px;" placeholder="联系电话" required oninvalid="setCustomValidity('请输入联系电话')" oninput="setCustomValidity('');"  pattern="[1][3,4,5,7,8][0-9]{9}"/></div>
                    <div class="item"><span>收货地址：</span><input type="text" class="txt" name="addr" value="${address.addr }"  style="padding-left:5px;" placeholder="收货地址" required oninvalid="setCustomValidity('收货地址')" oninput="setCustomValidity('');"/></div>
                    <div class="item"><input type="submit" class="sub" value="保存"/></div>
                </form>
                </dd>
            </dl>
        </div>
        <div class="clear"></div>
    </div>
</div>



<!---------------------保障------------------->
<jsp:include page="foot.jsp"></jsp:include>
</body>
<script type="text/javascript">


<%
String suc = (String)request.getAttribute("suc");
if(suc!=null){
%>
layer.msg('<%=suc%>');
<%}%>
</script>
</html>
