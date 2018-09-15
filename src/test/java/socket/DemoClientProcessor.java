package socket;

import org.smartboot.socket.MessageProcessor;
import org.smartboot.socket.StateMachineEnum;
import org.smartboot.socket.transport.AioSession;

/**
 * Created by wangyaoyao on 2018/7/28.
 */
public class DemoClientProcessor implements MessageProcessor<byte[]> {
    private AioSession<byte[]> session;

    @Override
    public void process(AioSession<byte[]> session, byte[] msg) {
        System.out.println("接受到服务端响应数据：" + new String(msg));
    }

    @Override
    public void stateEvent(AioSession<byte[]> session, StateMachineEnum stateMachineEnum, Throwable throwable) {
        switch (stateMachineEnum) {
            case NEW_SESSION:
                this.session = session;
                break;
            default:
                System.out.println("other state:" + stateMachineEnum);
        }

    }

    public AioSession<byte[]> getSession() {
        return session;
    }
}