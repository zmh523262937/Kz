/**
 * 
 */
$(function(){
	$('#tab').datagrid({
		url:'/BookShop/orderback.do?type=list',
		pageSize:10,
		pageIndex:1,
		pagination:true,
		title:'订单信息',
		iconCls:'icon-ok',
		collapsible:true,
		
		columns:[[{
			field:'chk',checkbox:true
		},{
			field:'orderid',title:'订单编号'
		},{
			field:'userid',title:'用户编号'
		},{
			field:'totalprice',title:'订单总价'
		},{
			field:'orderDate',title:'订单日期'
		},{
			field:'address',title:'收货地址'
		},{
			field:'username',title:'收件人'
		},{
			field:'usertel',title:'电话'
		}]],
		view: detailview, 
		detailFormatter:function(index,row){    
	        return '<table id="tab3-' + index + '" style="height: 200px;"></table>';    
	    },    
	    onExpandRow: function(index,row){  
	        $('#tab3-'+index).datagrid({    
	            url:'/BookShop/orderback.do?type=detail',
	    		queryParams:{orderid:row.orderid},
	    		title:'商品详情',
	    		iconCls:'icon-ok',
	    		collapsible:true,
	    		singleSelect:true,
	    		rownumbers:true,
	    		columns:[[{
	    			field:'orderid',title:'订单编号'
	    		},{
	    			field:'orderdetailid',title:'商品详情编号'
	    		},{
	    			field:'gtitle',title:'商品名称'
	    		},{
	    			field:'gnumber',title:'商品数量'
	    		},{
	    			field:'gsalprice',title:'商品价格'
	    		}]],
	            onLoad:function(){    
	                $('#tab3').datagrid('fixDetailRowHeight',index);    
	            }    
	        });    
	        
	    } 
	})
	})

	