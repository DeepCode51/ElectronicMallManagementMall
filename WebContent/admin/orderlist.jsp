<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>电子商城</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Visitors Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- bootstrap-css -->
<link rel="stylesheet" href="css/bootstrap.min.css" >
<!-- //bootstrap-css -->
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link href="css/style-responsive.css" rel="stylesheet"/>
<!-- font CSS -->
<link href='//fonts.googleapis.com/css?family=Roboto:400,100,100italic,300,300italic,400italic,500,500italic,700,700italic,900,900italic' rel='stylesheet' type='text/css'>
<!-- font-awesome icons -->
<link rel="stylesheet" href="css/font.css" type="text/css"/>
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- //font-awesome icons -->
<script src="js/jquery2.0.3.min.js"></script>
</head>
<body>
<section id="container">
<!--header start-->
<header class="header fixed-top clearfix">
<jsp:include page="top.jsp"></jsp:include>
</header>
<!--header end-->
<!--sidebar start-->
<aside>
  <jsp:include page="left.jsp"></jsp:include>
</aside>
<!--sidebar end-->
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
		<div class="table-agile-info">
  <div class="panel panel-default">
    <div class="panel-heading">
      订单列表
    </div>
    <div class="row w3-res-tb">
      <div class="col-sm-5 m-b-xs" style="border: 0px solid red;width: 500px;">
        <form action="<%=path %>/admin/orderList.do" method="post" id="searchform" name="searchform">
        按订单编号<input type="text" class="input-sm form-control w-sm inline v-middle" style="width: 200px;" value="${key}" placeholder="请输入标题" name="key">
        <button class="btn btn-sm btn-default" >搜索</button>     
        </form>           
      </div>
    </div>
    <div class="table-responsive">
      <table class="table table-striped b-t b-light">
        <thead>
          <tr>
            <th>订单编号</th>
            <th>会员</th>
		    <th>订单金额</th>
		    <th>订单状态</th>
		    <th>创建时间</th>
		    <th>支付方式</th>
		    <th>收货地址</th>
            <th style="width:130px;">操作</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="ordermsg" varStatus="status">
          <tr>
			<td style="color:orange;font-weight:bolder"><a href="ordermsgdetailsList.do?ddno=${ordermsg.ddno }">${ordermsg.ddno}</a></td>
			<td>${ordermsg.member.uname}</td>
			<td>${ordermsg.total}</td>
			<td>${ordermsg.fkstatus}</td>
			<td>${ordermsg.savetime}</td>
            <td>${ordermsg.zffs}</td>
            <td>${ordermsg.address.addr}&nbsp;&nbsp;${ordermsg.address.name}&nbsp;&nbsp;${ordermsg.address.tel}</td>
            <td>
            
            <c:if test="${ordermsg.fkstatus eq '已付款'}">
              <a href="orderFh.do?id=${ordermsg.id}"  class="active" ui-toggle-class="">发货</a>
            </c:if>
            <c:if test="${ordermsg.fkstatus eq '交易完成'}">
			<a href="orderDel.do?id=${ordermsg.id}">删除</a>
			</c:if>
			
			
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </div>
    <footer class="panel-footer">
      <div class="row">
        
        <div class="col-sm-5 text-center">
        </div>
        <div class="col-sm-7 text-right text-center-xs">                
          <ul class="pagination pagination-sm m-t-none m-b-none">
            <li>
            <p>当前 <span>${pageInfo.pageNum}</span> 页,总 <span>${pageInfo.pages}</span> 页,共 <span>${pageInfo.total}</span> 条记录</p>
             <a href="orderList.do?pageNum=1&key=${key}">首页</a>
             <a href="orderList.do?pageNum=${pageInfo.hasPreviousPage==false?1:pageInfo.prePage}&key=${key}">上一页</a>
             <a href="orderList.do?pageNum=${pageInfo.hasNextPage==false? pageInfo.pages : pageInfo.nextPage}&key=${key}">下一页</a>
             <a href="orderList.do?pageNum=${pageInfo.pages}&key=${key}">尾页</a>
            </li>
          </ul>
        </div>
      </div>
    </footer>
  </div>
</div>
</section>
 <!-- footer -->
		<jsp:include page="foot.jsp"></jsp:include>
  <!-- / footer -->
</section>

<!--main content end-->
</section>
<script src="js/bootstrap.js"></script>
<script src="js/jquery.dcjqaccordion.2.7.js"></script>
<script src="js/scripts.js"></script>
<script src="js/jquery.slimscroll.js"></script>
<script src="js/jquery.nicescroll.js"></script>
<!--[if lte IE 8]><script language="javascript" type="text/javascript" src="js/flot-chart/excanvas.min.js"></script><![endif]-->
<script src="js/jquery.scrollTo.js"></script>
<script type="text/javascript" src="<%=path %>/layer/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="<%=path %>/layer/layer.js"></script>
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
