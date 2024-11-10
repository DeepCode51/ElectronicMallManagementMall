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
    <link href="css/order.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <link href="css/youhuijuan.css" type="text/css" rel="stylesheet"/>
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
<div class="wt1080">
    <!--------------标题----------->
    <div class="my_order">
        <h1>提交订单</h1>
        <div class="place">
            <ul>
                <li class="current"><span>1</span><p>我的购物车</p></li>
                <li class="current"><span>2</span><p>提交订单</p></li>
                <li><span>3</span><p>选择支付方式</p></li>
            </ul>
            <span class="heng"></span>
            <span class="a_heng"></span>
        </div>
    </div>
    <!-------------收货地址-------------->
    <form action="skipZffs.do" method="post" onsubmit="return zf()">
    <input type="hidden"  name="id" value="${ordermsg.id}"/>
    <input type="hidden" id="totalstr" name="totalstr" value="${sjtotal}"/>
    <input type="hidden" id="ticketid" name="ticketid" value=""/>
    <input type="hidden" id="addrid" name="addrid" value="${addrid}"/>
    
    
    
    <div class="address">
        <div class="roo">收货地址<span></span></div>
        <div class="b_address">
            <ul>
                
                <c:forEach items="${addresslist}" var="address">
                <li class="current" style="height: 180px;">
                    <h1>${address.name }<span>${address.tel }</span>
                    <c:if test="${address.ismr eq 'no'}">
                    <span style="float: right;"><a href="updateIsmr.do?id=${address.id}&orderid=${ordermsg.id}">默认收货地址</a></span>
                    </c:if>
                    </h1>
                    <p>${address.addr}</p>
                    <p style="border-bottom: 0px;">&nbsp;</p>
                    <div class="operate">
                        <a href="addressShow.do?id=${address.id}&orderid=${ordermsg.id}" class="edit">编辑</a>
                        <a href="addressDel.do?id=${address.id}&orderid=${ordermsg.id}" class="del">删除</a>
                    </div>
                    <c:if test="${address.ismr eq 'yes'}">
                    <div class="check"></div>
                    </c:if>
                </li>
				</c:forEach>
            </ul>
            <div class="add_address"><a href="skipAddress.do?orderid=${ordermsg.id}">添加地址</a></div>
            <div class="clear"></div>
        </div>
    </div>
    
    
    <!---------------订单信息-------------->
    <div class="order_details">
        <div class="roo">订单信息<span></span></div>
        <div class="tt">
            <table>
                <tr>
                    <th width="50%">商品</th>
                    <th width="16%">单价</th>
                    <th width="16%">数量</th>
                    <th width="18%">小计</th>
                </tr>
                <c:forEach items="${ordermsg.ordermsglist }" var="ordermsgdetails">
                <tr>
                    <td>
                        <div class="pic"><img src="upload/${ordermsgdetails.product.filename }"></div>
                        <p class="one"><a href="#">${ordermsgdetails.product.productname }</a></p>
                        <p class="two">&nbsp;</p>
                    </td>
                    <td>
                    <c:choose>
                      <c:when test="${ordermsgdetails.product.tprice gt 0}">
                      <span class="e">￥${ordermsgdetails.product.tprice}</span>
                      <span><s>${ordermsgdetails.product.price}</s></span>
                      </c:when>
                      <c:otherwise>
                      <span class="e">￥${ordermsgdetails.product.price}</span>
                      </c:otherwise>
                    </c:choose>
                    </td>
                    <td>${ordermsgdetails.num}</td>
                    <td><span class="u">￥${ordermsgdetails.total}</span></td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <!------------------优惠卷--------------->
    <div class="coupon">
        <div class="roo">优惠卷<span></span></div>
        <div class="rr">
            <c:choose>
              <c:when test="${fn:length(ticketlist) le 0}">
              <p>此订单暂无可用的优惠卷。</p>
              </c:when>
              <c:otherwise>
              
              <div class="wt504 middle" style="border:0px solid red;">
            <div class="fr" style="width: 100%">
            <div class="m_r_m">
            <div class="y_list" style="border:0px solid red;float: left;padding: 0px;margin: 0px;">
                <ul>
                <c:forEach items="${ticketlist}" var="ticket">
                    <li onclick="sy('${ticket.id}','${ordermsg.id}','${sjtotal}')">
                        <div class="jj">
                            <span class="banyuan"></span>
                            <span class="guoqi" id="guoqi${ticket.id}" style="background:url('');border:1px solid #FF9900; height: 40px;width: 40px;border-radius:40px 40px;line-height: 40px;background-color: #FF9900;font-weight: bolder;display: none;">抵用</span>
                            <p class="one">￥${ticket.money}</p>
                            <p style="font-size: 12px;font-family: 微软雅黑;margin: 0px;padding: 0px;outline: 0px;">【可抵用现金】</p>
                        </div>
                    </li>
                </c:forEach>    
                    
                    
                    
                </ul>
                <div class="clear"></div>
            </div>
            </div>
            </div>
            </div>
            <!-- 优惠券 -->
            <p style="border-bottom: none;">共<font>${fn:length(ticketlist) }</font>张优惠卷 </p>
              </c:otherwise>
            </c:choose>
            
            <!-- 优惠券 -->
        </div>
        <div class="kk">
            <table>
                <tr>
                    <td>商品总金额：</td>
                    <td>￥${ordermsg.total}</td>
                </tr>
            </table>
            <p class="money">应付余额：<span>￥<font id="je">${sjtotal}</font></span></p>
        </div>
        <div class='clear'></div>
        <!---------------委托下的版本---------------->
        <div class="ll">
            <div class="bb">
                <h1>个人委托申报协议</h1>
                <p>本人承诺所购买商品系个人合理自用，现委托商家代理申报，代缴税款等通关事宜，本人保证遵守《海关法》和国家相关法律法规，保证所提供的身份信息和收货信息真是完整，无侵犯他人权益的行为，以上委托关系如实填写，本人愿意接受海关，检查检疫机构及其他监管部门的监管，并承担相应法律责任。</p>
            </div>
            <div class="ss">
            </div>
            <div class="gg">
                <p>应付余额：<span>￥<font id="zje">${sjtotal}</font></span></p>
            </div>
            <input type="submit" value="确认提交订单" >
            <div class="clear"></div>
        </div>
    </div>
 </form>

</div>

<!---------------------保障------------------->
<jsp:include page="foot.jsp"></jsp:include>
</body>
<script type="text/javascript">
function sy(id,ordermsgid,sjtotal){
	//$(".guoqi").css("display","none");
	var ys = $("#guoqi"+id).css("display");
	if(ys=="none"){
	$("#guoqi"+id).css("display","block");
	$(".guoqi").not($("#guoqi"+id)).css("display","none");
	updateYhtotal(id,ordermsgid,sjtotal);
	$("#ticketid").val(id);
	}else{
	$("#guoqi"+id).css("display","none");
	$("#je").text(sjtotal);
	$("#zje").text(sjtotal);
	$("#totalstr").val(sjtotal);
	}
}

function updateYhtotal(id,ordermsgid,sjtotal){
	$.ajax({
		type:"post",
		url:"updateYhtotal.do?id="+id+"&ordermsgid="+ordermsgid,
		date:"",
		success:function(msg){
			//layer.msg("添加购物车成功");
			if(msg==-1){
				layer.msg("不支持使用优惠券");
				$("#je").text(sjtotal);
				$("#zje").text(sjtotal);
			}else{
				$("#je").text(msg);
				$("#zje").text(msg);
				$("#totalstr").val(msg);
			}
		}
	})
}


function zf(){
	var addrid = $("#addrid").val();
	if(addrid==""){
		layer.msg("请先确认收货地址");
		return false;
	}
}
</script>
</html>



