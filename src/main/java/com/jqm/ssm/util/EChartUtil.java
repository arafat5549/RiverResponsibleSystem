package com.jqm.ssm.util;

import com.github.abel533.echarts.AxisPointer;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.*;
import com.github.abel533.echarts.data.PieData;
import com.github.abel533.echarts.data.PointData;
import com.github.abel533.echarts.json.GsonOption;
import com.github.abel533.echarts.series.Bar;
import com.github.abel533.echarts.series.Line;
import com.github.abel533.echarts.series.Pie;
import com.github.abel533.echarts.style.ItemStyle;
import com.github.abel533.echarts.style.LineStyle;
import com.github.abel533.echarts.style.itemstyle.Normal;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.jqm.ssm.dto.EChartMapData;
import com.jqm.ssm.entity.Devicegps;
import com.jqm.ssm.enums.CategoryEnum;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by wangyaoyao on 2018/9/5.
 * 教程
 * <a>http://echarts.baidu.com/tutorial.html#5%20%E5%88%86%E9%92%9F%E4%B8%8A%E6%89%8B%20ECharts</a>
 */
public class EChartUtil {

    public static String AXIS_NAME_MONTH[] = {
            "一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"
    };
    //月数据
    public final static String generateEchartOption(String type,String title,String subtext, Map<String,Object> datas, String [] axis, boolean axisOrder){
        if("pie".equals(type)) return generateEchartOption_Pie(title,subtext,datas,axis,axisOrder);
        GsonOption option = new GsonOption();
        option.title().text(title).subtext(subtext);
        AxisPointer axisPointer = new AxisPointer();
        axisPointer.setType(PointerType.shadow);
        option.tooltip().trigger(Trigger.axis).axisPointer(axisPointer);

        Set<String> legends = datas.keySet();

        //option.legend().
        option.calculable(true);
        if(axisOrder)
        {
            option.xAxis(new CategoryAxis().type(AxisType.category).data(axis));
            option.yAxis(new ValueAxis());
        }else{
            option.xAxis(new ValueAxis());
            option.yAxis(new CategoryAxis().type(AxisType.category).data(axis));
        }
        for(String legend:legends){
            option.legend().data().add(legend);
            Object[] objs = ( Object[])datas.get(legend);
            if("bar".equals(type)){
                Bar bar = new Bar(legend);
                for(Object o:objs)
                    bar.data().add(o);
                option.series().add(bar);
            }
            else if("line".equals(type)){
                Line line = new Line(legend);
                for(Object o:objs)
                    line.data().add(o);
                option.series().add(line);
            }


        }
        return JsonUtil.objectToJsonPretty(option);
    }

    public  static String  generateEchartOption_Pie(String title,String subtext, Map<String,Object> datas, String [] axis, boolean axisOrder){
        GsonOption option = new GsonOption();
        option.title().text(title).subtext(subtext);
        AxisPointer axisPointer = new AxisPointer();
        axisPointer.setType(PointerType.shadow);
        option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b}: {c} ({d}%)").axisPointer(axisPointer);
        Set<String> legends = datas.keySet();
        //option.legend(legends);
        Pie p1 = new Pie("水质监测");
        //p1.radius(0.5, 0.7).itemStyle().normal();
        //p1.center(900,380).radius(100);
        for(String legend:legends){
            option.legend().data().add(legend);
            float sum = 0;
            Object[] objs = ( Object[])datas.get(legend);
            for(Object o:objs){
                if( o instanceof Float){
                    sum += (float)o;
                }
            }
            p1.data(new PieData(legend,sum));
        }
        option.series(p1);
        return JsonUtil.objectToJsonPretty(option);
    }


    /**
     * ###########################################################################################################################
     */
    //生成柱状图返回整个option
    public final static String generateBar() {
        GsonOption option = new GsonOption();
        option.title().text("某网站访问量统计").subtext("纯属虚构");
        AxisPointer axisPointer = new AxisPointer();
        axisPointer.setType(PointerType.shadow);
        option.tooltip().trigger(Trigger.axis).axisPointer(axisPointer);

        option.legend("直接访问", "邮件营销", "联盟广告", "视频广告", "搜索引擎", "百度", "谷歌", "必应", "其他");
        //option.toolbox().show(true).orient(Orient.vertical).x("right").y("center").feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar, Magic.stack, Magic.tiled).show(true), Tool.restore, Tool.saveAsImage);
        option.calculable(true);
        option.xAxis(new CategoryAxis().type(AxisType.category).data("周一", "周二", "周三", "周四", "周五", "周六", "周日"));
        option.yAxis(new ValueAxis());

        Bar bar1 = new Bar("直接访问");
        bar1.data(320, 332, 301, 334, 390, 330, 320);

        Bar bar2 = new Bar("邮件营销");
        bar2.setStack("广告");
        bar2.data(120, 132, 101, 134, 90, 230, 210);

        Bar bar3 = new Bar("联盟广告");
        bar3.setStack("广告");
        bar3.data(220, 182, 191, 234, 290, 330, 310);

        Bar bar4 = new Bar("视频广告");
        bar4.setStack("广告");
        bar4.data(150, 232, 201, 154, 190, 330, 410);

        Bar bar5 = new Bar("搜索引擎");
        bar5.data(862, 1018, 964, 1026, 1679, 1600, 1570);
        ItemStyle itemStryle = new ItemStyle();
        itemStryle.normal(new Normal().lineStyle(new LineStyle().type(LineType.dashed)));
        bar5.markLine().data(new PointData().type(MarkType.min), new PointData().type(MarkType.max)).itemStyle(itemStryle);

        Bar bar6 = new Bar("百度");
        bar6.setStack("搜索引擎");
        bar6.barWidth(5);
        bar6.data(620, 732, 701, 734, 1090, 1130, 1120);

        Bar bar7 = new Bar("谷歌");
        bar7.setStack("搜索引擎");
        bar7.data(120, 132, 101, 134, 290, 230, 220);

        Bar bar8 = new Bar("必应");
        bar8.setStack("搜索引擎");
        bar8.data(60, 72, 71, 74, 190, 130, 110);

        Bar bar9 = new Bar("其他");
        bar9.setStack("搜索引擎");
        bar9.data(62, 82, 91, 84, 109, 110, 120);

        option.series(bar1, bar2, bar3, bar4, bar5, bar6, bar7, bar8, bar9);

        return JsonUtil.objectToJsonPretty(option);
    }
    //折线图
    public final static String  generateLine(){
        GsonOption option = new GsonOption();
        option.title().text("某网站访问量统计").subtext("纯属虚构");
        AxisPointer axisPointer = new AxisPointer();
        axisPointer.setType(PointerType.shadow);
        option.tooltip().trigger(Trigger.axis).axisPointer(axisPointer);

        option.legend("直接访问", "邮件营销", "联盟广告", "视频广告", "搜索引擎", "百度", "谷歌", "必应", "其他");
        //option.toolbox().show(true).orient(Orient.vertical).x("right").y("center").feature(Tool.mark, Tool.dataView, new MagicType(Magic.line, Magic.bar, Magic.stack, Magic.tiled).show(true), Tool.restore, Tool.saveAsImage);
        option.calculable(true);
        option.xAxis(new CategoryAxis().type(AxisType.category).data("周一", "周二", "周三", "周四", "周五", "周六", "周日"));
        option.yAxis(new ValueAxis());

        Line bar1 = new Line("直接访问");
        bar1.data(320, 332, 301, 334, 390, 330, 320);

        Line bar2 = new Line("邮件营销");
        bar2.setStack("广告");
        bar2.data(120, 132, 101, 134, 90, 230, 210);

        Line bar3 = new Line("联盟广告");
        bar3.setStack("广告");
        bar3.data(220, 182, 191, 234, 290, 330, 310);

        Line bar4 = new Line("视频广告");
        bar4.setStack("广告");
        bar4.data(150, 232, 201, 154, 190, 330, 410);

        Line bar5 = new Line("搜索引擎");
        bar5.data(862, 1018, 964, 1026, 1679, 1600, 1570);
        ItemStyle itemStryle = new ItemStyle();
        itemStryle.normal(new Normal().lineStyle(new LineStyle().type(LineType.dashed)));
        bar5.markLine().data(new PointData().type(MarkType.min), new PointData().type(MarkType.max)).itemStyle(itemStryle);

        Line bar6 = new Line("百度");
        bar6.setStack("搜索引擎");
        //bar6.barWidth(5);
        bar6.data(620, 732, 701, 734, 1090, 1130, 1120);

        Line bar7 = new Line("谷歌");
        bar7.setStack("搜索引擎");
        bar7.data(120, 132, 101, 134, 290, 230, 220);

        Line bar8 = new Line("必应");
        bar8.setStack("搜索引擎");
        bar8.data(60, 72, 71, 74, 190, 130, 110);

        Line bar9 = new Line("其他");
        bar9.setStack("搜索引擎");
        bar9.data(62, 82, 91, 84, 109, 110, 120);

        option.series(bar1, bar2, bar3, bar4, bar5, bar6, bar7, bar8, bar9);

        return JsonUtil.objectToJsonPretty(option);
    }
    //饼图
    public final static String  generatePie(){

        GsonOption option = new GsonOption();
        option.title().text("某网站访问量统计").subtext("纯属虚构");
        AxisPointer axisPointer = new AxisPointer();
        axisPointer.setType(PointerType.shadow);
        option.tooltip().trigger(Trigger.item).formatter("{a} <br/>{b}: {c} ({d}%)").axisPointer(axisPointer);

        option.legend("直接访问", "邮件营销", "联盟广告", "视频广告", "搜索引擎", "百度", "谷歌", "必应", "其他");
        Pie p1 = new Pie("访问来源");
        //p1.radius(0.5, 0.7).itemStyle().normal();
        //p1.center(900,380).radius(100);
        p1.data(new PieData("直接访问",335));
        p1.data(new PieData("邮件营销",310));
        p1.data(new PieData("联盟广告",135));
        p1.data(new PieData("视频广告",234));
        p1.data(new PieData("搜索引擎",1548));
        p1.data(new PieData("百度",548));
        p1.data(new PieData("谷歌",12));
        p1.data(new PieData("必应",12));
        p1.data(new PieData("其他",333));
        //p1.itemStyle().emphasis(new Emphasis().labelLine())
        option.series(p1);
        return JsonUtil.objectToJsonPretty(option);
    }

    /**
     * 地图数据 地图不会返回整个option 只返回seriase的data部分
      {name: '监测站1',coord: [117.51812,26.771538],symbol:'image://http://localhost:8080/assets/images/device.png'},
     {name: '监测站2',coord: [117.56049,26.298622],symbol:'image://http://localhost:8080/assets/images/monitor.png'}
     */

    public final static String  generateMapData(List<Devicegps> list,String url){
        List<EChartMapData> mapdatalist = Lists.newArrayList();
        for (Devicegps d:list){
            EChartMapData data = new EChartMapData();
            data.setName("设备名称: "+d.getDeviceinfoName());
            Float coords [] = new Float[2];
            coords[0]=Float.parseFloat(d.getLongitude());
            coords[1]=Float.parseFloat(d.getLatitude());
            data.setCoord(coords);
            String imgpath = "circle";
            int cid = d.getCategoryId();
           if(cid == CategoryEnum.CATEGORY_WATERSCAN.getState()){
               imgpath = "image://"+url+"/assets/images/device.png";
           }
           else if(cid == CategoryEnum.CATEGORY_MONITOR.getState()){
               imgpath = "image://"+url+"/assets/images/monitor.png";
           }
           data.setSymbol(imgpath);
           mapdatalist.add(data);
        }
        return JsonUtil.objectToJsonPretty(mapdatalist);

    }
}
