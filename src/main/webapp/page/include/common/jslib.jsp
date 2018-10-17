<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>

<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/respond.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/excanvas.min.js"></script>
<![endif]-->
<!-- BEGIN CORE PLUGINS -->
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/js.cookie.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
<!-- END CORE PLUGINS -->
<!-- BEGIN THEME GLOBAL SCRIPTS -->
<script src="${pageContext.request.contextPath}/statics/assets/js/app.js" type="text/javascript"></script>

<script type="text/javascript">
    var parseString = function(string){
        return string;
    }
    App.setCtxPath('${ctx}');
    <%--App.setAssetsPath('${pageContext.request.contextPath}/statics/assets/'), userId = "${SecurityUtil.getCurrentUserId()}";--%>
    App.setAssetsPath('${pageContext.request.contextPath}/statics/assets/'), userId = "1";
    App.setStaticsPath('${pageContext.request.contextPath}/statics/')
    console.log("getAssetsPath="+App.getAssetsPath()+",publicResPath="+App.getPublicResPath()+",ctxPath="+App.getCtxPath())

</script>

<!-- END THEME GLOBAL SCRIPTS -->
<!-- BEGIN THEME LAYOUT SCRIPTS -->
<script src="${pageContext.request.contextPath}/statics/assets/layouts/layout4/scripts/layout.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
<!-- END THEME LAYOUT SCRIPTS -->
<!-- BEGIN PAGE LEVEL PLUGINS -->
<script src="${pageContext.request.contextPath}/statics/assets/js/datatable.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/datatables/datatables.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/jquery-validation/js/jquery.validate.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/jquery-validation/js/jquery.validate.method.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/jquery-validation/js/localization/messages_zh.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/select2/js/select2.full.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/select2/js/i18n/zh-CN.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/moment.min.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>

<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/bootstrap-modal/js/bootstrap-modalmanager.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/bootstrap-modal/js/bootstrap-modal.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/pages/scripts/components-date-time-pickers.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/jquery-inputmask/jquery.inputmask.bundle.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/ion.rangeslider/js/ion.rangeSlider.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/jquery-ztree/3.5.12/js/jquery.ztree.core-3.5.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/jquery-ztree/3.5.12/js/jquery.ztree.excheck-3.5.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/bootstrap-summernote/summernote.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/pages/scripts/components-select2.js" type="text/javascript"></script>
<!-- ECharts单文件引入 -->
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/bootstrap-fileinput/bootstrap-fileinput.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/assets/js/fileupload/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/statics/assets/js/fileupload/jquery.iframe-transport.js"></script>
<script src="${pageContext.request.contextPath}/statics/assets/js/fileupload/jquery.fileupload.js"></script>

<!--<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/echarts-2.2.7/build/dist/echarts.js"></script>-->
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/echarts/echarts-min.js"></script>
<script src="${pageContext.request.contextPath}/statics/assets/global/plugins/My97DatePicker/WdatePicker.js"></script>

<!-- BEGIN PAGE LEVEL SCRIPTS<script src="${pageContext.request.contextPath}/statics/frame/jquery/jquery.md5.js" type="text/javascript"></script> -->
<script src="${pageContext.request.contextPath}/statics/assets/js/dialoglinks.js" type="text/javascript"></script>
<!-- END PAGE LEVEL PLUGINS -->



<script src="${pageContext.request.contextPath}/statics/frame/common/jqmkj.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/frame/common/jqmkj.form.component.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/frame/jquery/jquery.replenish.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/statics/frame/common/form-validation.js" type="text/javascript"></script>
<!-- zTree -->

<script>
    function initOption(divId,data){
        var divIdx = "#"+divId;
        var ddl = $(divIdx);
        $(divIdx+" option").remove();
        var result = eval(data);
        $(result).each(function (key) {
            //console.log("key=",key)
            var opt = $("<option></option>").text(result[key].name).val(result[key].code);
            if(key==0){
                opt.attr("selected", true);
                opt.text(result[key].name);
            }
            ddl.append(opt);
        });
    }

    function getSitesBase(sites) {
        sites=sites||'sites';
        var url = "${pageContext.request.contextPath}/api/getSites";
        $.ajax( {
            type : "GET",
            url : url,
            async: false,
            dataType : "json",
            success: function(data){
                //console.log(data)
                initOption(sites,data)
            }
        });

    }

    function getFactorsBase(site){
        var url = "${pageContext.request.contextPath}/api/getFactors";
        var siteVal  = $("#sites option:selected").val() !=undefined ? $("#sites option:selected").val() : "";
        site = site || siteVal;
        $.ajax( {
            type : "GET",
            url : url,
            async: false,
            data: {"monitorSiteId":site},
            dataType : "json",
            success: function(data){
                ///console.log(data)
                initOption("factors",data)
            }
        });
    }


</script>

<script type="text/javascript">
    var EChartCl =  function(){
        return {

            initBarChart:function(chartDivId,jsondata,name2){
                console.log("initBarChart",chartDivId,name2);

                var node= document.getElementById(chartDivId);
                var st =  "";
                var sst = "";
                if(jsondata.title!=undefined){
                    st = jsondata.title.text;
                    sst = (jsondata.title.subtext!=undefined && jsondata.title.subtext !="") ? " ("+ jsondata.title.subtext +")" : "";
                    jsondata.title.text = "";
                    jsondata.title.subtext = "";
                    var title = name2+st+sst;
                    $("#"+chartDivId+"_title").text(title);
                }

                var myChart = echarts.init(node , 'light');
                myChart.setOption(jsondata,true)
            },
            initMap:function(chartDivId,mapdata,areaJson,mapType){
                var node= document.getElementById(chartDivId);
                var myChart = echarts.init(node , 'light');
                myChart.showLoading();
                $.get(areaJson, function (geoJson) {
                    myChart.hideLoading();
                    echarts.registerMap('FJ', geoJson);
                    myChart.setOption(option = {
                        title: {
                            text: '设备部署'
                        },
                        tooltip: {
                            trigger: 'item',
                            formatter: '{b}'
                        },
                        series: [
                            {
                                name: '设备部署 ',
                                type: 'map',
                                mapType: mapType, // 自定义扩展图表类型
                                left: 350,
                                top: 50,
                                roam: true,
                                zoom: 1,
                                itemStyle:{
                                    normal:{label:{show:true}},
                                    emphasis:{label:{show:true}}
                                },
                                markPoint: {//动态标记
                                    large: true,//这个选项，悬浮自动失效
                                    //symbol:'image://../assets/images/device.png',
                                    symbolSize: 20,
                                    itemStyle: {
                                        normal: {
                                            shadowBlur: 2,
                                            shadowColor: 'rgba(37, 140, 249, 0.8)'
                                        }
                                    },
                                    tooltip: {
                                        trigger: 'item',
                                        formatter: '{b}'
                                    },
                                    data: mapdata
                                    //     [
                                    //     {name: '监测站1',coord: [117.51812,26.771538],symbol:'image://http://localhost:8080/assets/images/device.png'},
                                    //     {name: '监测站2',coord: [117.56049,26.298622],symbol:'image://http://localhost:8080/assets/images/monitor.png'}
                                    // ]
                                }
                            }
                        ]

                    });
                });
            }
        }
    }();
    //
    var dataTablesCl = function () {

        var initTradeOrderTable = function (url,columns,srcid) {
            //var srcid= "#data-table-report";
            var grid = new Datatable();
            grid.init({
                src: $(srcid),
                dataTable: {
                    //destroy:true,
                    "processing": true,
                    "ordering": false,
                    "ajax": {
                        "url": url,
                        type: 'GET',
                        "data": function (d) {
                            var dimension = $("#dimension option:selected").val() !=undefined ? $("#dimension option:selected").val() : "";
                            var stime = $("#stime").val()
                            var etime = $("#etime").val()
                            //console.log(dimension,stime,etime);
                            var pm = {};
                            if(stime==undefined || stime=="") return pm;

                            var year = stime.split("-")[0];
                            var month = stime.split("-")[1];
                            var day = stime.split("-")[2];
                            var factor = $("#factors option:selected").val() !=undefined ? $("#factors option:selected").val() : "";
                            var site   = $("#sites option:selected").val() !=undefined ? $("#sites option:selected").val() : "";


                            pm.rows = d.length;
                            pm.page = d.start / d.length + 1;
                            pm.start = d.start;

                            pm.echartType="report";
                            pm.year = year;
                            pm.month = month;
                            pm.day = day;
                            pm.dateType = dimension;
                            pm.monitorSiteId = site;
                            pm.factor = factor;
                            pm.beginDate = stime;
                            pm.endDate = etime;
                            //console.log(pm);
                            return pm;
                        },
                        "dataType": 'json'
                    },
                    "columns": columns
                }
            });
            //
        };

        return {
            init: function (url,columns,srcid) {
                if (!jQuery().dataTable) {
                    return;
                }
                srcid= srcid || "#data-table-report"
                initTradeOrderTable(url,columns,srcid);
            },
            reload: function (srcid) {
                srcid= srcid || "#data-table-report"
                $(srcid).DataTable().ajax.reload();
            }
        };
    }();



</script>
