 <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="UTF-8">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
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
<%@include file="header.jsp"%>
<!-- 广告   -->
<div id="divad">
<img src="ad/index_ad.jpg" />
</div>
<!-- 广告  end -->

<!-- 正文   -->
<div id="divcontent">
<table width="900px" border="0" cellspacing="0">
  <tr>
    <td width="497"><img src="images/billboard.gif" width="497" height="38" />      <table cellspacing="0" class="ctl">
    <c:forEach items="${news }" var="item">
        <tr>
          <td>&middot;<a href="/BookShop/dispatcher.do?type=news&id=${item.tid }" style="color:#000000">${item.title }</a></td>
        </tr>
</c:forEach>
      </table></td>
    <td style="padding:5px 15px 10px 40px"><table width="100%" border="0" cellspacing="0">
      <tr>
        <td><img src="images/hottitle.gif" width="126" height="29" /></td>
        </tr>
    </table>
      <table width="100%" border="0" cellspacing="0">
        <tr>
          <td style="width:50; text-align:center"><a href="info.html"><img src="bookcover/travelbook.jpg" width="102" height="130" border="0" /></a><br />
            <a href="info.html">TravelBook<br/>
作者:Lonley Plant</a></td>
          <td style="width:50; text-align:center"><a href="info.html"><img src="bookcover/java2.jpg" width="102" height="130" border="0" /></a><br />
            <a href="info.html">Java2入门经典(JDK5) <br/>
作者:(美)霍顿</a></td>
        </tr>
      </table></td>
  </tr>
</table>
</div>
<!-- 正文 end   -->

<!-- footer -->
<%@include file="footer.jsp" %>
<!-- footer end -->

</body>
</html>
