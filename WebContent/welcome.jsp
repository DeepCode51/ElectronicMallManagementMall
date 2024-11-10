<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
System.out.println("11111111111");
request.getRequestDispatcher("index.do").forward(request, response);
//response.sendRedirect("");
%>
