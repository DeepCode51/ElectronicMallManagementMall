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
    <link href="css/gouwuche.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
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

<!--------------------内容----------------------->
<div class="wt1080">
    <!--------------标题----------->
    <div class="my_car">
        <h1>我的购物车</h1>
        <div class="place">
            <ul>
                <li class="current"><span>1</span><p>我的购物车</p></li>
                <li><span>2</span><p>提交订单</p></li>
                <li><span>3</span><p>选择支付方式</p></li>
            </ul>
            <span class="heng"></span>
        </div>
    </div>
    <!--------------选择----------->
    <div class="details">
        <div class="title">
            <div style="text-align: left;width: 84px;">&nbsp;</div>
            <div style="width: 432px;">商品</div>
            <div style="width: 141px;">单价</div>
            <div style="width: 141px;">数量</div>
            <div style="width: 141px;">合计</div>
            <div style="width: 141px;">操作</div>
        </div>
        <!-----------------------商品展示---------------------------->
        <div class="goods">
            <script>

                $(function(){
                    $(".g_one a").toggle(function () {
                                $(this).children('img').attr('src','image/k1.png');
                            },function () {
                                $(this).children('img').attr('src','image/k2.png');
                            }
                    );
                });

            </script>
            <c:forEach items="${cartlist}" var="cart">
            <div class="goods_details">
                <div class="g_one"><a href="javascript:;">&nbsp;</div>
                <div class="g_two">
                    <div class="h">
                        <div class="pic"><a href="#"><img src="upload/${cart.product.filename}"></a></div>
                        <div class="miaoshu">
                            <p style="margin-top: 5px;"><a href="#">${cart.product.productname}</a></p>
                        </div>
                    </div>
                </div>
                <div class="g_three">
                <c:choose>
                  <c:when test="${cart.product.tprice gt 0}">
                  	<p class="y">￥${cart.product.price}</p>
                	<p class="k">￥${cart.product.tprice}</p>
                  </c:when>
                  <c:otherwise>
                  <p style="margin-bottom: 22px;">&nbsp;</p>
                  <p class="k">￥${cart.product.price}</p>
                  </c:otherwise>
                </c:choose>
                </div>
                <div class="g_four">
                    <div>
                        <span style="border-right: 1px solid #eeeeee" onclick="jj('zuo','${cart.id}')">-</span>
                        <input name="" type="text" value="${cart.num}" id="num${cart.id}" style="height: 20px;">
                        <span style="border-left: 1px solid #eeeeee" onclick="jj('you','${cart.id}')">+</span>
                    </div>
                </div>
                <div class="g_five"><p>￥${cart.subtotal}</p></div>
                <div class="g_six"><span onclick="removecart('${cart.id}')"></span></div>
            </div>
			</c:forEach>
            
            
        </div>
        <!------------------下部分------------------>
        <div class="d_d">
            <div class="d_d_l" style="border:0px solid red;">
                <a href="javascript:;" id="all">&nbsp;</a>
                <a href="#" style="margin-left: 120px;">&nbsp;</a>
            </div>

            <div class="d_d_r">
                <p class="o">已选商品<span>${fn:length(cartlist)}</span>件<font>总价
                	
                                  ：<span>${totalstr}</span></font></p>
            </div>
            <c:choose>
              <c:when test="${fn:length(cartlist) gt 0}">
              <a href="createDd.do?totalstr=${totalstr}" class="jiesuan">创建订单</a>
              </c:when>
              <c:otherwise>
              <a href="index.do" class="jiesuan">快去选购商品吧</a>
              </c:otherwise>
            </c:choose>
        </div>
    </div>
    <!---------------------热销------------------>
    
</div>





<!---------------------保障------------------->
<jsp:include page="foot.jsp"></jsp:include>
</body>
<script type="text/javascript">
function removecart(id){
	$.ajax({
		type:"post",
		url:"delCart.do?id="+id,
		date:"",
		success:function(msg){
			location.replace("cartList.do");
		}
	})
}

function jj(no,id){
	var num = $("#num"+id).val();
	if(no.trim()=="you"){
		num=parseInt(num)+1;
	}else{
		if(num>1){
		num=parseInt(num)-1;
		}else{
			num=1;
		}
	}
	$("#num").val(num);
	updateNum(num,id);
}

function updateNum(num,id){
	$.ajax({
		type:"post",
		url:"updateNum.do?id="+id+"&num="+num,
		date:"",
		success:function(msg){
		  location.reload();
			//layer.msg("添加购物车成功");
	}
	})
}
</script>
</html>


