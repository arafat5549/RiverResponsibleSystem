package socket;

import org.smartboot.socket.transport.AioQuickClient;

/**
 * Created by wangyaoyao on 2018/7/28.
 * TCP协议客户端
 */
public class DemoClient {
    public static void main(String[] args) throws Exception {
        DemoClientProcessor processor = new DemoClientProcessor();
        AioQuickClient<byte[]> aioQuickClient = new AioQuickClient<byte[]>("localhost", 8888, new DemoProtocol(), processor);
        aioQuickClient.start();
        for (int i =0 ;i<5;i++)
        {
            String rawdata = "deviceSN:1123,latitude:123.333";
            //String sendMsg = "";
            processor.getSession().write(rawdata.getBytes());
            Thread.sleep(1000);
        }
        aioQuickClient.shutdown();
    }
}
