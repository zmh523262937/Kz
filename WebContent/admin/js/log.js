$(function(){

	$('#adminName').on('input',function(){
		if($(this).val()==""){
			loadLog($(this).val());
		}
		$.ajax({
			type:"post",
			url:"/BookShop/log.do?type=search",
			dataType:"json",
			async:true,
			data:{key:$(this).val()},
			success:function(data){
				$('#ds').empty();
				$(data).each(function(index,obj){
					
					$('#ds').append("<option value='"+obj+"'>");
				})
				
			}
		});
	})
	
	loadLog($('#adminName').val());
	$('#search').on('click',function(){
		var adminName = $('#adminName').val();
		if(adminName==""){
			loadLog(adminName);
		}
		if(adminName!=""){
			loadLog(adminName);
		}
		
		
	})
	

	})
	
	function loadLog(adminName){
	$('#tab').datagrid({
		url:'/BookShop/log.do?adminName='+adminName+'&type=list',
		pageSize:10,
		pageIndex:1,
		pagination:true,
		title:'员工操作记录',
		iconCls:'icon-man',
		collapsible:true,
		columns:[[{
			field:'chk',checkbox:true
		},{
			field:'adminName',title:'管理员'
		},{
			field:'userName',title:'用户'
		},{
			field:'optionName',title:'操作'
		},{
			field:'date',title:'日期'
		}]]
		


	})
}