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
    <link href="css/order_1.css" type="text/css" rel="stylesheet"/>
    <link href="css/reg.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript" src="js/slide.js"></script>
    <style type="text/css">
	#zffsdiv{
	width: 100%;
	height: 100px;
	border:1px solid #EEEEEE;
	margin-bottom: 30px;
	border-radius:5px 5px;
	padding-top: 40px;
	}
	
	#zffsdiv li{
    border:0px solid red;
    float: left;
    margin-right: 10px;
	}
	
	#zffsdiv ul{
   list-style: none;
	}
	
	.zfimg{
	 width: 180px;height: 50px;
	border:1px solid red;
	}
	
	.zfimg img{
	border:1px solid #EEEEEE;
	}
	
	.zfradio{
	 height: 50px;
	 line-height: 50px;
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
<jsp:include page="top.jsp"></jsp:include>
<!---------------------导航完--------------------->

<div class="wt1080">
    <!--------------标题----------->
    <div class="my_order1">
        <h1>提交订单</h1>
        <div class="place">
            <ul>
                <li class="current"><span>1</span><p>我的购物车</p></li>
                <li class="current"><span>2</span><p>提交订单</p></li>
                <li class="current"><span>3</span><p>选择支付方式</p></li>
            </ul>
            <span class="heng"></span>
        </div>
    </div>
    <!-----------订单提交-------------->
    <!----------------支付信息------------------->
    <form action="fukuan.do" method="post">
    <input type="hidden" name="addrid" value="${addrid}">
    <input type="hidden" name="id" value="${id}">
    <input type="hidden" name="ticketid" value="${ticketid}">
    <input type="hidden" name="sjtotal" value="${sjtotal}">
    <div class="pay">
        <div class="roo">支付信息<span></span></div>
        <div class="kk">
            <div class="tt" style="border-bottom: 0px;">
                支付金额：<span>￥${sjtotal}</span>
                <a href="#">关于实名认证？</a>
            </div>
            
            <div  style="border:0px solid red;">
                <!-- 支付方式 -->
                <div id="zffsdiv" >
	    		  <ul style="padding-left: 20px;">
	    		    <li class="zfradio"><input type="radio" name="zffs" checked="checked" value="微信"/></li>
	    		    <li class="zfimg">
	    		      <img alt="" src="images/ico16.jpg" style="height: 50px;width: 90%">
	    		    </li>
	    		    
	    		    <li class="zfradio"><input type="radio" name="zffs" value="支付宝"/></li>
	    		    <li class="zfimg" style="padding-left: 20px;border:0px solid red;">
	    		      <img alt="" src="images/ico15.jpg"  style="height: 50px;width: 90%">
	    		    </li>
	    		    
	    		    <li class="zfradio"><input type="radio" name="zffs" value="中国建设银行"/></li>
	    		    <li class="zfimg" style="padding-left: 20px;border:0px solid red;">
	    		      <img alt="" src="images/ico19.jpg"  style="height: 50px;width: 90%">
	    		    </li>
	    		    
	    		    
	    		    <li class="zfradio"><input type="radio" name="zffs" value="中国银行"/></li>
	    		    <li class="zfimg" style="padding-left: 20px;border:0px solid red;">
	    		      <img alt="" src="images/ico18.jpg"  style="height: 50px;width: 90%">
	    		    </li>
	    		    
	    		    
	    		    
	    		    
	    		  </ul>
	    		</div>
                <!-- 支付方式 -->
                <input type="submit" value="确认提交" style="border:1px solid #FF5500;background: #FF5500;width: 120px;height: 40px;color:white;font-size:16px;cursor: pointer;margin-bottom: 40px;">
            </div>
            
        </div>
    </div>
    </form>
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



