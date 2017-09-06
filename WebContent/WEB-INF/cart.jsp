<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<meta content="all" name="robots"/>
<meta name="author" content="Fisher" />
<meta name="Copyright" content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
<meta name="description" content="reefdesign" />
<meta name="keywords" content="reefdesign" />
<title>电子书城</title>

<link rel="shortcut icon" href="favicon.ico" >
<link rel="stylesheet" rev="stylesheet" href="css/style.css" type="text/css" media="all" />
</head>

<body class="main">

<%@include file="header.jsp" %>


<div id="divpagecontent">
  <table width="100%" border="0" cellspacing="0">
    <tr>
      <td width="25%"><table width="100%" border="0" cellspacing="0" style="margin-top:30px">
        <tr>
          <td class="listtitle">配送方式、时间及费用</td>
        </tr>
        <tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;<a href="news.html">普通快递送货上门</a></td>
        </tr>
		
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">特快专递送货上门(EMS)</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
            <a href="news.html">申通快递送货上门</a></td>
        </tr>
<tr>
          <td class="listtd"><img src="images/miniicon.gif" width="9" height="6" />&nbsp;&nbsp;&nbsp;&nbsp;
<a href="news.html">普通邮寄</a></td>
        </tr>

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
      </table></td>
      <td><div style="text-align:right; margin:5px 10px 5px 0px"><a href="index.html">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;购物车</div>
        
		
		
		
		
        <table cellspacing="0" class="infocontent">
        <tr>
          <td><img src="ad/page_ad.jpg" width="666" height="89" />
            <table width="100%" border="0" cellspacing="0">
              <tr>
                <td><img src="images/buy1.gif" width="635" height="38" /></td>
              </tr>
              <tr>
                <td><table cellspacing="1" class="carttable">
                  <tr>
                    <td width="10%">序号</td>
                    <td width="40%">商品名称</td>
                    <td width="10%">市场价</td>
                    <td width="10%">优惠价</td>
                    <td width="10%">数量</td>
                    <td width="10%">小计</td>
             
                    <td width="10%">取消</td>
                  </tr>
                </table>
               <c:set var="sum" value="0"></c:set>
                <c:forEach items="${cart }"  var="i">
                  <table width="100%" border="0" cellspacing="0">
                    <tr>
                      <td width="10%">${i.gid }</td>
                      <td width="40%">${i.gtitle }</td>
                      <td width="10%">${i.inprice }</td>
                      <td width="10%" id="inprice">${i.saleprice }</td>
                      <td width="10%"><input name="amount" id="amount" type="text" value="${i.amount }" style="width:20px"/></td>
                      <td width="10%" id="minitotal">${i.saleprice*i.amount }</td>
                      <c:set var="sum" value="${sum+i.saleprice*i.amount }"></c:set>
                      <td width="10%"><a href="${pageContext.request.contextPath }/cart.do?option=remove&good=${i.gid}" style="color:#FF0000; font-weight:bold">X</a></td>
                    </tr>
                  </table>
			</c:forEach>
				  
				  
				   <table cellspacing="1" class="carttable">
                     <tr>
                       <td id="total" style="text-align:right; padding-right:40px;"><font style="color:#FF6600; font-weight:bold">合计：&nbsp;&nbsp;${sum }元</font></td>
                      </tr>
                   </table>
                  
				   <div style="text-align:right; margin-top:10px"><a href="${pageContext.request.contextPath }/NewsServlet.do"><img src="images/gwc_jx.gif" border="0" /></a>&nbsp;&nbsp;&nbsp;&nbsp;<c:if test="${fn:length(cart)!=0 }"><a href="${pageContext.request.contextPath }/order.do"><img src="images/gwc_buy.gif" border="0" /></a></c:if></div>
				  
				  </td>
              </tr>
            </table></td>
        </tr>
      </table></td>
    </tr>
  </table>
</div>



<%@include file="footer.jsp" %>


</body>
</html>