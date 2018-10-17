package com.jqmkj.WaterResponseSystem.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "deviceinfo_realtime_data",
        indexes = {  @Index(name = "my_index_DataTime", columnList="data_time",     unique = false)})
public class DeviceInfoRealTimeData extends BaseDataEntity{

    @Column(name="monitorsite_id")
    private Long monitorsiteId;//监测站ID
    @Column(length=16,name="data_time")
    private String dataTime;
    @Column(length=8,name="w00000_rtd")
    private String w00000Rtd;  //污水 B01 升/秒 立方米 N5.2
    @Column(length=8,name="w00000_flag")
    private String w00000Flag;
    @Column(length=8,name="w01001_rtd")
    private String w01001Rtd;  //pH 值 001 无量纲 N2.2
    @Column(length=8,name="w01001_flag")
    private String w01001Flag;
    @Column(length=8,name="w01002_rtd")
    private String w01002Rtd;  //色度 002 [色]度 N3.2
    @Column(length=8,name="w01002_flag")
    private String w01002Flag;
    @Column(length=8,name="w01006_rtd")
    private String w01006Rtd;  //溶解性总固体 -- 毫克/升 千克 N4
    @Column(length=8,name="w01006_flag")
    private String w01006Flag;
    @Column(length=8,name="w01009_rtd")
    private String w01009Rtd;  //溶解氧 -- 毫克/升 N3.1
    @Column(length=8,name="w01009_flag")
    private String w01009Flag;
    @Column(length=8,name="w01010_rtd")
    private String w01010Rtd;  //水温 -- 摄氏度 N3.1
    @Column(length=8,name="w01010_flag")
    private String w01010Flag;
    @Column(length=8,name="w01012_rtd")
    private String w01012Rtd;  //悬浮物 003 毫克/升 千克 N4
    @Column(length=8,name="w01012_flag")
    private String w01012Flag;
    @Column(length=8,name="w01014_rtd")
    private String w01014Rtd;  //电导率 -- 微西[门子]/厘米 N3.1
    @Column(length=8,name="w01014_flag")
    private String w01014Flag;
    @Column(length=8,name="w01017_rtd")
    private String w01017Rtd;  //五日生化需氧量 010 毫克/升 千克 N5.1
    @Column(length=8,name="w01017_flag")
    private String w01017Flag;
    @Column(length=8,name="w01018_rtd")
    private String w01018Rtd;  //化学需氧量 011 毫克/升 千克 N5.1
    @Column(length=8,name="w01018_flag")
    private String w01018Flag;
    @Column(length=8,name="w01019_rtd")
    private String w01019Rtd;  //高锰酸盐指数 -- 毫克/升 千克 N3.1
    @Column(length=8,name="w01019_flag")
    private String w01019Flag;
    @Column(length=8,name="w01020_rtd")
    private String w01020Rtd;  //总有机碳 015 毫克/升 千克 N3.1
    @Column(length=8,name="w01020_flag")
    private String w01020Flag;
    @Column(length=8,name="w02003_rtd")
    private String w02003Rtd;  //粪大肠菌群 550 个/升 N9
    @Column(length=8,name="w02003_flag")
    private String w02003Flag;
    @Column(length=8,name="w02006_rtd")
    private String w02006Rtd;  //细菌总数 -- 个/升 N9
    @Column(length=8,name="w02006_flag")
    private String w02006Flag;
    @Column(length=8,name="w03001_rtd")
    private String w03001Rtd;  //总 α 放射性 570 贝可[勒尔]/升 N3.1
    @Column(length=8,name="w03001_flag")
    private String w03001Flag;
    @Column(length=8,name="w03002_rtd")
    private String w03002Rtd;  //总 β 放射性 571 贝可[勒尔]/升 N3.1
    @Column(length=8,name="w03002_flag")
    private String w03002Flag;
    @Column(length=8,name="w19001_rtd")
    private String w19001Rtd;  //表面活性剂 -- 毫克/升 千克 N3.2
    @Column(length=8,name="w19001_flag")
    private String w19001Flag;
    @Column(length=8,name="w19002_rtd")
    private String w19002Rtd;  //阴离子表面活性剂 520 毫克/升 千克 N3.2
    @Column(length=8,name="w19002_flag")
    private String w19002Flag;
    @Column(length=8,name="w20012_rtd")
    private String w20012Rtd;  //钡 039 毫克/升 千克 N3.3
    @Column(length=8,name="w20012_flag")
    private String w20012Flag;
    @Column(length=8,name="w20023_rtd")
    private String w20023Rtd;  //硼 037 毫克/升 千克 N3.3
    @Column(length=8,name="w20023_flag")
    private String w20023Flag;
    @Column(length=8,name="w20038_rtd")
    private String w20038Rtd;  //钴 040 毫克/升 千克 N3.4
    @Column(length=8,name="w20038_flag")
    private String w20038Flag;
    @Column(length=8,name="w20061_rtd")
    private String w20061Rtd;  //钼 038 毫克/升 千克 N3.4
    @Column(length=8,name="w20061_flag")
    private String w20061Flag;
    @Column(length=8,name="w20089_rtd")
    private String w20089Rtd;  //铊 041 纳克/升 毫克 N4
    @Column(length=8,name="w20089_flag")
    private String w20089Flag;
    @Column(length=8,name="w20092_rtd")
    private String w20092Rtd;  //锡 036 毫克/升 千克 N3.1
    @Column(length=8,name="w20092_flag")
    private String w20092Flag;
    @Column(length=8,name="w20111_rtd")
    private String w20111Rtd;  //总汞 020 微克/升 克 N3.2
    @Column(length=8,name="w20111_flag")
    private String w20111Flag;
    @Column(length=8,name="w20113_rtd")
    private String w20113Rtd;  //烷基汞 021 纳克/升 毫克 N4
    @Column(length=8,name="w20113_flag")
    private String w20113Flag;
    @Column(length=8,name="w20115_rtd")
    private String w20115Rtd;  //总镉 022 微克/升 克 N3.1
    @Column(length=8,name="w20115_flag")
    private String w20115Flag;
    @Column(length=8,name="w20116_rtd")
    private String w20116Rtd;  //总铬 023 毫克/升 千克 N3.3
    @Column(length=8,name="w20116_flag")
    private String w20116Flag;
    @Column(length=8,name="w20117_rtd")
    private String w20117Rtd;  //六价铬 024 微克/升 克 N2.3
    @Column(length=8,name="w20117_flag")
    private String w20117Flag;
    @Column(length=8,name="w20119_rtd")
    private String w20119Rtd;  //总砷 026 微克/升 克 N2.3
    @Column(length=8,name="w20119_flag")
    private String w20119Flag;
    @Column(length=8,name="w20120_rtd")
    private String w20120Rtd;  //总铅 027 微克/升 克 N4
    @Column(length=8,name="w20120_flag")
    private String w20120Flag;
    @Column(length=8,name="w20121_rtd")
    private String w20121Rtd;  //总镍 028 毫克/升 千克 N3.2
    @Column(length=8,name="w20121_flag")
    private String w20121Flag;
    @Column(length=8,name="w20122_rtd")
    private String w20122Rtd;  //总铜 029 毫克/升 千克 N3.2
    @Column(length=8,name="w20122_flag")
    private String w20122Flag;
    @Column(length=8,name="w20123_rtd")
    private String w20123Rtd;  //总锌 030 毫克/升 千克 N3.3
    @Column(length=8,name="w20123_flag")
    private String w20123Flag;
    @Column(length=8,name="w20124_rtd")
    private String w20124Rtd;  //总锰 031 毫克/升 千克 N3.3
    @Column(length=8,name="w20124_flag")
    private String w20124Flag;
    @Column(length=8,name="w20125_rtd")
    private String w20125Rtd;  //总铁 032 毫克/升 千克 N3.3
    @Column(length=8,name="w20125_flag")
    private String w20125Flag;
    @Column(length=8,name="w20126_rtd")
    private String w20126Rtd;  //总银 033 毫克/升 千克 N3.3
    @Column(length=8,name="w20126_flag")
    private String w20126Flag;
    @Column(length=8,name="w20127_rtd")
    private String w20127Rtd;  //总铍 034 微克/升 克 N3.3
    @Column(length=8,name="w20127_flag")
    private String w20127Flag;
    @Column(length=8,name="w20128_rtd")
    private String w20128Rtd;  //总硒 035 微克/升 克 N4.2
    @Column(length=8,name="w20128_flag")
    private String w20128Flag;
    @Column(length=8,name="w20138_rtd")
    private String w20138Rtd;  //铜 毫克/升 千克 N3.3
    @Column(length=8,name="w20138_flag")
    private String w20138Flag;
    @Column(length=8,name="w20139_rtd")
    private String w20139Rtd;  //锌 毫克/升 千克 N3.3
    @Column(length=8,name="w20139_flag")
    private String w20139Flag;
    @Column(length=8,name="w20140_rtd")
    private String w20140Rtd;  //硒 毫克/升 千克 N3.3
    @Column(length=8,name="w20140_flag")
    private String w20140Flag;
    @Column(length=8,name="w20141_rtd")
    private String w20141Rtd;  //砷 毫克/升 千克 N3.3
    @Column(length=8,name="w20141_flag")
    private String w20141Flag;
    @Column(length=8,name="w20142_rtd")
    private String w20142Rtd;  //汞 微克/升 克 N3.3
    @Column(length=8,name="w20142_flag")
    private String w20142Flag;
    @Column(length=8,name="w20143_rtd")
    private String w20143Rtd;  //镉 微克/升 克 N3.3
    @Column(length=8,name="w20143_flag")
    private String w20143Flag;
    @Column(length=8,name="w20144_rtd")
    private String w20144Rtd;  //铅 毫克/升 千克 N3.3
    @Column(length=8,name="w20144_flag")
    private String w20144Flag;
    @Column(length=8,name="w21001_rtd")
    private String w21001Rtd;  //总氮 065 毫克/升 千克 N4.2
    @Column(length=8,name="w21001_flag")
    private String w21001Flag;
    @Column(length=8,name="w21003_rtd")
    private String w21003Rtd;  //氨氮 060 毫克/升 千克 N4.2
    @Column(length=8,name="w21003_flag")
    private String w21003Flag;
    @Column(length=8,name="w21004_rtd")
    private String w21004Rtd;  //凯氏氮 062 毫克/升 千克 N3.1
    @Column(length=8,name="w21004_flag")
    private String w21004Flag;
    @Column(length=8,name="w21006_rtd")
    private String w21006Rtd;  //亚硝酸盐 063 毫克/升 千克 N2.3
    @Column(length=8,name="w21006_flag")
    private String w21006Flag;
    @Column(length=8,name="w21007_rtd")
    private String w21007Rtd;  //硝酸盐 064 毫克/升 千克 N2.3
    @Column(length=8,name="w21007_flag")
    private String w21007Flag;
    @Column(length=8,name="w21011_rtd")
    private String w21011Rtd;  //总磷 101 毫克/升 千克 N3.2
    @Column(length=8,name="w21011_flag")
    private String w21011Flag;
    @Column(length=8,name="w21016_rtd")
    private String w21016Rtd;  //氰化物 070 毫克/升 千克 N3.3
    @Column(length=8,name="w21016_flag")
    private String w21016Flag;
    @Column(length=8,name="w21017_rtd")
    private String w21017Rtd;  //氟化物 072 毫克/升 千克 N4.2
    @Column(length=8,name="w21017_flag")
    private String w21017Flag;
    @Column(length=8,name="w21019_rtd")
    private String w21019Rtd;  //硫化物 071 毫克/升 千克 N3.3
    @Column(length=8,name="w21019_flag")
    private String w21019Flag;
    @Column(length=8,name="w21022_rtd")
    private String w21022Rtd;  //氯化物 090 毫克/升 千克 N3.1
    @Column(length=8,name="w21022_flag")
    private String w21022Flag;
    @Column(length=8,name="w21038_rtd")
    private String w21038Rtd;  //硫酸盐 -- 毫克/升 千克 N6
    @Column(length=8,name="w21038_flag")
    private String w21038Flag;
    @Column(length=8,name="w22001_rtd")
    private String w22001Rtd;  //石油类 080 毫克/升 千克 N3.2
    @Column(length=8,name="w22001_flag")
    private String w22001Flag;
    @Column(length=8,name="w23002_rtd")
    private String w23002Rtd;  //挥发酚 110 毫克/升 千克 N3.4
    @Column(length=8,name="w23002_flag")
    private String w23002Flag;
    @Column(length=8,name="w25043_rtd")
    private String w25043Rtd;  //苯并[α]芘 540 微克/升 克 N3.1
    @Column(length=8,name="w25043_flag")
    private String w25043Flag;
    @Column(length=8,name="w33001_rtd")
    private String w33001Rtd;  //六六六 350 纳克/升 毫克 N4
    @Column(length=8,name="w33001_flag")
    private String w33001Flag;
    @Column(length=8,name="w33007_rtd")
    private String w33007Rtd;  //滴滴涕 351 纳克/升 毫克 N4
    @Column(length=8,name="w33007_flag")
    private String w33007Flag;
    @Column(length=8,name="w99001_rtd")
    private String w99001Rtd;  //有机氮 061 毫克/升 千克 N3.1
    @Column(length=8,name="w99001_flag")
    private String w99001Flag;
    
}
