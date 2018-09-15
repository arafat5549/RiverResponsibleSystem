package com.jqm.ssm.enums;

/**
 * 字典
 */
public enum DictionayEnum {

    DICT_TYPE_SYSTEM_LOG   ("system_log"),
    DICT_TYPE_DEVICE_LOG   ("deviceinfo_log"),
    DICT_TYPE_DEVICE_STATUS("deviceinfo_status");

//            "deviceinfo_status","0","新建"
//            "deviceinfo_status","1","运行中"
//            "deviceinfo_status","2","维护中"
//            "deviceinfo_status","3","中止"
//            "deviceinfo_log","1","新增设备"
//            "deviceinfo_log","2","修改设备"
//            "deviceinfo_log","3","图片上传完整"
//            "deviceinfo_log","4","图片删除"
//            "deviceinfo_log","5","图片不再完整"
//            "deviceinfo_log","6","设备部署"
//            "deviceinfo_log","7","设备下架"
//            "deviceinfo_log","8","修改价格"
//            "deviceinfo_log","9","删除设备"
//            "deviceinfo_pictype","1","原图-o"
//            "deviceinfo_pictype","2","大图-l"
//            "deviceinfo_pictype","3","中图-m"
//            "deviceinfo_pictype","4","小图-s"
//            "deviceinfo_pictype","5","微图-t"
//            "deviceinfo_pictype","6","宝贝描述图-b"
//            "test","1","测试数据"
//            "system_log","0","登陆"
//            "system_log","1","注册"
//            "system_log","2","权限操作"
//            "system_log","3","品牌管理"
//            "system_log","4","分类管理"
//            "system_message_method","0","短信"
//            "system_message_method","1","邮件"
//            "system_message_method","2","其他"
//            "system_message_type","0","系统内部"
//            "system_message_type","1","消息推送"
//            "system_message_type","4","公告通知"
//            "system_message_type","5","警报"

    private String group;
    private int code;
    private String name;

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getGroup() {
        return group;
    }

    DictionayEnum(String group) {
        this.group = group;
    }

}
