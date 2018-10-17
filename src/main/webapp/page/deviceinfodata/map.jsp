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
    <title>地图统计</title>
    <jsp:include page="../include/common/meta.jsp"/>
    <jsp:include page="../include/common/csslib.jsp"/>
    <style>
        .vmaps {
            width: 100%;
            height: 800px;
            /*a {*/
                /*color: #bbb !important;*/
                /*font-size: 12px !important;*/
            /*}*/
        }
    </style>
</head>
<body>



<div class="page-content-wrapper">
    <div class="page-container">
        <div class="row">
            <div class="col-md-4">
                <label for="dimension">地图</label>
                <select class="form-control" id="dimension">
                    <option value="hour">小时数据</option>
                    <option value="day">日数据</option>
                    <option value="month">月数据</option>
                    <option selected="selected" value="year">年数据</option>
                </select>
            </div>
        </div>


        <!-- MAPCONTENT -->
        <div class="row">
            <div class="col-lg-12">
                <div class="portlet light">
                    <div class="portlet-title">
                        <div class="caption">
                            <i class="fa fa-reorder"></i>中国地图
                        </div>



                        <div class="btn-group">
                            <button class="btn blue dropdown-toggle" type="button" data-toggle="dropdown"> 选择区域
                                <i class="fa fa-angle-down"></i>
                            </button>
                            <ul class="dropdown-menu " role="menu" id="vmaps_menu">
                                <li>
                                    <a href="javascript:;" > 中国 </a>
                                </li>
                                <li class="dropdown-submenu">
                                    <a href="javascript:;"> 福建省 </a>
                                    <ul class="dropdown-menu " style="">
                                        <li><a href="javascript:;" id="3501"> 福州市 </a></li>
                                        <li><a href="javascript:;" id="3504"> 三明市 </a></li>
                                    </ul>
                                </li>

                            </ul>
                        </div>




                    </div>


                    <div class="portlet-body">
                        <div id="vmap_world" class="vmaps"></div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<jsp:include page="../include/common/jslib.jsp"/>
<script>
    function getEchartMapData(type,area){
        var jsonpath = App.getStaticsPath()+'res/geojson/area/中国.json';
        area = area == undefined ? "" : area;
        if(type=="福建省" ){
            jsonpath = App.getStaticsPath()+"res/geojson/area/中国(省级)/"+type+".json";
        }
        else if(type=="福州市" || type =="三明市"){
            jsonpath = App.getStaticsPath()+"res/geojson/area/中国/福建省/"+type+".json";
        }
        var url = "${pageContext.request.contextPath}/api/getEchartMapData?area="+area;
        console.log(type,area);
        $.ajax( {
            type : "GET",
            url : url,
            dataType : "json",
            success: function(mapdata){
                //console.log(mapdata)
                EChartCl.initMap("vmap_world", mapdata , jsonpath ,"FJ");
            }
        });
    }
    //
    function initEvent(){
        $("#vmaps_menu a").each(function(idx,ele){

           $(ele).click(function () {
               console.log(idx,ele,$(ele).text().trim());
               var type = $(ele).text().trim();
               var area = $(ele).attr("id")
               getEchartMapData(type,area);
           });
        });
    }

    jQuery(document).ready(function () {

        initEvent();


        getEchartMapData("福建省","");
    });
</script>
</body>
</html>
