package com.jqmkj.WaterResponseSystem.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "deviceinfo_minute_data",
        indexes = {  @Index(name = "my_index_DataTime", columnList="data_time",     unique = false)})
public class DeviceinfoMinuteData extends BaseDataEntity{

    @Column(name="monitorsite_id")
    private Long monitorsiteId;//监测站ID
    @Column(name="data_time")
    private String DataTime;
    @Column(length=12,name="w00000_cou")
    private String w00000Cou;  //污水 B01 升/秒 立方米 N5.2
    @Column(length=8,name="w00000_min")
    private String w00000Min;
    @Column(length=8,name="w00000_avg")
    private String w00000Avg;
    @Column(length=8,name="w00000_max")
    private String w00000Max;
    @Column(length=8,name="w00000_flag")
    private String w00000Flag;
    @Column(length=12,name="w01001_cou")
    private String w01001Cou;  //pH 值 001 无量纲 N2.2
    @Column(length=8,name="w01001_min")
    private String w01001Min;
    @Column(length=8,name="w01001_avg")
    private String w01001Avg;
    @Column(length=8,name="w01001_max")
    private String w01001Max;
    @Column(length=8,name="w01001_flag")
    private String w01001Flag;
    @Column(length=12,name="w01002_cou")
    private String w01002Cou;  //色度 002 [色]度 N3.2
    @Column(length=8,name="w01002_min")
    private String w01002Min;
    @Column(length=8,name="w01002_avg")
    private String w01002Avg;
    @Column(length=8,name="w01002_max")
    private String w01002Max;
    @Column(length=8,name="w01002_flag")
    private String w01002Flag;
    @Column(length=12,name="w01006_cou")
    private String w01006Cou;  //溶解性总固体 -- 毫克/升 千克 N4
    @Column(length=8,name="w01006_min")
    private String w01006Min;
    @Column(length=8,name="w01006_avg")
    private String w01006Avg;
    @Column(length=8,name="w01006_max")
    private String w01006Max;
    @Column(length=8,name="w01006_flag")
    private String w01006Flag;
    @Column(length=12,name="w01009_cou")
    private String w01009Cou;  //溶解氧 -- 毫克/升 N3.1
    @Column(length=8,name="w01009_min")
    private String w01009Min;
    @Column(length=8,name="w01009_avg")
    private String w01009Avg;
    @Column(length=8,name="w01009_max")
    private String w01009Max;
    @Column(length=8,name="w01009_flag")
    private String w01009Flag;
    @Column(length=12,name="w01010_cou")
    private String w01010Cou;  //水温 -- 摄氏度 N3.1
    @Column(length=8,name="w01010_min")
    private String w01010Min;
    @Column(length=8,name="w01010_avg")
    private String w01010Avg;
    @Column(length=8,name="w01010_max")
    private String w01010Max;
    @Column(length=8,name="w01010_flag")
    private String w01010Flag;
    @Column(length=12,name="w01012_cou")
    private String w01012Cou;  //悬浮物 003 毫克/升 千克 N4
    @Column(length=8,name="w01012_min")
    private String w01012Min;
    @Column(length=8,name="w01012_avg")
    private String w01012Avg;
    @Column(length=8,name="w01012_max")
    private String w01012Max;
    @Column(length=8,name="w01012_flag")
    private String w01012Flag;
    @Column(length=12,name="w01014_cou")
    private String w01014Cou;  //电导率 -- 微西[门子]/厘米 N3.1
    @Column(length=8,name="w01014_min")
    private String w01014Min;
    @Column(length=8,name="w01014_avg")
    private String w01014Avg;
    @Column(length=8,name="w01014_max")
    private String w01014Max;
    @Column(length=8,name="w01014_flag")
    private String w01014Flag;
    @Column(length=12,name="w01017_cou")
    private String w01017Cou;  //五日生化需氧量 010 毫克/升 千克 N5.1
    @Column(length=8,name="w01017_min")
    private String w01017Min;
    @Column(length=8,name="w01017_avg")
    private String w01017Avg;
    @Column(length=8,name="w01017_max")
    private String w01017Max;
    @Column(length=8,name="w01017_flag")
    private String w01017Flag;
    @Column(length=12,name="w01018_cou")
    private String w01018Cou;  //化学需氧量 011 毫克/升 千克 N5.1
    @Column(length=8,name="w01018_min")
    private String w01018Min;
    @Column(length=8,name="w01018_avg")
    private String w01018Avg;
    @Column(length=8,name="w01018_max")
    private String w01018Max;
    @Column(length=8,name="w01018_flag")
    private String w01018Flag;
    @Column(length=12,name="w01019_cou")
    private String w01019Cou;  //高锰酸盐指数 -- 毫克/升 千克 N3.1
    @Column(length=8,name="w01019_min")
    private String w01019Min;
    @Column(length=8,name="w01019_avg")
    private String w01019Avg;
    @Column(length=8,name="w01019_max")
    private String w01019Max;
    @Column(length=8,name="w01019_flag")
    private String w01019Flag;
    @Column(length=12,name="w01020_cou")
    private String w01020Cou;  //总有机碳 015 毫克/升 千克 N3.1
    @Column(length=8,name="w01020_min")
    private String w01020Min;
    @Column(length=8,name="w01020_avg")
    private String w01020Avg;
    @Column(length=8,name="w01020_max")
    private String w01020Max;
    @Column(length=8,name="w01020_flag")
    private String w01020Flag;
    @Column(length=12,name="w02003_cou")
    private String w02003Cou;  //粪大肠菌群 550 个/升 N9
    @Column(length=8,name="w02003_min")
    private String w02003Min;
    @Column(length=8,name="w02003_avg")
    private String w02003Avg;
    @Column(length=8,name="w02003_max")
    private String w02003Max;
    @Column(length=8,name="w02003_flag")
    private String w02003Flag;
    @Column(length=12,name="w02006_cou")
    private String w02006Cou;  //细菌总数 -- 个/升 N9
    @Column(length=8,name="w02006_min")
    private String w02006Min;
    @Column(length=8,name="w02006_avg")
    private String w02006Avg;
    @Column(length=8,name="w02006_max")
    private String w02006Max;
    @Column(length=8,name="w02006_flag")
    private String w02006Flag;
    @Column(length=12,name="w03001_cou")
    private String w03001Cou;  //总 α 放射性 570 贝可[勒尔]/升 N3.1
    @Column(length=8,name="w03001_min")
    private String w03001Min;
    @Column(length=8,name="w03001_avg")
    private String w03001Avg;
    @Column(length=8,name="w03001_max")
    private String w03001Max;
    @Column(length=8,name="w03001_flag")
    private String w03001Flag;
    @Column(length=12,name="w03002_cou")
    private String w03002Cou;  //总 β 放射性 571 贝可[勒尔]/升 N3.1
    @Column(length=8,name="w03002_min")
    private String w03002Min;
    @Column(length=8,name="w03002_avg")
    private String w03002Avg;
    @Column(length=8,name="w03002_max")
    private String w03002Max;
    @Column(length=8,name="w03002_flag")
    private String w03002Flag;
    @Column(length=12,name="w19001_cou")
    private String w19001Cou;  //表面活性剂 -- 毫克/升 千克 N3.2
    @Column(length=8,name="w19001_min")
    private String w19001Min;
    @Column(length=8,name="w19001_avg")
    private String w19001Avg;
    @Column(length=8,name="w19001_max")
    private String w19001Max;
    @Column(length=8,name="w19001_flag")
    private String w19001Flag;
    @Column(length=12,name="w19002_cou")
    private String w19002Cou;  //阴离子表面活性剂 520 毫克/升 千克 N3.2
    @Column(length=8,name="w19002_min")
    private String w19002Min;
    @Column(length=8,name="w19002_avg")
    private String w19002Avg;
    @Column(length=8,name="w19002_max")
    private String w19002Max;
    @Column(length=8,name="w19002_flag")
    private String w19002Flag;
    @Column(length=12,name="w20012_cou")
    private String w20012Cou;  //钡 039 毫克/升 千克 N3.3
    @Column(length=8,name="w20012_min")
    private String w20012Min;
    @Column(length=8,name="w20012_avg")
    private String w20012Avg;
    @Column(length=8,name="w20012_max")
    private String w20012Max;
    @Column(length=8,name="w20012_flag")
    private String w20012Flag;
    @Column(length=12,name="w20023_cou")
    private String w20023Cou;  //硼 037 毫克/升 千克 N3.3
    @Column(length=8,name="w20023_min")
    private String w20023Min;
    @Column(length=8,name="w20023_avg")
    private String w20023Avg;
    @Column(length=8,name="w20023_max")
    private String w20023Max;
    @Column(length=8,name="w20023_flag")
    private String w20023Flag;
    @Column(length=12,name="w20038_cou")
    private String w20038Cou;  //钴 040 毫克/升 千克 N3.4
    @Column(length=8,name="w20038_min")
    private String w20038Min;
    @Column(length=8,name="w20038_avg")
    private String w20038Avg;
    @Column(length=8,name="w20038_max")
    private String w20038Max;
    @Column(length=8,name="w20038_flag")
    private String w20038Flag;
    @Column(length=12,name="w20061_cou")
    private String w20061Cou;  //钼 038 毫克/升 千克 N3.4
    @Column(length=8,name="w20061_min")
    private String w20061Min;
    @Column(length=8,name="w20061_avg")
    private String w20061Avg;
    @Column(length=8,name="w20061_max")
    private String w20061Max;
    @Column(length=8,name="w20061_flag")
    private String w20061Flag;
    @Column(length=12,name="w20089_cou")
    private String w20089Cou;  //铊 041 纳克/升 毫克 N4
    @Column(length=8,name="w20089_min")
    private String w20089Min;
    @Column(length=8,name="w20089_avg")
    private String w20089Avg;
    @Column(length=8,name="w20089_max")
    private String w20089Max;
    @Column(length=8,name="w20089_flag")
    private String w20089Flag;
    @Column(length=12,name="w20092_cou")
    private String w20092Cou;  //锡 036 毫克/升 千克 N3.1
    @Column(length=8,name="w20092_min")
    private String w20092Min;
    @Column(length=8,name="w20092_avg")
    private String w20092Avg;
    @Column(length=8,name="w20092_max")
    private String w20092Max;
    @Column(length=8,name="w20092_flag")
    private String w20092Flag;
    @Column(length=12,name="w20111_cou")
    private String w20111Cou;  //总汞 020 微克/升 克 N3.2
    @Column(length=8,name="w20111_min")
    private String w20111Min;
    @Column(length=8,name="w20111_avg")
    private String w20111Avg;
    @Column(length=8,name="w20111_max")
    private String w20111Max;
    @Column(length=8,name="w20111_flag")
    private String w20111Flag;
    @Column(length=12,name="w20113_cou")
    private String w20113Cou;  //烷基汞 021 纳克/升 毫克 N4
    @Column(length=8,name="w20113_min")
    private String w20113Min;
    @Column(length=8,name="w20113_avg")
    private String w20113Avg;
    @Column(length=8,name="w20113_max")
    private String w20113Max;
    @Column(length=8,name="w20113_flag")
    private String w20113Flag;
    @Column(length=12,name="w20115_cou")
    private String w20115Cou;  //总镉 022 微克/升 克 N3.1
    @Column(length=8,name="w20115_min")
    private String w20115Min;
    @Column(length=8,name="w20115_avg")
    private String w20115Avg;
    @Column(length=8,name="w20115_max")
    private String w20115Max;
    @Column(length=8,name="w20115_flag")
    private String w20115Flag;
    @Column(length=12,name="w20116_cou")
    private String w20116Cou;  //总铬 023 毫克/升 千克 N3.3
    @Column(length=8,name="w20116_min")
    private String w20116Min;
    @Column(length=8,name="w20116_avg")
    private String w20116Avg;
    @Column(length=8,name="w20116_max")
    private String w20116Max;
    @Column(length=8,name="w20116_flag")
    private String w20116Flag;
    @Column(length=12,name="w20117_cou")
    private String w20117Cou;  //六价铬 024 微克/升 克 N2.3
    @Column(length=8,name="w20117_min")
    private String w20117Min;
    @Column(length=8,name="w20117_avg")
    private String w20117Avg;
    @Column(length=8,name="w20117_max")
    private String w20117Max;
    @Column(length=8,name="w20117_flag")
    private String w20117Flag;
    @Column(length=12,name="w20119_cou")
    private String w20119Cou;  //总砷 026 微克/升 克 N2.3
    @Column(length=8,name="w20119_min")
    private String w20119Min;
    @Column(length=8,name="w20119_avg")
    private String w20119Avg;
    @Column(length=8,name="w20119_max")
    private String w20119Max;
    @Column(length=8,name="w20119_flag")
    private String w20119Flag;
    @Column(length=12,name="w20120_cou")
    private String w20120Cou;  //总铅 027 微克/升 克 N4
    @Column(length=8,name="w20120_min")
    private String w20120Min;
    @Column(length=8,name="w20120_avg")
    private String w20120Avg;
    @Column(length=8,name="w20120_max")
    private String w20120Max;
    @Column(length=8,name="w20120_flag")
    private String w20120Flag;
    @Column(length=12,name="w20121_cou")
    private String w20121Cou;  //总镍 028 毫克/升 千克 N3.2
    @Column(length=8,name="w20121_min")
    private String w20121Min;
    @Column(length=8,name="w20121_avg")
    private String w20121Avg;
    @Column(length=8,name="w20121_max")
    private String w20121Max;
    @Column(length=8,name="w20121_flag")
    private String w20121Flag;
    @Column(length=12,name="w20122_cou")
    private String w20122Cou;  //总铜 029 毫克/升 千克 N3.2
    @Column(length=8,name="w20122_min")
    private String w20122Min;
    @Column(length=8,name="w20122_avg")
    private String w20122Avg;
    @Column(length=8,name="w20122_max")
    private String w20122Max;
    @Column(length=8,name="w20122_flag")
    private String w20122Flag;
    @Column(length=12,name="w20123_cou")
    private String w20123Cou;  //总锌 030 毫克/升 千克 N3.3
    @Column(length=8,name="w20123_min")
    private String w20123Min;
    @Column(length=8,name="w20123_avg")
    private String w20123Avg;
    @Column(length=8,name="w20123_max")
    private String w20123Max;
    @Column(length=8,name="w20123_flag")
    private String w20123Flag;
    @Column(length=12,name="w20124_cou")
    private String w20124Cou;  //总锰 031 毫克/升 千克 N3.3
    @Column(length=8,name="w20124_min")
    private String w20124Min;
    @Column(length=8,name="w20124_avg")
    private String w20124Avg;
    @Column(length=8,name="w20124_max")
    private String w20124Max;
    @Column(length=8,name="w20124_flag")
    private String w20124Flag;
    @Column(length=12,name="w20125_cou")
    private String w20125Cou;  //总铁 032 毫克/升 千克 N3.3
    @Column(length=8,name="w20125_min")
    private String w20125Min;
    @Column(length=8,name="w20125_avg")
    private String w20125Avg;
    @Column(length=8,name="w20125_max")
    private String w20125Max;
    @Column(length=8,name="w20125_flag")
    private String w20125Flag;
    @Column(length=12,name="w20126_cou")
    private String w20126Cou;  //总银 033 毫克/升 千克 N3.3
    @Column(length=8,name="w20126_min")
    private String w20126Min;
    @Column(length=8,name="w20126_avg")
    private String w20126Avg;
    @Column(length=8,name="w20126_max")
    private String w20126Max;
    @Column(length=8,name="w20126_flag")
    private String w20126Flag;
    @Column(length=12,name="w20127_cou")
    private String w20127Cou;  //总铍 034 微克/升 克 N3.3
    @Column(length=8,name="w20127_min")
    private String w20127Min;
    @Column(length=8,name="w20127_avg")
    private String w20127Avg;
    @Column(length=8,name="w20127_max")
    private String w20127Max;
    @Column(length=8,name="w20127_flag")
    private String w20127Flag;
    @Column(length=12,name="w20128_cou")
    private String w20128Cou;  //总硒 035 微克/升 克 N4.2
    @Column(length=8,name="w20128_min")
    private String w20128Min;
    @Column(length=8,name="w20128_avg")
    private String w20128Avg;
    @Column(length=8,name="w20128_max")
    private String w20128Max;
    @Column(length=8,name="w20128_flag")
    private String w20128Flag;
    @Column(length=12,name="w20138_cou")
    private String w20138Cou;  //铜 毫克/升 千克 N3.3
    @Column(length=8,name="w20138_min")
    private String w20138Min;
    @Column(length=8,name="w20138_avg")
    private String w20138Avg;
    @Column(length=8,name="w20138_max")
    private String w20138Max;
    @Column(length=8,name="w20138_flag")
    private String w20138Flag;
    @Column(length=12,name="w20139_cou")
    private String w20139Cou;  //锌 毫克/升 千克 N3.3
    @Column(length=8,name="w20139_min")
    private String w20139Min;
    @Column(length=8,name="w20139_avg")
    private String w20139Avg;
    @Column(length=8,name="w20139_max")
    private String w20139Max;
    @Column(length=8,name="w20139_flag")
    private String w20139Flag;
    @Column(length=12,name="w20140_cou")
    private String w20140Cou;  //硒 毫克/升 千克 N3.3
    @Column(length=8,name="w20140_min")
    private String w20140Min;
    @Column(length=8,name="w20140_avg")
    private String w20140Avg;
    @Column(length=8,name="w20140_max")
    private String w20140Max;
    @Column(length=8,name="w20140_flag")
    private String w20140Flag;
    @Column(length=12,name="w20141_cou")
    private String w20141Cou;  //砷 毫克/升 千克 N3.3
    @Column(length=8,name="w20141_min")
    private String w20141Min;
    @Column(length=8,name="w20141_avg")
    private String w20141Avg;
    @Column(length=8,name="w20141_max")
    private String w20141Max;
    @Column(length=8,name="w20141_flag")
    private String w20141Flag;
    @Column(length=12,name="w20142_cou")
    private String w20142Cou;  //汞 微克/升 克 N3.3
    @Column(length=8,name="w20142_min")
    private String w20142Min;
    @Column(length=8,name="w20142_avg")
    private String w20142Avg;
    @Column(length=8,name="w20142_max")
    private String w20142Max;
    @Column(length=8,name="w20142_flag")
    private String w20142Flag;
    @Column(length=12,name="w20143_cou")
    private String w20143Cou;  //镉 微克/升 克 N3.3
    @Column(length=8,name="w20143_min")
    private String w20143Min;
    @Column(length=8,name="w20143_avg")
    private String w20143Avg;
    @Column(length=8,name="w20143_max")
    private String w20143Max;
    @Column(length=8,name="w20143_flag")
    private String w20143Flag;
    @Column(length=12,name="w20144_cou")
    private String w20144Cou;  //铅 毫克/升 千克 N3.3
    @Column(length=8,name="w20144_min")
    private String w20144Min;
    @Column(length=8,name="w20144_avg")
    private String w20144Avg;
    @Column(length=8,name="w20144_max")
    private String w20144Max;
    @Column(length=8,name="w20144_flag")
    private String w20144Flag;
    @Column(length=12,name="w21001_cou")
    private String w21001Cou;  //总氮 065 毫克/升 千克 N4.2
    @Column(length=8,name="w21001_min")
    private String w21001Min;
    @Column(length=8,name="w21001_avg")
    private String w21001Avg;
    @Column(length=8,name="w21001_max")
    private String w21001Max;
    @Column(length=8,name="w21001_flag")
    private String w21001Flag;
    @Column(length=12,name="w21003_cou")
    private String w21003Cou;  //氨氮 060 毫克/升 千克 N4.2
    @Column(length=8,name="w21003_min")
    private String w21003Min;
    @Column(length=8,name="w21003_avg")
    private String w21003Avg;
    @Column(length=8,name="w21003_max")
    private String w21003Max;
    @Column(length=8,name="w21003_flag")
    private String w21003Flag;
    @Column(length=12,name="w21004_cou")
    private String w21004Cou;  //凯氏氮 062 毫克/升 千克 N3.1
    @Column(length=8,name="w21004_min")
    private String w21004Min;
    @Column(length=8,name="w21004_avg")
    private String w21004Avg;
    @Column(length=8,name="w21004_max")
    private String w21004Max;
    @Column(length=8,name="w21004_flag")
    private String w21004Flag;
    @Column(length=12,name="w21006_cou")
    private String w21006Cou;  //亚硝酸盐 063 毫克/升 千克 N2.3
    @Column(length=8,name="w21006_min")
    private String w21006Min;
    @Column(length=8,name="w21006_avg")
    private String w21006Avg;
    @Column(length=8,name="w21006_max")
    private String w21006Max;
    @Column(length=8,name="w21006_flag")
    private String w21006Flag;
    @Column(length=12,name="w21007_cou")
    private String w21007Cou;  //硝酸盐 064 毫克/升 千克 N2.3
    @Column(length=8,name="w21007_min")
    private String w21007Min;
    @Column(length=8,name="w21007_avg")
    private String w21007Avg;
    @Column(length=8,name="w21007_max")
    private String w21007Max;
    @Column(length=8,name="w21007_flag")
    private String w21007Flag;
    @Column(length=12,name="w21011_cou")
    private String w21011Cou;  //总磷 101 毫克/升 千克 N3.2
    @Column(length=8,name="w21011_min")
    private String w21011Min;
    @Column(length=8,name="w21011_avg")
    private String w21011Avg;
    @Column(length=8,name="w21011_max")
    private String w21011Max;
    @Column(length=8,name="w21011_flag")
    private String w21011Flag;
    @Column(length=12,name="w21016_cou")
    private String w21016Cou;  //氰化物 070 毫克/升 千克 N3.3
    @Column(length=8,name="w21016_min")
    private String w21016Min;
    @Column(length=8,name="w21016_avg")
    private String w21016Avg;
    @Column(length=8,name="w21016_max")
    private String w21016Max;
    @Column(length=8,name="w21016_flag")
    private String w21016Flag;
    @Column(length=12,name="w21017_cou")
    private String w21017Cou;  //氟化物 072 毫克/升 千克 N4.2
    @Column(length=8,name="w21017_min")
    private String w21017Min;
    @Column(length=8,name="w21017_avg")
    private String w21017Avg;
    @Column(length=8,name="w21017_max")
    private String w21017Max;
    @Column(length=8,name="w21017_flag")
    private String w21017Flag;
    @Column(length=12,name="w21019_cou")
    private String w21019Cou;  //硫化物 071 毫克/升 千克 N3.3
    @Column(length=8,name="w21019_min")
    private String w21019Min;
    @Column(length=8,name="w21019_avg")
    private String w21019Avg;
    @Column(length=8,name="w21019_max")
    private String w21019Max;
    @Column(length=8,name="w21019_flag")
    private String w21019Flag;
    @Column(length=12,name="w21022_cou")
    private String w21022Cou;  //氯化物 090 毫克/升 千克 N3.1
    @Column(length=8,name="w21022_min")
    private String w21022Min;
    @Column(length=8,name="w21022_avg")
    private String w21022Avg;
    @Column(length=8,name="w21022_max")
    private String w21022Max;
    @Column(length=8,name="w21022_flag")
    private String w21022Flag;
    @Column(length=12,name="w21038_cou")
    private String w21038Cou;  //硫酸盐 -- 毫克/升 千克 N6
    @Column(length=8,name="w21038_min")
    private String w21038Min;
    @Column(length=8,name="w21038_avg")
    private String w21038Avg;
    @Column(length=8,name="w21038_max")
    private String w21038Max;
    @Column(length=8,name="w21038_flag")
    private String w21038Flag;
    @Column(length=12,name="w22001_cou")
    private String w22001Cou;  //石油类 080 毫克/升 千克 N3.2
    @Column(length=8,name="w22001_min")
    private String w22001Min;
    @Column(length=8,name="w22001_avg")
    private String w22001Avg;
    @Column(length=8,name="w22001_max")
    private String w22001Max;
    @Column(length=8,name="w22001_flag")
    private String w22001Flag;
    @Column(length=12,name="w23002_cou")
    private String w23002Cou;  //挥发酚 110 毫克/升 千克 N3.4
    @Column(length=8,name="w23002_min")
    private String w23002Min;
    @Column(length=8,name="w23002_avg")
    private String w23002Avg;
    @Column(length=8,name="w23002_max")
    private String w23002Max;
    @Column(length=8,name="w23002_flag")
    private String w23002Flag;
    @Column(length=12,name="w25043_cou")
    private String w25043Cou;  //苯并[α]芘 540 微克/升 克 N3.1
    @Column(length=8,name="w25043_min")
    private String w25043Min;
    @Column(length=8,name="w25043_avg")
    private String w25043Avg;
    @Column(length=8,name="w25043_max")
    private String w25043Max;
    @Column(length=8,name="w25043_flag")
    private String w25043Flag;
    @Column(length=12,name="w33001_cou")
    private String w33001Cou;  //六六六 350 纳克/升 毫克 N4
    @Column(length=8,name="w33001_min")
    private String w33001Min;
    @Column(length=8,name="w33001_avg")
    private String w33001Avg;
    @Column(length=8,name="w33001_max")
    private String w33001Max;
    @Column(length=8,name="w33001_flag")
    private String w33001Flag;
    @Column(length=12,name="w33007_cou")
    private String w33007Cou;  //滴滴涕 351 纳克/升 毫克 N4
    @Column(length=8,name="w33007_min")
    private String w33007Min;
    @Column(length=8,name="w33007_avg")
    private String w33007Avg;
    @Column(length=8,name="w33007_max")
    private String w33007Max;
    @Column(length=8,name="w33007_flag")
    private String w33007Flag;
    @Column(length=12,name="w99001_cou")
    private String w99001Cou;  //有机氮 061 毫克/升 千克 N3.1
    @Column(length=8,name="w99001_min")
    private String w99001Min;
    @Column(length=8,name="w99001_avg")
    private String w99001Avg;
    @Column(length=8,name="w99001_max")
    private String w99001Max;
    @Column(length=8,name="w99001_flag")
    private String w99001Flag;

}
