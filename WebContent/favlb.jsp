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
    <link href="css/my_order.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript" src="js/slide.js"></script>
    <style>
            .ordertable{
            border:1px solid #E8E8E8;
            border-collapse: collapse;
            margin: 0px;
            padding: 0px;
            }
            .tableth{
            background: #F7F7F7;
            height: 50px;
            }
            
            .ordertable td{
            border: 1px solid #E8E8E8;
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
	<div class="position"><a href="#">首页</a> > <a href="#">会员中心</a> > <a href="#">收藏</a></div>
    
    <div class="m_d">
    	<jsp:include page="grleft.jsp"></jsp:include>
        <div class="right" style="border:0px solid red;">
        <!-- s -->
        <div class="middle" style="border:0px solid red;margin: 0px;float: left;width: 100%;padding:0px;">
        <div class="fr" style="border:0px solid black;margin: 0px;width: 100%;padding:0px;">
        <div class="c_r_o" style="margin: 0px;padding: 0px;">
            <!------------表单------------->
            <table class="ordertable"  bordercolor="#E8E8E8" cellpadding="1" cellspacing="0" >
                <tr class="tableth" >
                    <td style="font-size:12px;" align="left">商品</td>
                    <td style="font-size:12px;">操作</td>
                </tr>
                <c:forEach items="${pageInfo.list}" var="fav">
                <tr class="thitems">
                    <td  style="font-size:12px;text-align: left;padding:20px;">
                    <a href="productDetails.do?id=${fav.product.id}">
                     <img alt="" src="upload/${fav.product.filename}" style="height: 60px;">
                     </a>
                     <h6 style="border:0px solid red;"><a href="productDetails.do?id=${fav.product.id}">${fav.product.productname}</a></h6>
                     </td>
                    <td style="width: 120px;font-size:12px;">
                    <a href="favDel.do?id=${fav.id}" style="color: green">删除</a>
                    
                    </td>
                </tr>
                </c:forEach>
            </table>
            <!--------------列表----------->
            <!-------------------列表----------------------->
        </div>
        </div>
        </div>
        
        <!-- e -->
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
<%
String msg = (String)request.getAttribute("msg");
if(msg!=null){
%>
layer.msg('<%=msg%>');
<%}%>
</script>
</html>
