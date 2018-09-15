package other;

import com.jqm.ssm.cache.RedisCache;
import com.jqm.ssm.misc.es.MqttBroker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Created by Administrator on 2018/7/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:spring/spring-redis.xml")
@ContextConfiguration(locations = { "classpath:spring/spring-dao.xml", "classpath:spring/spring-redis.xml" })
public class ESTest {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
//    @Autowired
//    private WaterDepartmentDao deptDao;
    @Autowired
    private RedisCache cache;

    @Autowired
    private MqttBroker mqttBroker;

    @Test
    public void mqttTest(){
        mqttBroker.sendMessage(MqttBroker.TOPICS,"sosomesg");
        //mqttBroker.sendMessage("");
    }

    /**
     * redis缓存测试
     */
    @Test
    public void redisTest(){


//        List<WaterDepartment> result_cache = cache.getListCache(cache_key, WaterDepartment.class);
//        //cache.putListCacheWithExpireTime(cache_key, result_cache, RedisCache.CAHCETIME);
//        if (result_cache != null) {
//            LOG.info("get cache with key:" + cache_key);
//        } else {
//            //Map<Object, Object> map = new HashMap<>();
//            result_cache = deptDao.selectListByMap(null);
//            cache.putListCacheWithExpireTime(cache_key, result_cache, RedisCache.CAHCETIME);
//            LOG.info("put cache with key:" + cache_key);
//            System.out.println(result_cache);
//        }
//        String cache_key = "logstash-list"; //RedisCache.CAHCENAME + "|Test|";
//        cache.deleteCache(cache_key);
//        List<MyMessage> result_cache = new ArrayList<>();
//        MyMessage m1 = new MyMessage();
//        m1.setMyField("@测试1------------");
//
//        MyMessage m2 = new MyMessage();
//        m2.setMyField("@测试2");
//        result_cache.add(m1);
//        result_cache.add(m2);


        //cache.putListCacheWithExpireTime(cache_key, result_cache, RedisCache.CAHCETIME);
         //cache.putUnBuffedListCacheWithExpireTime(cache_key, result_cache, RedisCache.CAHCETIME);

//        String str =  JSON.toJSONString(result_cache);
//        LOG.info(str);
    }
}
