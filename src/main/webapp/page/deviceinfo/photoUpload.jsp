<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String deviceinfoId = request.getParameter("deviceinfoId");
	String count = request.getParameter("count");
%>
<div class="modal-body">
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN MULTI SELECT-->
			<div class="form-group">
				<div class="col-md-12">
					<div class="carousel-caption" style="color:#F00">还可以上传<%=count%>张</div>
					<input type="file" multiple name="filename" id="ssi-upload" />
				</div>
			</div>
			<!-- END MULTI SELECT-->
		</div>
	</div>
</div>

<div class="modal-footer">
	<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
	   onclick="javascript:$(this).closest('.window-body').dialog('destroy')" style="width:90px">取消</a>
</div>

<script>
    $( '#ssi-upload' ).ssi_uploader( {
        url: basePath+"api/deviceinfos/photoUpload",                                             //上传路径
        method: 'POST',
        data:{"deviceinfoId":<%=deviceinfoId%>},
        maxFileSize: 10,                                               //最大文件尺寸，这里10代表10m
        maxNumberOfFiles: <%=count%>,
        allowed: ['jpg', 'jpeg', 'png', 'bmp', 'gif' ],    //允许格式，自行添加
        onUpload: function() {
            grid.datagrid('reload');
            $('.window-body').dialog("destroy");
            alert( "上传完毕" )
        }
    } );
</script>