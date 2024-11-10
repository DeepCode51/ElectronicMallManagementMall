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
    <link href="css/reg.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript" src="js/slide.js"></script>
    <style type="text/css">
      input{
      padding-left: 8px;
      }
    </style>
    <style>
		/*验证码*/
		
		.upload-awrp {
			overflow: hidden;
			margin: 120px 0;
		}
		
		.code {
			font-family: Arial;
			font-style: italic;
			font-size: 20px;
			border: 0;
			padding: 5px 3px;
			letter-spacing: 2px;
			font-weight: bolder;
			float: left;
			cursor: pointer;
			width: 110px;
			height: 35px;
			line-height: 30px;
			text-align: center;
			vertical-align: middle;
			border: 1px solid #E7E7E7;
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
<div class="member" style="background-color: white;">
<div class="reg" style="background-color: white;margin-bottom: 60px;">
	<div class="w1185">
    	<div class="left">
        	<p><b style="font-weight: bold;font-size:20px;">登录</b></p>
            <form action="Login.do" class="form" method="post">
                <ul>
                     <li><span><font>*</font> 用户名：</span><input type="text"  name="uname" class="txt" placeholder="用户名" required oninvalid="setCustomValidity('请输入用户名')" oninput="setCustomValidity('');"/></li>
                     <li><span><font>*</font> 密码：</span><input type="password" name="upass" class="txt" placeholder="密码" required oninvalid="setCustomValidity('请输入密码')" oninput="setCustomValidity('');"/></li>
                     <li class="li03"><span><font>*</font> 验证码：</span><input type="text" name="yzm" id="yzm" class="txt txt2" placeholder="验证码" required oninvalid="setCustomValidity('请输入验证码')" oninput="setCustomValidity('');"/>
                     
                     <div id="check-code" style="overflow: hidden;">
						<div class="code" id="data_code"></div>
					</div>
                     
                     </li>
                     <li class="li02"><input type="submit" value="登录" class="sub"/></li>
                </ul>
            </form>
        </div>
        <div class="right">未注册用户，马上<a  href="skipReg.do">注册</a></div>
        <div class="clear"></div>
    </div>
</div>
    <!-------------------分类------------------>
    <!-------------------位置------------------>
    <!----------------产品详细------------------->
</div>



<!---------------------保障------------------->
<jsp:include page="foot.jsp"></jsp:include>
</body>
<script type="text/javascript">
	/**
	 * 验证码
	 * @param {Object} o 验证码长度
	 */
	$.fn.code_Obj = function(o) {
		var _this = $(this);
		var options = {
			code_l: o.codeLength,//验证码长度
			codeChars: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
				'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
				'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
			],
			codeColors: ['#f44336', '#009688', '#cddc39', '#03a9f4', '#9c27b0', '#5e4444', '#9ebf9f', '#ffc8c4', '#2b4754', '#b4ced9', '#835f53', '#aa677e'],
			code_Init: function() {
				var code = "";
				var codeColor = "";
				var checkCode = _this.find("#data_code");
				for(var i = 0; i < this.code_l; i++) {
					var charNum = Math.floor(Math.random() * 52);
					code += this.codeChars[charNum];
				}
				for(var i = 0; i < this.codeColors.length; i++) {
					var charNum = Math.floor(Math.random() * 12);
					codeColor = this.codeColors[charNum];
				}
				console.log(code);
				if(checkCode) {
					checkCode.css('color', codeColor);
					checkCode.className = "code";
					checkCode.text(code);
					checkCode.attr('data-value', code);
				}
			}
		};

		options.code_Init();//初始化验证码
		_this.find("#data_code").bind('click', function() {
			options.code_Init();
		});
	};
</script>
<script type="text/javascript">
	/**
	 * 验证码
	 * codeLength:验证码长度
	 */
	$('#check-code').code_Obj({
		codeLength: 5
	});
</script>
<script type="text/javascript">
$("#yzm").blur(function(){
	var yzm = $("#yzm").val().toLowerCase();
	var data_code = $("#data_code").html().toLowerCase();
	if(yzm!=data_code.toLowerCase()){
	 	layer.msg('验证码不正确 ');
	 	$("#yzm").val("");
	 }
})

<%
String suc = (String)request.getAttribute("suc");
if(suc!=null){
%>
layer.msg('<%=suc%>');
<%}%>
</script>
</html>
