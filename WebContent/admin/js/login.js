$(function(){
	$('#login').dialog({
		width:400,
		height:200,
		title:'用户登入',
		collapsible:true,
		iconCls:'icon-man',
		buttons:[{
			text: '登录',
			iconCls: 'icon-ok',
			handler: function() {
				//需要判断用户是否通过验证;
				var isValid = $('form').form('validate');
				if(isValid){
					$.ajax({
						type:'post',
						url:'/BookShop/loginController.do',
						data:$('form').serialize(),
						success:function(data){
							if(data=="2"){
								window.location='index.html';
								
							}if(data=="0"){
								$.messager.alert('登录失败','登录失败，请检查登录密码');
							}
							if(data=="1"){
								$.messager.alert('登录失败','您的权限无法登陆');
							}
						}
					})
				}else{
					$.messager.alert('出错了','请填写表单信息','error')
				}
		
			}
		},{
			text: '取消',
			iconCls: 'icon-cancel',
			handler: function() {
				$.messager.alert("cancel")
			}
		}]
		
	})
})
