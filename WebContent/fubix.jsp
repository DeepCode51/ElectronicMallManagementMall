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
    <link href="css/show.css" type="text/css" rel="stylesheet"/>
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

<script type="text/javascript" src="js/jquery.imagezoom.min.js"></script>
<script type="text/javascript" src="js/owl.carousel.min.js"></script>
<div class="wt1080">
    <!----------------位置--------------------->
    <div class="place">
        当前位置：<span class="check">${product.productname}</span>
    </div>
    <!-------------商品详细----------------->
    <div class="property">
        <div class="left">
            <div class="left_top">
                <img src="upload/${product.filename}" class="jqzoom" style="width: 400px;height: 370px;">
                <a href="#" class="search"></a>
            </div>
        </div>
        <script>
            $(function() {
                $(".jqzoom").imagezoom();

                $('#scroll').owlCarousel({
                    items: 4,
                    autoPlay: false,
                    navigation: true,
                    navigationText: ["",""],
                    scrollPerPage: true
                });
            });
        </script>
        <div class="right">
            <a href="#" class="title">${product.productname}</a>
            <div class="aa" style="width: 300px;">
                <span class="a">当前价格</span>
                  <span class="b">${product.jf}积分</span>
            </div>
            <table>
                <tr>
                    <td class="one" style="width: 30px;">数量</td>
                    <td>
                        <div class="change">
                            <span class="zuo" onclick="jj('zuo')">-</span>
                            <input name="num" id="num" type="text" value="1">
                            <span class="you" onclick="jj('you')">+</span>
                        </div>
                        <span class="tishi"></span>
                    </td>
                </tr>
            </table>
            <!-----------------购买按钮--------------->
            <div class="shopping">
                <a href="javascript:void(0)" onclick="addOrder('${product.id}')" class="buy">兑换</a>
                <div class="clear"></div>
            </div>
            <!-----------保障---------------->
        </div>
        <div class="clear"></div>
    </div>

    <!-----------------下半部分------------------->
    <div class="details">
        <div class="details_left">
            <div class="d_l_t">
                <div class="d_l_t_t">
                    <a href="" class="current">商品详情</a>
                </div>
                <!-----------详细内容---------->
                <div class="d_l_t_d">
                    ${product.content}
                </div>
                <!----------评价---------->
            </div>
            
            <!---------------------常见问题------------------->
            <div class="problem">
            </div>
        </div>



        <div class="clear"></div>
    </div>


</div>

<!---------------------保障------------------->
<jsp:include page="foot.jsp"></jsp:include>
</body>
<script type="text/javascript">
function jj(no){
	var num = $("#num").val();
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
}

function addOrder(id){
	var num = $("#num").val();
	$.ajax({
		type:"post",
		url:"addOrder.do?productid="+id+"&num="+num,
		date:"",
		success:function(msg){
		if(msg==1){
		  //location.reload();
			layer.msg("兑换成功");
		}else if(msg==0){
	    //layer.msg("请先登录");
		    location.replace("skipLogin.do");
		}else{
			layer.msg("积分不足");
		}
	}
	})
}


<%
String suc = (String)request.getAttribute("suc");
if(suc!=null){
%>
layer.msg('<%=suc%>');
<%}%>
</script>
</html>

