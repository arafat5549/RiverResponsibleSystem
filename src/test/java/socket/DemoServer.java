package socket;

import org.smartboot.socket.transport.AioQuickServer;

import java.io.IOException;

/**
 * Created by Administrator on 2018/7/28.
 * TCP协议server
 */
public class DemoServer {
    public static void main(String[] args) throws IOException {
        AioQuickServer<byte[]> server = new AioQuickServer<byte[]>(8888, new DemoProtocol(), new DemoServerProcessor());
        server.start();
    }
}
