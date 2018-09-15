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
    <title>金钱猫 | 配置中心 - 设备部署管理</title>
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
                        设备部署管理
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
                                设备部署管理
                            </a>
                        </li>
                    </ul>
                    <!-- END PAGE TITLE & BREADCRUMB-->
                </div>
            </div>
            <!-- END PAGE HEADER-->
            <!-- BEGIN PAGE CONTENT-->
            <div class="row">
                <div class="col-md-6">
                    <div class="portlet grey box">
                        <div class="portlet-title">
                            <div class="caption">
                                设备部署树
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div id="tree_cl" class="tree-demo">
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="portlet grey box">
                        <div class="portlet-title">
                            <div class="caption">
                                设备部署管理
                            </div>
                        </div>
                        <div class="portlet-body form">
                            <!-- BEGIN FORM-->
                            <form action="#" id="form_cl" class="form-horizontal">
                                <div class="form-body">
                                    <div class="form-group">
                                        <label class="control-label col-md-3">监测站
                                            <span class="required">
											 *
										</span>
                                        </label>
                                        <div class="col-md-8">
                                            <input type="hidden" id="id" name="id"/>
                                            <select id="monitorsiteId" name="monitorsiteId"
                                                    class="form-control select2me">
                                                <option value="">请选择...</option>
								<#if monitorsites??>
                                    <#list monitorsites as m>
					   			<option value="${m.id}">${m.name}</option>
                                    </#list>
                                </#if>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">设备
                                            <span class="required">
											 *
										</span>
                                        </label>
                                        <div class="col-md-8">
                                            <select id="deviceinfoId" name="deviceinfoId" class="form-control select2me">
                                                <option value="">请选择...</option>
								<#if deviceinfos??>
                                    <#list deviceinfos as d>
					   			<option value="${d.id}">${d.name}</option>
                                    </#list>
                                </#if>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">经度
                                            <span class="required">
											 *
										</span>
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" id="longitude" name="longitude"  class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">纬度
                                            <span class="required">
											 *
										</span>
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" id="latitude" name="latitude" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">ip地址
                                            <span class="required">
											 *
										</span>
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" id="ipAddress" name="ipAddress" data-required="1" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">端口号
                                            <span class="required">
											 *
										</span>
                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" id="port" name="port" data-required="1" class="form-control"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-md-3">视频地址

                                        </label>
                                        <div class="col-md-8">
                                            <input type="text" id="url" name="url"  class="form-control"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-actions fluid">
                                    <div class="col-md-offset-3 col-md-9">
                                        <button type="submit" class="btn blue" onclick="javascript:Cl.action='create';">
                                            增加
                                        </button>
                                        <button type="submit" class="btn blue" onclick="javascript:Cl.action='update';">
                                            修改
                                        </button>
                                        <button type="button" class="btn blue"
                                                onclick="javascript:Devicegps.remove();">删除
                                        </button>
                                        <button type="button" class="btn blue"
                                                onclick="javascript:Devicegps.clear();">取消
                                        </button>
                                    </div>
                                </div>
                            </form>
                            <!-- END FORM-->
                        </div>
                    </div>
                </div>

            <#include "../portlet_map.ftl" >
            </div>
            <!-- END PAGE CONTENT-->
        </div>
    </div>
    <!-- END CONTENT -->
</div>
<!-- END CONTAINER -->

<#include "../footer.ftl" >

<#include "../jslib.ftl" >
<script src="${BasePath}/scripts/custom/devicegps.js"></script>
<script src="${BasePath}/assets/plugins/echarts.min.js"></script>
<script src="${BasePath}/assets/js/echartFunc.js"></script>

<script>
    function getEchartMapData(){
        $.ajax( {
            type : "GET",
            url : "/devicegps/getEcharMapData",
            dataType : "json",
            success: function(data){
                console.log(data)
                EChartCl.initMap("vmap_world",data,'${BasePath}/assets/geojson/area/福建省.json');
            }
        });
    }
    //
    jQuery(document).ready(function () {
        // initiate layout and plugins
        App.init();
        Cl.initModal();
        TreeCl.init();
        FormCl.init();

        getEchartMapData();
    });
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>
