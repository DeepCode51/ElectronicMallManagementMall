<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//Dtd XHTML 1.0 Transitional//EN" "http://www.w3.org/tr/xhtml1/Dtd/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="x-ua-compatible" content="IE=edge, chrome=1">
    <title>电子商城</title>
    <meta name="description" content="" />
    <meta name="keywords" content="" />
    <link href="css/public.css" type="text/css" rel="stylesheet"/>
    <link href="css/liebiao.css" type="text/css" rel="stylesheet"/>
    <link href="css/reg.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript" src="js/slide.js"></script>
    <style type="text/css">
    input{
    padding-left:10px;
    }
    </style>
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
	<div class="position"><a href="#">首页</a> > <a href="#">会员中心</a> > <a href="#">个人资料</a></div>
    
    <div class="m_d">
    	<jsp:include page="grleft.jsp"></jsp:include>
        <div class="right" >
             <dl class="dl01">
            	<dt><img src="images/ico04.jpg"/></dt>
                <dd style="border:0px solid red;">
					<table width="725" border="0" cellpadding="0" cellspacing="0">
                      <tr>
                        <td width="416" class="td01">尊敬的，<font>${member.tname}</font> 您好！<span></span></td>
                        <td width="44">余额：</td>
                        <td width="122"><span>${member.money}元</span></td>
                      </tr>
                      <tr>
                        <td width="416" class="td01"></td>
                        <td width="44">积分：</td>
                        <td width="122"><span>${member.jf}</span></td>
                        <td width="122" align="right"></td>
                        <td width="87"><span></span></td>
                      </tr>
                    </table>
                    
                </dd>
            </dl>
            <dl class="dl02">
            	<dt>
                	<span>个人资料</span>
                </dt>
                <dd>
                <form action="memberEdit.do">
                <input type="hidden" name="id" value="${member.id}"/>
                	<div class="item"><span>用户名：</span><input type="text" class="txt" name="uname" value="${member.uname}" placeholder="用户名" required oninvalid="setCustomValidity('请输入用户名')" oninput="setCustomValidity('');"/></div>
                    <div class="item"><span>密码：</span><input type="password" class="txt" name="upass" value="${member.upass}" placeholder="密码" required oninvalid="setCustomValidity('请输入密码')" oninput="setCustomValidity('');"/></div>
                    <div class="item"><span>姓名：</span><input type="text" class="txt" name="tname" value="${member.tname}" placeholder="姓名" required oninvalid="setCustomValidity('请输入姓名')" oninput="setCustomValidity('');"/></div>
                    <div class="item"><span>生日：</span><input type="date" class="txt" name="brithtime" value="${member.brithtime}" placeholder="生日" required oninvalid="setCustomValidity('请输入生日')" oninput="setCustomValidity('');"/></div>
                    <div class="item"><span>电话：</span><input type="text" class="txt" name="tel" value="${member.tel}" placeholder="电话" required oninvalid="setCustomValidity('请输入电话')" oninput="setCustomValidity('');"/></div>
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
