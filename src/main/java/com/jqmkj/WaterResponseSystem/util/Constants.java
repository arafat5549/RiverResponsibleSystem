package com.jqmkj.WaterResponseSystem.util;


import com.google.common.collect.Lists;
import com.jqmkj.WaterResponseSystem.entity.BaseDataEntity;
import com.jqmkj.WaterResponseSystem.entity.DeviceInfoRealTimeData;
import com.jqmkj.WaterResponseSystem.entity.DeviceinfoDayData;
import com.jqmkj.WaterResponseSystem.entity.DeviceinfoHourData;
import com.sun.tools.javac.code.Attribute;

import java.util.List;

/**
 * Created by wangyaoyao on 2018/8/14.
 */
public class Constants {
    public static boolean Debug(){return true;}
    public static final String SYS_DATABASE = "mysql";


    //###############################################################################################################//
    //                                      KEY索引字段区域
    //###############################################################################################################//
    /**   完整的redis缓存的key   **/
    //public static final String CACHE_KEY_CURRENT_LOGINUSER = SessionUtil.SessionSystemLoginUserName;  //当前登录用户
    public static final String CACHE_KEY_ROLE_LIST          = "rolelist";
    public static final String CACHE_KEY_PERMISSION_LIST   = "permissionlist";

    /**   拼接的redis缓存的key   **/
    public static final String CACHE_KEY_LOGINNAME_ = "logname#";

    public static final String CACHE_KEY_LISTBYMAP_ALL_ = "all";
//    /**
//     * 获取缓存key
//     */
//    public static String getCachekey(String value){
//        return CACHE_KEY_LOGINNAME + value;
//    }


    public static  final Integer ECHART_MONTHS   = 0;
    public static  final Integer ECHART_YEARS    = 1;
    public static  final Integer ECHART_DAYS     = 2;
    //###############################################################################################################//
    //                                       私有字段区域
    //###############################################################################################################//
    public static final String Fail = "fail";
    public static final String Success = "success";
    public static final String Exists = "exists";
    public static final String DefaultMd5Password ="63a9f0ea7bb98050796b649e85481845";//123456
    public static final String EmptyJsonObject = "{}";
    public static final int DEFALUT_LIMIT = 20; //默认每页显示50条
    public static final String ENCODE_UTF8 = "UTF-8";
    public static final String HEADER_CONTENT_TYPE_JSON = "application/json;charset=UTF-8";

    //###############################################################################################################//
    //                                       Enum
    //###############################################################################################################//
    //	//
//	private short head;//固定为2位##
//	private short contentSize;//数据段长度-4位
//	//数据段- 0≤n≤1024
//	private String QN;	//	请求编码QN 字符 20 精确到毫秒的时间戳:QN=YYYYMMDDhhmmsszzz，用来唯一标识一次命令交互
//	private String ST;	//	系统编码ST 字符 5 ST=系统编码
//	private String CN;	//	命令编码CN 字符 7 CN=命令编码
//	private String PW;	//	访问密码PW 字符 9 PW=访问密码
//	private String MN;	//	设备唯一标识MN 字符 27 MN=设备唯一标识
//	private String Flag;	//	拆分包及应答标志Flag 整数（0-255）8 Flag=标志位
//	private String PNUM;	//	总包数PNUM 字符 9 PNUM 指示本次通讯中总共包含的包数注：不分包时可以没有本字段，与标志位有关
//	private String PNO;	//	包号PNO 字符 8 PNO 指示当前数据包的包号注：不分包时可以没有本字段，与标志位有关

//	private String CP;	//	指令参数CP 字符 0≤n≤950 CP=&&数据区&&

//	private String crc;//4位16进制
//	private String tail;//2位固定为回车换行

    //设备解析消息类型
    public enum NettyWater212Enum{
        HEADER_INIT("##",2),
        CONTENT_QN("QN",20),
        CONTENT_ST("ST",5),
        CONTENT_CN("CN",7),
        TAIL("",2)//"\r\n"
        ;
        private final String  sname;
        private final Integer size;
        public String sname() {return this.sname;}
        public Integer size() {return this.size;}

        NettyWater212Enum(String  sname,Integer size){
            this.sname = sname;
            this.size = size;
        }
    }

    /**212协议的系统编号：21地表水环境监测系统编码 */
    public enum DeviceDataTypeEnum{
        WATER_NA212("21",0,"地表水监测212解析"), //地表水环境监测系统编码
//        AIR_SIM212("22",1,""),  //大气环境监测系统编码
//        AIR_NA212("22",2,""),
//        AIR_TVOC212("31",3,""), //空气污染监测系统编码
//        POS_NA212("65",4,"")    //移动定位监测系统编码
        ;

        private final String code;
        private final Integer dataType;
        private final String processName;

        public String code() {return this.code;}
        public Integer dataType() {return this.dataType;}
        public String processName() {return this.processName;}

        DeviceDataTypeEnum(String code,Integer dataType,String processName) {
            this.code = code;
            this.dataType = dataType;
            this.processName = processName;
        }
    }
    //日期类型
    public enum DateTypeEnum{
        year("year","年",""),
        quarter("quarter","季度",""),
        season("season","季度",""),
        month("month","月",""),
        week("week","周",""),
        day("day","日","2031"),
        hour("hour","小时","2061"),
        minute("minute","分钟","2051"),
        realtime("realtime","实时","2011")
        ;
        private final String column;
        private final String cnname;
        private final String code;
        public String value() {return this.column;}
        public String cnname(){return this.cnname;}
        public String code() {return this.code;}

        public static String cnname(String type) {
            for(DateTypeEnum d:values()){
                if(d.column.equals(type)){
                    return d.cnname;
                }
            }
            return "";
        }
        public static BaseDataEntity entity(String code){
            BaseDataEntity tmpdata = null;
            if(Constants.DateTypeEnum.realtime.code().equals(code)) {
                tmpdata = new DeviceInfoRealTimeData();
            }else if(Constants.DateTypeEnum.day.code().equals(code)){
                tmpdata = new DeviceinfoDayData();
            }else if(Constants.DateTypeEnum.hour.code().equals(code)){
                tmpdata = new DeviceinfoHourData();
            }else{
                //tmpdata = new DeviceinfoHourData();
            }
            return tmpdata;
        }



        DateTypeEnum(String column,String cnname,String code) {
            this.column = column;
            this.cnname = cnname;
            this.code = code;
        }
    }
    //因子数值类型
    public enum FactorTypeEnum{
        Avg("Avg","平均值"),//指定时间内平均值
        Min("Min","最小值"),//指定时间内最小值
        Max("Max","最大值"),//指定时间内最大值
        Cou("Cou","累计值"),//指定时间内累计值
        Flag("Flag","标示位"),//标示位
        Rtd("Rtd","实时采样数据")   //实时采样数据
        ;

        private final String column;
        private final String cnname;
        public String value() {return this.column;}
        public String cnname(){return this.cnname;}
        public static String cnname(String type) {
            for(FactorTypeEnum d:values()){
                if(d.column.equals(type)){
                    return d.cnname;
                }
            }
            return "";
        }
        public static List<String> valuelist(){
            List<String> list = Lists.newArrayList();
            for(FactorTypeEnum d:values()){
                list.add(d.value());
            }
            return list;
        }
        FactorTypeEnum(String column,String cnname) {this.column = column; this.cnname = cnname;}
    }

    //###############################################################################################################//
    //                                        Netty 解析区域
    //###############################################################################################################//
    public static final String REMOTEIP = "127.0.0.1";
    public static final int PORT = 8095;

    //public static final String HEADER_INIT = "##";// 固定报表头
    public static final int HEADER_INIT_SIZE = NettyWater212Enum.HEADER_INIT.size();

    public static final int MAX_FRAME_LENGTH = 1024 * 1024;
    public static final int LENGTH_FIELD_LENGTH = HEADER_INIT_SIZE;//4;
    public static final int LENGTH_FIELD_OFFSET = 4;//6;
    public static final int LENGTH_ADJUSTMENT = 0;
    public static final int INITIAL_BYTES_TO_STRIP = 0;
}
