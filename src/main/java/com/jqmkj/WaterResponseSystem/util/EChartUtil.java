package com.jqmkj.WaterResponseSystem.util;

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
import com.jqmkj.WaterResponseSystem.entity.Devicegps;
import com.jqmkj.WaterResponseSystem.pojo.EChartMapData;

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
    //###############################################################################################################//
    //                                               私有方法区域
    //###############################################################################################################//
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
        return JsonUtil.objectToJson(option);
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
        return JsonUtil.objectToJson(option);
    }



    /**
     * 地图数据 地图不会返回整个option 只返回seriase的data部分
      {name: '监测站1',coord: [117.51812,26.771538],symbol:'image://http://localhost:8080/assets/images/device.png'},
     {name: '监测站2',coord: [117.56049,26.298622],symbol:'image://http://localhost:8080/assets/images/monitor.png'}*/

    public final static String  generateMapData(List<Devicegps> list, String url){
        List<EChartMapData> mapdatalist = Lists.newArrayList();
        for (Devicegps d:list){
            EChartMapData data = new EChartMapData();
            data.setName("设备名称: "+d.getDeviceinfoName());
            Float coords [] = new Float[2];
            coords[0]=Float.parseFloat(d.getLongitude());
            coords[1]=Float.parseFloat(d.getLatitude());
            data.setCoord(coords);
            String imgpath = "circle";
            int cid = d.getGpsType();
           if(cid == 1){
               imgpath = "image://"+url+"res/image/sdz.png";
           }
           else if(cid == 2){
               imgpath = "image://"+url+"res/image/monitor.png";
           }
           data.setSymbol(imgpath);
           mapdatalist.add(data);
        }
        return JsonUtil.objectToJson(mapdatalist);

    }
}
