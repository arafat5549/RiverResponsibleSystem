<!-- BEGIN CHART PORTLETS-->
<div class="row">
    <div class="portlet">
        <div class="col-md-2">
            <div class="form-group">
                <label class="col-sm-pull-2 control-label" for="brandId">日数据</label>
                <select  name="brandId" class="form-control select2me">

                <#if months??>
                    <#list months as m>
                        `<option value="${m.code}">${m.name}</option>
                    </#list>
                </#if>
                </select>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN BASIC CHART PORTLET-->
        <div class="portlet box yellow">
            <div class="portlet-title">

                <div class="caption">
                    <i class="fa fa-reorder"></i><span id="chart_1_title">Basic Chart</span>
                </div>

                <div class="tools avoid-this">
                    <a href="javascript:;" class="collapse"></a>
                    <!--
                    <a href="#portlet-config" data-toggle="modal" class="config"></a>
                    <a href="javascript:;" class="reload"></a>
                    <a href="javascript:;" class="remove"></a>
                    -->
                </div>
                <div class="actions avoid-this">
                    <a href="javascript:void(0);" onclick="getEhcartData('bar')" class="btn green btn-sm">
                        <i class="fa fa-bar-chart"></i> 柱状图
                    </a>
                    <a href="javascript:void(0);" onclick="getEhcartData('pie')" class="btn blue btn-sm">
                        <i class="fa fa-pie-chart"></i> 饼图
                    </a>
                    <a href="javascript:void(0);" onclick="getEhcartData('line')" class="btn red btn-sm">
                        <i class="fa fa-line-chart"></i> 折线图
                    </a>
                    <a href="javascript:void(0);" onclick="printArea()" class="btn purple btn-sm">
                        <i class="fa fa-print"></i> 打印
                    </a>
                </div>



            </div>
            <div class="portlet-body">
                <div id="chart_1" class="chart" style="padding: 0px; position: relative;">
                     <!--
                    <canvas class="flot-base" width="293" height="300" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 293px; height: 300px;"></canvas><div class="flot-text" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; font-size: smaller; color: rgb(84, 84, 84);"><div class="flot-x-axis flot-x1-axis xAxis x1Axis" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;"><div class="flot-tick-label tickLabel" style="position: absolute; max-width: 58px; top: 282px; left: 25px; text-align: center;">0</div><div class="flot-tick-label tickLabel" style="position: absolute; max-width: 228px; top: 282px; left: 83px; text-align: center;">π/2</div>
                    <div class="flot-tick-label tickLabel" style="position: absolute; max-width: 228px; top: 282px; left: 154px; text-align: center;">π</div><div class="flot-tick-label tickLabel" style="position: absolute; max-width: 228px; top: 282px; left: 209px; text-align: center;">3π/2</div></div><div class="flot-y-axis flot-y1-axis yAxis y1Axis" style="position: absolute; top: 0px; left: 0px; bottom: 0px; right: 0px; display: block;"><div class="flot-tick-label tickLabel" style="position: absolute; top: 269px; left: 1px; text-align: right;">-2.0</div><div class="flot-tick-label tickLabel" style="position: absolute; top: 235px; left: 1px; text-align: right;">-1.5</div><div class="flot-tick-label tickLabel" style="position: absolute; top: 202px; left: 1px; text-align: right;">-1.0</div><div class="flot-tick-label tickLabel" style="position: absolute; top: 168px; left: 1px; text-align: right;">-0.5</div><div class="flot-tick-label tickLabel" style="position: absolute; top: 135px; left: 6px; text-align: right;">0.0</div><div class="flot-tick-label tickLabel" style="position: absolute; top: 101px; left: 6px; text-align: right;">0.5</div><div class="flot-tick-label tickLabel" style="position: absolute; top: 68px; left: 6px; text-align: right;">1.0</div><div class="flot-tick-label tickLabel" style="position: absolute; top: 34px; left: 6px; text-align: right;">1.5</div><div class="flot-tick-label tickLabel" style="position: absolute; top: 1px; left: 6px; text-align: right;">2.0</div></div></div><canvas class="flot-overlay" width="293" height="300" style="direction: ltr; position: absolute; left: 0px; top: 0px; width: 293px; height: 300px;"></canvas><div class="legend"><div style="position: absolute; width: 48px; height: 48px; top: 14px; right: 12px; background-color: rgb(255, 255, 255); opacity: 0.85;"> </div><table style="position:absolute;top:14px;right:12px;;font-size:smaller;color:#545454"><tbody><tr><td class="legendColorBox"><div style="border:1px solid #ccc;padding:1px"><div style="width:4px;height:0;border:5px solid rgb(237,194,64);overflow:hidden"></div></div></td><td class="legendLabel">sin(x)</td></tr><tr><td class="legendColorBox"><div style="border:1px solid #ccc;padding:1px"><div style="width:4px;height:0;border:5px solid rgb(175,216,248);overflow:hidden"></div></div></td><td class="legendLabel">cos(x)</td></tr><tr><td class="legendColorBox"><div style="border:1px solid #ccc;padding:1px"><div style="width:4px;height:0;border:5px solid rgb(203,75,75);overflow:hidden"></div></div></td><td class="legendLabel">tan(x)</td></tr></tbody></table></div>
                      -->
                </div>
            </div>
        </div>
        <!-- END BASIC CHART PORTLET-->
    </div>
</div>
<!-- END CHART PORTLETS-->