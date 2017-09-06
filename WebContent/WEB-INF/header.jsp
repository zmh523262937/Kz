 <%@page import="entity.Cart"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.util.ArrayList"%>
<%@page import="entity.Catagory"%>
<%@page import="java.util.List"%>
<%@page import="service.CatagoryServiceImp"%>
<%@page import="service.CatagoryService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Logo -->
<div id="divhead">
  <table cellspacing="0" class="headtable">
    <tr>
      <td><a href="${pageContext.request.contextPath }/NewsServlet.do"><img src="images/logo.gif" width="95" height="30" border="0" /></a></td>
      <c:set var="num" value="0"></c:set>
  <c:if test="${cart!=null }">
  	<c:set var = "num" value="${fn:length(cart) }"></c:set>
  </c:if>
  <c:if test="${cart==null }">
  	<%int num = 0;
  		Cookie [] cookies =request.getCookies();
  		if(cookies!=null){
  			
  		
  		for(int i=0;i<cookies.length;i++){
  			if(cookies[i].getName().contains("cartGood")){
  				num++;
  			}
  			}
  		}%>
  	<c:set var="num" value="<%=num %>"></c:set>
  </c:if>
  	<c:if test="${isEmpty!=null }">
  	<c:set var="num" value="0"></c:set>
  	</c:if>
  	
      <td style="text-align:right"><img src="images/cart.gif" width="26" height="23" style="margin-bottom:-4px"/>&nbsp;<a href="${pageContext.request.contextPath }/dispatcher.do?type=cart">购物车<font color="red">(${num })</font></a>　|　<a href="#">帮助中心</a>　|　<a href="${pageContext.request.contextPath }/dispatcher.do?type=login ">我的帐户</a>　|　<a href="${pageContext.request.contextPath }/dispatcher.do?type=regist">新用户注册</a></td>
    </tr>
  </table>
</div>
<!-- Logo end -->
<%CatagoryService dao = new CatagoryServiceImp();
	List<Catagory> list = new ArrayList();
	list=dao.newslist();
	request.setAttribute("catas", list);%>
<!-- menu -->
<div id="divmenu">
	<c:forEach items="${catas }" var="item">
    <a href="${pageContext.request.contextPath }/list.do?cid=${item.cid }">${item.cname }</a>　　
	</c:forEach>
	<a href="${pageContext.request.contextPath }/list.do?cid=17" style="color:#FFFF00">全部商品目录</a>
</div>
<!-- menu end -->
<!-- search -->
<div id="divsearch"><table width="100%" border="0" cellspacing="0">
  <tr>
    <td style="text-align:right; padding-right:220px">Search
  <input type="text" name="textfield" class="inputtable"/>
<!--<input name="searchbutton" type="image" src="images/serchbutton.gif" style=" margin-bottom:-4px"/>-->
<a href="search.html"><img src="images/serchbutton.gif" border="0" style="margin-bottom:-4px"/></a></td>
  </tr>
</table>

</div>
<!-- search end -->