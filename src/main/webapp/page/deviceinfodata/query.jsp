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
    <title>数据查询</title>
    <jsp:include page="../include/common/meta.jsp"/>
    <jsp:include page="../include/common/csslib.jsp"/>

</head>
<body>

<div class="page-content-wrapper">
    <div class="page-container">

        <div class="row">
            <!--
            <div class="col-md-4 timebox">
                <label for="stime">开始时间:</label>
                <input id="stime" class="form-control" value="2017-01-01" type="text" value="" readonly/>
            </div>

            <div class="col-md-4 timebox">
                <label for="etime">结束时间:</label>
                <input id="etime" class="form-control" value="2017-01-01" type="text" value="" readonly/>
            </div>
            -->
            <div class="col-md-4 ">
                <label for="stime">时间范围:</label><br>
                <input id="stime" type="text" class="Wdate" style="height: 30px"
                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,startDate:'2017-01-01',minDate:'2017-01-01',maxDate:'#F{$dp.$D(\'etime\')}'})" />
                 -
                <input id="etime" type="text" class="Wdate" style="height: 30px"
                       onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'stime\')}',maxDate:'2019-01-01',startDate:'#F{$dp.$D(\'stime\',{d:+1})}'})" />
            </div>
        </div>


        <div class="row">
            <div class="col-md-3">
                <label class="col-sm-pull-2 control-label" for="sites">监测站点</label>
                <select  id="sites" name="sites" class="form-control ">

                </select>
            </div>

            <div class="col-md-3">
                <label class="col-sm-pull-2 control-label" for="factors">监测指标</label>
                <select  id="factors" name="factors" class="form-control ">

                </select>
            </div>

            <div class="col-md-3">
                <label for="dimension">查询类型</label>
                <select class="form-control" id="dimension">
                    <option value="realtime">实时数据查询</option>
                    <option value="minute">分钟数据查询</option>
                    <option value="hour">小时数据查询</option>
                    <option selected="selected" value="day">日数据查询</option>
                    <!-- <option value="alert">报警数据查询</option> -->
                </select>
            </div>

            <div class="col-md-1">
                <label for="summary"></label>
                <button id="summary" class="btn btn-primary form-control" type="button" onclick="getsummarybytime(true)">提交</button>
            </div>
        </div>


        <!-- ECHART -->
        <div class="row">
            <div class="col-md-12">
                <div class="portlet box yellow">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-reorder"></i><span id="main_title">数据查询</span>
                        </div>
                        <div class="actions avoid-this">
                            <a href="javascript:void(0);" onclick="getEhcartDataAll('bar',1)" class="btn green btn-sm">
                                <i class="fa fa-bar-chart"></i> 柱状图
                            </a>
                            <a href="javascript:void(0);" onclick="getEhcartDataAll('pie',1)" class="btn blue btn-sm">
                                <i class="fa fa-pie-chart"></i> 饼图
                            </a>
                            <a href="javascript:void(0);" onclick="getEhcartDataAll('line',1)" class="btn red btn-sm">
                                <i class="fa fa-line-chart"></i> 折线图
                            </a>

                        </div>
                    </div>
                    <div class="portlet-body">
                        <div class="caption">
                            <i class="fa fa-reorder"></i><span id="chart_0_title">Basic Chart</span>
                        </div>
                        <div id="chart_0" class="chart" ></div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Table -->
        <div class="row">
            <div class="col-md-12">
                <table class="table table-striped table-bordered table-hover dataTable no-footer dt-responsive" id="data-table-report">
                    <thead>
                    <tr area="row" class="heading">

                    </tr>
                    </thead>
                    <tbody>

                    </tbody>
                </table>
            </div>
        </div>



    <!--END CONTNET -->
    </div>
</div>

<jsp:include page="../include/common/jslib.jsp"/>
 <script>
     function getsummarybytime(flag){
         //flag= flag || true;
         getEhcartDataAll('line',1)
         //getEhcartDataAll('line',2)


         if(flag) dataTablesCl.reload()
     }
     function getEhcartDataAll(echartType,idx) {
         var dimension = $("#dimension option:selected").val() !=undefined ? $("#dimension option:selected").val() : "";
         if(idx==1){
             getEhcartData(echartType,dimension ,'chart_0');
         }
     }
     function getEhcartData(echartType,dateType,divId){
         var url = "${pageContext.request.contextPath}/api/getQueryData/echart";
         var stime = $("#stime").val()
         var etime = $("#etime").val()
         if(stime==undefined || etime == undefined) return;
         if(stime=="" || etime == "") return;
         var site  = $("#sites option:selected").val() !=undefined ? $("#sites option:selected").val() : "";
         var factor = $("#factors option:selected").val() !=undefined ? $("#factors option:selected").val() : "";

         var rows  = 100;
         var page  = 1;
         var start = 0;
         var pm = {};
         if(site!=""){
             pm.rows = rows;
             pm.page = page;
             pm.start = start;
         }
         pm.echartType=echartType;
         pm.dateType = dateType;
         pm.monitorSiteId = site;
         pm.factor = factor;
         var plus = ""
          plus=" 00:00:00";
         pm.beginDate = stime+ plus;
         pm.endDate = etime+ plus;
         $.ajax( {
             type : "GET",
             url : url,
             data: pm,//{"echartType":echartType,"dateType":dateType,"monitorSiteId":site,"factor":factor,"beginDate":stime+ " 00:00:00","endDate":etime + " 00:00:00"},
             dataType : "json",
             success: function(data){
                 EChartCl.initBarChart(divId,data,"");
             }
         });
     }

     function initDatePicker(){
         //initDatePickerBase();
         $(".timebox input").datepicker({
             orientation: "bottom",
             language:"zh-CN",
             format: "yyyy-mm-dd", // HH:ii
             autoclose: true,
             todayBtn: true,
             todayHighlight: true,
             startDate: "2017-01-01",
             endDate: "2019-01-01"
             //startView: 2,  maxViewMode: 1,minViewMode:1
             //,minuteStep: 10
         });
     }

     function initDataTable(datas,columns){
         // $("tr.heading th").each(function(i, data){
         //     console.log($(data).text())
         // });
         //var url = "${pageContext.request.contextPath}/api/getQueryData/page";
         var url = "${pageContext.request.contextPath}/api/getQueryData/page";
         var datas = datas || [
             "站点名称",
             "时间",
             "各监测因子均值",
             //"日均值",
             //"各单项指标对应的水质类别",
             //"水质类别",
             "首要污染物指标"
         ];
         var columns = columns || [
             {
                 data: 'monitorsiteName'
             }, {
                 data: 'data1'
             }
             , {
                 data: 'data2'
             }
             // , {
             //     data: 'data3'
             // }
             // , {
             //     data: 'factorWaterType'
             // }
             // , {
             //     data: 'waterType'
             // }
             , {
                 data: 'topFactor'
             }
         ];
         //console.log(columns)
         $("tr.heading").html("");
         for(var i=0;i<datas.length;i++){
             $("tr.heading").append("<th>"+datas[i]+"</th>");
         }
         dataTablesCl.init(url,columns);

     }

     function getSites() {
         getSitesBase()

         $("#sites").change(function(){
             getFactors()
         });
     }
     function getFactors(){
         getFactorsBase();
     }

     jQuery(document).ready(function () {
         //App.init();
         getSites();
         getFactors();
         //initDatePicker();

         initDataTable();

     });
 </script>
</body>
</html>
