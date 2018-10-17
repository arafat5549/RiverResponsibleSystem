package com.jqmkj.WaterResponseSystem.iot;


import com.google.common.base.Strings;
import com.jqmkj.WaterResponseSystem.entity.BaseDataEntity;
import com.jqmkj.WaterResponseSystem.iot.process.IProcesser;
import com.jqmkj.WaterResponseSystem.iot.prorocol.ProtocolHeader;
import com.jqmkj.WaterResponseSystem.iot.prorocol.ProtocolMsg;
import com.jqmkj.WaterResponseSystem.util.Constants;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * 检验出的异常：
 * 1.212协议检测异常
 * 2.包含空格错误
 * 3.设备号不合规范 > 字母加数字
 * 4.数据包头错误
 * @author Liu Siyuan
 *
 */
public class IOTChecker {

    private static String RE_CODE_OK = "检测成功！";
    private static String RE_CONTENTSIZE_ERR = "数据错误！（数据段长度错误）";
    private static String RE_CRC_ERR = "数据错误！（CRC校验算法错误）";
    private static String RE_CRC_STRING_ERR = "数据错误！（CRC段必须为16进制）";
    private static String RE_EMPTY_CHAR_ERR = "数据错误！（不允许包含空格）";
    private static String RE_START_CHAR_ERR = "数据错误！（包头：固定为##）";
    private static String RE_PROTOCOL212_OTHER_ERR = "数据错误！（其他）";
    private static String RE_MN_ERR = "设备号错误！（字母加数字构成或者数字长度超过10位）";
    private static String RE_HEADER_ERR = "协议头部描述错误！(包头：固定为 ##+数据段长度+ST系统类型)(或者设备类型选择错误)";


    public static String checkMessage(ProtocolMsg msg, IProcesser p1) {
        //if(type == Constants.DeviceDataTypeEnum.WATER_NA212.dataType())
        {
            String head  = Constants.NettyWater212Enum.HEADER_INIT.sname();
            int headsize = Constants.NettyWater212Enum.HEADER_INIT.size();

            String mess = msg.getBody();
            if(Strings.isNullOrEmpty(mess)){
                return RE_PROTOCOL212_OTHER_ERR;
            }
            ProtocolHeader header = msg.getProtocolHeader();
            int bodySize = mess.getBytes(CharsetUtil.UTF_8).length;
            //System.out.println("size="+header.getContentSize()+","+bodySize);
            if(header.getContentSize()!=bodySize){
                return RE_CONTENTSIZE_ERR;
            }

            if(!head.equals(mess.substring(0, headsize))) {
                return RE_START_CHAR_ERR;
            }
            int start = mess.lastIndexOf("&&");
            if(!isHexNumber(mess.substring(start + 2, mess.length()))) {
                return RE_CRC_STRING_ERR;
            }
            if(!p1.CheckData(mess)){
                return RE_HEADER_ERR;
             }
            if(!checkCRC(mess)) {
                return RE_CRC_ERR;
            }


        }
        return "";
    }

    private static boolean checkCRC(String mess) {
        int start = mess.indexOf("ST=");
        int end = mess.lastIndexOf("&&");
        String target = mess.substring(start, end + 2);
        String CRC = Integer.toHexString(IOTUtils.generateCRC(target)).toUpperCase();
        return CRC.equals(mess.substring(end + 2, mess.length()));
    }


    public static boolean checkDevieId(String str) {
        if(str == null || str.length() ==0 || str.length() < 10) {
            return false;
        }
        return containNum(str) && containNum(str);
    }

    private static boolean containNum(String str){
        char ch[] = str.toCharArray();
        boolean contain=false;
        for(int i=0;i<ch.length;i++){
            if(Character.isDigit(ch[i])){
                contain=true;
            }
        }
        return contain;
    }

    private static boolean containLetter(String str){
        char ch[] = str.toCharArray();
        boolean contain=false;
        for(int i=0;i<ch.length;i++){
            if(Character.isLetter(ch[i])){
                contain=true;
            }
        }
        return contain;
    }
    
    //十六进制  
    private static boolean isHexNumber(String str){  
        boolean flag = true;  
        for(int i=0;i<str.length();i++){  
            char cc = str.charAt(i);  
            if(!(cc=='0'||cc=='1'||cc=='2'||cc=='3'||cc=='4'||cc=='5'||cc=='6'||cc=='7'||cc=='8'||cc=='9'||cc=='A'||cc=='B'||cc=='C'||  
                    cc=='D'||cc=='E'||cc=='F'||cc=='a'||cc=='b'||cc=='c'||cc=='c'||cc=='d'||cc=='e'||cc=='f')){  
                flag = false;  
                break;
            }  
        }  
        return flag;  
    }  
}
