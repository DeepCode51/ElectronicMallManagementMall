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
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="js/slide.js"></script>
    <style type="text/css">
    .page.wt1080.acvtive{
    border:1px solid red;
    background-color: #FE5500;
    color:white;
    border:1px solid #FE5500;
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
<div class="content">
    <!-------------------分类------------------>
    <!-------------------位置------------------>
    <div class="place">
        位置：<a class="check" href="#">兑换商品</a>共<span class="number">${fn:length(nlist)}</span>件商品
    </div>
    <!----------------产品详细------------------->
    <div class="product">
        <ul>
        <c:forEach items="${pageInfo.list}" var="product">
            <li>
                <div class="pic"><a href="fubix.do?id=${product.id}"><img src="upload/${product.filename}"></a></div>
                <p class="one"><a href="fubix.do?id=${product.id}">${product.productname}</a></p>
                <p class="two">
                  <span style="color: #fe5500;margin-right: 10px;"><span style="font-size:20px;">${product.price}积分</span></span>
                </p>
            </li>
        </c:forEach>
        </ul>
        <div class="clear"></div>
    </div>
    <div class="page wt1080">
    <a href="fubiLb.do?pageNum=${pageInfo.hasPreviousPage==false?1:pageInfo.prePage}">上一页</a>
    <c:forEach begin="1" end="${pageInfo.pages}" varStatus="status">
    <a href="fubiLb.do?pageNum=${status.count}" ${status.count eq pageInfo.pageNum ?"class='page wt1080 acvtive'":""}}>${status.count}</a>
    </c:forEach>
    <a href="fubiLb.do?pageNum=${pageInfo.hasNextPage==false? pageInfo.pages : pageInfo.nextPage}">下一页</a>
    </div>
</div>



<!---------------------保障------------------->

<!---------------底部--------------->
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>
