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
        当前位置：<span class="check"><a href="shopGoods.do?shopid=${product.shop.id}" style="color:#FF9900">${product.shop.realname}</a>>>${product.productname}</span>
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
            <div class="aa">
                <span class="a">当前价格</span>
                <c:choose>
                  <c:when test="${product.tprice gt 0}">
                  <span class="b">￥${product.tprice}</span>
                  </c:when>
                  <c:otherwise>
                  <span class="b">￥${product.price}</span>
                  </c:otherwise>
                </c:choose>
                <c:if test="${product.tprice gt 0}">
                <span class="d">原价 ￥${product.price}</span>
                </c:if>
            </div>
            <table>
                <tr>
                    <td class="one">类型</td>
                    <td>${product.fcategory.name }/${product.scategory.name }</td>
                </tr>
                <tr>
                    <td class="one">数量</td>
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
                <a href="javascript:void(0)" onclick="addFav('${product.id}')" class="buy">加入收藏</a>
                <a href="javascript:void(0)" onclick="addCart('${product.id}')" class="shop_car">加入购物车</a>
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
            <div class="pingjia">
                <div class="pingjia_t">
                    <span>买家口碑</span>
                </div>
                <!----------评价----------->
                <div class="pingjia_d">
                    <div class="pingjia_d_t">
                        <span class="current"><img src="image/d1.png">全部评价（${fn:length(commentlist)}）</span>
                    </div>
                    <!----------留言----------->
                    <div class="pingjia_d_l">
                        <ul>
						<c:forEach items="${commentlist}" var="comment">
                            <li>
                                <p class="title"><span>${comment.member.tname}</span>（${comment.savetime}）</p>
                                <p class="mess">${comment.content}</p>
                                <c:if test="${comment.hfcontent ne null and comment.hfcontent ne ''}">
                                <p class="admin">商家：${comment.hfcontent}</p>
                                </c:if>
                            </li>
						</c:forEach>
                        </ul>
                    </div>
                    <!---------------分页--------------->
                
                <!-- 评价 -->
                <div class="wt1080" style="border:0px solid red;width: auto;margin-top: 30px;">
                <h2 style="padding-left: 10px;color:#B1585A;font-size:20px;margin-bottom: 30px;">请在下方评论</h2>
                <div style="padding-left: 10px;">
                  <form action="commentAdd.do" method="post">
                  <input type="hidden" value="${product.id}" name="productid"/>
                    <textarea rows="" cols="" name="content" style="width: 500px;height: 150px;padding:5px;border:1px solid #EEEEEE" placeholder="评论内容" required oninvalid="setCustomValidity('请输入评论内容')" oninput="setCustomValidity('');"></textarea>
                    <div style="margin-top: 15px;"><input type="submit" value="提交" style="width: 120px;height: 40px;color:white;background: #FF9900;font-size:18px;border:0px;cursor:pointer"/></div>
                  </form>
                </div>
                
                </div>
                <!-- 评价 -->
                </div>
            </div>
            <!---------------------常见问题------------------->
            <div class="problem">
            </div>
        </div>



        <div class="details_right">
            <div class="d_r_t">相似推荐</div>
            <div class="d_r_d">
                <ul>
                <c:forEach items="${xslist}" var="xsproduct">
                    <li>
                        <a href="productDetails.do?id=${xsproduct.id}"><img src="upload/${xsproduct.filename}"></a>
                        <p><a href="productDetails.do?id=${xsproduct.id}">${xsproduct.productname}</a></p>
                        <p><span>￥<font>${xsproduct.price}</font></span> </p>
                    </li>
                </c:forEach>
                </ul>
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


function addCart(id){
	var num = $("#num").val();
	$.ajax({
		type:"post",
		url:"addCart.do?productid="+id+"&num="+num,
		date:"",
		success:function(msg){
		if(msg==1){
		  location.reload();
			//layer.msg("添加购物车成功");
		}else{
	    //layer.msg("请先登录");
		    location.replace("skipLogin.do");
		}
	}
	})
}

function addFav(id){
	$.ajax({
		type:"post",
		url:"addFav.do?productid="+id,
		date:"",
		success:function(msg){
		if(msg==1){
		  //location.reload();
			layer.msg("收藏成功");
		}else if(msg==0){
	    //layer.msg("请先登录");
		    location.replace("skipLogin.do");
		}else if(msg==2){
			layer.msg("不能重复操作");
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

