<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">新增数据字典</h4>
</div>

<div class="modal-body">
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN FORM-->
			<form action="#" id="form_cl" class="form-horizontal">
				<div class="form-body">
					<div class="form-group">
						<label class="control-label col-md-3">分组
						<span class="required">
							 *
						</span>
						</label>
						<#--<div class="col-md-8">
							<input type="hidden" id="id" name="id"/>
							<input type="text" id="group" name="group" data-required="1" class="form-control"/>
						</div>-->
                        <div class="col-md-8">
                            <select id="group" name="group" class="form-control select2me">
                                <option value="">请选择...</option>
								<#if groups??>
									<#list groups as group>
					   			<option value="${group}">${group}</option>
									</#list>
								</#if>
                            </select>
                        </div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">排序号
						<span class="required">
							 *
						</span>
						</label>
						<div class="col-md-8">
							<input type="text" id="sortNo" name="sortNo" data-required="1" class="form-control"/>
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