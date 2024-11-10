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
</head>

<body>
    	<div class="left">
        	<dl><dt>账户管理</dt>
            <dd id="defaultmenu">
            	<a href="memberHome.do" class="on">个人资料</a>
                <a href="orderLb.do">订单信息</a>
                <a href="fubiOrder.do">兑换订单</a>
                <a href="skipChongzhi.do">充值</a>
                <a href="favLb.do">收藏列表</a>
            </dd>
            </dl>
        </div>
</body>

<script type="text/javascript">
$(document).ready(function(){
		$("#defaultmenu").find("a").each(function(){
			if($(this)[0].href==window.location){
				$(this).addClass("on");
			}else{
				$(this).removeClass("on");
			}
				
		})
}); 
</script>
</html>
