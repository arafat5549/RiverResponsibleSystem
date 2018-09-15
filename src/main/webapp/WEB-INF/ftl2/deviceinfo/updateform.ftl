<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">新增设备</h4>
</div>

<div class="modal-body">
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN FORM-->
			<form action="#" id="form_cl" class="form-horizontal">
				<div class="form-body">
					<div class="form-group">
						<label class="control-label col-md-3">序列号
						<span class="required">
							 *
						</span>
						</label>
						<div class="col-md-8">
							<input type="hidden" id="id" name="id" value="${deviceinfo.id}"/>
							<input type="text" id="sno" name="sno" value="${deviceinfo.sno}" data-required="1" class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">名称
						<span class="required">
							 *
						</span>
						</label>
						<div class="col-md-8">
							<input type="text" id="name" name="name" value="${deviceinfo.name}" data-required="1" class="form-control"/>
						</div>
					</div>				
					<div class="form-group">
						<label class="control-label col-md-3">协议
						<span class="required">
							 *
						</span>
						</label>
						<div class="col-md-8">
							<input type="text" id="protocol" name="protocol" value="${deviceinfo.protocol}" data-required="1" class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">品牌
						<span class="required">
							 *
						</span>
						</label>
						<div class="col-md-8">
							<select id="brandId" name="brandId" class="form-control select2me">
								<option value="">请选择...</option>
								<#if brands??>
					   			<#list brands as brand>
					   			<option
										<#if brand.id == deviceinfo.brandId>selected="selected"</#if>
										value="${brand.id}" >${brand.name}</option>
					   			</#list>
					   			</#if>
							</select>
						</div>
                    </div>
                        <div class="form-group">
                            <label class="control-label col-md-3">分类
                                <span class="required">
							 *
						</span>
                            </label>
                            <div class="col-md-8">
                                <select id="categoryId" name="categoryId" class="form-control select2me">
                                    <option value="">请选择...</option>
								<#if categorys??>
									<#list categorys as category>

					   			<option
										<#if deviceinfo.categoryId == category.id >selected="selected"</#if>
										value="${category.id}">${category.name}</option>
									</#list>
								</#if>
                                </select>
                            </div>
					</div>
                  <#--  <div class="form-group">
                        <label class="control-label col-md-3">图片</label>
                        <div class="col-md-8">
                                <input type="file" multiple id="ssi-upload"/>
                        </div>
                    </div>-->
                        <div class="form-group">
                            <label class="control-label col-md-3">供应商
                                <span class="required">
							 *
						</span>
                            </label>
                            <div class="col-md-8">
                                <input type="text" id="supplier" name="supplier" value="${deviceinfo.supplier}" data-required="1" class="form-control"/>
                            </div>
                        </div>

			</form>
			<!-- END FORM-->
		</div>
	</div>
</div>

<div class="modal-footer">
	<button type="button" class="btn default" data-dismiss="modal">关闭</button>
	<button type="button" class="btn blue" onclick="javascript:$('#form_cl').submit();">保存</button>
</div>

<script>
    jQuery(document).ready(function() {
      /*  $('#fileupload').fileupload({
            dataType: 'json',
            add: function (e, data) {
                data.context = $('<button/>').text('Upload')
                        .appendTo(document.body)
                        .click(function () {
                            $(this).replaceWith($('<p/>').text('Uploading...'));
                            data.submit();
                        });
            },
            done: function (e, data) {
                data.context.text('Upload finished.');
            }
        });*/
       FormCl.init();
    });
</script>
<script type="text/javascript">
/*    $( '#ssi-upload' ).ssi_uploader( {
        url: 'index.php',                                             //上传路径
        method: 'POST',
        maxFileSize: 10,                                               //最大文件尺寸，这里10代表10m
        allowed: ['jpg', 'jpeg', 'png', 'bmp', 'gif', 'txt', 'doc' ],    //允许格式，自行添加
        onUpload: function()
        {
            alert( "正在上传" )
        }
    } );*/
</script>