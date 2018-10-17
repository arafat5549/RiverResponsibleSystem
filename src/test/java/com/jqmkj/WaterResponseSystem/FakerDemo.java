package com.jqmkj.WaterResponseSystem;

import com.github.javafaker.Faker;
import com.google.common.collect.Lists;

import com.google.common.collect.Maps;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.*;
import com.jqmkj.WaterResponseSystem.entity.*;
import com.jqmkj.WaterResponseSystem.entity.other.DeviceinfoDataSearchModel;
import com.jqmkj.WaterResponseSystem.entity.other.Option;
import com.jqmkj.WaterResponseSystem.service.TestService;
import com.jqmkj.WaterResponseSystem.util.DateUtils;
import com.jqmkj.WaterResponseSystem.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import tk.mybatis.mapper.entity.Example;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyaoyao on 2018/9/15.
 * 数据伪造
 *
 * 数据统一为 单精度float(10,3)  由于浮点数的特性 会损失一部分精度，但我们不是财务和银行系统 并不需要高精度的decimal这样会损失大量性能 (非基础数据类型)
 */
///@RunWith(SpringJUnit4ClassRunner.class)
///@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-redis.xml","classpath:spring/spring-service.xml"})


@RunWith(SpringRunner.class)
@SpringBootTest
public class FakerDemo {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private DeviceinfodataPollutionMapper deviceinfodataPollutionDao;
    @Autowired
    private DevicegpsMapper devicegpsDao;
    @Autowired
    private RegionMapper regionDao;
    @Autowired
    TestService testService;

    @Autowired
    DeviceinfoDayDataMapper deviceinfoDayDataMapper;
    @Autowired
    MonitofactorsMapper monitofactorsMapper;


    @Test
    public void fakerTest1(){
//        LOG.info("生成模拟监测站位置 真实行政区域坐标");
//        long pid = 3501;
//        long pid2 = 3504;
        //long pid_new = testService.initMonitorsiteData(pid);
        //long pid2_new = testService.initMonitorsiteData(pid2);
        //testService.initDeviceGpsData(pid);
        //testService.initDeviceGpsData(pid2);
        //testService.initDeviceinfoData_Pollution();

        Faker faker = new Faker();
//        List<String> list = Lists.newArrayList();
//        List<Monitofactors> mlist = monitofactorsMapper.selectAll();
//        int factorNum = faker.random().nextInt(5,10);   //随机取5到20个数值
//
//        List<String> dictNameRands = Lists.newArrayList();
//        while(dictNameRands.size()<factorNum){
//            int idx = faker.random().nextInt(mlist.size());
//            String code = mlist.get(idx).getSno();
//            if(!dictNameRands.contains(code))
//                dictNameRands.add(code);
//        }

    }

    /*
    @Test
    public void fakerTest2(){
        Faker faker = new Faker();
        Date from  = DateUtils.parseDate(TestService.TEST_START_DATE);
        Date to = DateUtils.parseDate(TestService.TEST_END_DATE);
        String avatar = faker.avatar().image();
        System.out.println(avatar);

        List<Long> idlist = Lists.newArrayList();
        List<Devicegps> list = devicegpsDao.selectAll();
        for(Devicegps d:list){
            if(d.getMonitorsiteId()>=100)
                idlist.add(d.getId());
        }
        //System.out.println(idlist);
        List<DeviceinfodataPollution> datalist = Lists.newArrayList();
        int rand = faker.random().nextInt(0,5);
        for (int i=0;i<10000;i++){
            Date d = faker.date().between(from,to);
            //LocalDateTime.of(d.getYear(),d.getMonth())
            LocalDateTime ldt = DateUtils.date2LocalDateTime(d);
            String name = "水利数据[2]:"+i; //String name = faker.name().title();
            double suspension = faker.number().randomDouble(3,1+rand,100);
            double ph = faker.number().randomDouble(3,0+rand,14);
            double mercury = faker.number().randomDouble(3,1+rand,50);
            double organicCarbon = faker.number().randomDouble(3,1+rand,30);
            double lead = faker.number().randomDouble(3,0+rand,10);
            long devicegpsId = idlist.get(faker.random().nextInt(idlist.size()));
            DeviceinfodataPollution data = new DeviceinfodataPollution();
            //data.setId(i+1L);
            data.setName(name);
            data.setCreateDate(ldt);
            data.setSuspension((float)suspension);
            data.setPh((float)ph);
            data.setMercury((float)mercury);
            data.setOrganicCarbon((float)organicCarbon);
            data.setLead((float)lead);
            data.setDevicegpsId(devicegpsId);
            datalist.add(data);
        }
        deviceinfodataPollutionDao.insertList(datalist);
    }
    */

    @Test
    public void fakerTest3(){
        //testService.initFactosData();
        //testService.initBrandAndCateInfos();
        //testService.initDeviceInfos(5);
        //testService.initDeviceGpsData(3501);
        //testService.initDeviceGpsData(3504);
        //testService.updateDeviceInfos();

        //testService.createTestSql_data("deviceinfo_day_data",1);
        //testService.createTestSql_data("deviceinfo_hour_data",1);
        //testService.createTestSql_data("deviceinfo_minute_data",10);

        //testService.createTestSql_data("deviceinfo_test_data",1);


    }
    @Autowired
    CodeGeneratorUtil codeuitl;

    @Test
    public void fakerTest4(){
        //codeuitl.createEntity();
        //codeuitl.createSql_entitity(Monitofactors.class);
    }

    @Test
    public void serviceTest(){
        testService.testFactorService();
        //testService.testDao();;
    }


}
