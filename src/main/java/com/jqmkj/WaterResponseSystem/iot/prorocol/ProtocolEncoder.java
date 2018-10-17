/**
 * 
 */
package com.jqmkj.WaterResponseSystem.iot.prorocol;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * 说明：自定义协议编码器
 *
 */
public class ProtocolEncoder extends MessageToByteEncoder<ProtocolMsg> {


	public ProtocolEncoder() {}

	public ProtocolEncoder(Class<? extends ProtocolMsg> outboundMessageType) {
		super(outboundMessageType);
	}

	public ProtocolEncoder(boolean preferDirect) {
		super(preferDirect);
	}

	public ProtocolEncoder(Class<? extends ProtocolMsg> outboundMessageType,
			boolean preferDirect) {
		super(outboundMessageType, preferDirect);
	}


	@Override
	protected void encode(ChannelHandlerContext ctx, ProtocolMsg msg,ByteBuf out) throws Exception {
		if (msg == null | msg.getProtocolHeader() == null) {
			throw new Exception("The encode message is null");
		}
		ProtocolHeader header = msg.getProtocolHeader();
		String body = msg.getBody();
		byte[] bodyBytes = body.getBytes(CharsetUtil.UTF_8);
		//int bodySize = bodyBytes.length;
		int bodySize = header.getContentSize();
		if(bodyBytes.length != bodySize){
			System.out.println("Encoder Error="+bodySize+","+bodyBytes.length);
		}

		out.writeCharSequence(header.getHead(),CharsetUtil.UTF_8);
		out.writeInt(bodySize);
		out.writeBytes(bodyBytes);
	}

}
