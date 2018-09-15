package com.jqm.ssm;

import com.github.javafaker.Faker;
import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import com.jqm.ssm.dao.DevicegpsDao;
import com.jqm.ssm.dao.DeviceinfodataPollutionDao;
import com.jqm.ssm.entity.Devicegps;
import com.jqm.ssm.entity.DeviceinfodataPollution;
import com.jqm.ssm.util.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by wangyaoyao on 2018/9/15.
 * 数据伪造
 *
 * 数据统一为 单精度float(10,3)  由于浮点数的特性 会损失一部分精度，但我们不是财务和银行系统 并不需要高精度的decimal这样会损失大量性能 (非基础数据类型)
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-redis.xml","classpath:spring/spring-service.xml"})
public class FakerDemo {

    @Autowired
    private DeviceinfodataPollutionDao deviceinfodataPollutionDao;
    @Autowired
    private DevicegpsDao devicegpsDao;

    @Test
    public void fakerTest(){
        Faker faker = new Faker();
        Date from  = DateUtils.parseDate("2017-01-01 00:00:00");
        Date to = DateUtils.parseDate("2019-01-01 00:00:00");
        String avatar = faker.avatar().image();
        System.out.println(avatar);

        List<Integer> idlist = Lists.newArrayList();
        List<Devicegps> list = devicegpsDao.selectListByMap(null);
        for(Devicegps d:list){
            idlist.add(d.getId());
        }


        List<DeviceinfodataPollution> datalist = Lists.newArrayList();
        for (int i=0;i<10000;i++){
            Date d = faker.date().between(from,to);
            String name = "水利数据:"+i;//String name = faker.name().title();
            double suspension = faker.number().randomDouble(3,1,100);
            double ph = faker.number().randomDouble(3,0,14);
            double mercury = faker.number().randomDouble(3,1,100);
            double organicCarbon = faker.number().randomDouble(3,1,100);
            double lead = faker.number().randomDouble(3,1,100);
            int devicegpsId = idlist.get(faker.random().nextInt(idlist.size()));
            //
            DeviceinfodataPollution data = new DeviceinfodataPollution();
            data.setName(name);
            data.setCreateDate(d);
            data.setSuspension((float)suspension);
            data.setPh((float)ph);
            data.setMercury((float)mercury);
            data.setOrganicCarbon((float)organicCarbon);
            data.setLead((float)lead);
            data.setDevicegpsId(devicegpsId);
            //System.out.println(data);
            datalist.add(data);
        }
        deviceinfodataPollutionDao.batchInsert(datalist);



    }
}
