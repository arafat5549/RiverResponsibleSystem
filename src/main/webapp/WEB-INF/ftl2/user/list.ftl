<!DOCTYPE html>
<!-- 
Template Name: Metronic - Responsive Admin Dashboard Template build with Twitter Bootstrap 3.1.1
Version: 2.0.2
Author: KeenThemes
Website: http://www.keenthemes.com/
Contact: support@keenthemes.com
Purchase: http://themeforest.net/item/metronic-responsive-admin-dashboard-template/4021469?ref=keenthemes
License: You must have a valid license purchased only from themeforest(the above link) in order to legally use the theme for your project.
-->
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
    <meta charset="utf-8"/>
    <title>金钱猫 | 配置中心 - 帐户管理</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>

<#include "../csslib.ftl" >

</head>
<!-- END HEAD -->
<!-- BEGIN BODY -->
<body class="page-header-fixed">

<#include "../header.ftl" >

<div class="clearfix">
</div>

<!-- BEGIN CONTAINER -->
<div class="page-container">
	
	<#include "../sidebar.ftl" >

    <!-- BEGIN CONTENT -->
    <div class="page-content-wrapper">
        <div class="page-content">
            <!-- BEGIN PAGE HEADER-->
            <div class="row">
                <div class="col-md-12">
                    <!-- BEGIN PAGE TITLE & BREADCRUMB-->
                    <h3 class="page-title">
                        帐户管理
                    </h3>
                    <ul class="page-breadcrumb breadcrumb">
                        <li>
                            <i class="fa fa-home"></i>
                            <a href="${BasePath}">
                                主页
                            </a>
                            <i class="fa fa-angle-right"></i>
                        </li>
                        <li>
                            <a href="${BasePath}">
                                配置中心
                            </a>
                            <i class="fa fa-angle-right"></i>
                        </li>
                        <li>
                            <a>
                                帐户管理
                            </a>
                        </li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row">
                <div class="col-md-12">
                    <!-- Begin: life time stats -->
                    <div class="portlet">
                        <div class="portlet-title">
                            <div class="caption">
                                <i class="fa fa-user"></i>帐户列表
                            </div>
                        </div>
                        <!-- 检索  -->
                        <form class="form-inline" action="/user/export" role="form" id="searchForm" onchange="search(this.id)">
                            <table class="table">
                                <tr>
                                    <td>
                                        <div class="form-group">
                                            <label class="col-sm-pull-2 control-label" for="keyWord">名称</label>
                                            <input type="text" class="form-control"  name="keyWord"
                                                   placeholder="关键字查询">
                                            <button type="button" class="btn btn-default">查询</button>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <label class="col-sm-pull-2 control-label" for="keyWord">最后操作人</label>
                                            <input type="text" class="form-control"  name="updatePerson"
                                                   placeholder="输入用户名">
                                        </div>
                                    </td>
                                    <td>

                                        <div class="form-group">
                                            <label class="col-sm-pull-2 control-label" for="updateDateMin">最后操作时间</label>
                                            <input size="16" name="updateDateMin" class="form_datetime"
                                                   data-date-format="yyyy-mm-dd hh:ii">
                                            <label class="col-sm-pull-1 control-label">~</label>
                                            <input size="16" name="updateDateMax" class="form_datetime"
                                                   data-date-format="yyyy-mm-dd hh:ii">
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="form-group">
                                            <label class="col-sm-pull-2 control-label" for="departmentId">所属部门</label>
                                            <select  name="departmentId"
                                                    class="form-control select2me">
                                                <option value="">请选择...</option>
								<#if departments??>
                                    <#list departments as department>
					   			<option value="${department.id}">${department.name}</option>
                                    </#list>
                                </#if>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-pull-2 control-label" for="sex">性别</label>
                                            <select  name="gender" class="form-control select2me">
                                                <option value="">请选择...</option>
                                                <option value="1">男</option>
                                                <option value="0">女</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <label class="col-sm-pull-2 control-label" for="isLock">是否锁定
                                            <#-- <input type="checkbox" name="isLock" ></label>-->
                                                <select  name="isLock" class="form-control select2me">
                                                    <option value="">请选择...</option>
                                                    <option value="1">是</option>
                                                    <option value="0">否</option>
                                                </select>
                                        </div>

                                        <div class="form-group">
                                            <label class="col-sm-pull-2 control-label" for="isAdmin">是否管理员
                                            <#--<input type="checkbox" name="isAdmin"></label>-->
                                                <select  name="isAdmin" class="form-control select2me">
                                                    <option value="">请选择...</option>
                                                    <option value="1">是</option>
                                                    <option value="0">否</option>

                                                </select>
                                        </div>

                                    </td>
                                    <td>
                                        <div class="form-group">
                                            <button type="button" id="reset" class="btn btn-default">清空</button>
                                        </div>

                                        <div class="form-group">
                                            <button type="button" id="export" class="btn btn-default">导出excel</button>
                                        </div>
                                    </td>
                                </tr>
                            </table>
                        </form>

                        <!-- 检索  -->
                        <div class="portlet-body">
                            <div class="table-container">
                                <div class="table-actions-wrapper">
                                    <button class="btn btn-add blue table-group-action-submit" id="user-add"
                                            data-toggle="modal" onclick="javascript:User.add_click();"> 新增
                                    </button>
                                </div>
                                <table class="table table-striped table-bordered table-hover" id="datatable_cl">
                                    <thead>
                                    <tr role="row" class="heading">
                                        <th width="1%">
                                            <input type="checkbox" class="group-checkable">
                                        </th>
                                        <th width="5%">
                                            ID
                                        </th>
                                        <th width="10%">
                                            名称
                                        </th>
                                        <th width="10%">
                                            姓名
                                        </th>
                                        <th width="5%">
                                            性别
                                        </th>
                                        <th width="5%">
                                            类型
                                        </th>
                                        <th width="5%">
                                            是否锁定
                                        </th>
                                        <th width="10%">
                                            所属部门
                                        </th>
                                        <th width="10%">
                                            最后操作人
                                        </th>
                                        <th width="15%">
                                            最后操作日期
                                        </th>
                                        <th width="20%">
                                            操作
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <!-- End: life time stats -->
                </div>
            </div>
            <!-- END PAGE CONTENT-->
        </div>
    </div>
    <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->

<#include "../footer.ftl" >

<#include "../jslib.ftl" >
<script src="${BasePath}/scripts/custom/user.js"></script>
<script>
    jQuery(document).ready(function () {
        // initiate layout and plugins
        App.init();
        Cl.initModal();
        DataTableCl.init(Cl.oLanguage);
        myForm.dateTimePickerInit();
    });
</script>
<script type="text/javascript">
    function search(formId) {
        Cl.refreshDataTable("datatable_cl");
    }
    $("#reset").click(function () {
        myForm.clear("searchForm");
        Cl.refreshDataTable("datatable_cl");
    })
    $("#export").click(function () {
        $("#searchForm").submit();
    })
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>