package com.jqmkj.WaterResponseSystem.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "deviceinfo_test_data",
        indexes = {  @Index(name = "my_index_DataTime", columnList="data_time",     unique = false)})
public class DeviceinfoTestData extends BaseDataEntity{

    @Column(name="monitorsite_id")
    private Long monitorsiteId;//监测站ID
    @Column(name="data_time")
    private String DataTime;
    @Column(length=12,name="w00000_Cou")
    private Float w00000_Cou;  //污水 B01 升/秒 立方米 N5.2
    @Column(length=8,name="w00000_Min")
    private Float w00000_Min;
    @Column(length=8,name="w00000_Avg")
    private Float w00000_Avg;
    @Column(length=8,name="w00000_Max")
    private Float w00000_Max;
    @Column(length=8,name="w00000_Flag")
    private String w00000_Flag;
    @Column(length=12,name="w01001_Cou")
    private Float w01001_Cou;  //pH 值 001 无量纲 N2.2
    @Column(length=8,name="w01001_Min")
    private Float w01001_Min;
    @Column(length=8,name="w01001_Avg")
    private Float w01001_Avg;
    @Column(length=8,name="w01001_Max")
    private Float w01001_Max;
    @Column(length=8,name="w01001_Flag")
    private String w01001_Flag;
    @Column(length=12,name="w01002_Cou")
    private Float w01002_Cou;  //色度 002 [色]度 N3.2
    @Column(length=8,name="w01002_Min")
    private Float w01002_Min;
    @Column(length=8,name="w01002_Avg")
    private Float w01002_Avg;
    @Column(length=8,name="w01002_Max")
    private Float w01002_Max;
    @Column(length=8,name="w01002_Flag")
    private String w01002_Flag;
    @Column(length=12,name="w01006_Cou")
    private Float w01006_Cou;  //溶解性总固体 -- 毫克/升 千克 N4
    @Column(length=8,name="w01006_Min")
    private Float w01006_Min;
    @Column(length=8,name="w01006_Avg")
    private Float w01006_Avg;
    @Column(length=8,name="w01006_Max")
    private Float w01006_Max;
    @Column(length=8,name="w01006_Flag")
    private String w01006_Flag;
    @Column(length=12,name="w01009_Cou")
    private Float w01009_Cou;  //溶解氧 -- 毫克/升 N3.1
    @Column(length=8,name="w01009_Min")
    private Float w01009_Min;
    @Column(length=8,name="w01009_Avg")
    private Float w01009_Avg;
    @Column(length=8,name="w01009_Max")
    private Float w01009_Max;
    @Column(length=8,name="w01009_Flag")
    private String w01009_Flag;
    @Column(length=12,name="w01010_Cou")
    private Float w01010_Cou;  //水温 -- 摄氏度 N3.1
    @Column(length=8,name="w01010_Min")
    private Float w01010_Min;
    @Column(length=8,name="w01010_Avg")
    private Float w01010_Avg;
    @Column(length=8,name="w01010_Max")
    private Float w01010_Max;
    @Column(length=8,name="w01010_Flag")
    private String w01010_Flag;
    @Column(length=12,name="w01012_Cou")
    private Float w01012_Cou;  //悬浮物 003 毫克/升 千克 N4
    @Column(length=8,name="w01012_Min")
    private Float w01012_Min;
    @Column(length=8,name="w01012_Avg")
    private Float w01012_Avg;
    @Column(length=8,name="w01012_Max")
    private Float w01012_Max;
    @Column(length=8,name="w01012_Flag")
    private String w01012_Flag;
    @Column(length=12,name="w01014_Cou")
    private Float w01014_Cou;  //电导率 -- 微西[门子]/厘米 N3.1
    @Column(length=8,name="w01014_Min")
    private Float w01014_Min;
    @Column(length=8,name="w01014_Avg")
    private Float w01014_Avg;
    @Column(length=8,name="w01014_Max")
    private Float w01014_Max;
    @Column(length=8,name="w01014_Flag")
    private String w01014_Flag;
    @Column(length=12,name="w01017_Cou")
    private Float w01017_Cou;  //五日生化需氧量 010 毫克/升 千克 N5.1
    @Column(length=8,name="w01017_Min")
    private Float w01017_Min;
    @Column(length=8,name="w01017_Avg")
    private Float w01017_Avg;
    @Column(length=8,name="w01017_Max")
    private Float w01017_Max;
    @Column(length=8,name="w01017_Flag")
    private String w01017_Flag;
    @Column(length=12,name="w01018_Cou")
    private Float w01018_Cou;  //化学需氧量 011 毫克/升 千克 N5.1
    @Column(length=8,name="w01018_Min")
    private Float w01018_Min;
    @Column(length=8,name="w01018_Avg")
    private Float w01018_Avg;
    @Column(length=8,name="w01018_Max")
    private Float w01018_Max;
    @Column(length=8,name="w01018_Flag")
    private String w01018_Flag;
    @Column(length=12,name="w01019_Cou")
    private Float w01019_Cou;  //高锰酸盐指数 -- 毫克/升 千克 N3.1
    @Column(length=8,name="w01019_Min")
    private Float w01019_Min;
    @Column(length=8,name="w01019_Avg")
    private Float w01019_Avg;
    @Column(length=8,name="w01019_Max")
    private Float w01019_Max;
    @Column(length=8,name="w01019_Flag")
    private String w01019_Flag;
    @Column(length=12,name="w01020_Cou")
    private Float w01020_Cou;  //总有机碳 015 毫克/升 千克 N3.1
    @Column(length=8,name="w01020_Min")
    private Float w01020_Min;
    @Column(length=8,name="w01020_Avg")
    private Float w01020_Avg;
    @Column(length=8,name="w01020_Max")
    private Float w01020_Max;
    @Column(length=8,name="w01020_Flag")
    private String w01020_Flag;
    @Column(length=12,name="w02003_Cou")
    private Float w02003_Cou;  //粪大肠菌群 550 个/升 N9
    @Column(length=8,name="w02003_Min")
    private Float w02003_Min;
    @Column(length=8,name="w02003_Avg")
    private Float w02003_Avg;
    @Column(length=8,name="w02003_Max")
    private Float w02003_Max;
    @Column(length=8,name="w02003_Flag")
    private String w02003_Flag;
    @Column(length=12,name="w02006_Cou")
    private Float w02006_Cou;  //细菌总数 -- 个/升 N9
    @Column(length=8,name="w02006_Min")
    private Float w02006_Min;
    @Column(length=8,name="w02006_Avg")
    private Float w02006_Avg;
    @Column(length=8,name="w02006_Max")
    private Float w02006_Max;
    @Column(length=8,name="w02006_Flag")
    private String w02006_Flag;
    @Column(length=12,name="w03001_Cou")
    private Float w03001_Cou;  //总 α 放射性 570 贝可[勒尔]/升 N3.1
    @Column(length=8,name="w03001_Min")
    private Float w03001_Min;
    @Column(length=8,name="w03001_Avg")
    private Float w03001_Avg;
    @Column(length=8,name="w03001_Max")
    private Float w03001_Max;
    @Column(length=8,name="w03001_Flag")
    private String w03001_Flag;
    @Column(length=12,name="w03002_Cou")
    private Float w03002_Cou;  //总 β 放射性 571 贝可[勒尔]/升 N3.1
    @Column(length=8,name="w03002_Min")
    private Float w03002_Min;
    @Column(length=8,name="w03002_Avg")
    private Float w03002_Avg;
    @Column(length=8,name="w03002_Max")
    private Float w03002_Max;
    @Column(length=8,name="w03002_Flag")
    private String w03002_Flag;
    @Column(length=12,name="w19001_Cou")
    private Float w19001_Cou;  //表面活性剂 -- 毫克/升 千克 N3.2
    @Column(length=8,name="w19001_Min")
    private Float w19001_Min;
    @Column(length=8,name="w19001_Avg")
    private Float w19001_Avg;
    @Column(length=8,name="w19001_Max")
    private Float w19001_Max;
    @Column(length=8,name="w19001_Flag")
    private String w19001_Flag;
    @Column(length=12,name="w19002_Cou")
    private Float w19002_Cou;  //阴离子表面活性剂 520 毫克/升 千克 N3.2
    @Column(length=8,name="w19002_Min")
    private Float w19002_Min;
    @Column(length=8,name="w19002_Avg")
    private Float w19002_Avg;
    @Column(length=8,name="w19002_Max")
    private Float w19002_Max;
    @Column(length=8,name="w19002_Flag")
    private String w19002_Flag;
    @Column(length=12,name="w20012_Cou")
    private Float w20012_Cou;  //钡 039 毫克/升 千克 N3.3
    @Column(length=8,name="w20012_Min")
    private Float w20012_Min;
    @Column(length=8,name="w20012_Avg")
    private Float w20012_Avg;
    @Column(length=8,name="w20012_Max")
    private Float w20012_Max;
    @Column(length=8,name="w20012_Flag")
    private String w20012_Flag;
    @Column(length=12,name="w20023_Cou")
    private Float w20023_Cou;  //硼 037 毫克/升 千克 N3.3
    @Column(length=8,name="w20023_Min")
    private Float w20023_Min;
    @Column(length=8,name="w20023_Avg")
    private Float w20023_Avg;
    @Column(length=8,name="w20023_Max")
    private Float w20023_Max;
    @Column(length=8,name="w20023_Flag")
    private String w20023_Flag;
    @Column(length=12,name="w20038_Cou")
    private Float w20038_Cou;  //钴 040 毫克/升 千克 N3.4
    @Column(length=8,name="w20038_Min")
    private Float w20038_Min;
    @Column(length=8,name="w20038_Avg")
    private Float w20038_Avg;
    @Column(length=8,name="w20038_Max")
    private Float w20038_Max;
    @Column(length=8,name="w20038_Flag")
    private String w20038_Flag;
    @Column(length=12,name="w20061_Cou")
    private Float w20061_Cou;  //钼 038 毫克/升 千克 N3.4
    @Column(length=8,name="w20061_Min")
    private Float w20061_Min;
    @Column(length=8,name="w20061_Avg")
    private Float w20061_Avg;
    @Column(length=8,name="w20061_Max")
    private Float w20061_Max;
    @Column(length=8,name="w20061_Flag")
    private String w20061_Flag;
    @Column(length=12,name="w20089_Cou")
    private Float w20089_Cou;  //铊 041 纳克/升 毫克 N4
    @Column(length=8,name="w20089_Min")
    private Float w20089_Min;
    @Column(length=8,name="w20089_Avg")
    private Float w20089_Avg;
    @Column(length=8,name="w20089_Max")
    private Float w20089_Max;
    @Column(length=8,name="w20089_Flag")
    private String w20089_Flag;
    @Column(length=12,name="w20092_Cou")
    private Float w20092_Cou;  //锡 036 毫克/升 千克 N3.1
    @Column(length=8,name="w20092_Min")
    private Float w20092_Min;
    @Column(length=8,name="w20092_Avg")
    private Float w20092_Avg;
    @Column(length=8,name="w20092_Max")
    private Float w20092_Max;
    @Column(length=8,name="w20092_Flag")
    private String w20092_Flag;
    @Column(length=12,name="w20111_Cou")
    private Float w20111_Cou;  //总汞 020 微克/升 克 N3.2
    @Column(length=8,name="w20111_Min")
    private Float w20111_Min;
    @Column(length=8,name="w20111_Avg")
    private Float w20111_Avg;
    @Column(length=8,name="w20111_Max")
    private Float w20111_Max;
    @Column(length=8,name="w20111_Flag")
    private String w20111_Flag;
    @Column(length=12,name="w20113_Cou")
    private Float w20113_Cou;  //烷基汞 021 纳克/升 毫克 N4
    @Column(length=8,name="w20113_Min")
    private Float w20113_Min;
    @Column(length=8,name="w20113_Avg")
    private Float w20113_Avg;
    @Column(length=8,name="w20113_Max")
    private Float w20113_Max;
    @Column(length=8,name="w20113_Flag")
    private String w20113_Flag;
    @Column(length=12,name="w20115_Cou")
    private Float w20115_Cou;  //总镉 022 微克/升 克 N3.1
    @Column(length=8,name="w20115_Min")
    private Float w20115_Min;
    @Column(length=8,name="w20115_Avg")
    private Float w20115_Avg;
    @Column(length=8,name="w20115_Max")
    private Float w20115_Max;
    @Column(length=8,name="w20115_Flag")
    private String w20115_Flag;
    @Column(length=12,name="w20116_Cou")
    private Float w20116_Cou;  //总铬 023 毫克/升 千克 N3.3
    @Column(length=8,name="w20116_Min")
    private Float w20116_Min;
    @Column(length=8,name="w20116_Avg")
    private Float w20116_Avg;
    @Column(length=8,name="w20116_Max")
    private Float w20116_Max;
    @Column(length=8,name="w20116_Flag")
    private String w20116_Flag;
    @Column(length=12,name="w20117_Cou")
    private Float w20117_Cou;  //六价铬 024 微克/升 克 N2.3
    @Column(length=8,name="w20117_Min")
    private Float w20117_Min;
    @Column(length=8,name="w20117_Avg")
    private Float w20117_Avg;
    @Column(length=8,name="w20117_Max")
    private Float w20117_Max;
    @Column(length=8,name="w20117_Flag")
    private String w20117_Flag;
    @Column(length=12,name="w20119_Cou")
    private Float w20119_Cou;  //总砷 026 微克/升 克 N2.3
    @Column(length=8,name="w20119_Min")
    private Float w20119_Min;
    @Column(length=8,name="w20119_Avg")
    private Float w20119_Avg;
    @Column(length=8,name="w20119_Max")
    private Float w20119_Max;
    @Column(length=8,name="w20119_Flag")
    private String w20119_Flag;
    @Column(length=12,name="w20120_Cou")
    private Float w20120_Cou;  //总铅 027 微克/升 克 N4
    @Column(length=8,name="w20120_Min")
    private Float w20120_Min;
    @Column(length=8,name="w20120_Avg")
    private Float w20120_Avg;
    @Column(length=8,name="w20120_Max")
    private Float w20120_Max;
    @Column(length=8,name="w20120_Flag")
    private String w20120_Flag;
    @Column(length=12,name="w20121_Cou")
    private Float w20121_Cou;  //总镍 028 毫克/升 千克 N3.2
    @Column(length=8,name="w20121_Min")
    private Float w20121_Min;
    @Column(length=8,name="w20121_Avg")
    private Float w20121_Avg;
    @Column(length=8,name="w20121_Max")
    private Float w20121_Max;
    @Column(length=8,name="w20121_Flag")
    private String w20121_Flag;
    @Column(length=12,name="w20122_Cou")
    private Float w20122_Cou;  //总铜 029 毫克/升 千克 N3.2
    @Column(length=8,name="w20122_Min")
    private Float w20122_Min;
    @Column(length=8,name="w20122_Avg")
    private Float w20122_Avg;
    @Column(length=8,name="w20122_Max")
    private Float w20122_Max;
    @Column(length=8,name="w20122_Flag")
    private String w20122_Flag;
    @Column(length=12,name="w20123_Cou")
    private Float w20123_Cou;  //总锌 030 毫克/升 千克 N3.3
    @Column(length=8,name="w20123_Min")
    private Float w20123_Min;
    @Column(length=8,name="w20123_Avg")
    private Float w20123_Avg;
    @Column(length=8,name="w20123_Max")
    private Float w20123_Max;
    @Column(length=8,name="w20123_Flag")
    private String w20123_Flag;
    @Column(length=12,name="w20124_Cou")
    private Float w20124_Cou;  //总锰 031 毫克/升 千克 N3.3
    @Column(length=8,name="w20124_Min")
    private Float w20124_Min;
    @Column(length=8,name="w20124_Avg")
    private Float w20124_Avg;
    @Column(length=8,name="w20124_Max")
    private Float w20124_Max;
    @Column(length=8,name="w20124_Flag")
    private String w20124_Flag;
    @Column(length=12,name="w20125_Cou")
    private Float w20125_Cou;  //总铁 032 毫克/升 千克 N3.3
    @Column(length=8,name="w20125_Min")
    private Float w20125_Min;
    @Column(length=8,name="w20125_Avg")
    private Float w20125_Avg;
    @Column(length=8,name="w20125_Max")
    private Float w20125_Max;
    @Column(length=8,name="w20125_Flag")
    private String w20125_Flag;
    @Column(length=12,name="w20126_Cou")
    private Float w20126_Cou;  //总银 033 毫克/升 千克 N3.3
    @Column(length=8,name="w20126_Min")
    private Float w20126_Min;
    @Column(length=8,name="w20126_Avg")
    private Float w20126_Avg;
    @Column(length=8,name="w20126_Max")
    private Float w20126_Max;
    @Column(length=8,name="w20126_Flag")
    private String w20126_Flag;
    @Column(length=12,name="w20127_Cou")
    private Float w20127_Cou;  //总铍 034 微克/升 克 N3.3
    @Column(length=8,name="w20127_Min")
    private Float w20127_Min;
    @Column(length=8,name="w20127_Avg")
    private Float w20127_Avg;
    @Column(length=8,name="w20127_Max")
    private Float w20127_Max;
    @Column(length=8,name="w20127_Flag")
    private String w20127_Flag;
    @Column(length=12,name="w20128_Cou")
    private Float w20128_Cou;  //总硒 035 微克/升 克 N4.2
    @Column(length=8,name="w20128_Min")
    private Float w20128_Min;
    @Column(length=8,name="w20128_Avg")
    private Float w20128_Avg;
    @Column(length=8,name="w20128_Max")
    private Float w20128_Max;
    @Column(length=8,name="w20128_Flag")
    private String w20128_Flag;
    @Column(length=12,name="w20138_Cou")
    private Float w20138_Cou;  //铜 毫克/升 千克 N3.3
    @Column(length=8,name="w20138_Min")
    private Float w20138_Min;
    @Column(length=8,name="w20138_Avg")
    private Float w20138_Avg;
    @Column(length=8,name="w20138_Max")
    private Float w20138_Max;
    @Column(length=8,name="w20138_Flag")
    private String w20138_Flag;
    @Column(length=12,name="w20139_Cou")
    private Float w20139_Cou;  //锌 毫克/升 千克 N3.3
    @Column(length=8,name="w20139_Min")
    private Float w20139_Min;
    @Column(length=8,name="w20139_Avg")
    private Float w20139_Avg;
    @Column(length=8,name="w20139_Max")
    private Float w20139_Max;
    @Column(length=8,name="w20139_Flag")
    private String w20139_Flag;
    @Column(length=12,name="w20140_Cou")
    private Float w20140_Cou;  //硒 毫克/升 千克 N3.3
    @Column(length=8,name="w20140_Min")
    private Float w20140_Min;
    @Column(length=8,name="w20140_Avg")
    private Float w20140_Avg;
    @Column(length=8,name="w20140_Max")
    private Float w20140_Max;
    @Column(length=8,name="w20140_Flag")
    private String w20140_Flag;
    @Column(length=12,name="w20141_Cou")
    private Float w20141_Cou;  //砷 毫克/升 千克 N3.3
    @Column(length=8,name="w20141_Min")
    private Float w20141_Min;
    @Column(length=8,name="w20141_Avg")
    private Float w20141_Avg;
    @Column(length=8,name="w20141_Max")
    private Float w20141_Max;
    @Column(length=8,name="w20141_Flag")
    private String w20141_Flag;
    @Column(length=12,name="w20142_Cou")
    private Float w20142_Cou;  //汞 微克/升 克 N3.3
    @Column(length=8,name="w20142_Min")
    private Float w20142_Min;
    @Column(length=8,name="w20142_Avg")
    private Float w20142_Avg;
    @Column(length=8,name="w20142_Max")
    private Float w20142_Max;
    @Column(length=8,name="w20142_Flag")
    private String w20142_Flag;
    @Column(length=12,name="w20143_Cou")
    private Float w20143_Cou;  //镉 微克/升 克 N3.3
    @Column(length=8,name="w20143_Min")
    private Float w20143_Min;
    @Column(length=8,name="w20143_Avg")
    private Float w20143_Avg;
    @Column(length=8,name="w20143_Max")
    private Float w20143_Max;
    @Column(length=8,name="w20143_Flag")
    private String w20143_Flag;
    @Column(length=12,name="w20144_Cou")
    private Float w20144_Cou;  //铅 毫克/升 千克 N3.3
    @Column(length=8,name="w20144_Min")
    private Float w20144_Min;
    @Column(length=8,name="w20144_Avg")
    private Float w20144_Avg;
    @Column(length=8,name="w20144_Max")
    private Float w20144_Max;
    @Column(length=8,name="w20144_Flag")
    private String w20144_Flag;
    @Column(length=12,name="w21001_Cou")
    private Float w21001_Cou;  //总氮 065 毫克/升 千克 N4.2
    @Column(length=8,name="w21001_Min")
    private Float w21001_Min;
    @Column(length=8,name="w21001_Avg")
    private Float w21001_Avg;
    @Column(length=8,name="w21001_Max")
    private Float w21001_Max;
    @Column(length=8,name="w21001_Flag")
    private String w21001_Flag;
    @Column(length=12,name="w21003_Cou")
    private Float w21003_Cou;  //氨氮 060 毫克/升 千克 N4.2
    @Column(length=8,name="w21003_Min")
    private Float w21003_Min;
    @Column(length=8,name="w21003_Avg")
    private Float w21003_Avg;
    @Column(length=8,name="w21003_Max")
    private Float w21003_Max;
    @Column(length=8,name="w21003_Flag")
    private String w21003_Flag;
    @Column(length=12,name="w21004_Cou")
    private Float w21004_Cou;  //凯氏氮 062 毫克/升 千克 N3.1
    @Column(length=8,name="w21004_Min")
    private Float w21004_Min;
    @Column(length=8,name="w21004_Avg")
    private Float w21004_Avg;
    @Column(length=8,name="w21004_Max")
    private Float w21004_Max;
    @Column(length=8,name="w21004_Flag")
    private String w21004_Flag;
    @Column(length=12,name="w21006_Cou")
    private Float w21006_Cou;  //亚硝酸盐 063 毫克/升 千克 N2.3
    @Column(length=8,name="w21006_Min")
    private Float w21006_Min;
    @Column(length=8,name="w21006_Avg")
    private Float w21006_Avg;
    @Column(length=8,name="w21006_Max")
    private Float w21006_Max;
    @Column(length=8,name="w21006_Flag")
    private String w21006_Flag;
    @Column(length=12,name="w21007_Cou")
    private Float w21007_Cou;  //硝酸盐 064 毫克/升 千克 N2.3
    @Column(length=8,name="w21007_Min")
    private Float w21007_Min;
    @Column(length=8,name="w21007_Avg")
    private Float w21007_Avg;
    @Column(length=8,name="w21007_Max")
    private Float w21007_Max;
    @Column(length=8,name="w21007_Flag")
    private String w21007_Flag;
    @Column(length=12,name="w21011_Cou")
    private Float w21011_Cou;  //总磷 101 毫克/升 千克 N3.2
    @Column(length=8,name="w21011_Min")
    private Float w21011_Min;
    @Column(length=8,name="w21011_Avg")
    private Float w21011_Avg;
    @Column(length=8,name="w21011_Max")
    private Float w21011_Max;
    @Column(length=8,name="w21011_Flag")
    private String w21011_Flag;
    @Column(length=12,name="w21016_Cou")
    private Float w21016_Cou;  //氰化物 070 毫克/升 千克 N3.3
    @Column(length=8,name="w21016_Min")
    private Float w21016_Min;
    @Column(length=8,name="w21016_Avg")
    private Float w21016_Avg;
    @Column(length=8,name="w21016_Max")
    private Float w21016_Max;
    @Column(length=8,name="w21016_Flag")
    private String w21016_Flag;
    @Column(length=12,name="w21017_Cou")
    private Float w21017_Cou;  //氟化物 072 毫克/升 千克 N4.2
    @Column(length=8,name="w21017_Min")
    private Float w21017_Min;
    @Column(length=8,name="w21017_Avg")
    private Float w21017_Avg;
    @Column(length=8,name="w21017_Max")
    private Float w21017_Max;
    @Column(length=8,name="w21017_Flag")
    private String w21017_Flag;
    @Column(length=12,name="w21019_Cou")
    private Float w21019_Cou;  //硫化物 071 毫克/升 千克 N3.3
    @Column(length=8,name="w21019_Min")
    private Float w21019_Min;
    @Column(length=8,name="w21019_Avg")
    private Float w21019_Avg;
    @Column(length=8,name="w21019_Max")
    private Float w21019_Max;
    @Column(length=8,name="w21019_Flag")
    private String w21019_Flag;
    @Column(length=12,name="w21022_Cou")
    private Float w21022_Cou;  //氯化物 090 毫克/升 千克 N3.1
    @Column(length=8,name="w21022_Min")
    private Float w21022_Min;
    @Column(length=8,name="w21022_Avg")
    private Float w21022_Avg;
    @Column(length=8,name="w21022_Max")
    private Float w21022_Max;
    @Column(length=8,name="w21022_Flag")
    private String w21022_Flag;
    @Column(length=12,name="w21038_Cou")
    private Float w21038_Cou;  //硫酸盐 -- 毫克/升 千克 N6
    @Column(length=8,name="w21038_Min")
    private Float w21038_Min;
    @Column(length=8,name="w21038_Avg")
    private Float w21038_Avg;
    @Column(length=8,name="w21038_Max")
    private Float w21038_Max;
    @Column(length=8,name="w21038_Flag")
    private String w21038_Flag;
    @Column(length=12,name="w22001_Cou")
    private Float w22001_Cou;  //石油类 080 毫克/升 千克 N3.2
    @Column(length=8,name="w22001_Min")
    private Float w22001_Min;
    @Column(length=8,name="w22001_Avg")
    private Float w22001_Avg;
    @Column(length=8,name="w22001_Max")
    private Float w22001_Max;
    @Column(length=8,name="w22001_Flag")
    private String w22001_Flag;
    @Column(length=12,name="w23002_Cou")
    private Float w23002_Cou;  //挥发酚 110 毫克/升 千克 N3.4
    @Column(length=8,name="w23002_Min")
    private Float w23002_Min;
    @Column(length=8,name="w23002_Avg")
    private Float w23002_Avg;
    @Column(length=8,name="w23002_Max")
    private Float w23002_Max;
    @Column(length=8,name="w23002_Flag")
    private String w23002_Flag;
    @Column(length=12,name="w25043_Cou")
    private Float w25043_Cou;  //苯并[α]芘 540 微克/升 克 N3.1
    @Column(length=8,name="w25043_Min")
    private Float w25043_Min;
    @Column(length=8,name="w25043_Avg")
    private Float w25043_Avg;
    @Column(length=8,name="w25043_Max")
    private Float w25043_Max;
    @Column(length=8,name="w25043_Flag")
    private String w25043_Flag;
    @Column(length=12,name="w33001_Cou")
    private Float w33001_Cou;  //六六六 350 纳克/升 毫克 N4
    @Column(length=8,name="w33001_Min")
    private Float w33001_Min;
    @Column(length=8,name="w33001_Avg")
    private Float w33001_Avg;
    @Column(length=8,name="w33001_Max")
    private Float w33001_Max;
    @Column(length=8,name="w33001_Flag")
    private String w33001_Flag;
    @Column(length=12,name="w33007_Cou")
    private Float w33007_Cou;  //滴滴涕 351 纳克/升 毫克 N4
    @Column(length=8,name="w33007_Min")
    private Float w33007_Min;
    @Column(length=8,name="w33007_Avg")
    private Float w33007_Avg;
    @Column(length=8,name="w33007_Max")
    private Float w33007_Max;
    @Column(length=8,name="w33007_Flag")
    private String w33007_Flag;
    @Column(length=12,name="w99001_Cou")
    private Float w99001_Cou;  //有机氮 061 毫克/升 千克 N3.1
    @Column(length=8,name="w99001_Min")
    private Float w99001_Min;
    @Column(length=8,name="w99001_Avg")
    private Float w99001_Avg;
    @Column(length=8,name="w99001_Max")
    private Float w99001_Max;
    @Column(length=8,name="w99001_Flag")
    private String w99001_Flag;

}
