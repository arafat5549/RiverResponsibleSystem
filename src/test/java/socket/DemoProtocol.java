package socket;

import org.smartboot.socket.Protocol;
import org.smartboot.socket.transport.AioSession;

import java.nio.ByteBuffer;

/**
 * Created by Administrator on 2018/7/28.
 * 自定义传输协议
 *
 */
public class DemoProtocol  implements Protocol<byte[]> {

   private static final int INTEGER_BYTES = Integer.SIZE / Byte.SIZE;

    @Override
    public byte[] decode(ByteBuffer readBuffer, AioSession<byte[]> session, boolean eof) {

        int remaining = readBuffer.remaining();
        if (remaining < INTEGER_BYTES) {
            return null;
        }
        int messageSize = readBuffer.getInt(readBuffer.position());
        if (messageSize > remaining) {
            return null;
        }
        byte[] data = new byte[messageSize - INTEGER_BYTES];
        readBuffer.getInt();
        readBuffer.get(data);
        return data;
    }

    @Override
    public ByteBuffer encode(byte[] msg, AioSession<byte[]> session) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(msg.length+INTEGER_BYTES);
        byteBuffer.putInt(byteBuffer.capacity());
        byteBuffer.put(msg);
        byteBuffer.flip();
        return byteBuffer;
    }
}
