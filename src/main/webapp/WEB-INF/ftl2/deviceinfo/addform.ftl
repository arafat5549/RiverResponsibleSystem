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
							<input type="hidden" id="id" name="id"/>
							<input type="text" id="sno" name="sno" data-required="1" class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">名称
						<span class="required">
							 *
						</span>
						</label>
						<div class="col-md-8">
							<input type="text" id="name" name="name" data-required="1" class="form-control"/>
						</div>
					</div>				
					<div class="form-group">
						<label class="control-label col-md-3">协议
						<span class="required">
							 *
						</span>
						</label>
						<div class="col-md-8">
							<input type="text" id="protocol" name="protocol" data-required="1" class="form-control"/>
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
					   			<option value="${brand.id}">${brand.name}</option>
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
					   			<option value="${category.id}">${category.name}</option>
									</#list>
								</#if>
                                </select>
                            </div>
					</div>
                        <div class="form-group">
                            <label class="control-label col-md-3">供应商
                                <span class="required">
							 *
						</span>
                            </label>
                            <div class="col-md-8">
                                <input type="text" id="supplier" name="supplier" data-required="1" class="form-control"/>
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
       FormCl.init();
    });
</script>