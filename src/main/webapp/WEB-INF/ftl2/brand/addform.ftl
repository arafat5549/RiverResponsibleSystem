<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
	<h4 class="modal-title">新增品牌</h4>
</div>

<div class="modal-body">
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN FORM-->
			<form action="#" id="form_cl" class="form-horizontal">
				<div class="form-body">
					<div class="form-group">
						<label class="control-label col-md-3">名称
						<span class="required">
							 *
						</span>
						</label>
						<div class="col-md-8">
							<input type="hidden" id="id" name="id"/>
							<input type="text" id="name" name="name" data-required="1" class="form-control"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">英文名
						<span class="required">
							 *
						</span>
						</label>
						<div class="col-md-8">
							<input type="text" id="ename" name="ename" data-required="1" class="form-control"/>
						</div>
					</div>				
					<div class="form-group">
						<label class="control-label col-md-3">网址
						<span class="required">
							 *
						</span>
						</label>
						<div class="col-md-8">
							<input type="text" id="website" name="website" data-required="1" class="form-control"/>
						</div>
					</div>
				<#--	<div class="form-group">
						<label class="control-label col-md-3">性别
						<span class="required">
							 *
						</span>
						</label>
						<div class="col-md-8">
							<div class="radio-list" data-error-container="#form_user_gender_error">
								<label>
								<input type="radio" name="gender" value="1"/>
								男 </label>
								<label>
								<input type="radio" name="gender" value="0"/>
								女 </label>
							</div>
							<div id="form_user_gender_error">
							</div>
						</div>
					</div>-->

					<#--<div class="form-group">
						<label class="control-label col-md-3">所属部门
						<span class="required">
							 *
						</span>
						</label>
						<div class="col-md-8">
							<select id="departmentId" name="departmentId" class="form-control select2me">
								<option value="">请选择...</option>
								<#if departments??>
					   			<#list departments as department>
					   			<option value="${department.id}">${department.name}</option>
					   			</#list>
					   			</#if>
							</select>
						</div>
					</div>-->
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