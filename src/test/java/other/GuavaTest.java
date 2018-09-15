package other;

import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import org.junit.Test;

//import java.util.Base64;

//import java.util.Base64;


/**
 * Created by Administrator on 2018/7/19.
 */
public class GuavaTest {
//    @Test
//    public void josnTest(){
//        User user = new User();
//        user.setName("arafat5549");
//        user.setLogname("admin");
//        user.setPassword("123456");
//        System.out.println(JSON.toJSON(user));
//        BaseRequestBody body = new BaseRequestBody("token:abcdefg",user);
//        System.out.println(JSON.toJSON(body));
//
//        Department dept = new Department();
//        dept.setName("测试部门");
//        //dept.setParentx(0);
//        dept.setIsparent(false);
//        dept.setLevel((short)1);
//        BaseRequestBody body2 = new BaseRequestBody("token:deparment",dept);
//        System.out.println(JSON.toJSON(body2));
//    }
    @Test
    public void test() {
        String input = "hello, world";
        // 计算MD5
        System.out.println(Hashing.md5().hashBytes(input.getBytes()).toString());
        // 计算sha256
        System.out.println(Hashing.sha256().hashBytes(input.getBytes()).toString());
        // 计算sha512
        System.out.println(Hashing.sha512().hashBytes(input.getBytes()).toString());
        // 计算crc32
        System.out.println(Hashing.crc32().hashBytes(input.getBytes()).toString());

        System.out.println(Hashing.md5().hashUnencodedChars(input).toString());

        String base64=BaseEncoding.base64().encode(input.getBytes());
        System.out.println(base64);
        System.out.println(new String(BaseEncoding.base64().decode(base64)));

        //IntMath.
        //Base64.getUrlDecoder()
//        String asB64 = Base64.getEncoder().encodeToString(input.getBytes("utf-8"));
//        System.out.println(asB64);
    }
}
