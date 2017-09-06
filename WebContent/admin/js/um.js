/**
 * 
 */
$(function(){
	$('#btnsave').on('click',function(){
		var isValid = $('#f2').form('validate');
		if(!isValid){
			$.messager.alert("提示","请按格式添加数据","error")
		}
		if(isValid){
		$.ajax({
			type:'post',
			url:'/BookShop/usercontroller.do?type=add',
			data:$('#f2').serialize(),
				  
			success:function(data){
				if(data=="1"){
					$.messager.alert("提示","添加成功","info")
					$('#f2').form('clear');
					$('#add').window('close');
					$('#tab').datagrid('reload');
					
				}
			}
		})
		}
//		$('#tab').datagrid('reload');
	})
	$('#btncancel').on('click',function(){
		$('#f2').form('clear');
		$('#update').window('close');
	})
	$('#tab').datagrid({
		url:'/BookShop/usercontroller.do',
		title:'用户信息',
		iconCls:'icon-edit',
		collapsible:true,
		rownumbers:true,
		singleSelect:true,
		queryParams:{type:"list"},
		idField:'userid',
		loadMsg:'正在加载,请稍后',
		toolbar:[
		         {text:'编辑',iconCls:'icon-edit',handler:function(){
		        	 var row =$('#tab').datagrid('getSelected');
		        	 if(row==null){
		        		 $.messager.alert('提示','请选择要修改的行','info')
		        	 }
		        	 if(row!=null){
		        		 $('#gname').textbox('setValue',row.gname)
		        		 $('#uloginid').textbox('setValue',row.uloginid)
		        		 $('#uloginid').textbox('readonly')
		        		 $('#upassword').textbox('setValue',row.upassword)
		        		 $('#sex').textbox('setValue',row.usex) 
		        		 $('#address').textbox('setValue',row.uaddress)
		        		 $('#tel').textbox('setValue',row.utel)
		        		
		        		var radios = document.getElementsByName("userstate");  
		        		    for (var i=0; i<radios.length; i++){  
		        		        if (radios[i].value==row.ustateid) {  
		        		            radios[i].checked= true;  
		        		            break;  
		        		        }  
		        		    }  
		        		 $('#update').window('open');
		        		 $('#update').dialog({
		        			 closed:false,
		        			 closable:false,
		        			 title:'修改用户',
		        			 iconCls:'icon-edit',
		        			 buttons:[{
		        				 text:'保存修改',
		        				 iconCls:'icon-save',
		        				 handler:function(){
		        					 var isValid = $('#f2').form('validate');
		        					 if(!isValid){
		        							$.messager.alert("提示","请按格式修改数据","error")
		        						}
		        					 if(isValid){
		        						$.ajax({
		        							type:'post',
		        							url:'/BookShop/usercontroller.do?type=update&userid='+row.userid,
		        							data:$('#f2').serialize(),
		        								  
		        							success:function(data){
		        								if(data=="1"){
		        									$.messager.alert("提示","修改成功","info")
		        									$('#f2').form('clear');
		        									$('#update').window('close');
		        									$('#tab').datagrid('reload');
		        									
		        								}
		        							}
		        						})
		        					 }
		        				 }
		        			 },{
		        				 text:'取消',
		        				 iconCls:'icon-cancel',
		        				 handler:function(){
		        					 $('#f2').form('clear');
		        					$('#update').window('close');
		        					$('#tab').datagrid('reload');
		        				 }
		        			 }]
		        		 })
		        	 }
		         }},'-',
		         {text:'删除',iconCls:'icon-remove',handler:function(){
		        	 var row =$('#tab').datagrid('getSelected');
		        	 if(row==null){
		        		 $.messager.alert("提示","请选择要删除的行","info")
		        	 }else{
		        		 $.messager.confirm('删除确认',"确定删除"+row.uloginid+"?",function(r){
		        			 if(r){
		        				 $.ajax({
		        					 type:'post',
		        					 url:'/BookShop/usercontroller.do',
		        					 data:{type:'remove',userid:row.userid},
		        					 success:function(data){
		        						 if(data=='1'){
		        							 $.messager.alert("提示","删除成功！","info")
		        							 $('#tab').datagrid('reload');
		        							
		        							
		        						 }
		        						 
		        					 }
		        				 })
		        			 }
		        		 })
		        	 } 
		         }},'-',
		         {text:'添加',iconCls:'icon-add',handler:function(){
		        	$('#update').window('open');
		        	$('#sex').combobox('setValue',$('#sex').combobox('getData')[0].value);
		        	var radios = document.getElementsByName("userstate");  
		        	radios[1].checked=true;
		        	$('#update').dialog({
		        		closed:false,
		        		title:'增加用户',
		        		closable:true,
		        		iconCls:'icon-add',
		        		buttons:[{
		        			text:'确定',
		        			iconCls:'icon-ok',
		        			handler:function(){
			        			var isValid=$('#f2').form('validate');
			        			if(!isValid){
			        				$.messager.alert('提示','请按格式输入信息','info')
			        			}
			        			if(isValid){
			        				$.ajax({
			        					type:'post',
			        					url:'/BookShop/usercontroller.do?type=add',
			        					data:$('#f2').serialize(),
			        					success:function(data){
			        						$.messager.alert("提示","添加成功","info")
        									$('#f2').form('clear');
        									$('#update').window('close');
        									$('#tab').datagrid('reload');
			        					}
			        				})
			        			}
			        		}
		        		}]
		        		
		        	})
		        	
		         }},'-',
		         {text:'设置管理员',iconCls:'icon-man',handler:function(){
		        
		        	 var row =$('#tab').datagrid('getSelected');
		        	 if(row==null){
		        		 $.messager.alert("提示","请选择一人","info");
		        		 
		        	 }
		        	 if(row!=null){
		        		 if(row.uroleid=='377D0AE90A804D289F301FB085A6E9AA'){
		        			 $.messager.alert('提示','该用户已经是管理员','info')
		        		 }
		        		 if(row.uroleid!='377D0AE90A804D289F301FB085A6E9AA'){
		        		 $('#confirmID').window('open');
		        		 $('#confirmID').dialog({
		        			 closed:false,
		        			 	buttons:[{
		        			text:'确定',
		        			iconCls:'icon-ok',
		        			handler:function(){
			        			var isValid=$('#cpassword').textbox('getValue');
			        			if(!isValid){
			        				$.messager.alert('提示','密码不得为空','info')
			        			}
			        			if(isValid){
			        				$.ajax({
			        					type:'post',
			        					url:'/BookShop/usercontroller.do?type=toAdmin',
			        					data:{userid:row.userid,username:row.uloginid,password:$('#cpassword').textbox('getValue')},
			        					success:function(data){
			        						if(data=="0"){
			        							$.messager.alert('提示','密码错误','info')
			        							
			        						}
			        						if(data=="1"){
			        							$.messager.alert('提示','设置成功','info')
			        							$('#cpassword').textbox('setValue',"");
			        							$('#confirmID').window('close');
			        						}
			        					
        									$('#f2').form('clear');
        									$('#update').window('close');
        									$('#tab').datagrid('reload');
			        					}
			        				})
			        			}
			        		}
		        		}]
		        		 })
		        	 }
		        	 }
		         }}
		         ],
		columns:[[{
			field:'chk',checkbox:true
		},{
			field:'userid',title:'编号'
		},{
			field:'uemail',title:'邮箱'
		},{
			field:'uloginid',title:'账号'
		},{
			field:'upassword',title:'密码'
		},{
			field:'usex',title:'性别'
		},{
			field:'uaddress',title:'地址'
		},{
			field:'utel',title:'联系方式'
		},{
			field:'ustateid',title:'状态码'
		},{
			field:'uroleid',title:'角色码'
		}]]
	})
})