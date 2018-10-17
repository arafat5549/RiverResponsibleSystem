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
    <%--<jsp:include page="../../csslib_metornic.jsp"/>--%>

</head>
<body>

<div class="page-content-wrapper">
    <div class="page-container">
        <!-- -->
        <div class="row">
            <div class="col-md-6 offset-md-6">
                <div class="card">
                    <div class="card-body">
                        <center>
                            <h5 class="card-title">水质监测报表生成</h5>
                        </center>
                        <div class="form-group">
                            <label class="col-sm-pull-2 control-label" for="sites">监测站点</label>
                            <select  id="sites" name="sites" class="form-control">

                            </select>

                            <label for="stime">开始时间:</label>
                            <input id="stime" type="date" class="form-control" value="2017-01-01" placeholder="请输入开始时间">
                            <label for="dimension">报表类型</label>
                            <select class="form-control" id="dimension">
                                <option value="hour">日报表</option>
                                <option value="day">月报表</option>
                                <option value="quarter">季度报表</option>
                                <option selected="selected"  value="month">年报表</option>
                            </select>
                        </div>
                        <span class="input-group-btn">
                                <center><button class="btn btn-primary" type="button" onclick="getsummarybytime(true)">提交</button></center>
                        </span>
                        <br>
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
                    <th class=""> 站点名称</th>
                    <th class="" id="th1"> 小时值</th>
                    <th class="" id="th2"> 各监测因子小时均值</th>
                    <th class="" id="th3"> 日均值</th>
                    <th class=""> 各单项指标对应的水质类别</th>
                    <th class=""> 水质类别</th>
                    <th class=""> 首要污染物指标</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
            </div>
        </div>

    </div>
</div>

<jsp:include page="../include/common/jslib.jsp"/>

<script>


    jQuery(document).ready(function () {
        getSitesBase();
        var url = "${pageContext.request.contextPath}/api/getReportData/page";
        var columns = [
            {
                data: 'monitorsiteName'
            }, {
                data: 'data1'
            }
            , {
                data: 'data2'
            }
            , {
                data: 'data3'
            }
            , {
                data: 'factorWaterType'
            }
            , {
                data: 'waterType'
            }
            , {
                data: 'topFactor'
            }
        ];
        dataTablesCl.init(url,columns);

        getsummarybytime(false)
    });


    function getsummarybytime(flag){

        var dimension = $("#dimension option:selected").val() !=undefined ? $("#dimension option:selected").val() : "";

        var stime = $("#stime").val()
        if(stime==undefined || stime=="") return;
        var year = stime.split("-")[0];
        var month = stime.split("-")[1];
        var day = stime.split("-")[2];
        //var quarter = 1;
        //var site  = $("#sites option:selected").val() !=undefined ? $("#sites option:selected").val() : "";
        switch(month){
            case 1:
            case 2:
            case 3:
                quarter =1; break;

            case 4:
            case 5:
            case 6:
                quarter =2; break;

            case 7:
            case 8:
            case 9:
                quarter =3; break;

            case 10:
            case 11:
            case 12:
                quarter =4; break;
        }
        //var datajson = {"dateType":dimension,"year":year,"month":month,"day":day};

        if(dimension == "hour"){
            $("#th1").text("小时值");
            $("#th2").text("各监测因子小时均值");
            $("#th3").text("日均值");

        }
        else if(dimension == "day"){
            $("#th1").text("日期");
            $("#th2").text("各监测因子日均值");
            $("#th3").text("月均值");
        }
        else if(dimension == "quarter"){
            $("#th1").text("季度");
            $("#th2").text("各监测因子月均值");
            $("#th3").text("季度均值");
        }
        else if(dimension == "month"){
            $("#th1").text("年份");
            $("#th2").text("各监测因子月均值");
            $("#th3").text("年均值");
        }
        if(flag) {
            dataTablesCl.reload()
        }
    }

</script>


</body>
</html>