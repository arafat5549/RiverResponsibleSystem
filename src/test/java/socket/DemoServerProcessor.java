package socket;

import com.jqm.ssm.misc.es.TestTrackingData;
import com.jqm.ssm.util.ReflectionUtil;
import org.smartboot.socket.MessageProcessor;
import org.smartboot.socket.StateMachineEnum;
import org.smartboot.socket.transport.AioSession;

import java.io.IOException;

/**
 * Created by Administrator on 2018/7/28
 * stateEvent用于定义AioSession状态机的监控与处理。process则会处理每一个接收到的业务消息
 */
public class DemoServerProcessor implements MessageProcessor<byte[]> {
    @Override
    public void process(AioSession<byte[]> session, byte[] msgBytes) {
        String msg = new String(msgBytes);

        TestTrackingData dataobj = ReflectionUtil.parseTackingDate(msg, TestTrackingData.class);
        String respMsg = msg +"2";
        System.out.println("接受到客户端数据：" + msg + " ,响应数据:" + (respMsg));
        System.out.println(dataobj.toString());
        try {
            session.write(respMsg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stateEvent(AioSession<byte[]> session, StateMachineEnum stateMachineEnum, Throwable throwable) {

    }
}
