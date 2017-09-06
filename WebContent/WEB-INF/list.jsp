<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <meta content="all" name="robots" />
    <meta name="author" content="Fisher" />
    <meta name="Copyright" content="Copyright 2007-2008, 版权所有 www.reefdesign.cn" />
    <meta name="description" content="reefdesign" />
    <meta name="keywords" content="reefdesign" />
    <title>电子书城</title>
    <style type="stylesheet"  >
    
    </style>
    <link rel="shortcut icon" href="favicon.ico"/>
    <link rel="stylesheet" rev="stylesheet" href="${pageContext.request.contextPath }/css/style.css" type="text/css" media="all" />
</head>
<body class="main">
   <%@include file="header.jsp" %>

    <div id="divpagecontent">
        <table width="100%" border="0" cellspacing="0">
            <tr>
                <td>
                    <div style="text-align: right; margin: 5px 10px 5px 0px">
                        <a href="${pageContext.request.contextPath}/NewsServlet.do">首页</a>&nbsp;&nbsp;&nbsp;&nbsp;&gt;&nbsp;&nbsp;&nbsp;&nbsp;${cate.cname }</div>
                    <table cellspacing="0" class="infocontent">
                        <tr>
                            <td>
                                <table width="100%" border="0" cellspacing="0">
                                    <tr>
                                        <td style="padding: 10px">
                                            以下 <strong>${pageInfo.totalNumber }</strong> 条结果按 <strong>销量</strong> 排列 每页显示<strong>${fn:length(pageInfo.data) }</strong>条<hr />
                                            <c:set var="cid" value="${cid }"> </c:set>
										<c:forEach items="${pageInfo.data }" var="i">
                                            <table border="0" cellspacing="0" class="searchtable">
                                                <tr>
                                                    <td width="20%" rowspan="2">
                                                        <div class="divbookpic">
                                                            <p>
                                                                <a href="${pageContext.request.contextPath }/dispatcher.do?type=goods&gid=${i.gid}">
                                                                    <img src="${pageContext.request.contextPath}/bookcover/${i.gimg }.jpg" width="115" height="129" border="0" /></a></p>
                                                        </div>
                                                    </td>
                                                    <td colspan="2">
                                                        <font class="bookname">${i.gtitle }</font><br />
                                                        作者：${i.gauthor } 著<br />
                                                      ${i.gdesc }  
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td>
                                                        售价：<font color="#FF0000">￥${i.gsaleprice } </font>&nbsp;&nbsp;&nbsp;&nbsp;原价：<s>￥${i.ginprice }</s>
                                                    </td>
                                                    <td style="text-align: right">
                                                        <a href="${pageContext.request.contextPath}/cart.do?option=add&good=${i.gid}">
                                                            <img src="${pageContext.request.contextPath}/images/buy.gif" width="91" height="27" border="0" style="margin-bottom: -8px" /></a>
                                                    </td>
                                                </tr>
                                            </table>
                                      </c:forEach>
                                            <div class="pagination">
                                                <ul>
													<c:if test="${pageInfo.isFirstPage }">
														<li class="disablepage"><< 上一页</li>
													</c:if>
													<c:if test="${!pageInfo.isFirstPage }">
														<li><a
															href="${pageContext.request.contextPath }/list.do?pageIndex=${pageInfo.pageIndex-1}&cid=${cid}">上一页</a></li>
													</c:if>
											<!-- -------处理分页首页 --------------------->
											
													
													<c:if test="${pageInfo.totalPage<=6 }">
														<c:forEach begin="1" end="${ pageInfo.totalPage}" var="i">
															<c:if test="${pageInfo.pageIndex!=i }">
											<li><a href="${pageContext.request.contextPath }/list.do?pageIndex=${i}&cid=${cid}">${i }</a></li>

													</c:if>
													<c:if test="${pageInfo.pageIndex==i }">
															<li class="disablepage"> ${i }</li>
													</c:if>
														</c:forEach>
													</c:if>
											<!-- ------总页数小于6页的情况 -------------------->
													<c:if test="${pageInfo.totalPage>6 }">
														<c:if test="${pageInfo.pageIndex<3 }">
															<c:forEach begin="1" end="6" var="i">
																<c:if test="${pageInfo.pageIndex!=i }">
											<li><a href="${pageContext.request.contextPath }/list.do?pageIndex=${i}&cid=${cid}">${i }</a></li>

													</c:if>
													<c:if test="${pageInfo.pageIndex==i }">
															<li class="disablepage"> ${i }</li>
													</c:if>
															</c:forEach>
														</c:if>
														<c:if test="${pageInfo.pageIndex>pageInfo.totalPage-3 }">
															<c:forEach begin="${pageInfo.totalPage-5 }" end="${pageInfo.totalPage }" var="i">
																<c:if test="${pageInfo.pageIndex!=i }">
												<li><a href="${pageContext.request.contextPath }/list.do?pageIndex=${i}&cid=${cid}">${i }</a></li>

																</c:if>
													<c:if test="${pageInfo.pageIndex==i }">
															<li class="disablepage"> ${i }</li>
													</c:if>
															</c:forEach>
														</c:if>
														<c:if test="${pageInfo.pageIndex<=pageInfo.totalPage-3&&pageInfo.pageIndex>=3 }">
																		<c:forEach begin="${pageInfo.pageIndex-2 }" end="${pageInfo.pageIndex+3 }" var="i">
																<c:if test="${pageInfo.pageIndex!=i }">
												<li><a href="${pageContext.request.contextPath }/list.do?pageIndex=${i}&cid=${cid}">${i }</a></li>

																</c:if>
													<c:if test="${pageInfo.pageIndex==i }">
															<li class="disablepage"> ${i }</li>
													</c:if>
															</c:forEach>
														
														</c:if>
													</c:if>


													<c:if test="${pageInfo.pageIndex<pageInfo.totalPage }">
														<li><a
															href="${pageContext.request.contextPath }/list.do?pageIndex=${pageInfo.pageIndex+1}&cid=${cid}">下一页</a></li>

													</c:if>
													<c:if test="${pageInfo.pageIndex>=pageInfo.totalPage }">
															<li class="disablepage"> 下一页>></li>
													</c:if>
													
												</ul>
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </div>
    <%@include file="footer.jsp" %>
</body>
</html>
