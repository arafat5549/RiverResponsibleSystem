<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%String basePath = request.getContextPath();%>
<script type="text/javascript">
function getSysResource(){
        $.ajax({
            type: 'GET',
            url:'${pageContext.request.contextPath}/api/permissions/getAllMenus',
	    success: function(data){
	        $.each(data,function(i,n){
	            $('#menu').accordion('add',{
               		id:n.id,
	                title: n.text,
	                iconCls:n.iconCls,
	                selected:i==0,
	                content:'<div style="padding:10px"><ul id="menuTree_'+n.id+'" name="'+n.text+'"></ul></div>',
	            });
	        });
	    }
	});
}
$(function() {
	//普通菜单
	getSysResource();
	$('#menu').accordion({
		animate:true,
		fit:true,
		border:false,
	    onSelect : function(title, index) {
	    	var p = $('#menu').accordion('getSelected');
	    	var options = p.panel('options');
	    	if(options.id){
	    	 	var curtree = $("#menuTree_"+options.id).tree({
	    	 		idField : 'id',
                    method:'GET',
	            	url:'${pageContext.request.contextPath}/api/permissions/getAllMenus?parentId='+options.id,
	            	lines : true,
	            	onClick:function(node){
		            	var leaf = curtree.tree('isLeaf', node.target);
		            	if(leaf){     //子节点
		            		var url;
							if (node.url) {
								url = '<%=basePath%>'+node.url;
							} else {
								url = '<%=basePath%>/jsp/error/dog.jsp';
							}
		            		layout_center_addTabFun({
								title : node.text,
								closable : true,
								iconCls : node.iconCls,
								href : url
							});
// 							$('#indexLayout').layout('collapse','west');
		            	}else{
		            		var child = curtree.tree('getChildren',node.target);
		            		var state = curtree.tree('getSelected').state;
		            		if(child.length > 0 && state =='closed'){
			                    curtree.tree('expand',node.target);
			                }else if(child.length > 0 && state =='open'){
			                     curtree.tree('collapse',node.target);
			                }
		            	}
	            	}
	            });
			}
	    }
	});
});

</script>

<div id="menu" class="easyui-accordion" data-options="fit:true,border:false">
	<div title="" data-options="iconCls:'icon-reload'"></div>
</div>