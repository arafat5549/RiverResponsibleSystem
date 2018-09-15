<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">分配角色</h4>
</div>

<div class="modal-body">
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN MULTI SELECT-->
				<div class="form-group">
					<div class="col-md-12">
						<#--<input  type="hidden" id="id" name="id" value="${id}" />-->
							<div class="carousel-caption" style="color:#F00">还可以上传${count}张</div>
                            <input type="file" multiple name="filename" id="ssi-upload" />
					</div>
				</div>
			<!-- END MULTI SELECT-->
		</div>
	</div>
</div>

<div class="modal-footer">
	<input type="hidden" id="deviceinfoId" name="deviceinfoId" value="${id}"/>
	<button type="button" class="btn default" data-dismiss="modal">关闭</button>
	<#--<button type="button" class="btn blue" onclick="javascript:Deviceinfo.assign();">保存</button>-->
</div>
<script>
      $( '#ssi-upload' ).ssi_uploader( {
        url: 'uploader.do',                                             //上传路径
        method: 'POST',
          data:{"deviceinfoId":$("#deviceinfoId").val()},
        maxFileSize: 10,                                               //最大文件尺寸，这里10代表10m
		maxNumberOfFiles: ${count},
        allowed: ['jpg', 'jpeg', 'png', 'bmp', 'gif' ],    //允许格式，自行添加
        onUpload: function() {
            Cl.hideModalWindow(Cl.modalName);
            Cl.refreshDataTable(DataTableCl.tableName);
            alert( "上传完毕" )
        }
    } );
</script>