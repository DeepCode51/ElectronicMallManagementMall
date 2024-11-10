<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
      商品库存信息
    </div>
    <div class="row w3-res-tb">
      <div class="col-sm-5 m-b-xs" style="border: 0px solid red;width: 700px;">
        <form action="<%=path %>/admin/inventoryList.do" method="post" id="searchform" name="searchform">
        商品名称：<input type="text" class="input-sm form-control w-sm inline v-middle" style="width: 200px;" value="${key}" placeholder="请输入标题" name="key">
        <select name="fid" id="fid" onchange="selectsid()"  class="input-sm form-control w-sm inline v-middle"  style="width: 150px;"   autocomplete="off"  class="layui-input">
    	  <option value="">选择大类</option>
    	  <c:forEach items="${ctlist}" var="fcategory">
	  	  <option value="${fcategory.id}" <c:if test="${fcategory.id eq fid }">selected</c:if>>${fcategory.name}</option>
	  	  </c:forEach>
	  	</select>
	  	
	  	<select name="sid" id="sid" class="input-sm form-control w-sm inline v-middle"  style="width: 150px;"   autocomplete="off"  class="layui-input">
    	  <option value="">选择小类</option>
    	  <c:forEach items="${scategorylist}" var="scategory">
	  	  <option value="${scategory.id}" <c:if test="${scategory.id eq sid }">selected</c:if>>${scategory.name}</option>
	  	  </c:forEach>
	  	</select>
        <button class="btn btn-sm btn-default" >搜索</button>     
        </form>           
      </div>
    </div>
    <div class="table-responsive">
      <table class="table table-striped b-t b-light">
        <thead>
          <tr>
            <th>商品图片</th>
            <th>商品名称</th>
			<th>商品价格</th>
			<th>商品类别</th>
			<th>库存数量</th>
            <th style="width:100px;">操作</th>
          </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageInfo.list}" var="product" varStatus="status">
          <tr>
			<td><img src="<%=path %>/upload/${product.filename}" style="width: 60px;height: 60px;"/></td>
			<td>${product.productname}</td>
			<td>${product.price}</td>
			<td>${product.fcategory.name}/${product.scategory.name}</td>
			<td>${product.kc}</td>
            <td>
              <a href="skipinventoryadd.do?productid=${product.id}" title="入库" class="active" ui-toggle-class=""><i class="fa fa-plus-square"></i></a>
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
             <a href="inventoryList.do?pageNum=1&key=${key}&fid=${fid}&sid=${sid}">首页</a>
             <a href="inventoryList.do?pageNum=${pageInfo.hasPreviousPage==false?1:pageInfo.prePage}&key=${key}&fid=${fid}&sid=${sid}">上一页</a>
             <a href="inventoryList.do?pageNum=${pageInfo.hasNextPage==false? pageInfo.pages : pageInfo.nextPage}&key=${key}&fid=${fid}&sid=${sid}">下一页</a>
             <a href="inventoryList.do?pageNum=${pageInfo.pages}&key=${key}&fid=${fid}&sid=${sid}">尾页</a>
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
</body>

<script type="text/javascript">
function selectsid(){
	var sid = $("#sid");
	$.ajax({
  	  url:"searchCtype.do?fatherid="+$('#fid').val(),
  	  datetype:"json",
  	  type:"post",
  	  contentType: "application/Json; charset=UTF-8",
  	  success:function(msg){
  		  sid.empty();
      	  //key2.append("<option value=\"\">请选择</option>");
      	  sid.append(msg);
	  }
	});
}
</script>
</html>
