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
				$("#uname").on("blur",function(){
					
					$.ajax({
						data:{'uname':$(this).val()},
						dataType:'text',
						type:'GET',
						url:'/BookShop/checkname.do',
						success:function(data){
							if(data=="0"){
								$('span:first').html('可以使用');
								
							}if(data=="1"){
								$('span:first').html('已被注册');
							}
						}
					})
				})
				$('#email').on("blur",function(){
					
					$.ajax({
						data:{'data':$(this).val()},
						dataType:"text",
						type:'POST',
						url:'/BookShop/checkEmail.do',
						success:function(data){
							if(data=="0"){
								
								$('span:eq(1)').html('可以使用');
								
							}if(data=="1"){
								
								$('span:eq(1)').html('已被注册');
							}
						}
					})
				})
				
			})
		</script>
	</head>
	<body>
		账号:<input type="text" name="uname" id="uname" value="" /><span></span>
		邮箱:<input type="text" name="email" id="email" value="" /><span></span>
	</body>
</html>
