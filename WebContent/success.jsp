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
<jsp:include page="top.jsp"></jsp:include>
<!---------------------导航完--------------------->

<div class="wt1080">
    <!--------------标题----------->
    <div class="my_order1">
        <h1>支付成功</h1>
        <div class="place">
            <ul>
                <li class="current"><span>1</span><p>我的购物车</p></li>
                <li class="current"><span>2</span><p>提交订单</p></li>
                <li class="current"><span>3</span><p>选择支付方式</p></li>
                <li class="current"><span>4</span><p>支付成功</p></li>
            </ul>
            <span class="heng"></span>
        </div>
    </div>
    <!-----------订单提交-------------->
    <div class="finish" style="margin-bottom: 100px;">
        <div class="rr">
            <span class="one">支付成功!恭喜你获得免费抽奖一次</span>
            <span class="two" onclick="cj()" style="cursor: pointer;"><font >抽奖</font></span>
        </div>
    </div>
    <!----------------支付信息------------------->
</div>


<!---------------------保障------------------->
<jsp:include page="foot.jsp"></jsp:include>
</body>
<script type="text/javascript">
function cj(){
	$.ajax({
		type:"post",
		url:"choujiang.do",
		date:"",
		success:function(res){
			//alert(msg);
		//layer.alert(res);
		location.replace("orderLb.do?msg="+res);
		}
	})
}
</script>
</html>



