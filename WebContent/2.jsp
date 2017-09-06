<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title></title>
		<script src="${pageContext.request.contextPath }/js/jquery-2.1.4.min.js" type="text/javascript"></script>
		<script type="text/javascript">
			$(function(){
				$('#search').on('input',function(){
					$.ajax({
						type:"post",
						url:"/BookShop/search.do",
						dataType:"json",
						async:true,
						data:{key:$(this).val()},
						success:function(data){
							$('#ds').empty();
							console.log(data);
							$(data).each(function(index,obj){
								
								$('#ds').append("<option value='"+obj.keyword+"'>");
							})
							
						}
					});
				})
			})
		</script>
	</head>
	<body>
		<input type="text" name="search" id="search" value="" list="ds"/>
		<datalist id="ds"></datalist>
	</body>
</html>
