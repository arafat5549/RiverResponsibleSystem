<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<script type="text/javascript">
function getAllPermission(){
	$.ajax({  
	    type: 'POST',
	    url:'${pageContext.request.contextPath}/menu/getOneMenu',
	    success: function(data){
	        $.each(data,function(i,n){
	            if (i == 0) {//显示第一个一级菜单下的二级菜单  
                 	$('#menu').accordion('add',{
                 		id:n.id,
		                title: n.name,
		                iconCls:n.icon,
		                selected: true,
		                content:'<div style="padding:10px"><ul name="'+n.name+'"></ul></div>',
		            });  
		        }else {  
                	$('#menu').accordion('add',{
                		id:n.id,
		                title: n.name,
		                iconCls:n.icon,
		                selected: false,
		                content:'<div style="padding:10px"><ul name="'+n.name+'"></ul></div>',
		            });  
                } 
	        });
	        
	    }
	}); 
}
$(function() { 
	//树形菜单
	$('#layout_west_tree').tree({
		url : '${pageContext.request.contextPath}/menu/allTreeNode',
		parentField : 'pid',
		lines : true,
		onClick : function(node) {
			var url;
			if (node.attributes.url) {
				url = '${pageContext.request.contextPath}/' + node.attributes.url;
			} else {
				url = '${pageContext.request.contextPath}/error/dog.jsp';
			}
			if (url.indexOf('druidController') > -1) {/*要查看连接池监控页面*/
				layout_center_addTabFun({
					title : node.text,
					closable : true,
					iconCls : node.iconCls,
					content : '<iframe src="' + url + '" frameborder="0" style="border:0;width:100%;height:99%;"></iframe>'
				});
			} else {
				layout_center_addTabFun({
					title : node.text,
					closable : true,
					iconCls : node.iconCls,
					href : url
				});
			}
		}
	});
	//普通菜单
	getAllPermission();
	$('#menu').accordion({  
	    onSelect : function(title, index) {
	    	var p = $('#menu').accordion('getSelected');
	    	var options = p.panel('options');
	    	if(options.id!=""){
	    	 	$("ul[name='"+title+"']").tree({
	            	url: '${pageContext.request.contextPath}/menu/getSubTreeMenu?pId='+options.id,
	            	lines : true,
	            	onClick:function(node){
			            var url;
						if (node.attributes.url) {
							url = '${pageContext.request.contextPath}/' + node.attributes.url;
						} else {
							url = '${pageContext.request.contextPath}/error/dog.jsp';
						}
						layout_center_addTabFun({
							title : node.text,
							closable : true,
							iconCls : node.iconCls,
							href : url
						});
	            	}
	            });
			}
	    }  
	});
});
</script>
<!-- <div class="easyui-accordion" data-options="fit:false,border:false"> -->
<!-- 	<div title="树形系统菜单" data-options="iconCls:'icon-reload'"> -->
<!-- 		<ul id="layout_west_tree"></ul> -->
<!-- 	</div> -->
<!-- </div> -->
<div id="menu" class="easyui-accordion" data-options="fit:true,border:false">
	<div title="" data-options="iconCls:'icon-reload'"></div>
</div>