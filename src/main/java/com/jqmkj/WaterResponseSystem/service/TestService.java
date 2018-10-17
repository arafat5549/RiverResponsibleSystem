package com.jqmkj.WaterResponseSystem.service;

import com.github.javafaker.Faker;
import com.google.common.base.Joiner;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.io.Files;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Longs;
import com.jqmkj.WaterResponseSystem.CodeGeneratorUtil;
import com.jqmkj.WaterResponseSystem.basemapper.mapper.*;
import com.jqmkj.WaterResponseSystem.entity.*;
import com.jqmkj.WaterResponseSystem.entity.other.DeviceinfoDataSearchModel;
import com.jqmkj.WaterResponseSystem.entity.other.Option;
import com.jqmkj.WaterResponseSystem.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class TestService {
    public static final String TEST_START_DATE = "2017-01-01 00:00:00";
    public static final String TEST_END_DATE   = "2019-01-01 00:00:00";
    public static final Long OPERATOR_ID = 1L;

    @Autowired
    BrandMapper brandMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    UserMapper        userDao;
    @Autowired
    RegionMapper      regionDao;
    @Autowired
    MonitorsiteMapper monitorsiteDao;
    @Autowired
    DeviceinfoMapper  deviceinfoDao;
    @Autowired
    DevicegpsMapper   devicegpsDao;
    @Autowired
    DeviceinfodataPollutionMapper deviceinfodataPollutionDao;
    @Autowired
    private MonitofactorsMapper monitofactorsMapper;

    private Monitorsite parseSite(Region obj,Region parent,SysUser operator){
        Monitorsite site = new Monitorsite();
        site.setId(obj.getId());

        site.setUserId(operator.getId());
        site.setName(obj.getName()+"站点");
        long pid = 0;
        String struct = "s-"+site.getId();
        if(parent!=null){
            pid = parent.getId();
            struct = "s-"+pid+"-"+site.getId();
        }
        site.setParentId(pid);
        //site.setStructure(struct);
        //site.setRegion(obj.getName());
        site.setLongitude(obj.getLongitude());
        site.setLatitude(obj.getLatitude());
        site.setOperator(operator.getUsername());

        site.setCreatePerson(operator.getUsername());
        site.setUpdatePerson(operator.getUsername());
        site.setCreateDate(LocalDateTime.now());
        site.setUpdateDate(LocalDateTime.now());
        return site;
    }
    private List<Devicegps> parseDevicegps(Monitorsite obj,Monitorsite parent,SysUser operator,List<Deviceinfo> infolist,int count){
        Faker faker = new Faker();
        List<Devicegps> retlist = Lists.newArrayList();
        //int count = 1;
        for(int i=0;i<count;i++){
            Devicegps gps = new Devicegps();
            gps.setId(obj.getId()*1000+(i+1));
            gps.setMonitorsiteId(obj.getId());
            long pid = 0;
            String struct = "s-"+gps.getId();
            if(parent!=null){
                pid = parent.getId();
                struct = "s-"+pid+"-"+gps.getId();
            }
            gps.setParentId(0L);
            gps.setStructure("");
            gps.setStatus((byte)1);
            gps.setDeleteFlag(false);
            Deviceinfo d = infolist.get(faker.random().nextInt(infolist.size()));
            gps.setDeviceinfoId(d.getId());
            Double d1= faker.number().randomDouble(5,1,2)/100.0;
            if(faker.random().nextInt(6) %2 == 0)
                d1= -d1;
            Double d2= faker.number().randomDouble(5,1,2)/100.0;
            if(faker.random().nextInt(6) %2 == 0)
                d2= -d2;

            gps.setLongitude(String.valueOf((Doubles.tryParse(obj.getLongitude())+d1)));
            gps.setLatitude(String.valueOf((Doubles.tryParse(obj.getLatitude())+d2)));
            System.out.println(String.valueOf((Doubles.tryParse(obj.getLatitude())+d2)));
            gps.setIpAddress("127.0.0.1");
            gps.setPort(8080);
            if(d.getCategoryId()==7 || d.getCategoryId()==8){
                gps.setUrl("rtmp://183.250.15.3:10035/live/qingzhouqiao");
            }

            gps.setCreatePerson(operator.getUsername());
            gps.setUpdatePerson(operator.getUsername());
            gps.setCreateDate(LocalDateTime.now());
            gps.setUpdateDate(LocalDateTime.now());
            retlist.add(gps);
        }
        return retlist;
    }

    private List<DeviceinfodataPollution> parseDeviceinfodataPollution(Devicegps gps,int count){
        List<DeviceinfodataPollution> retlist = Lists.newArrayList();

        Faker faker = new Faker();
        Date from  = DateUtils.parseDate(TEST_START_DATE);
        Date to = DateUtils.parseDate(TEST_END_DATE);
        //String avatar = faker.avatar().image();
        for(int i=0;i<count;i++){
            int rand_min = faker.random().nextInt(-1,5);
            int rand_max = faker.random().nextInt(-1,15);
            Date d = faker.date().between(from,to);
            String name = "水利数据[2]:"+i; //String name = faker.name().title();
            double suspension = faker.number().randomDouble(3,1+rand_min,100+rand_max);
            double ph = faker.number().randomDouble(3,0+rand_min,14);
            double mercury = faker.number().randomDouble(3,1+rand_min,50+rand_max);
            double organicCarbon = faker.number().randomDouble(3,1+rand_min,30+rand_max);
            double lead = faker.number().randomDouble(3,0+rand_min,10+rand_max);
            long devicegpsId = gps.getId();
            DeviceinfodataPollution data = new DeviceinfodataPollution();
            data.setName(name);
            data.setCreateDate(DateUtils.date2LocalDateTime(d));
            data.setSuspension((float)suspension);
            data.setPh((float)ph);
            data.setMercury((float)mercury);
            data.setOrganicCarbon((float)organicCarbon);
            data.setLead((float)lead);
            data.setDevicegpsId(devicegpsId);
            retlist.add(data);
        }
        return retlist;
    }

    //###############################################################################################################//
    //                                      生成测试数据
    //###############################################################################################################//

    //生成监测设备信息
    public void initBrandAndCateInfos(){
        SysUser operator = userDao.selectByPrimaryKey(OPERATOR_ID);
        Deviceinfo deviceinfo = new Deviceinfo();


        List<Brand> brandlist = Lists.newArrayList();
        for(int i=0;i<5;i++){
            Brand brand = new Brand();
            brand.setName("华为水质监测设备"+(i+1));
            brand.setEname("Huawei"+(i+1));
            brand.setLetter("H");
            brand.setWebsite("www.huawei.com");
            brand.setSortNo(i+1);
            brand.setCreatePerson(operator.getUsername());
            brand.setUpdatePerson(operator.getUsername());
            brand.setCreateDate(LocalDateTime.now());
            brand.setUpdateDate(LocalDateTime.now());
            brandlist.add(brand);
        }

        for(int i=0;i<5;i++){
            Brand brand = new Brand();
            brand.setName("海信视频监控"+(i+1));
            brand.setEname("HKVISION"+(i+1));
            brand.setLetter("H");
            brand.setWebsite("www.hkvision.com");
            brand.setSortNo(i+1*10);
            brand.setCreatePerson(operator.getUsername());
            brand.setUpdatePerson(operator.getUsername());
            brand.setCreateDate(LocalDateTime.now());
            brand.setUpdateDate(LocalDateTime.now());
            brandlist.add(brand);
        }
        brandMapper.insertList(brandlist);


        List<Category> catelist = Lists.newArrayList();
        Category cate = new  Category();
        cate.setLevel((byte)(1));
        cate.setName("水质监测设备");
        cate.setType(1);
        cate.setCreatePerson(operator.getUsername());
        cate.setUpdatePerson(operator.getUsername());
        cate.setCreateDate(LocalDateTime.now());
        cate.setUpdateDate(LocalDateTime.now());
        catelist.add(cate);

        cate = new  Category();
        cate.setLevel((byte)(2));
        cate.setName("视频监控设备");
        cate.setType(2);
        cate.setCreatePerson(operator.getUsername());
        cate.setUpdatePerson(operator.getUsername());
        cate.setCreateDate(LocalDateTime.now());
        cate.setUpdateDate(LocalDateTime.now());
        catelist.add(cate);

        categoryMapper.insertList(catelist);

    }

    public void updateDeviceInfos(){
        Faker faker = new Faker();
        List<Deviceinfo> deviceinfoList = deviceinfoDao.selectAll();
        List<Monitofactors> monitofactorsList = monitofactorsMapper.selectAll();

        StringBuffer  sb = new StringBuffer();
        sb.append("INSERT INTO `c_monitofactors_deviceinfo` VALUES \t\n");



        for(Deviceinfo di:deviceinfoList){
            String factors = "";

            int factorNum = faker.random().nextInt(5,20);   //随机取5到20个数值

            List<String> randFactorList = Lists.newArrayList();
            while(randFactorList.size()<factorNum){
                int idx = faker.random().nextInt(monitofactorsList.size());
                Monitofactors code = monitofactorsList.get(idx);
                if(!randFactorList.contains(code.getSno()))
                {
                    randFactorList.add(code.getSno());
                    sb.append("("+code.getId()+","+di.getId()+"),\t\n");
                }
            }
            //
            //factors = Joiner.on(",").join(randFactorList);
            //di.setFactors(factors);
            //deviceinfoDao.updateByPrimaryKeySelective(di);
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(";\t\n");
        System.out.println(sb.toString());

    }

    public void initDeviceInfos(int size) {
        List<Deviceinfo> deviceinfoList = Lists.newArrayList();


        Faker faker = new Faker();
        SysUser operator = userDao.selectByPrimaryKey(OPERATOR_ID);
        Brand b = new Brand();
        b.setWebsite("www.huawei.com");
        List<Brand> brandList = brandMapper.select(b);
        Brand b2 = new Brand();
        b2.setWebsite("www.hkvision.com");
        List<Brand> brandList2 = brandMapper.select(b2);
        //List<Category> categoryList = categoryMapper.selectAll();
        List<Monitofactors> monitofactorsList = monitofactorsMapper.selectAll();

        for(int i=0;i<size;i++){
            Deviceinfo deviceinfo = new Deviceinfo();

            int bidx = faker.random().nextInt(brandList.size());
            Brand brand = brandList.get(bidx);
            deviceinfo.setBrandId(brand.getId());
            deviceinfo.setCategoryId(7L);
            deviceinfo.setName("水质监测设备"+(i+1));
            //deviceinfo.setFactors();
            deviceinfo.setStatus((byte)1);
            deviceinfo.setSupplier("华为");
            deviceinfo.setSno(faker.number().digit());

            deviceinfo.setCreatePerson(operator.getUsername());
            deviceinfo.setUpdatePerson(operator.getUsername());
            deviceinfo.setCreateDate(LocalDateTime.now());
            deviceinfo.setUpdateDate(LocalDateTime.now());
            deviceinfoList.add(deviceinfo);
        }


        for(int i=0;i<size;i++){
            Deviceinfo deviceinfo = new Deviceinfo();

            int bidx = faker.random().nextInt(brandList2.size());
            Brand brand = brandList2.get(bidx);
            deviceinfo.setBrandId(brand.getId());
            deviceinfo.setCategoryId(8L);
            deviceinfo.setName("视频监控设备"+(i+1));
            //deviceinfo.setFactors();
            deviceinfo.setStatus((byte)1);
            deviceinfo.setSupplier("海信");
            deviceinfo.setSno(faker.number().digit());

            deviceinfo.setCreatePerson(operator.getUsername());
            deviceinfo.setUpdatePerson(operator.getUsername());
            deviceinfo.setCreateDate(LocalDateTime.now());
            deviceinfo.setUpdateDate(LocalDateTime.now());
            deviceinfoList.add(deviceinfo);
        }

        System.out.println(deviceinfoList);
        deviceinfoDao.insertList(deviceinfoList);
    }

    //生成监测站点信息
    public long initMonitorsiteData(long regionPid){
        SysUser operator = userDao.selectByPrimaryKey(OPERATOR_ID);
        List<Monitorsite> batchlist = Lists.newArrayList();

        Region p = regionDao.selectByPrimaryKey(regionPid);
        Monitorsite site = parseSite(p,null,operator);
        long pid = monitorsiteDao.insertUseGeneratedKeys(site);
        System.out.println("--------------------"+site+",pid="+pid+",siteid="+site.getId());
        batchlist.add(site);

        //Map<Object,Object> map = Maps.newHashMap();
        //map.put(Region.Column.parentId.toString(),regionPid);
        Example example = new Example(Region.class);
        example.createCriteria().andEqualTo(Region.EnumColumn.parentId.value(),regionPid);
        List<Region> list = regionDao.selectByExample(example);
        for(Region region:list){
            Monitorsite site2 = parseSite(region,p,operator);
            batchlist.add(site2);
        }
        monitorsiteDao.insertList(batchlist);

        regionPid = site.getId();
        return regionPid;
    }
    //生成测试设备部署信息跟站点走
    public void initDeviceGpsData(long siteId){
        List<Deviceinfo> infolist = deviceinfoDao.selectAll();
        List<Devicegps> batchlist = Lists.newArrayList();

        SysUser operator = userDao.selectByPrimaryKey(OPERATOR_ID);
        //Map<Object,Object> map = Maps.newHashMap();
        //map.put(Monitorsite.Column.parentId.toString(),siteId);
        Example example = new Example(Region.class);
        example.createCriteria().andEqualTo(Region.EnumColumn.parentId.value(),siteId);
        List<Monitorsite> sitelist = monitorsiteDao.selectByExample(example);  //.selectListByMap(map);

        Monitorsite parent = monitorsiteDao.selectByPrimaryKey(siteId);
        //每个站点有3个下属部署设备
        List<Devicegps> gpslist = parseDevicegps(parent,null,operator,infolist,3);
        for(Devicegps gps:gpslist){
            batchlist.add(gps);
        }

        for(Monitorsite site:sitelist){
            List<Devicegps> gpslist2 = parseDevicegps(site,parent,operator,infolist,3);
            for(Devicegps gps:gpslist2){
                batchlist.add(gps);
            }
        }

        System.out.println(batchlist.size());
        devicegpsDao.insertList(batchlist);
    }
    //生成测试设备数据 跟设备部署走
    public void initDeviceinfoData_Pollution(){
        List<DeviceinfodataPollution> batchlist = Lists.newArrayList();
        List<Devicegps> gpslist = devicegpsDao.selectAll();//.selectListByMap(null);
        for(Devicegps gps:gpslist){
            List<DeviceinfodataPollution> retlist = parseDeviceinfodataPollution(gps,1000);
            for(DeviceinfodataPollution data:retlist){
                batchlist.add(data);
            }
        }
        deviceinfodataPollutionDao.insertList(batchlist); //.batchInsert(batchlist);

    }
    //###############################################################################################################//
    //                                      生成测试数据
    //###############################################################################################################//


    //将监测因子插入数据库
    public void initFactosData() {
        try {
            List<Monitofactors> dicts = Lists.newArrayList();
            List<String> rets = Files.readLines(new File("/Users/arafat/workspace/factors.txt"), Charset.forName("GBK"));
            for(String s:rets){
                System.out.println(s);
                String fs[] = s.split( " ");
                Monitofactors dp = new Monitofactors();
                dp.setSno(fs[0]);
                dp.setName(fs[1]);
                dicts.add(dp);
            }

            monitofactorsMapper.insertList(dicts);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //生成测试sql
    public void createTestSql_data(String tableName,Integer offset) {
        Faker faker = new Faker();
        List<Monitorsite> sites = Lists.newArrayList();
        if("deviceinfo_minute_data".equals(tableName))
        {
            sites= monitorsiteDao.selectByIds("3501");
        }
        else
            sites = monitorsiteDao.selectAll();

        System.out.println(sites);


        int genType = 0;
        switch (tableName){
            case "deviceinfo_day_data": genType =1; break;
            case "deviceinfo_hour_data":
            case "deviceinfo_test_data":
                genType =2; break;
            case "deviceinfo_minute_data":
                genType =3; break;
        }

        File dest = new File("/Users/arafat/workspace/test_"+tableName+".sql");
        int fCount = 10;   //生成X组不同的因子组合
        int sCount = 10; //每种组合插入x条
        //随机生成的时间位于这两个之间
        String sql_createDate = "create_date";
        Date from  = DateUtils.parseDate("2017-01-01 00:00:00");
        Date to = DateUtils.parseDate("2019-01-01 00:00:00");
        String sdf = DateUtils._yyyyMMddHHmmss;

        if(genType == 1){
            fCount = sites.size();
            int totalDays = 0;
            for(int i=1;i<=12;i++){
                totalDays += DateUtils.getDays(2017,i);
                totalDays += DateUtils.getDays(2018,i);
            }
            sCount = totalDays;

        }
        else if(genType == 2){
            fCount = sites.size();
            int totalDays = 0;
            for(int i=1;i<=12;i++){
                totalDays += DateUtils.getDays(2017,i);
                totalDays += DateUtils.getDays(2018,i);
            }
            sCount = totalDays * (24 /offset);// 每offset分钟一次
        }
        else if(genType == 3){
            fCount = sites.size();
            int totalDays = 0;
            for(int i=1;i<=12;i++){
                totalDays += DateUtils.getDays(2017,i);
                totalDays += DateUtils.getDays(2018,i);
            }
            sCount = totalDays * 24 * (60/offset);// 每offset分钟一次
        }
        //最终生成的数量应该为 fCount * sCount

        try {
            Files.write(("TRUNCATE `"+tableName+"`;\t\n").getBytes(),dest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i=0;i<fCount;i++){
            StringBuffer sb = new StringBuffer();

            sb.append("INSERT INTO `"+tableName+"`(");
            List<String> dictNameRands = Lists.newArrayList();
            List<Monitofactors> dicts = monitofactorsService.findFactorsBySiteId(sites.get(i).getId());
            for(Monitofactors f:dicts){
                dictNameRands.add(f.getSno());
            }

            for (String code:dictNameRands){
                sb.append(code+"_avg,");
                sb.append(code+"_min,");
                sb.append(code+"_max,");
                sb.append(code+"_cou,");
                sb.append(code+"_flag,");
            }
            //sb.deleteCharAt(sb.length()-1);
            sb.append("monitorsite_id,");
            sb.append("data_time,");
            sb.append(sql_createDate);
            sb.append(")");

            sb.append("\t\n VALUES \t\n");

            for(int k=0;k<sCount;k++){
                sb.append("(");
                Date d = faker.date().between(from,to);
                if(genType == 1){
                    d = future(k*offset, TimeUnit.DAYS,from);
                }
                else if(genType == 2){
                    d = future(k*offset, TimeUnit.HOURS,from);
                }
                else if(genType == 3){
                    d = future(k*offset, TimeUnit.MINUTES,from);
                }

                for (String code:dictNameRands) {
                    //生成的随机数 可以自己调整
                    sb.append("\'"+faker.number().randomDouble(5,1,10)+"\'"+",");
                    sb.append("\'"+faker.number().randomDouble(5,1,10)+"\'"+",");
                    sb.append("\'"+faker.number().randomDouble(5,1,10)+"\'"+",");
                    sb.append("\'"+faker.number().randomDouble(5,1,10)+"\'"+",");
                    sb.append("\'"+"N"+"\'"+",");
                }
                //sb.deleteCharAt(sb.length()-1);
                int randidx = faker.random().nextInt(0,sites.size()-1);
                sb.append("\'"+sites.get(i).getId()+"\'"+",");
                sb.append("\'"+DateUtils.formatDate(d,sdf)+"\'"+",");
                sb.append("\'"+DateUtils.formatDate(d,sdf)+"\'");
                sb.append("),\t\n");
            }
            sb.deleteCharAt(sb.lastIndexOf(","));
            sb.append(";\t\n");
            try {
                //将最终结果写入sql
                Files.append(sb.toString(),dest,Charset.defaultCharset());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private Date future(int atMost, TimeUnit unit, Date referenceDate){
        long upperBound = unit.toMillis((long)atMost);
        long futureMillis = referenceDate.getTime();
        futureMillis += 1L + upperBound;
        return new Date(futureMillis);
    }

    //###############################################################################################################//
    //                                      测试Service方法
    //###############################################################################################################//
    @Autowired
    private MonitofactorsService monitofactorsService;
    @Autowired
    MonitorsiteService monitorsiteService;
    //@Autowired
   // MonitofactorsService monitofactorsService;
    @Autowired
    DeviceinfoDayDataService deviceinfoDayDataService;
    @Autowired
    DevicegpsService devicegpsService;

    public void testFactorService(){
//        List<Long> deviceinfoids = Lists.newArrayList(5L,6L,7L);
//        List<Monitofactors> list = monitofactorsMapper.findFactorsByDeviceInfoIds(deviceinfoids);
//        for(Monitofactors f:list){
//            System.out.println(f);
//        }

//        List<Long> siteids = Lists.newArrayList(3501L,3504L);
//        List<Deviceinfo> list =deviceinfoDao.findDeviceinfosBySiteIds(siteids);
//        for(Deviceinfo f:list){
//            System.out.println(f);
//        }

        List<Monitofactors> list = monitofactorsService.findFactorsBySiteId(3501L);
        for(Monitofactors f:list){
            System.out.println(f.getSno());
        }

//        List<Devicegps> list = devicegpsService.findDevicesByType(1);
//        for(Devicegps f:list){
//            System.out.println(f);
//        }
    }

    @Autowired
    private  DeviceinfoDayDataMapper deviceinfoDayDataMapper;

    public void testDao(){
        Long siteId = 3501L;
        DeviceinfoDataSearchModel s = new DeviceinfoDataSearchModel();
        s.setDateType("hour");
        s.setEchartType("line");
        s.setYear("2017");
        s.setMonth("1");
        s.setDay("1");
        List<Monitofactors> factorlist = monitofactorsService.findFactorsBySiteId(siteId);
        List<String> fNameList = Lists.newArrayList();//("w00000", "w99001", "w20140", "w03002", "w20125", "w20115", "w20128", "w21022", "w01014");
        for(Monitofactors mf:factorlist){
            fNameList.add(mf.getSno());
        }
        s.setFactorList(fNameList);
        s.setMonitorSiteId(siteId);

//        List<DeviceinfoDayData> dlist = deviceinfoDayDataMapper.getStatData(s);
//        System.out.println(dlist.size());
//        for(DeviceinfoDayData data:dlist){
//            //System.out.println(data);
//            String key = fNameList.get(0);
//            String value = (String) ReflectionUtil.getFieldValue(data, StringUtil.underlineToCamelhump(key+"Avg"));
//            System.out.println("key="+key+",value="+value);
//            //String jsonx = JsonUtil.objectToJsonPretty(data).toString();
//            //System.out.println(jsonx);
//        }
        String data = getDateBase(s,false);
        //System.out.println(data);
        //List<DeviceinfoDayData> list = deviceinfoDayDataService.getStatData(s);

        //list.get(0).getMonitorsiteName()
    }

    private String getDateBase(DeviceinfoDataSearchModel s,boolean order){
        String data = Constants.EmptyJsonObject;
        String type = s.getEchartType();
        Long mid = s.getMonitorSiteId();
        List<Monitofactors> dictlist = monitofactorsService.findFactorsBySiteId(mid);//.selectListByMap(paramMap);
        List<String> factors = Lists.newArrayList();
        for (Monitofactors d:dictlist){
            factors.add(d.getSno());
        }

        List<DeviceinfoDayData> list = deviceinfoDayDataService.getStatData(s);

        Map<Integer,Integer> arrMaps= Maps.newHashMap();
        int mi = 0;
        String pre = "全部站点";
        if(mid!=null && mid >0 )
        {
            Monitorsite mm = monitorsiteService.selectByPrimaryKey(mid);
            pre=  mm!=null ? mm.getName() : "";
        }
        System.out.println("factors="+factors.size()+",list="+list.size());

        for (int i=0;i<list.size();i++) {
            DeviceinfoDayData d = list.get(i);
            System.out.println(d.getMonitorsiteName());
            if(!arrMaps.containsKey(d.getYear()))
                arrMaps.put(d.getYear(),mi++);
        }

        String title= s.parseTitleBase(pre);
        String []axis = s.parseAxis(arrMaps);
        int arraySize = axis.length;

        Map<String,Object[]> datalists = Maps.newHashMap();
        for(int k=0;k<factors.size();k++){
            String key = factors.get(k);
            Object dataobj[] = new Object[arraySize];
            datalists.put(key,dataobj);
        }
        //
        Map<String,Object> datas = Maps.newHashMap();
        for (int i=0;i<list.size();i++){
            DeviceinfoDayData d = list.get(i);
            for(int k=0;k<factors.size();k++){
                String key = factors.get(k);
                int idx = s.parseIndex(arrMaps,d);
                String value = (String)ReflectionUtil.getFieldValue(d, StringUtil.underlineToCamelhump(key+"Avg"));
                System.out.println("idx="+idx+","+value+",key="+(key+"Avg")+",year="+d.getYear());
                if(idx>=0 && idx < datalists.get(key).length)
                    datalists.get(key)[idx] = value;
            }
        }
        for(String key:datalists.keySet()){
            datas.put(key.toString(),datalists.get(key));
        }
        System.out.println(datas);
        data= EChartUtil.generateEchartOption(type,title,"平均值",datas,axis,order);

        return data;
    }

}
