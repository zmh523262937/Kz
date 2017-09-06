$(function(){
	$('#img').textbox({onClickIcon:function(){
		alert("1");
	}})
	
	$('#cate').combobox({
		url:'/BookShop/catecontroller.do',
		valueField:'cid',
		textField:'cname',
		onLoadSuccess:function(){
			var datas=$(this).combobox('getData');
			if(datas.length>0){
				$(this).combobox('setValue',datas[0].cid);
				var cid = $('#cate').combobox('getValue');
				loadGoods(cid);
				
			}
		},
		onSelect:function(data){
			loadGoods(data.cid)
		}
	})
})
function loadGoods(cid){
	$('#tab').datagrid({
		url:'/BookShop/goods.do?cid='+cid+'&type=list',
		pageSize:10,
		pageIndex:1,
		pagination:true,
		title:'商品数据',
		iconCls:'icon-ok',
		collapsible:true,
		
		toolbar:[
		         {text:'编辑',iconCls:'icon-edit',handler:function(){
		        	 var row =$('#tab').datagrid('getSelected');
		        	 if(row==null){
		        		 $.messager.alert('提示','请选择要修改的行','info')
		        	 }
		        	 if(row!=null){
		        		 $('#gtitle').textbox('setValue',row.gtitle)
		        		 $('#gauthor').textbox('setValue',row.gauthor)
		        		 $('#gsaleprice').textbox('setValue',gsaleprice)
		        		 $('#ginprice').textbox('setValue',row.ginprice)
		        		 $('#gcate').textbox('setValue',row.gcid) 
		        		 $('#gpublish').textbox('setValue',row.gpid)
		        		 $('#img').textbox('setValue',row.img)
		        		  $('#disc').textbox('setValue',row.disc)
		        		  
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
		        		 $.messager.confirm('删除确认',"确定删除该"+row.gtitle+"吗?",function(r){
		        			 if(r){
		        				 $.ajax({
		        					 type:'post',
		        					 url:'/BookShop/goods.do',
		        					 data:{type:'remove',gid:row.gid},
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
		        	$('#update').dialog({
		        		closed:false,
		        		title:'增加用户',
		        		contentType:"multipart/form-data; text/xml;charset=utf-8",
		        		closable:true,
		        		iconCls:'icon-add',
		        		buttons:[{
		        			text:'确定',
		        			iconCls:'icon-ok',
		        			handler:function(){
		        				$('#f2').form('clear');
			        			var isValid=$('#f2').form('validate');
			        			if(!isValid){
			        				$.messager.alert('提示','请按格式输入信息','info')
			        			}
			        			if(isValid){
			   
			        				$.ajax({
			        					type:'post',
			        					url:'/BookShop/goods.do?type=add',
			        					data:$('#f2').serialize(),
			        					success:function(data){
			        						console.log(data);
			        						if(data=='0'){
			        							$.messager.alert("提示","添加失败,无此出版社","info")
			        						}
			        						if(data=='1'){
			        							$.messager.alert("提示","添加失败,无此类别","info")
			        						}
			        						if (data=='2'){
			        						$.messager.alert("提示","添加成功","info")
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
		        	
		         }}],columns:[[{
		 			field:'chk',checkbox:true
		 		},{
		 			field:'gid',title:'商品编号'
		 		},{
		 			field:'gtitle',title:'商品名称'
		 		},{
		 			field:'gauthor',title:'作者'
		 		},{
		 			field:'gsaleprice',title:'售价'
		 		},{
		 			field:'ginprice',title:'进价'
		 		},{
		 			field:'gclicks',title:'点击量'
		 		},{
		 			field:'cid',title:'类别'
		 		},{
		 			field:'pid',title:'出版社'
		 		}]],
		
	view: detailview, 
	 detailFormatter:function(index,row){    
        return '<table id="tb_' + index + '" style="height:150px;"></table>';    
    },    
    onExpandRow: function(index,row){    
        $('#tb_'+index).datagrid({    
   		 data:[row],//此时的data是整行对象
   		 iconCls:'icon-ok',
   		 collapsible:true,
   		 rownumbers:true,
   		 nowrap:false,//分行显示
   		 columns:[[{
   			 field:'gimg',title:'商品图片',
   			 formatter:imgFormatter
   		 },{
   			 field:'gdesc',title:'商品描述',
   		 }
   		 ]],
            onLoad:function(){    
                $('#tab').datagrid('fixDetailRowHeight',index);    
            }    
        });  
  }
	
});
}
function imgFormatter(value){
	if('' != value && null != value){    
	    value = "<img onclick=download(\""+value+"\") style='width:66px; height:60px;margin-left:3px;' src='/BookShop/bookcover/" + value + ".jpg' title='点击查看图片'/>";     
	    return  value;
	}
}
function download(img){  
    var simg =  "/BookShop/bookcover/"+ img+".jpg";  
    $("#simg").attr("src","/BookShop/bookcover/101.jpg");  
    $('#dlg').dialog({  
    	title: '预览',  
    	width: $("#simg").attr("width"),  
    	height:$("#simg").attr("height"),  
    	resizable:true,  
    	closed: false,  
    	cache: false,  
    	modal: true  
    });  
      
} 
 