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
    <style type="text/css">
    .ctgnamebox a{
    margin-right: 20px;
    }
    </style>
</head>
<body>
<!------------顶部---------------->
<div class="top">
    <div class="wt1080">
        <div class="fl">
            <a href="index.do">首页</a>&emsp;
          
          <c:choose>
            <c:when test="${sessionScope.sessionmember eq null}">
            |&emsp;请&emsp;<a href="skipLogin.do" style="color: #ff9900">登陆</a>&emsp;或&emsp;<a href="skipReg.do">用户注册</a>&emsp;
            </c:when>
            <c:otherwise>
                                  ${sessionScope.sessionmember.uname}欢迎登录！
            </c:otherwise>
          </c:choose>
          
        </div>
        <div class="fr">
            <ul>
                <c:if test="${sessionScope.sessionmember ne null}">
                <!-- 
                <li style="position: relative;" id="my">
                    <a href="my_order.html">我的主页 <img src="image/sanjiao.png"></a>
                    <div class="personal">
                        <dl>
                            <dt><a href="my_order.html">我的订单</a></dt>
                            <dd><a href="my_youhuijuan.html">我的优惠卷</a></dd>
                            <dd><a href="my_jifen.html">我的积分</a></dd>
                            <dt><a href="my_order.html">个人信息</a></dt>
                        </dl>
                    </div>
                </li>
                 -->
                <li><span><a href="memberHome.do">我的主页</a></span></li>
                <li><span class="shop">购物车<a href="cartList.do">${fn:length(cartlist)}</a>件</span></li>
                <li><span><a href="memberExit.do">安全退出</a></span></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
<!--------------logo搜索------------->
<div class="wt1080 header">
    <div class="logo fl"><a href="index.do" style="font-size:28px;color:#FF9900">电子商城</a></div>
    <form action="Search.do" method="post" name="searchForm" id="searchForm">
    <div class="search fl">
        <div>
            <input name="key" value="${key}" type="text" class="a_search fl" placeholder="请输入关键字">
            <span class="b_search fl" id="searchbutton"></span>
            <div class="clear"></div>
        </div>
    </div>
    </form>
    <a class="my_shop fr" href="cartList.do">我的购物车
    <c:if test="${sessionScope.sessionmember ne null}">
    <span>${fn:length(cartlist)}</span>
    </c:if>
    </a>
    <div class="clear"></div>
</div>
<!--------------导航------------------>
<div class="nav">
    <div class="wt1080">
        <ul>
            <li>
                <a href="index.do" class="current"><span>首页</span></a>
            </li>
            
            <c:forEach items="${ctlist}" var="fcategory">
            <li>
                <a href="productLb.do?fid=${fcategory.id}"><span>${fcategory.name}</span></a>
                    <div class="details">
                        <div class="ctgnamebox">
                          <c:forEach items="${fcategory.scategorylist}" var="scategory">
                            <a href="productLb.do?sid=${scategory.id}" >${scategory.name}</a>
                            <!-- class="current" -->
                          </c:forEach>
                        </div>
                    </div>
            </li>
            </c:forEach>
            <li><a href="fubiLb.do"><span>积分商品</span></a></li>
            <!-- 
            <li><a href="lanmu.html"><span>家具生活</span></a></li>
            <li><a href="lanmu.html"><span>食品营养</span></a></li>
            <li><a href="lanmu.html"><span>全球直邮</span></a></li>
            <li><a href="lanmu.html"><span>合作申请</span></a></li>
             -->
        </ul>
        <div style="clear:both"></div>
    </div>
</div>

<!----------------轮播图-------------------->

</body>
<script type="text/javascript">
$("#searchbutton").click(function(){
	$("#searchForm").submit();
})
</script>
</html>