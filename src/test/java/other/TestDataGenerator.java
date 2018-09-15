package other;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by wangyaoyao on 2018/8/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
public class TestDataGenerator {
    /*
    //批量插入测试数据
    @Autowired
    Devicedata1Dao data1Dao;
    @Autowired
    DeviceinfoDao deviceinfoDao;
    @Autowired
    DevicegpsDao devicegpsDao;

    List<Devicedata1> insert(int idx,int count,String startDate,String endDate, Set<Integer> idset)
    {
        List<Devicedata1> list = new ArrayList();
        Random r= new Random();
        for (int i =idx + 1;i<=idx+count;i++)
        {
            Date date  = DateUtils.randomDate(startDate, endDate,"yyyy-MM-dd");
            //Date date2 = DateUtils.randomDate("2017-01-01", "2018-08-01","yyyy-MM-dd");
            Devicedata1 data1 = new Devicedata1();
            data1.setId(i);
            data1.setCreatetime(date);
            int devicegpsid = 1+r.nextInt(100);
            data1.setDevicegpsid(devicegpsid);
            data1.setIsreceived((r.nextInt(1) == 0 ? true : false));
            data1.setName("设备名称"+i);
            TestTrackingData d = new TestTrackingData();
            int deviceId = 1 + r.nextInt(100);
            idset.add(deviceId);
            d.setDeviceSN(String.valueOf(deviceId));
            d.setLongitude(r.nextInt(999)+"."+r.nextInt(999));
            d.setLatitude(r.nextInt(999)+"."+r.nextInt(999));
            d.setTemperature(String.valueOf(10+r.nextInt(30)));
            d.setHumidity(String.valueOf(10+r.nextInt(70)));
            d.setTimestamp(DateUtils.formatDateTime(date));
            String dataStr = ReflectionUtil.toTackingDateString(d);
            System.out.println(dataStr);
            data1.setData1(dataStr);
            list.add(data1);
        }
        return list;
    }

    //批量插入
    @Test
    public void insertTest(){

        //Date date_2018  = DateUtils.randomDate("2018-01-01", "2018-09-01","yyyy-MM-dd");
        //Date date_2017  = DateUtils.randomDate("2017-01-01", "2018-01-01","yyyy-MM-dd");
        //Date date_2016  = DateUtils.randomDate("2016-01-01", "2017-01-01","yyyy-MM-dd");
        List<Devicedata1> list = new ArrayList();
        int idx = data1Dao.selectCountByMap(null);
        int count = 100;
        Set<Integer> idset = new HashSet<>();

        List<Devicedata1> l1 = insert(idx,count,"2016-01-01","2017-01-01",idset);
        List<Devicedata1> l2 = insert(idx+count,count,"2017-01-01", "2018-01-01",idset);
        List<Devicedata1> l3 = insert(idx+count*2,count,"2018-01-01", "2018-09-01",idset);
        list.addAll(l1);
        list.addAll(l2);
        list.addAll(l3);

        System.out.println(idset);
        data1Dao.batchInsert(list);
    }
    @Test
    public void miscTest(){
        for (int i =1;i<=10;i++)
        {
            Date randomDate = DateUtils.randomDate("2017-01-01", "2018-08-01","yyyy-MM-dd");
            System.out.println("$$$$$$$$$$"+DateUtils.formatDateTime(randomDate));
        }

    }


//    public static void main(String[] args) {
//        Date randomDate = DateUtils.randomDate("2007-01-01", "2007-03-01","yyyy-MM-dd");
//        System.out.println(DateUtils.formatDateTime(randomDate));
//    }

    */
}
