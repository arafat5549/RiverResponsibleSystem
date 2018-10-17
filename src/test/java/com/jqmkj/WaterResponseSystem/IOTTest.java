package com.jqmkj.WaterResponseSystem;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.jqmkj.WaterResponseSystem.entity.BaseDataEntity;
import com.jqmkj.WaterResponseSystem.entity.DeviceInfoRealTimeData;
import com.jqmkj.WaterResponseSystem.iot.IOTUtils;
import com.jqmkj.WaterResponseSystem.iot.message.WaterDeviceData;
import com.jqmkj.WaterResponseSystem.iot.process.ProcesserWater212;
import com.jqmkj.WaterResponseSystem.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.List;

public class IOTTest {
//        msg ="##0856ST=21;CN=2011;PW=123456;MN=huantai_device_0021;CP=&&DataTime=20181015161239;w01018-Rtd=15.95,w01018-Flag=N;w01017-Rtd=1.29,w01017-Flag=N;" +
//                "w21003-Rtd=0.6,w21003-Flag=N;w01019-Rtd=3.0,w01019-Flag=N;w01001-Rtd=7.00,w01001-Flag=N;w01009-Rtd=9.5,w01009-Flag=N;w01014-Rtd=75.0,w01014-Flag=N;" +
//                "a01021-Rtd=102.0,a01021-Flag=N;w01010-Rtd=4.36,w01010-Flag=N;w21011-Rtd=0.01,w21011-Flag=N;w21001-Rtd=0.20,w21001-Flag=N;w20122-Rtd=0.00683,w20122-Flag=N;" +
//                "w20139-Rtd=0.50,w20139-Flag=N;w21017-Rtd=0.50,w21017-Flag=N;w20141-Rtd=0.00,w20141-Flag=N;w20142-Rtd=0.00004,w20142-Flag=N;w20143-Rtd=0.00208,w20143-Flag=N;" +
//                "w20144-Rtd=0.03,w20144-Flag=N;w20117-Rtd=0.05,w20117-Flag=N;w20125-Rtd=4.0,w20125-Flag=N;w21016-Rtd=0.0352,w21016-Flag=N;w23002-Rtd=0.00300,w23002-Flag=N;" +
//                "w22001-Rtd=0.0500,w22001-Flag=N;w19002-Rtd=0.11,w19002-Flag=N;w21007-Rtd=2.0,w21007-Flag=N&&9AC1";


    public static void main(String[] args) {

        List<String> site3501Factors = Lists.newArrayList(
                "w01014",
                "w01017",
                "w01019",
                "w02003",
                "w19001",
                "w19002",
                "w20023",
                "w20038",
                "w20113",
                "w20115",
                "w20117",
                "w20119",
                "w20120",
                "w20121",
                "w20122",
                "w20123",
                "w20124",
                "w20125",
                "w20139",
                "w21001",
                "w21003",
                "w21004",
                "w21011",
                "w21022",
                "w22001"
        );

        String host = "127.0.0.1";
        Integer port = 8095;
        WaterDeviceData waterDeviceData = new WaterDeviceData(host,port);
        waterDeviceData.setFullFactor(true);
        waterDeviceData.setDeviceId("huantai_device_0021");
        String msg = IOTUtils.generateMessage(waterDeviceData);
        System.out.println("["+msg+"]");




        ProcesserWater212 processer = new ProcesserWater212();
        List<BaseDataEntity> datas =processer.Process(msg);
        //System.out.println(datas.size());

        for(BaseDataEntity data:datas){
            StringBuffer sb = new StringBuffer();
            if(data instanceof DeviceInfoRealTimeData){
                Field fs[] =data.getClass().getDeclaredFields();
                for(Field f:fs){
                    String avgvalue = (String)ReflectionUtil.getFieldValue(data,f.getName());
                    if(!Strings.isNullOrEmpty(avgvalue))
                        sb.append(f.getName()+"="+avgvalue+",");
                }
            }
            else{
                for(String f:site3501Factors){
                    String avgvalue = (String)ReflectionUtil.getFieldValue(data,f+"Avg");
                    String minvalue = (String)ReflectionUtil.getFieldValue(data,f+"Min");
                    sb.append(f+"Avg="+avgvalue+",");
                    sb.append(f+"Min="+minvalue+",");
                }

            }
            System.out.println(sb.toString());

        }
    }
}
