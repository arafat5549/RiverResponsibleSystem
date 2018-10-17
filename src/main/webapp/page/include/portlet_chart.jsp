<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>

<!-- BEGIN CHART PORTLETS-->

<div class="row">
    <div class="col-md-12">
        <!-- BEGIN BASIC CHART PORTLET-->
        <div class="portlet box yellow">
            <div class="portlet-title">

                <div class="caption">
                    <i class="fa fa-reorder"></i><span id="main_title">同站同参不同时段/同站同时段多参数对比</span>
                </div>

                <!--
                <div class="tools avoid-this">
                    <a href="javascript:;" class="collapse"></a>
                </div>
                -->
                <div class="actions avoid-this">
                    <!--
                    <a href="javascript:void(0);" onclick="getEhcartDataAll('datatable',1)" class="btn blue btn-sm">
                        <i class="fa fa-bar-chart"></i> 数据表格
                    </a>
                    -->
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

                <!--
                <div class="caption">
                    <i class="fa fa-reorder"></i><span id="chart_1_title">Basic Chart</span>
                </div>
                <div id="chart_1" class="chart" style="padding: 0px; position: relative;"></div>

                <div class="caption">
                    <i class="fa fa-reorder"></i><span id="chart_2_title">Basic Chart</span>
                </div>
                <div id="chart_2" class="chart" style="padding: 0px; position: relative;"></div>

                <div class="caption">
                    <i class="fa fa-reorder"></i><span id="chart_3_title">Basic Chart</span>
                </div>
                <div id="chart_3" class="chart" ></div>

                <div class="caption">
                    <i class="fa fa-reorder"></i><span id="chart_4_title">Basic Chart</span>
                </div>
                <div id="chart_4" class="chart" ></div>
                -->
            </div>
        </div>
        <!-- END BASIC CHART PORTLET-->
    </div>
</div>


<div class="row">
    <div class="col-md-12">
        <div class="portlet box yellow">
            <div class="portlet-title">
                <div class="caption">
                    <i class="fa fa-reorder"></i><span id="main_title2">同时同参数多站点</span>
                </div>

                <div class="actions avoid-this">
                    <a href="javascript:void(0);" onclick="getEhcartDataAll('bar',2)" class="btn green btn-sm">
                        <i class="fa fa-bar-chart"></i> 柱状图
                    </a>
                    <a href="javascript:void(0);" onclick="getEhcartDataAll('pie',2)" class="btn blue btn-sm">
                        <i class="fa fa-pie-chart"></i> 饼图
                    </a>
                    <a href="javascript:void(0);" onclick="getEhcartDataAll('line',2)" class="btn red btn-sm">
                        <i class="fa fa-line-chart"></i> 折线图
                    </a>

                </div>

            </div>
            <div class="portlet-body">
                <div class="caption">
                    <i class="fa fa-reorder"></i><span id="chartBox_0_title">Basic Chart</span>
                </div>
                <div id="chartBox_0" class="chart" style="padding: 0px; position: relative;height: 600px;"></div>

                <!--
                <div class="caption">
                    <i class="fa fa-reorder"></i><span id="chartBox_1_title">Basic Chart</span>
                </div>
                <div id="chartBox_1" class="chart" style="padding: 0px; position: relative;"></div>

                <div class="caption">
                    <i class="fa fa-reorder"></i><span id="chartBox_2_title">Basic Chart</span>
                </div>
                <div id="chartBox_2" class="chart" style="padding: 0px; position: relative;"></div>

                <div class="caption">
                    <i class="fa fa-reorder"></i><span id="chartBox_3_title">Basic Chart</span>
                </div>
                <div id="chartBox_3" class="chart" style="padding: 0px; position: relative;"></div>

                <div class="caption">
                    <i class="fa fa-reorder"></i><span id="chartBox_4_title">Basic Chart</span>
                </div>
                <div id="chartBox_4" class="chart" style="padding: 0px; position: relative;"></div>
                -->
            </div>
        </div>
    </div>
</div>

<!-- END CHART PORTLETS-->