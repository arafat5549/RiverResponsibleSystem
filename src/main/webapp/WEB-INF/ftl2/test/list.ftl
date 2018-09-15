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
<title>金钱猫 | 配置中心 - 测试页面</title>
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
		    <#include "../portlet_map.ftl" >

			<!-- BEGIN PAGE HEADER-->
			<div class="row avoid-this">
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
					<div class="portlet avoid-this">
						<div class="portlet-title">
							<div class="caption">
								<i class="fa fa-user"></i>帐户列表
							</div>
						</div>
						<div class="portlet-body">
							<div class="table-container">
								<div class="table-actions-wrapper">
									<button class="btn btn-add blue table-group-action-submit" id="user-add" data-toggle="modal" onclick="javascript:User.add_click();"> 新增</button>
								</div>
								<div  id="printContainer">

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

								     <#include "../portlet_chart.ftl" >
                                </div>
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
<!-- 新引入的第三方组件 -->
<script src="${BasePath}/assets/plugins/jQuery.print.js"></script>
<script src="${BasePath}/assets/plugins/echarts.min.js"></script>
<script src="${BasePath}/assets/js/echartFunc.js"></script>

<script>
	function getEhcartData(type){
        $.ajax( {
            type : "GET",
            url : "${BasePath}/test/getEchartDataOption?type="+type,
            dataType : "json",
            success: function(data){
                console.log(data)
                EChartCl.initBarChart("chart_1",data);
            }
        });
	}
	function getEhcartMapData(){
        $.ajax( {
            type : "GET",
            url : "${BasePath}/test/getEcharMapData",
            dataType : "json",
            success: function(data){
                console.log(data)
                EChartCl.initMap("vmap_world",data,'${BasePath}/assets/geojson/area/福建省.json');
            }
        });
	}
	function printArea() {
        var charBoxIdList = ["chart_1"];
        var cahrtMap = new Map();
        for (var i = charBoxIdList.length - 1; i >= 0; i--) {
            var chart =echarts.init(document.getElementById(charBoxIdList[i]) , 'light');
            cahrtMap.set(charBoxIdList[i], chart);
        }

        for (var i = charBoxIdList.length - 1; i >= 0; i--)
        {
            var node = document.getElementById(charBoxIdList[i]);
            if(node != null)
            {
                //将chart转化为图形再打印
                var chartBox = $(node);
                var imgBoxname = charBoxIdList[i]+"_imgbox";
                var imgBox = $("#"+imgBoxname);
                if (imgBox.length <= 0) {
                    chartBox.after('<div id="'+imgBoxname+'"></div>');
                    imgBox = $("#"+imgBoxname);
                }
                imgBox.html('<img src="' + cahrtMap.get(charBoxIdList[i]).getDataURL() + '"/>').css('display','block');
                chartBox.css('display','none');
                var img = imgBox.find('img');
                var imgWidth = img.width();
                var showWidth = 1000; // 显示宽度，即图片缩小到的宽度
                if (imgWidth > showWidth) { // 只有当图片大了才缩小
                    var imgNewHeight = img.height() / (imgWidth / showWidth);
                    img.css({'width': showWidth + 'px', 'height': imgNewHeight + 'px'});
                }
            }
        }
        $("#datatable_cl_wrapper .row").css('display','none');

		//需要打印的DIV id
        $("#printContainer").print({
            globalStyles : true,
            mediaPrint : false,
            iframe : false,
            noPrintSelector : ".avoid-this",
        });

        for (var i = charBoxIdList.length - 1; i >= 0; i--) {
            var node = document.getElementById(charBoxIdList[i]);
            if(node!=null){
                var chartBox = $(node);
                var imgBoxname = charBoxIdList[i]+"_imgbox";
                var imgBox = $("#"+imgBoxname);
                chartBox.css('display','block');
                imgBox.css('display','none');
            }
        }
        $("#datatable_cl_wrapper .row").css('display','block');
    }


	jQuery(document).ready(function() {       
	   // initiate layout and plugins
		App.init();
		Cl.initModal();
		DataTableCl.init(Cl.oLanguage);

		getEhcartData('bar');
		getEhcartMapData();
	});
</script>
<!-- END JAVASCRIPTS -->
</body>
<!-- END BODY -->
</html>