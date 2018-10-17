package com.jqmkj.WaterResponseSystem.iot.prorocol;

import com.google.common.base.Strings;
import com.jqmkj.WaterResponseSystem.entity.BaseDataEntity;
import com.jqmkj.WaterResponseSystem.entity.DeviceInfoRealTimeData;
import com.jqmkj.WaterResponseSystem.iot.IOTChecker;
import com.jqmkj.WaterResponseSystem.iot.process.IProcesser;
import com.jqmkj.WaterResponseSystem.iot.process.ProcesserWater212;
import com.jqmkj.WaterResponseSystem.util.Constants;
import com.jqmkj.WaterResponseSystem.util.ReflectionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.lang.reflect.Field;
import java.util.List;

/**
 * 说明：自定义协议服务端处理器
 *
 */
public class ProtocolServerHandler extends SimpleChannelInboundHandler<Object> {

	//解析器
	private static ProcesserWater212 processer = new ProcesserWater212();

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, Object obj)
			throws Exception {
        Channel incoming = ctx.channel();

        //将前端传过来的设备解析为java对象
		if(obj instanceof ProtocolMsg) {
			ProtocolMsg msg = (ProtocolMsg)obj;
			String body = msg.getBody();
			System.out.println("Client->Server:"+incoming.remoteAddress()+"="+body);

			//System.out.println(datas.size());
			String error = IOTChecker.checkMessage(msg,processer);
			if(!Strings.isNullOrEmpty(error)){
				System.out.println("error="+error);
				((ProtocolMsg) obj).getProtocolHeader().setContentSize(error.getBytes(CharsetUtil.UTF_8).length);
				((ProtocolMsg) obj).setBody(error);
			}


			if(Strings.isNullOrEmpty(error)){
				List<BaseDataEntity> datas = processer.Process(body);
				for(BaseDataEntity data:datas)
				{
					boolean sflag = IOTChecker.checkDevieId(data.getDeviceId());
					if(!sflag)
						System.out.println("设备编号错误");
					StringBuffer sb = new StringBuffer();
					Field fs[] =data.getClass().getDeclaredFields();
					for(Field f:fs){
						String fname = f.getName();
						boolean flag = false;
						if(data instanceof  DeviceInfoRealTimeData){
							if(fname.endsWith(Constants.FactorTypeEnum.Rtd.value()) || fname.endsWith(Constants.FactorTypeEnum.Flag.value())){
								flag = true;
							}
						}else{
							if(fname.endsWith(Constants.FactorTypeEnum.Avg.value())
									|| fname.endsWith(Constants.FactorTypeEnum.Min.value())
									|| fname.endsWith(Constants.FactorTypeEnum.Max.value())
									|| fname.endsWith(Constants.FactorTypeEnum.Cou.value())
									|| fname.endsWith(Constants.FactorTypeEnum.Flag.value())){
								flag = true;
							}
						}

						if(!flag) continue;;
						String avgvalue = (String)ReflectionUtil.getFieldValue(data,f.getName());
						if(!Strings.isNullOrEmpty(avgvalue))
							sb.append(f.getName()+"="+avgvalue+",");
					}
					sb.append("class="+data.getClass());
					//System.out.println(sb.toString());

					((ProtocolMsg) obj).getProtocolHeader().setContentSize(sb.toString().getBytes(CharsetUtil.UTF_8).length);
					((ProtocolMsg) obj).setBody(sb.toString());
				}
			}

			incoming.write(obj);

		}
	}
	
	@Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
