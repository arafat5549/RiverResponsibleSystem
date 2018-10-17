package com.jqmkj.WaterResponseSystem.iot.prorocol;

import com.google.common.collect.Lists;
import com.jqmkj.WaterResponseSystem.iot.IOTUtils;
import com.jqmkj.WaterResponseSystem.iot.message.WaterDeviceData;
import com.jqmkj.WaterResponseSystem.util.Constants;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.List;

//解析协议客户端
//自定义协议客户端
public class ProtocolClient {
    private static  List<String> site3501Factors = Lists.newArrayList(
            "w01014",
            "w01017",
            "w01019",
            "w02003",
            "w19001",
            "w19002",
            "w20023",
            "w20038",
            "w20113",
            "w20115",
            "w20117",
            "w20119",
            "w20120",
            "w20121",
            "w20122",
            "w20123",
            "w20124",
            "w20125",
            "w20139",
            "w21001",
            "w21003",
            "w21004",
            "w21011",
            "w21022",
            "w22001"
    );


    private String no;
    private String host;
    private int port;

    public ProtocolClient(String no,String host, int port) {
        this.no=no;
        this.host = host;
        this.port = port;
    }

    private String genrateMessage(boolean flag,long count){
        StringBuffer sb = new StringBuffer();
        if(!flag){
            sb.append("##");
            String body = "床前明月光,疑是地上霜;";
            for (int i = 0; i < 5; i++) {
                sb.append(body);
            }
        }else{
            WaterDeviceData waterDeviceData = new WaterDeviceData(host,port);
            waterDeviceData.setFullFactor(true);
            waterDeviceData.setDeviceId(no);
            List<String> factors = null;
            String cncode = Constants.DateTypeEnum.realtime.code();
            if(count%3==0)
            {
                factors = site3501Factors;
                cncode = Constants.DateTypeEnum.day.code();
            }
            else if(count%4==0){
                factors = site3501Factors;
                cncode = Constants.DateTypeEnum.hour.code();
            }
            String body = IOTUtils.generateMessage(waterDeviceData,cncode,factors);
            sb.append(body);
        }
        return  sb.toString();
    }

    public void run() throws InterruptedException {

        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true); //保持连接
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(
                            "decoder",
                            new ProtocolDecoder(Constants.MAX_FRAME_LENGTH,
                                    Constants.LENGTH_FIELD_OFFSET, Constants.LENGTH_FIELD_LENGTH,
                                    Constants.LENGTH_ADJUSTMENT, Constants.INITIAL_BYTES_TO_STRIP));
                    ch.pipeline().addLast("encoder", new ProtocolEncoder());
                    ch.pipeline().addLast(new ProtocolClientHandler());

                }
            });

            // 启动客户端
            ChannelFuture f = b.connect(host, port).sync();
            System.out.println("客户端【"+no+"】启动于 "+ host+":" + port );
            long count = 0;
            while (true) {

                // 发送消息给服务器
                ProtocolMsg msg = new ProtocolMsg();
                ProtocolHeader protocolHeader = new ProtocolHeader();

                protocolHeader.setHead(Constants.NettyWater212Enum.HEADER_INIT.sname());

                String body = genrateMessage(true,count++);//(count++)%5==0


                byte[] bodyBytes = body.getBytes(CharsetUtil.UTF_8);
                int bodySize = bodyBytes.length;
                protocolHeader.setContentSize(bodySize);
                //System.out.println("bodySize="+bodySize);
                msg.setProtocolHeader(protocolHeader);
                msg.setBody(body.toString());
                f.channel().writeAndFlush(msg);
                Thread.sleep(3000);
            }
            // 等待连接关闭
            // f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    /**
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        new ProtocolClient("huantai_device_0021",Constants.REMOTEIP, Constants.PORT).run();
    }

}
