<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" class="no-js">
<!--<![endif]-->
<head>
    <base href="<%=basePath%>">
    <title>nihao11</title>
    <jsp:include page="../include/common/meta.jsp"/>
    <jsp:include page="../include/common/csslib.jsp"/>

    <style>
        .chart{
            padding: 0px; position: relative;height: 600px;
        }
    </style>

</head>

<body>

<div class="page-content-wrapper">
    <div class="page-container">
        <div class="row">

            <div class="col-md-4">
                <label for="stime">开始时间:</label>
                <input id="stime" type="date" class="form-control" value="2017-01-01" placeholder="请输入开始时间">
            </div>

            <div class="col-md-4">
                <label class="col-sm-pull-2 control-label" for="sites">站点数据</label>
                <select  id="sites" name="sites" class="form-control ">

                </select>
            </div>

            <div class="col-md-4">
                <label class="col-sm-pull-2 control-label" for="factors">监测指标</label>
                <select  id="factors" name="factors" class="form-control ">

                </select>
            </div>

            <div class="col-md-4">
                <label for="dimension">报表类型</label>
                <select class="form-control" id="dimension">
                    <option value="hour">小时数据</option>
                    <option value="day">日数据</option>
                    <option value="month">月数据</option>
                    <option selected="selected" value="year">年数据</option>
                </select>
            </div>

            <div class="col-md-1">
                <label></label>
                <button id="summary" class="btn btn-primary form-control" type="button" onclick="getsummarybytime()">提交</button>
            </div>

        </div>

        <jsp:include page="../include/portlet_chart.jsp"></jsp:include>
    </div>
</div>

<jsp:include page="../include/common/jslib.jsp"/>


<script>
    function getsummarybytime(){
        getEhcartDataAll('line',1)
        getEhcartDataAll('line',2)
    }

    function getEhcartDataAll(echartType,idx) {
        var dimension = $("#dimension option:selected").val() !=undefined ? $("#dimension option:selected").val() : "";
        if(idx==1){
            getEhcartData(echartType,dimension ,'chart_0');
            //getEhcartData(echartType,'hour'  ,'chart_0');
            //getEhcartData(echartType,'day'   ,'chart_1');
            //getEhcartData(echartType,'week'  ,'chart_2');
            //getEhcartData(echartType ,'month','chart_3');
            //getEhcartData(echartType ,'year' ,'chart_4');
        }
        else if(idx==2){
            getEhcartData2(echartType,dimension ,'chartBox_0');
            //getEhcartData2(echartType,'hour'  ,'chartBox_0');
            //getEhcartData2(echartType,'day'   ,'chartBox_1');
            //getEhcartData2(echartType,'week'  ,'chartBox_2');
            //getEhcartData2(echartType,'month' ,'chartBox_3');
            //getEhcartData2(echartType,'year'  ,'chartBox_4');
        }
        else if(idx==3){

        }
    }
    //同站同参不同时段/同站同时段多参数对比
    function getEhcartData(echartType,dateType,divId){
        var url = "${pageContext.request.contextPath}/api/getEchartDataOption";
        var stime = $("#stime").val()
        if(stime==undefined) return;
        var year = stime.split("-")[0];
        var month = stime.split("-")[1];
        var day = stime.split("-")[2];
        var site  = $("#sites option:selected").val() !=undefined ? $("#sites option:selected").val() : "";
        $.ajax( {
            type : "GET",
            url : url,
            data: {"echartType":echartType,"dateType":dateType,"day":day,"month":month,"year":year,"monitorSiteId":site},
            dataType : "json",
            success: function(data){
                EChartCl.initBarChart(divId,data,"");
            }
        });
    }
    //同时同参数多站点
    function getEhcartData2(echartType,dateType,divId){
        var url = "${pageContext.request.contextPath}/api/getEchartDataOption2";
        var stime = $("#stime").val()
        if(stime==undefined) return;
        var year = stime.split("-")[0];
        var month = stime.split("-")[1];
        var day = stime.split("-")[2];
        var factor = $("#factors option:selected").val() !=undefined ? $("#factors option:selected").val() : "";
        var site   = $("#sites option:selected").val() !=undefined ? $("#sites option:selected").val() : "";
        $.ajax( {
            type : "GET",
            url : url,
            data: {"echartType":echartType,"dateType":dateType,"day":day,"month":month,"year":year,"monitorSiteId":site,"factor":factor},
            dataType : "json",
            success: function(data){
                EChartCl.initBarChart(divId,data,"");
            }
        });
    }
    //同时多站多惨
    function getEhcartData3(echartType,dateType){
        var url = "${pageContext.request.contextPath}/api/getEchartDataOption3";
        var stime = $("#stime").val()
        if(stime==undefined) return;
        var year = stime.split("-")[0];
        var month = stime.split("-")[1];
        var day = stime.split("-")[2];
        var site  = $("#sites option:selected").val() !=undefined ? $("#sites option:selected").val() : "";
        $.ajax( {
            type : "GET",
            url : url,
            data: {"echartType":echartType,"dateType":dateType,"day":day,"month":month,"year":year,"monitorSiteId":site},
            dataType : "json",
            success: function(data){
                EChartCl.initBarChart(divId,data,"");
            }
        });
    }

    /*
    function printArea() {
        var charBoxIdList = ["chart_0","chart_1","chart_2","chart_3","chart_4"];
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
    */

    function getSites() {
        getSitesBase()

        $("#sites").change(function(){
            getFactors()
        });
    }
    function getFactors(){
        getFactorsBase();
    }

    jQuery(document).ready(function() {
        getSites()
        getFactors()

        getsummarybytime();

    });
</script>

</body>

</html>
