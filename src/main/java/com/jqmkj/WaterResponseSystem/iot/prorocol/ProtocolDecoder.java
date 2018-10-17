package com.jqmkj.WaterResponseSystem.iot.prorocol;

import com.jqmkj.WaterResponseSystem.util.Constants;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.CharsetUtil;

/**
 * 说明：自定义协议解码器
 */
public class ProtocolDecoder extends LengthFieldBasedFrameDecoder {


	public ProtocolDecoder(int maxFrameLength, int lengthFieldOffset,
			int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength,lengthAdjustment, initialBytesToStrip);
 	}

	public ProtocolDecoder(int maxFrameLength, int lengthFieldOffset,
			int lengthFieldLength, int lengthAdjustment,
			int initialBytesToStrip, boolean failFast) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength,lengthAdjustment, initialBytesToStrip, failFast);
	}

 
	@Override
	protected ProtocolMsg decode(ChannelHandlerContext ctx, ByteBuf in2) throws Exception {
		ByteBuf in = (ByteBuf) super.decode(ctx, in2);
		if (in == null) {
		    return null;
		}
		
		if (in.readableBytes() < Constants.LENGTH_FIELD_LENGTH + Constants.LENGTH_FIELD_OFFSET) {
			return null;// response header is 6 bytes
		}

		CharSequence head= in.readCharSequence(Constants.HEADER_INIT_SIZE, CharsetUtil.UTF_8);
		int len       = in.readInt();

		if (in.readableBytes() < len) {
			System.out.println("Decoder Error="+len+","+in.readableBytes());
			return null; // until we have the entire payload return
		}
		ByteBuf buf = in.readBytes(len);
		byte[] req = new byte[buf.readableBytes()];
		buf.readBytes(req);
		String body = new String(req, CharsetUtil.UTF_8);
		ProtocolMsg msg = new ProtocolMsg();
		ProtocolHeader protocolHeader = new ProtocolHeader(head, len);
		msg.setBody(body);
		msg.setProtocolHeader(protocolHeader);
		return msg;
	}
}
