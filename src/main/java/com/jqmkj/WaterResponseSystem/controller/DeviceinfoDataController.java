package com.jqmkj.WaterResponseSystem.controller;


import com.google.common.base.Strings;
import com.google.common.collect.*;
import com.google.common.primitives.Doubles;
import com.jqmkj.WaterResponseSystem.entity.*;
import com.jqmkj.WaterResponseSystem.entity.other.DeviceinfoDataSearchModel;
import com.jqmkj.WaterResponseSystem.entity.other.Option;
import com.jqmkj.WaterResponseSystem.pojo.*;
import com.jqmkj.WaterResponseSystem.service.*;
import com.jqmkj.WaterResponseSystem.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DeviceinfoDataController {

    @Autowired
    MonitorsiteService monitorsiteService;
    @Autowired
    MonitofactorsService monitofactorsService;
    @Autowired
    DeviceinfoDayDataService deviceinfoDayDataService;
    @Autowired
    DeviceinfoService deviceinfoService;
    @Autowired
    DevicegpsService devicegpsService;

    //地图数据
    @GetMapping("/getEchartMapData")
    public String getEcharMapData(Integer area,HttpServletRequest request, HttpServletResponse response){
        response.setCharacterEncoding(Constants.ENCODE_UTF8);
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        String data = Constants.EmptyJsonObject;

        List<Devicegps> list =  devicegpsService.findDevicesByArea(area);
        System.out.println("getEchartMapData="+area+",list="+list.size());
        //System.out.println(request.getRequestURL()+","+request.getRequestURI()+","+request.getServletPath()+","+request.getContextPath());
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
        data = EChartUtil.generateMapData(list,basePath+"statics/");
        return data;
    }
    //获取数据区域
    @GetMapping("/getSites")
    public String getSites(HttpServletResponse response){
        response.setCharacterEncoding(Constants.ENCODE_UTF8);
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        //String data = Constants.EmptyJsonObject;

        List<MonitorSiteTree> sitelist = monitorsiteService.getAllMonitorsiteTree();
        List<Option>  sites = Lists.newArrayList();
        Option curnd = new Option();
        curnd.setName("全部站点");
        curnd.setCode("");
        sites.add(curnd);
        for (MonitorSiteTree dp:sitelist){
            Option nd = new Option();
            nd.setName(dp.getText());
            nd.setCode(dp.getId().toString());
            sites.add(nd);
            for(MonitorSiteTree child:dp.getChildren()){
                Option nd2 = new Option();
                nd2.setName("--"+child.getText());
                nd2.setCode(child.getId().toString());
                sites.add(nd2);
            }
        }
        return JsonUtil.objectToJson(sites);
    }

    @GetMapping("/getFactors")
    public String getFactors(Long monitorSiteId,HttpServletResponse response){
        response.setCharacterEncoding(Constants.ENCODE_UTF8);
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        //跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("X-Powered-By", "3.2.1");

        List<Monitofactors> dictlist = monitofactorsService.findFactorsBySiteId(monitorSiteId);
        List<Option>  factors = Lists.newArrayList();
        for (Monitofactors dp:dictlist){
            Option nd = new Option();
            nd.setName(dp.getName());
            nd.setCode(dp.getSno().toString());
            factors.add(nd);
        }
        return JsonUtil.objectToJson(factors);
    }
    //###############################################################################################################//
    //                                               GPS地图请求-需要处理跨域问题
    //###############################################################################################################//
    @GetMapping("/gps/getDevices")
    public String getDevices(Integer type,HttpServletResponse response){
        response.setCharacterEncoding(Constants.ENCODE_UTF8);
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        //跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("X-Powered-By", "3.2.1");

        List<Devicegps> devicegpss = devicegpsService.findDevicesByType(type);
        List<GpsFeature> glist = Lists.newArrayList();
        for (Devicegps obj:devicegpss){
            obj.setGpsType(type);
            GpsFeature<Devicegps> g = new GpsFeature<Devicegps>(obj);
            glist.add(g);
        }
        return JsonUtil.objectToJson(new BaseGpsResult(glist));
    }
    @GetMapping("/gps/getSites")
    public String site(HttpServletResponse response){
        response.setCharacterEncoding(Constants.ENCODE_UTF8);
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        //跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("X-Powered-By", "3.2.1");

        //获取有子站点的所有监测站
        List<Monitorsite> list =  monitorsiteService.selectAll();
        List<GpsFeature> glist = Lists.newArrayList();
        for (Monitorsite obj:list){
            obj.setGpsType(99);
            GpsFeature<Monitorsite> g = new GpsFeature<Monitorsite>(obj);
            glist.add(g);
        }
        return JsonUtil.objectToJson(new BaseGpsResult(glist));
    }
    /**
     * GPS地图请求末端时间内的数据 startDate-EndDate
     * @param s
     * @return
     */
    @GetMapping("/gps/getEchartDataOption3")
    public String getEchartDataOption3(DeviceinfoDataSearchModel s,HttpServletResponse response){
        response.setCharacterEncoding(Constants.ENCODE_UTF8);
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        //跨域
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("X-Powered-By", "3.2.1");
        String data = Constants.EmptyJsonObject;
        if(s==null) return data;
        if(DateUtils.getMonths(s.getBeginDate(),s.getEndDate())<=0) return data;
        data = getDateBase3(s,true);
        return data;
    }

    //
    /**
     * 同站同参不同时段/同站同时段多参数对比
     */
    @GetMapping("/getEchartDataOption")
    public String getEchartDataOption(DeviceinfoDataSearchModel s,
                                      HttpServletResponse response){
        response.setCharacterEncoding(Constants.ENCODE_UTF8);
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        String data = Constants.EmptyJsonObject;
        if(s==null) return data;
        return getDateBase(s,true);
    }

    /**
     * 同时同参数多站点
     */
    @GetMapping("/getEchartDataOption2")
    public String getEchartDataOption2( DeviceinfoDataSearchModel s, HttpServletResponse response){
        response.setCharacterEncoding(Constants.ENCODE_UTF8);
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        String data = Constants.EmptyJsonObject;
        if(s==null) return data;
        return  getDateBase2(s,true);
    }

//    /**
//     * 报表管理
//     */
//    @ResponseBody
//    @RequestMapping("/getReportData")
//    public String getReportData(DeviceinfoDataSearchModel s,HttpServletResponse response){
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
//        String data = Constants.EmptyJsonObject;
//        if(s==null) return data;
//        //参数判断
//        List<ReportData> dataList = getReportDataBase(s);
//        data = JsonUtil.objectToJson(dataList);
//        return data;
//    }

    /**
     * 报表管理
     */
    @ResponseBody
    @RequestMapping("/getReportData/page")
    public String getReportDataPage(DeviceinfoDataSearchModel s,HttpServletResponse response){
        response.setCharacterEncoding(Constants.ENCODE_UTF8);
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        String data = Constants.EmptyJsonObject;
        if(s==null) return data;

        List<ReportData> dataList = getReportDataBase(s);
        PageModel<ReportData> page = new PageModel();
        page.setData(dataList);
        page.setRecordsTotal(dataList.size());
        page.setRecordsFiltered(dataList.size());
        page.setPage(s.getPage());
        page.setSize(s.getRows());
        data = JsonUtil.objectToJson(page);
        return data;
    }
    /**
     * 数据查询
     */
    @ResponseBody
    @RequestMapping("/getQueryData/page")
    public String getQueryDataPage(DeviceinfoDataSearchModel s,HttpServletResponse response){
        response.setCharacterEncoding(Constants.ENCODE_UTF8);
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);

        PageModel<ReportData> page = new PageModel();
        page.setData(Lists.newArrayList());
        String data = JsonUtil.objectToJsonPretty(page);
        if(s==null) return data;
        Long mid = s.getMonitorSiteId();
        String factor = s.getFactor();
        String factorName = factor;
        List<Monitofactors> dictlist = monitofactorsService.findFactorsBySiteId(mid);
        List<String> factors = Lists.newArrayList();
        for (Monitofactors d:dictlist){
            factors.add(d.getSno());
            if(d.getSno().equals(factor)){
                System.out.println("getQueryDataPage--------"+d.getSno()+","+d.getName()+","+mid);
                factorName =d.getName();
            }
        }
        s.setFactorList(factors);

        String day = Constants.DateTypeEnum.day.value();
        String hour = Constants.DateTypeEnum.hour.value();
        String minute = Constants.DateTypeEnum.minute.value();
        String realtime = Constants.DateTypeEnum.realtime.value();
        String dateType = s.getDateType();
        PageModel<DeviceinfoDayData> pageModel = null;
        if(day.equals(dateType)){
            pageModel = deviceinfoDayDataService.getQueryDataPage(s);
        }
        else if(hour.equals(dateType)){
            pageModel = deviceinfoDayDataService.getQueryDataPage(s);
        }
        else if(minute.equals(dateType)){
            pageModel = deviceinfoDayDataService.getQueryDataPage(s);
        }
        if(pageModel ==null || pageModel.getData()==null ) return data;
        List<ReportData> dataList = Lists.newArrayList();
        for(DeviceinfoDayData d:pageModel.getData()){
            ReportData reportData = new ReportData();
            reportData.setMonitorsiteName(d.getMonitorsiteName());
            reportData.setData1(d.getDataTime());
            reportData.setData2(getAllDeviceinfoData(d,dictlist));
            reportData.setTopFactor(factorName);
            dataList.add(reportData);
        }

        page.setData(dataList);
        page.setRecordsTotal(pageModel.getRecordsTotal());
        page.setRecordsFiltered(pageModel.getRecordsFiltered());
        page.setPage(s.getPage());
        page.setSize(s.getRows());
        data = JsonUtil.objectToJsonPretty(page);
        return data;
    }

    /**
     * 数据查询 Echart
     */
    @ResponseBody
    @RequestMapping("/getQueryData/echart")
    public String getQueryDataPageEchart(DeviceinfoDataSearchModel s,HttpServletResponse response){
        response.setCharacterEncoding(Constants.ENCODE_UTF8);
        response.setContentType(Constants.HEADER_CONTENT_TYPE_JSON);
        String data = Constants.EmptyJsonObject;
        if(s==null) return data;
        //
        String type = s.getEchartType();
        Long mid = s.getMonitorSiteId();

        String factor = s.getFactor();
        String factorName = factor;
        List<Monitofactors> dictlist = monitofactorsService.findFactorsBySiteId(mid);
        List<String> factors = Lists.newArrayList();
        for (Monitofactors d:dictlist){
            factors.add(d.getSno());
            if(d.getSno().equals(factor)){
                System.out.println("getQueryDataPageEchart--------"+d.getSno()+","+d.getName());
                factorName =d.getName();
            }
        }
        s.setFactorList(factors);

        String day = Constants.DateTypeEnum.day.value();
        String hour = Constants.DateTypeEnum.hour.value();
        String minute = Constants.DateTypeEnum.minute.value();
        String realtime = Constants.DateTypeEnum.realtime.value();
        if(mid==null || mid<=0){
            s.setDateType(day);//全部站点只有日数据
        }
        String dateType = s.getDateType();
        PageModel<DeviceinfoDayData> pageModel = null;
        if(day.equals(dateType)){
            pageModel = deviceinfoDayDataService.getQueryDataPage(s);
        }
        else if(hour.equals(dateType)){
            pageModel = deviceinfoDayDataService.getQueryDataPage(s);
        }
        else if(minute.equals(dateType)){
            pageModel = deviceinfoDayDataService.getQueryDataPage(s);
        }

        if(pageModel==null || pageModel.getData()==null) return data;



        List<DeviceinfoDayData> list = pageModel.getData();
        String title= s.parseTitleBase(factorName);
        String subTitle = "平均值";
        Multimap<String,DeviceinfoDayData> datalists = ArrayListMultimap.create();

        String []axis = s.parseAxis(s.getBeginDate(),s.getEndDate(),dateType);

        System.out.println("listsize="+list.size()+",size="+axis.length+"，s="+s);
        Map<String,Object> datas = Maps.newLinkedHashMap();
        if(mid==null || mid<=0)//全部站点只显示平均值
        {
            for (int i=0;i<list.size();i++) {
                DeviceinfoDayData d = list.get(i);
                String key = d.getMonitorsiteName();
                datalists.put(key,d);
            }
            datas = paserEchartAxisBySite(datalists,axis,s);
            subTitle = Constants.FactorTypeEnum.Avg.cnname();
        }
        else{
            //"平均值","最小值","最大值","累计值"
            List<String> keys = Lists.newArrayList(
                    Constants.FactorTypeEnum.Avg.value(),
                    Constants.FactorTypeEnum.Min.value(),
                    Constants.FactorTypeEnum.Max.value(),Constants.FactorTypeEnum.Cou.value()
            );
            Monitorsite mm = monitorsiteService.selectByPrimaryKey(mid);
            String pre =  mm!=null ? mm.getName() : "";
            subTitle = pre;
            int arraySize = axis.length;
            for (String key:keys){
                String keyname = Constants.FactorTypeEnum.cnname(key);
                Object dataobj[] = new Object[arraySize];
                for(int oi=0;oi<arraySize;oi++){dataobj[oi]="";}

                if(!Strings.isNullOrEmpty(factor)){
                    for(DeviceinfoDayData d:list){
                        int idx = -1;
                        String datetime = d.getDataTime();
                        for (int ki=0;ki<axis.length;ki++){
                            String pattern = DateUtils.getPatternByDateType(dateType);
                            String datetimeStr = DateUtils.formatDate(DateUtils.parseDate(datetime),pattern);
                            System.out.println(axis[ki]+"，"+datetimeStr);
                            if(axis[ki].equals(datetimeStr)){
                                idx = ki;
                                break;
                            }
                        }
                        if(idx>=0 && idx < dataobj.length)
                        {
                            String filedName = StringUtil.underlineToCamelhump(factor+key);
                            String value = (String)ReflectionUtil.getFieldValue(d, filedName);
                            value = value !=null? value : "";
                            dataobj[idx] = value;
                        }
                    }
                    datas.put(keyname,dataobj);
                }
                else{
                    datas.put(keyname,dataobj);
                }
            }

        }

        data= EChartUtil.generateEchartOption(type,title,subTitle,datas,axis,true);
        return data;
    }
    //###############################################################################################################//
    //                                               私有方法区域
    //###############################################################################################################//
    //按日期顺序获取Echat值 axis为站点
    private Map<String,Object> paserEchartAxisBySite(Multimap<String,DeviceinfoDayData> datalists,String []axis,DeviceinfoDataSearchModel s){
        String dateType = s.getDateType();
        String factor = s.getFactor();
        int arraySize = axis.length;
        Map<String,Object> datas = Maps.newLinkedHashMap();
        for (String key:datalists.keySet()){
            Collection<DeviceinfoDayData> childs = datalists.get(key);
            Object dataobj[] = new Object[arraySize];
            for(int oi=0;oi<arraySize;oi++){dataobj[oi]="";}
            if(!Strings.isNullOrEmpty(factor))
            {
                for(DeviceinfoDayData d:childs){
                    int idx = -1;
                    String datetime = d.getDataTime();
                    for (int ki=0;ki<axis.length;ki++){
                        String pattern = DateUtils.getPatternByDateType(dateType);
                        String datetimeStr = DateUtils.formatDate(DateUtils.parseDate(datetime),pattern);
                        if(axis[ki].equals(datetimeStr)){
                            idx = ki;
                            break;
                        }
                    }
                    if(idx>=0 && idx < dataobj.length)
                    {
                        String filedName = StringUtil.underlineToCamelhump(factor+"Avg");
                        String value = (String)ReflectionUtil.getFieldValue(d, filedName);
                        value = value !=null? value : "";
                        dataobj[idx] = value;
                    }
                }
                datas.put(key.toString(),dataobj);
            }
            else{
                datas.put(key.toString(),dataobj);
            }
        }
        return datas;
    }

    //报表
    private List<ReportData> getReportDataBase(DeviceinfoDataSearchModel s){
        List<ReportData> reportDataList = Lists.newArrayList();
        Long mid = s.getMonitorSiteId();
        String factor = s.getFactor();
        String factorName = factor;
        List<Monitofactors> dictlist = monitofactorsService.findFactorsBySiteId(mid);
        List<String> factors = Lists.newArrayList();
        for (Monitofactors d:dictlist){
            factors.add(d.getSno());
            if(d.getSno().equals(factor)){
                System.out.println("getReportDataBase--------"+d.getSno()+","+d.getName());
                factorName =d.getName();
            }
        }
        s.setFactorList(factors);

        List<DeviceinfoDayData> retlist = deviceinfoDayDataService.getStatData(s);
        System.out.println("size="+retlist.size()+",s="+s);
        if(retlist==null || retlist.size()==0)
        {
            return reportDataList;
        }
        String year = Constants.DateTypeEnum.year.value();
        String month = Constants.DateTypeEnum.month.value();
        String season = Constants.DateTypeEnum.season.value();
        String quarter = Constants.DateTypeEnum.quarter.value();
        String day = Constants.DateTypeEnum.day.value();
        String hour = Constants.DateTypeEnum.hour.value();

        List<DeviceinfoDayData> sublist = Lists.newArrayList();
        String dateType = s.getDateType();
        if(hour.equals(dateType)){
            s.setDateType(day);
            sublist.addAll(deviceinfoDayDataService.getStatData(s));
            s.setDateType(hour);
        }
        else if(day.equals(dateType)){
            s.setDateType(month);
            sublist.addAll(deviceinfoDayDataService.getStatData(s));
            s.setDateType(day);
        }
        else if(quarter.equals(dateType)){
            s.setDateType(season);
            for(Integer k=1;k<=4;k++){
                s.setQuarter(k.toString());
                sublist.addAll(deviceinfoDayDataService.getStatData(s));
            }
            s.setDateType(quarter);

        }
        else if(month.equals(dateType)){
            s.setDateType(year);
            sublist.addAll(deviceinfoDayDataService.getStatData(s));
            s.setDateType(month);
        }
        //
        Map<String,String> dayavgMap = Maps.newHashMap();
        for(DeviceinfoDayData b:sublist){
            String allavg = getAllDeviceinfoData(b,dictlist);
            String key = b.getMonitorsiteName();
            if(quarter.equals(dateType)){
                key = b.getYear()+"-"+b.getQuarter();
            }
            dayavgMap.put(key,allavg);
        }
        //

        for(DeviceinfoDayData b:retlist){
            ReportData reportData = new ReportData();
            reportData.setMonitorsiteName(b.getMonitorsiteName());
            String data1 ="";
            if(hour.equals(dateType))
            {
                data1 = b.getHour().toString();
            }
            else if(day.equals(dateType))
            {
                data1 = b.getYear()+"年-"+b.getMonth()+"月-"+b.getDay();;
            }
            else if(quarter.equals(dateType)) {
                data1 = b.getYear()+"年-第"+b.getQuarter()+"季度-"+b.getMonth()+"月";
            }
            else if(month.equals(dateType)){
                data1 = b.getYear()+"年-"+b.getMonth()+"月";
            }
            reportData.setData1(data1);
            String allavg = getAllDeviceinfoData(b,dictlist);
            reportData.setData2(allavg);

            String key = b.getMonitorsiteName();
            if(quarter.equals(dateType)){
                key = b.getYear()+"-"+b.getQuarter();
            }
            reportData.setData3(dayavgMap.get(key));
            reportData.setFactorWaterType("");
            reportData.setWaterType("");
            reportData.setTopFactor(dictlist.get(0).getName());
            reportDataList.add(reportData);
        }
        //data = JsonUtil.objectToJson(reportDataList);
        return reportDataList;
    }

    //
    private String getAllDeviceinfoData(DeviceinfoDayData b,List<Monitofactors> dictlist){
        StringBuffer sb = new StringBuffer();
        for(Monitofactors d:dictlist){
            String filedName = d.getSno()+"Avg";
            String dataAvg = (String)ReflectionUtil.getFieldValue(b, filedName);
            Double value = dataAvg!=null ? Doubles.tryParse(dataAvg) : -1;
            String ret = dataAvg;
            //System.out.println(d+","+value+",key="+dataAvg);
            if(d.getOverproofMin()!=null && value > d.getOverproofMin() ){
                ret = "<span class=\'color_red\'>"+ret+"</span>";
            }
            if(d.getInvalidMin()!=null && value < d.getInvalidMin() ){
                ret = "<span class=\'color_blue\'>"+ret+"</span>";
            }
            if (!Strings.isNullOrEmpty(ret))
                sb.append(d.getName()+"="+ret+",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        return sb.toString();
    }

    //获取某段时间内的EchartOption
    private String getDateBase3(DeviceinfoDataSearchModel s,boolean order){
        String data = Constants.EmptyJsonObject;
        String type = s.getEchartType();
        Long mid = s.getMonitorSiteId();
        String factor = s.getFactor();
        String factorName = factor;
        List<Monitofactors> dictlist = monitofactorsService.findFactorsBySiteId(mid);
        List<String> factors = Lists.newArrayList();
        for (Monitofactors d:dictlist){
            factors.add(d.getSno());
            if(d.getSno().equals(factor)){
                System.out.println("--------"+d.getSno()+","+d.getName());
                factorName =d.getName();
            }
        }
        s.setFactorList(factors);


        List<DeviceinfoDayData> list = deviceinfoDayDataService.getStatDataRange(s);
        System.out.println("size="+list.size()+",s="+s);
        Multimap<String,DeviceinfoDayData> datalists = ArrayListMultimap.create();
        for (int i=0;i<list.size();i++) {
            DeviceinfoDayData d = list.get(i);
            String key = d.getMonitorsiteName();
            //if(Strings.isNullOrEmpty(key)){
            //    key = d.getDevicegpsId().toString();
            //}
            datalists.put(key,d);
        }
        String dateType = s.getDateType();
        String title= s.parseTitleBase(factorName);
        String []axis = s.parseAxis(s.getBeginDate(),s.getEndDate(),dateType);
        int arraySize = axis.length;
        Map<String,Object> datas = Maps.newHashMap();
        for (String key:datalists.keySet()){
            Collection<DeviceinfoDayData> childs = datalists.get(key);
            Object dataobj[] = new Object[arraySize];
            for(int oi=0;oi<arraySize;oi++){dataobj[oi]="";}
            if(!Strings.isNullOrEmpty(factor)){
                for(DeviceinfoDayData d:childs){
                    int idx = s.parseIndex(s.getBeginDate(),s.getEndDate(),d);
                    if(idx>=0 && idx < dataobj.length)
                    {
                        String filedName = StringUtil.underlineToCamelhump(factor+"Avg");
                        String value = (String)ReflectionUtil.getFieldValue(d, filedName);
                        value = value !=null? value : "";
                        dataobj[idx] = value;
                    }
                }
                datas.put(key.toString(),dataobj);
            }
            else{
                datas.put(key.toString(),dataobj);
            }
        }
        data= EChartUtil.generateEchartOption(type,title,"平均值",datas,axis,order);
        return data;
    }
    //同时多站单参
    private String getDateBase2(DeviceinfoDataSearchModel s,boolean order){
        String data = Constants.EmptyJsonObject;
        String type = s.getEchartType();
        Long mid = s.getMonitorSiteId();
        String factor = s.getFactor();
        String factorName = factor;
        List<Monitofactors> dictlist = monitofactorsService.findFactorsBySiteId(mid);
        List<String> factors = Lists.newArrayList();
        for (Monitofactors d:dictlist){
            factors.add(d.getSno());
            if(d.getSno().equals(factor)){
                System.out.println("--------"+d.getSno()+","+d.getName());
                factorName =d.getName();
            }
        }
        s.setFactorList(factors);

        List<DeviceinfoDayData> list = deviceinfoDayDataService.getStatData(s);
        System.out.println("size="+list.size()+",s="+s);

        String title= s.parseTitleBase(factorName);
        Multimap<String,DeviceinfoDayData> datalists = ArrayListMultimap.create();

        Map<Integer,Integer> arrMaps= Maps.newLinkedHashMap();
        int mi = 0;
        for (int i=0;i<list.size();i++) {
            DeviceinfoDayData d = list.get(i);
            String key = d.getMonitorsiteName();
            datalists.put(key,d);
            if(!arrMaps.containsKey(d.getYear()))
                arrMaps.put(d.getYear(),mi++);
        }
        String []axis = s.parseAxis(arrMaps);
        int arraySize = axis.length;

        Map<String,Object> datas = Maps.newLinkedHashMap();
        for (String key:datalists.keySet()){
            Collection<DeviceinfoDayData> childs = datalists.get(key);
            Object dataobj[] = new Object[arraySize];
            for(int oi=0;oi<arraySize;oi++){dataobj[oi]="";}
            if(!Strings.isNullOrEmpty(factor))
            {
                for(DeviceinfoDayData d:childs){
                    int idx = s.parseIndex(arrMaps,d);
                    if(idx>=0 && idx < dataobj.length)
                    {
                        String filedName = StringUtil.underlineToCamelhump(factor+"Avg");
                        String value = (String)ReflectionUtil.getFieldValue(d, filedName);
                        value = value !=null? value : "";
                        dataobj[idx] = value;
                    }
                }
                datas.put(key.toString(),dataobj);
            }
            else{
                datas.put(key.toString(),dataobj);
            }
        }
        data= EChartUtil.generateEchartOption(type,title,"平均值",datas,axis,order);
        return data;
    }
    //同时多站多参
    private String getDateBase(DeviceinfoDataSearchModel s,boolean order){
        String data = Constants.EmptyJsonObject;
        String type = s.getEchartType();
        Long mid = s.getMonitorSiteId();
        System.out.println("mid="+mid);
        List<Monitofactors> dictlist = monitofactorsService.findFactorsBySiteId(mid);
        List<String> factors = Lists.newArrayList();
        for (Monitofactors d:dictlist){
            factors.add(d.getSno());
        }
        s.setFactorList(factors);

        List<DeviceinfoDayData> list = deviceinfoDayDataService.getStatData(s);

        Map<Integer,Integer> arrMaps= Maps.newHashMap();
        int mi = 0;
        String pre = "全部站点";
        if(mid!=null && mid >0 )
        {
            Monitorsite mm = monitorsiteService.selectByPrimaryKey(mid);
            pre=  mm!=null ? mm.getName() : "";
        }
        //System.out.println("list="+list.size());

        for (int i=0;i<list.size();i++) {
            DeviceinfoDayData d = list.get(i);
            if(!arrMaps.containsKey(d.getYear()))
                arrMaps.put(d.getYear(),mi++);
        }

        String title= s.parseTitleBase(pre);
        String []axis = s.parseAxis(arrMaps);
        int arraySize = axis.length;

        Map<String,Object[]> datalists = Maps.newLinkedHashMap();
        for(int k=0;k<dictlist.size();k++){
            Monitofactors keyobj = dictlist.get(k);
            String key  = keyobj.getName();
            Object dataobj[] = new Object[arraySize];
            for(int oi=0;oi<arraySize;oi++){dataobj[oi]="";}
            datalists.put(key,dataobj);
        }
        //
        Map<String,Object> datas = Maps.newLinkedHashMap();
        for (int i=0;i<list.size();i++){
            DeviceinfoDayData d = list.get(i);

            for(int k=0;k<dictlist.size();k++){
                Monitofactors keyobj = dictlist.get(k);
                String key = keyobj.getName();
                int idx = s.parseIndex(arrMaps,d);
                String value = (String)ReflectionUtil.getFieldValue(d, StringUtil.underlineToCamelhump(keyobj.getSno()+"Avg"));
                value = value !=null? value : "";
                if(idx>=0 && idx < datalists.get(key).length)
                    datalists.get(key)[idx] = value;
            }
        }
        for(String key:datalists.keySet()){
            datas.put(key.toString(),datalists.get(key));
        }
        data= EChartUtil.generateEchartOption(type,title,"平均值",datas,axis,order);

        return data;
    }


}
