package com.jqm.ssm;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.jqm.ssm.dao.DevicegpsDao;
import com.jqm.ssm.dao.RoleDao;
import com.jqm.ssm.dto.BaseGpsResult;
import com.jqm.ssm.dto.GpsFeature;
import com.jqm.ssm.entity.Brand;
import com.jqm.ssm.entity.Devicegps;
import com.jqm.ssm.entity.User;
import com.jqm.ssm.service.IBrandService;
import com.jqm.ssm.service.IDevicegpsService;
import com.jqm.ssm.service.IUserService;
import com.jqm.ssm.util.JsonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wangyaoyao on 2018/8/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-redis.xml","classpath:spring/spring-service.xml"})
public class UserServiceTest {
    @Autowired
    IUserService userService;
    @Autowired
    IDevicegpsService devicegpsService;
    @Autowired
    IBrandService brandService;

    @Autowired
    RoleDao roleDao;
    @Autowired
    DevicegpsDao devicegpsDao;


    @Test
    public void devicegpsTest(){
        System.out.println("-----------------------------------------------------");
        List<Devicegps>  list= devicegpsDao.getDeviceinfoListByCid(7);
        for (Devicegps gps:
                list) {
            System.out.println(gps+","+gps.getDeviceinfoName()+","+gps.getMonitorsiteName());
            System.out.println();
        }
        System.out.println("-----------------------------------------------------");
    }

    @Test
    public  void batchUserTest(){
        User operator = userService.selectByPrimaryKey(1);

        for (int i=0;i<100;i++){
            User user = new User();
            user.setFullname("测试用户"+i);
            user.setUsername("test:"+i);
            user.setPassword("123456");
            user.setGender(true);
            user.setDeleteFlag(false);
            user.setDepartmentId(1);
            user.setIsAdmin(false);
            user.setIsLock(false);
            userService.createUser(user,operator);
            //userService.insertSelective(user);
        }
    }

    @Test
    public void brandTest(){
        User operator = userService.selectByPrimaryKey(1);
        Brand brand = new Brand();
        for (int i=0;i<100;i++){
            brand.setName("测试数据"+i);
            brand.setEname("TestData:"+i);
            brand.setWebsite("www.test"+i+".com");
            brand.setLetter("A");

//            Date d = new Date();
//            brand.setDeleteFlag(false);
//            brand.setCreatePerson(operator.getUsername());
//            brand.setUpdatePerson(operator.getUsername());
//            brand.setCreateDate(d);
//            brand.setUpdateDate(d);
            brandService.createBrand(brand,operator);
            //brandService.insertSelective(brand);
        }

    }

    @Test
    public void devicegpsServiceTest(){
        List<Devicegps> list=devicegpsService.selectListByMap(null);
        List<GpsFeature> glist = Lists.newArrayList();
        for (Devicegps devicegps:list){
            GpsFeature<Devicegps> g = new GpsFeature<Devicegps>(devicegps);
            //String json = JSON.toJSONString(g);
            glist.add(g);
           // System.out.println( JSON.toJSONString(g));
            //System.out.println(json2);
            //
            //System.out.println(g);
           // System.out.println(devicegps);
        }
        BaseGpsResult reesult = new BaseGpsResult(glist);
//        String json =  JSON.toJSONString(reesult);
//        System.out.println(json);
//        System.out.println();
//        System.out.println();
        String json2 =  JsonUtil.objectToJsonPretty(reesult).toString();
        System.out.println(json2);
        System.out.println();
        System.out.println();

       // BaseGpsResult<User> obj = (BaseGpsResult<Devicegps>)JSON.parseObject(js, Devicegps.class);

        String json3 =  JsonUtil.convertObj2json(reesult).toString();
        System.out.println(json3);
    }
    @Test
    public void userServicetest(){
//        List<User> lists =  userService.selectListByMap(null);
//        System.out.println(lists);


        Map<Object,Object> map = Maps.newHashMap();
        map.put("name","arafat5549");
        //.out.println(map);
       // List<User> userlist =  userService.selectListByMap(map);
        //System.out.println(userlist);

//        List<Role> roleList =   roleDao.selectListByMap(null);
//        System.out.println(roleList);

//        User  user1 = userService.selectObjByMap(map);
//        System.out.println(user1);
//        role.setUser(user1);
//        List<Role> roleList =roleDao.findRoleList(role);
//        System.out.println(roleList);

//        Map<Object,Object> map2 = Maps.newHashMap();
//        map2.put(User.Column.logname,"wyy");
//        User  user2 = userService.selectObjByMap(map2);
//        //List<Role> roleList2 =roleDao.findRoleList(user2);
//        System.out.println(user2.getRoleList());
//        System.out.println(user2.getDepartment());
//        System.out.println(User.Column.logname);

        List<User> userlist = userService.listPage(0, 1,null);
        System.out.println(userlist.size());
        System.out.println(userlist);
    }

}
