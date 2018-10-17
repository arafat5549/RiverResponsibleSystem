package com.jqmkj.WaterResponseSystem.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "deviceinfo_day_data",
        indexes = {  @Index(name = "my_index_DataTime", columnList="data_time",     unique = false)})
public class DeviceinfoDayData extends BaseDataEntity{

    @Column(name="monitorsite_id")
    private Long monitorsiteId;//监测站ID
    @Column(name="data_time")
    private String dataTime;

    @Column(name="w00000_avg")
    private String w00000Avg;
    @Column(name="w00000_min")
    private String w00000Min;
    @Column(name="w00000_max")
    private String w00000Max;
    @Column(name="w00000_cou")
    private String w00000Cou;
    @Column(name="w00000_flag")
    private String w00000Flag;
    @Column(name="w01001_avg")
    private String w01001Avg;
    @Column(name="w01001_min")
    private String w01001Min;
    @Column(name="w01001_max")
    private String w01001Max;
    @Column(name="w01001_cou")
    private String w01001Cou;
    @Column(name="w01001_flag")
    private String w01001Flag;
    @Column(name="w01002_avg")
    private String w01002Avg;
    @Column(name="w01002_min")
    private String w01002Min;
    @Column(name="w01002_max")
    private String w01002Max;
    @Column(name="w01002_cou")
    private String w01002Cou;
    @Column(name="w01002_flag")
    private String w01002Flag;
    @Column(name="w01006_avg")
    private String w01006Avg;
    @Column(name="w01006_min")
    private String w01006Min;
    @Column(name="w01006_max")
    private String w01006Max;
    @Column(name="w01006_cou")
    private String w01006Cou;
    @Column(name="w01006_flag")
    private String w01006Flag;
    @Column(name="w01009_avg")
    private String w01009Avg;
    @Column(name="w01009_min")
    private String w01009Min;
    @Column(name="w01009_max")
    private String w01009Max;
    @Column(name="w01009_cou")
    private String w01009Cou;
    @Column(name="w01009_flag")
    private String w01009Flag;
    @Column(name="w01010_avg")
    private String w01010Avg;
    @Column(name="w01010_min")
    private String w01010Min;
    @Column(name="w01010_max")
    private String w01010Max;
    @Column(name="w01010_cou")
    private String w01010Cou;
    @Column(name="w01010_flag")
    private String w01010Flag;
    @Column(name="w01012_avg")
    private String w01012Avg;
    @Column(name="w01012_min")
    private String w01012Min;
    @Column(name="w01012_max")
    private String w01012Max;
    @Column(name="w01012_cou")
    private String w01012Cou;
    @Column(name="w01012_flag")
    private String w01012Flag;
    @Column(name="w01014_avg")
    private String w01014Avg;
    @Column(name="w01014_min")
    private String w01014Min;
    @Column(name="w01014_max")
    private String w01014Max;
    @Column(name="w01014_cou")
    private String w01014Cou;
    @Column(name="w01014_flag")
    private String w01014Flag;
    @Column(name="w01017_avg")
    private String w01017Avg;
    @Column(name="w01017_min")
    private String w01017Min;
    @Column(name="w01017_max")
    private String w01017Max;
    @Column(name="w01017_cou")
    private String w01017Cou;
    @Column(name="w01017_flag")
    private String w01017Flag;
    @Column(name="w01018_avg")
    private String w01018Avg;
    @Column(name="w01018_min")
    private String w01018Min;
    @Column(name="w01018_max")
    private String w01018Max;
    @Column(name="w01018_cou")
    private String w01018Cou;
    @Column(name="w01018_flag")
    private String w01018Flag;
    @Column(name="w01019_avg")
    private String w01019Avg;
    @Column(name="w01019_min")
    private String w01019Min;
    @Column(name="w01019_max")
    private String w01019Max;
    @Column(name="w01019_cou")
    private String w01019Cou;
    @Column(name="w01019_flag")
    private String w01019Flag;
    @Column(name="w01020_avg")
    private String w01020Avg;
    @Column(name="w01020_min")
    private String w01020Min;
    @Column(name="w01020_max")
    private String w01020Max;
    @Column(name="w01020_cou")
    private String w01020Cou;
    @Column(name="w01020_flag")
    private String w01020Flag;
    @Column(name="w02003_avg")
    private String w02003Avg;
    @Column(name="w02003_min")
    private String w02003Min;
    @Column(name="w02003_max")
    private String w02003Max;
    @Column(name="w02003_cou")
    private String w02003Cou;
    @Column(name="w02003_flag")
    private String w02003Flag;
    @Column(name="w02006_avg")
    private String w02006Avg;
    @Column(name="w02006_min")
    private String w02006Min;
    @Column(name="w02006_max")
    private String w02006Max;
    @Column(name="w02006_cou")
    private String w02006Cou;
    @Column(name="w02006_flag")
    private String w02006Flag;
    @Column(name="w03001_avg")
    private String w03001Avg;
    @Column(name="w03001_min")
    private String w03001Min;
    @Column(name="w03001_max")
    private String w03001Max;
    @Column(name="w03001_cou")
    private String w03001Cou;
    @Column(name="w03001_flag")
    private String w03001Flag;
    @Column(name="w03002_avg")
    private String w03002Avg;
    @Column(name="w03002_min")
    private String w03002Min;
    @Column(name="w03002_max")
    private String w03002Max;
    @Column(name="w03002_cou")
    private String w03002Cou;
    @Column(name="w03002_flag")
    private String w03002Flag;
    @Column(name="w19001_avg")
    private String w19001Avg;
    @Column(name="w19001_min")
    private String w19001Min;
    @Column(name="w19001_max")
    private String w19001Max;
    @Column(name="w19001_cou")
    private String w19001Cou;
    @Column(name="w19001_flag")
    private String w19001Flag;
    @Column(name="w19002_avg")
    private String w19002Avg;
    @Column(name="w19002_min")
    private String w19002Min;
    @Column(name="w19002_max")
    private String w19002Max;
    @Column(name="w19002_cou")
    private String w19002Cou;
    @Column(name="w19002_flag")
    private String w19002Flag;
    @Column(name="w20012_avg")
    private String w20012Avg;
    @Column(name="w20012_min")
    private String w20012Min;
    @Column(name="w20012_max")
    private String w20012Max;
    @Column(name="w20012_cou")
    private String w20012Cou;
    @Column(name="w20012_flag")
    private String w20012Flag;
    @Column(name="w20023_avg")
    private String w20023Avg;
    @Column(name="w20023_min")
    private String w20023Min;
    @Column(name="w20023_max")
    private String w20023Max;
    @Column(name="w20023_cou")
    private String w20023Cou;
    @Column(name="w20023_flag")
    private String w20023Flag;
    @Column(name="w20038_avg")
    private String w20038Avg;
    @Column(name="w20038_min")
    private String w20038Min;
    @Column(name="w20038_max")
    private String w20038Max;
    @Column(name="w20038_cou")
    private String w20038Cou;
    @Column(name="w20038_flag")
    private String w20038Flag;
    @Column(name="w20061_avg")
    private String w20061Avg;
    @Column(name="w20061_min")
    private String w20061Min;
    @Column(name="w20061_max")
    private String w20061Max;
    @Column(name="w20061_cou")
    private String w20061Cou;
    @Column(name="w20061_flag")
    private String w20061Flag;
    @Column(name="w20089_avg")
    private String w20089Avg;
    @Column(name="w20089_min")
    private String w20089Min;
    @Column(name="w20089_max")
    private String w20089Max;
    @Column(name="w20089_cou")
    private String w20089Cou;
    @Column(name="w20089_flag")
    private String w20089Flag;
    @Column(name="w20092_avg")
    private String w20092Avg;
    @Column(name="w20092_min")
    private String w20092Min;
    @Column(name="w20092_max")
    private String w20092Max;
    @Column(name="w20092_cou")
    private String w20092Cou;
    @Column(name="w20092_flag")
    private String w20092Flag;
    @Column(name="w20111_avg")
    private String w20111Avg;
    @Column(name="w20111_min")
    private String w20111Min;
    @Column(name="w20111_max")
    private String w20111Max;
    @Column(name="w20111_cou")
    private String w20111Cou;
    @Column(name="w20111_flag")
    private String w20111Flag;
    @Column(name="w20113_avg")
    private String w20113Avg;
    @Column(name="w20113_min")
    private String w20113Min;
    @Column(name="w20113_max")
    private String w20113Max;
    @Column(name="w20113_cou")
    private String w20113Cou;
    @Column(name="w20113_flag")
    private String w20113Flag;
    @Column(name="w20115_avg")
    private String w20115Avg;
    @Column(name="w20115_min")
    private String w20115Min;
    @Column(name="w20115_max")
    private String w20115Max;
    @Column(name="w20115_cou")
    private String w20115Cou;
    @Column(name="w20115_flag")
    private String w20115Flag;
    @Column(name="w20116_avg")
    private String w20116Avg;
    @Column(name="w20116_min")
    private String w20116Min;
    @Column(name="w20116_max")
    private String w20116Max;
    @Column(name="w20116_cou")
    private String w20116Cou;
    @Column(name="w20116_flag")
    private String w20116Flag;
    @Column(name="w20117_avg")
    private String w20117Avg;
    @Column(name="w20117_min")
    private String w20117Min;
    @Column(name="w20117_max")
    private String w20117Max;
    @Column(name="w20117_cou")
    private String w20117Cou;
    @Column(name="w20117_flag")
    private String w20117Flag;
    @Column(name="w20119_avg")
    private String w20119Avg;
    @Column(name="w20119_min")
    private String w20119Min;
    @Column(name="w20119_max")
    private String w20119Max;
    @Column(name="w20119_cou")
    private String w20119Cou;
    @Column(name="w20119_flag")
    private String w20119Flag;
    @Column(name="w20120_avg")
    private String w20120Avg;
    @Column(name="w20120_min")
    private String w20120Min;
    @Column(name="w20120_max")
    private String w20120Max;
    @Column(name="w20120_cou")
    private String w20120Cou;
    @Column(name="w20120_flag")
    private String w20120Flag;
    @Column(name="w20121_avg")
    private String w20121Avg;
    @Column(name="w20121_min")
    private String w20121Min;
    @Column(name="w20121_max")
    private String w20121Max;
    @Column(name="w20121_cou")
    private String w20121Cou;
    @Column(name="w20121_flag")
    private String w20121Flag;
    @Column(name="w20122_avg")
    private String w20122Avg;
    @Column(name="w20122_min")
    private String w20122Min;
    @Column(name="w20122_max")
    private String w20122Max;
    @Column(name="w20122_cou")
    private String w20122Cou;
    @Column(name="w20122_flag")
    private String w20122Flag;
    @Column(name="w20123_avg")
    private String w20123Avg;
    @Column(name="w20123_min")
    private String w20123Min;
    @Column(name="w20123_max")
    private String w20123Max;
    @Column(name="w20123_cou")
    private String w20123Cou;
    @Column(name="w20123_flag")
    private String w20123Flag;
    @Column(name="w20124_avg")
    private String w20124Avg;
    @Column(name="w20124_min")
    private String w20124Min;
    @Column(name="w20124_max")
    private String w20124Max;
    @Column(name="w20124_cou")
    private String w20124Cou;
    @Column(name="w20124_flag")
    private String w20124Flag;
    @Column(name="w20125_avg")
    private String w20125Avg;
    @Column(name="w20125_min")
    private String w20125Min;
    @Column(name="w20125_max")
    private String w20125Max;
    @Column(name="w20125_cou")
    private String w20125Cou;
    @Column(name="w20125_flag")
    private String w20125Flag;
    @Column(name="w20126_avg")
    private String w20126Avg;
    @Column(name="w20126_min")
    private String w20126Min;
    @Column(name="w20126_max")
    private String w20126Max;
    @Column(name="w20126_cou")
    private String w20126Cou;
    @Column(name="w20126_flag")
    private String w20126Flag;
    @Column(name="w20127_avg")
    private String w20127Avg;
    @Column(name="w20127_min")
    private String w20127Min;
    @Column(name="w20127_max")
    private String w20127Max;
    @Column(name="w20127_cou")
    private String w20127Cou;
    @Column(name="w20127_flag")
    private String w20127Flag;
    @Column(name="w20128_avg")
    private String w20128Avg;
    @Column(name="w20128_min")
    private String w20128Min;
    @Column(name="w20128_max")
    private String w20128Max;
    @Column(name="w20128_cou")
    private String w20128Cou;
    @Column(name="w20128_flag")
    private String w20128Flag;
    @Column(name="w20138_avg")
    private String w20138Avg;
    @Column(name="w20138_min")
    private String w20138Min;
    @Column(name="w20138_max")
    private String w20138Max;
    @Column(name="w20138_cou")
    private String w20138Cou;
    @Column(name="w20138_flag")
    private String w20138Flag;
    @Column(name="w20139_avg")
    private String w20139Avg;
    @Column(name="w20139_min")
    private String w20139Min;
    @Column(name="w20139_max")
    private String w20139Max;
    @Column(name="w20139_cou")
    private String w20139Cou;
    @Column(name="w20139_flag")
    private String w20139Flag;
    @Column(name="w20140_avg")
    private String w20140Avg;
    @Column(name="w20140_min")
    private String w20140Min;
    @Column(name="w20140_max")
    private String w20140Max;
    @Column(name="w20140_cou")
    private String w20140Cou;
    @Column(name="w20140_flag")
    private String w20140Flag;
    @Column(name="w20141_avg")
    private String w20141Avg;
    @Column(name="w20141_min")
    private String w20141Min;
    @Column(name="w20141_max")
    private String w20141Max;
    @Column(name="w20141_cou")
    private String w20141Cou;
    @Column(name="w20141_flag")
    private String w20141Flag;
    @Column(name="w20142_avg")
    private String w20142Avg;
    @Column(name="w20142_min")
    private String w20142Min;
    @Column(name="w20142_max")
    private String w20142Max;
    @Column(name="w20142_cou")
    private String w20142Cou;
    @Column(name="w20142_flag")
    private String w20142Flag;
    @Column(name="w20143_avg")
    private String w20143Avg;
    @Column(name="w20143_min")
    private String w20143Min;
    @Column(name="w20143_max")
    private String w20143Max;
    @Column(name="w20143_cou")
    private String w20143Cou;
    @Column(name="w20143_flag")
    private String w20143Flag;
    @Column(name="w20144_avg")
    private String w20144Avg;
    @Column(name="w20144_min")
    private String w20144Min;
    @Column(name="w20144_max")
    private String w20144Max;
    @Column(name="w20144_cou")
    private String w20144Cou;
    @Column(name="w20144_flag")
    private String w20144Flag;
    @Column(name="w21001_avg")
    private String w21001Avg;
    @Column(name="w21001_min")
    private String w21001Min;
    @Column(name="w21001_max")
    private String w21001Max;
    @Column(name="w21001_cou")
    private String w21001Cou;
    @Column(name="w21001_flag")
    private String w21001Flag;
    @Column(name="w21003_avg")
    private String w21003Avg;
    @Column(name="w21003_min")
    private String w21003Min;
    @Column(name="w21003_max")
    private String w21003Max;
    @Column(name="w21003_cou")
    private String w21003Cou;
    @Column(name="w21003_flag")
    private String w21003Flag;
    @Column(name="w21004_avg")
    private String w21004Avg;
    @Column(name="w21004_min")
    private String w21004Min;
    @Column(name="w21004_max")
    private String w21004Max;
    @Column(name="w21004_cou")
    private String w21004Cou;
    @Column(name="w21004_flag")
    private String w21004Flag;
    @Column(name="w21006_avg")
    private String w21006Avg;
    @Column(name="w21006_min")
    private String w21006Min;
    @Column(name="w21006_max")
    private String w21006Max;
    @Column(name="w21006_cou")
    private String w21006Cou;
    @Column(name="w21006_flag")
    private String w21006Flag;
    @Column(name="w21007_avg")
    private String w21007Avg;
    @Column(name="w21007_min")
    private String w21007Min;
    @Column(name="w21007_max")
    private String w21007Max;
    @Column(name="w21007_cou")
    private String w21007Cou;
    @Column(name="w21007_flag")
    private String w21007Flag;
    @Column(name="w21011_avg")
    private String w21011Avg;
    @Column(name="w21011_min")
    private String w21011Min;
    @Column(name="w21011_max")
    private String w21011Max;
    @Column(name="w21011_cou")
    private String w21011Cou;
    @Column(name="w21011_flag")
    private String w21011Flag;
    @Column(name="w21016_avg")
    private String w21016Avg;
    @Column(name="w21016_min")
    private String w21016Min;
    @Column(name="w21016_max")
    private String w21016Max;
    @Column(name="w21016_cou")
    private String w21016Cou;
    @Column(name="w21016_flag")
    private String w21016Flag;
    @Column(name="w21017_avg")
    private String w21017Avg;
    @Column(name="w21017_min")
    private String w21017Min;
    @Column(name="w21017_max")
    private String w21017Max;
    @Column(name="w21017_cou")
    private String w21017Cou;
    @Column(name="w21017_flag")
    private String w21017Flag;
    @Column(name="w21019_avg")
    private String w21019Avg;
    @Column(name="w21019_min")
    private String w21019Min;
    @Column(name="w21019_max")
    private String w21019Max;
    @Column(name="w21019_cou")
    private String w21019Cou;
    @Column(name="w21019_flag")
    private String w21019Flag;
    @Column(name="w21022_avg")
    private String w21022Avg;
    @Column(name="w21022_min")
    private String w21022Min;
    @Column(name="w21022_max")
    private String w21022Max;
    @Column(name="w21022_cou")
    private String w21022Cou;
    @Column(name="w21022_flag")
    private String w21022Flag;
    @Column(name="w21038_avg")
    private String w21038Avg;
    @Column(name="w21038_min")
    private String w21038Min;
    @Column(name="w21038_max")
    private String w21038Max;
    @Column(name="w21038_cou")
    private String w21038Cou;
    @Column(name="w21038_flag")
    private String w21038Flag;
    @Column(name="w22001_avg")
    private String w22001Avg;
    @Column(name="w22001_min")
    private String w22001Min;
    @Column(name="w22001_max")
    private String w22001Max;
    @Column(name="w22001_cou")
    private String w22001Cou;
    @Column(name="w22001_flag")
    private String w22001Flag;
    @Column(name="w23002_avg")
    private String w23002Avg;
    @Column(name="w23002_min")
    private String w23002Min;
    @Column(name="w23002_max")
    private String w23002Max;
    @Column(name="w23002_cou")
    private String w23002Cou;
    @Column(name="w23002_flag")
    private String w23002Flag;
    @Column(name="w25043_avg")
    private String w25043Avg;
    @Column(name="w25043_min")
    private String w25043Min;
    @Column(name="w25043_max")
    private String w25043Max;
    @Column(name="w25043_cou")
    private String w25043Cou;
    @Column(name="w25043_flag")
    private String w25043Flag;
    @Column(name="w33001_avg")
    private String w33001Avg;
    @Column(name="w33001_min")
    private String w33001Min;
    @Column(name="w33001_max")
    private String w33001Max;
    @Column(name="w33001_cou")
    private String w33001Cou;
    @Column(name="w33001_flag")
    private String w33001Flag;
    @Column(name="w33007_avg")
    private String w33007Avg;
    @Column(name="w33007_min")
    private String w33007Min;
    @Column(name="w33007_max")
    private String w33007Max;
    @Column(name="w33007_cou")
    private String w33007Cou;
    @Column(name="w33007_flag")
    private String w33007Flag;
    @Column(name="w99001_avg")
    private String w99001Avg;
    @Column(name="w99001_min")
    private String w99001Min;
    @Column(name="w99001_max")
    private String w99001Max;
    @Column(name="w99001_cou")
    private String w99001Cou;
    @Column(name="w99001_flag")
    private String w99001Flag;

}