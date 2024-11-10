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
	<div class="position"><a href="#">首页</a> > <a href="#">会员中心</a> > <a href="#">个人资料</a></div>
    
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
                    <td style="font-size:12px;">订单编号</td>
                    <td style="font-size:12px;">总计（元）</td>
                    <td style="font-size:12px;">订单状态</td>
                    <td style="font-size:12px;">支付方式</td>
                    <td style="font-size:12px;">收货地址</td>
                    <td style="font-size:12px;">操作</td>
                </tr>
                <c:forEach items="${list}" var="ordermsg">
                <tr class="thitems">
                    <td style="width: 90px;font-size:12px;height: 40px;"><a style="color:#FFAA25" href="orderdetailsLb.do?ddno=${ordermsg.ddno}">${ordermsg.ddno}</a> </td>
                    <td style="width: 120px;font-size:12px;">${ordermsg.total}</td>
                    <td style="width: 120px;font-size:12px;">${ordermsg.fkstatus }</td>
                    <td style="width: 120px;font-size:12px;">${ordermsg.zffs }</td>
                    <td style="width: 120px;font-size:12px;">${ordermsg.address.addr}&nbsp;&nbsp;${ordermsg.address.name}&nbsp;&nbsp;${ordermsg.address.tel}</td>
                    <td style="width: 120px;font-size:12px;">
                    
                    <c:if test="${ordermsg.fkstatus eq '待付款'}">
                    <a href="skipFukuan.do?id=${ordermsg.id}" style="color: green">支付</a>
                    <a href="qxOrdermsg.do?id=${ordermsg.id}" style="color: green">取消订单</a>
                    </c:if>
                    
                    <c:if test="${ordermsg.fkstatus eq '已发货'}">
                    <a href="qianShou.do?id=${ordermsg.id}" style="color: green">签收</a>
                    </c:if>
                    
                    
                    <c:if test="${ordermsg.fkstatus eq '交易完成' or ordermsg.fkstatus eq '已取消'}">
                    <a href="orderSc.do?id=${ordermsg.id}" style="color: green">删除</a>
                    </c:if>
                    
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
