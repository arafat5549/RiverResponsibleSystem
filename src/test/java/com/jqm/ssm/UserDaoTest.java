package com.jqm.ssm;

import com.jqm.ssm.dao.UserDao;
import com.jqm.ssm.entity.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 
 * UserTest DAO测试类
 *
 * @author wang
 */
public class UserDaoTest {

	private UserDao mapper;

	@Before
    public void prepare() throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/spring-dao.xml");
        mapper = (UserDao) ctx.getBean("userDao");
    }

	@Test
	public void selectListByMapTest() {
		List<User> ret = mapper.selectListByMap(null);
		System.out.println("测试selectListByMap："+ret);
	}

	
	//@Test
	//public void deleteLogicByIdsTest() {
		//Integer deleteFlag, Integer[] ids
		//return dao.deleteLogicByIds(deleteFlag, ids);
	//}

	//@Test
	//public void insertTest() {
		//User record =new User();
		//return dao.insert(record);
	//}

	//@Test
	//public void insertSelectiveTest() {
	    //User record
		//return dao.insertSelective(record);
	//}

	//@Test
	//public void updateByPrimaryKeySelectiveTest() {
		//User record
		//return dao.updateByPrimaryKeySelective(record);
	//}

	//@Test
	//public void updateByPrimaryKey() {
		//User record
		//return dao.updateByPrimaryKey(record);
	//}
}
