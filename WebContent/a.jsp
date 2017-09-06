<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/pagination/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/pagination/mricode.pagination.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/pagination/mricode.pagination.css"/>
<script type="text/javascript" >
	$(function(){
		$('#pagination').pagination({
			pageSize:5,
			firstBtnText:'首页',
			lastBtnText:'尾页',
			prevBtnText:'上一页',
			nextBtnText:'下一页',
			showInfo:true,
			noInfoText:'没有数据',
			showJump:true,
			showPageSizes:true,
			pageSizeItems:[10,20,30],
			remote:{
				url:'/BookShop/page.do',
				totalName:'totalNumber',
				success:function(pageinfo){
					$('#container').empty();
					var ul="<ul>";
					$(pageinfo.data).each(function(index,good){
						ul+="<li>"+good.gtitle+"</li>";
					
					})
					ul+="</ul>";
					$('#container').append(ul);
				}
			}
		})
	})
	
</script>
</head>
<body>
<div id="container"></div>
<div id="pagination"></div>
</body>
</html>