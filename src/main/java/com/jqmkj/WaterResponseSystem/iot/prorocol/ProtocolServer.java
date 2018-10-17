/**
 * 
 */
package com.jqmkj.WaterResponseSystem.iot.prorocol;

import com.jqmkj.WaterResponseSystem.util.Constants;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 说明：自定义协议服务端
 * 先启动服务器端在启动客户端
 */
public class ProtocolServer {

	private int port;
	/**
	 * 
	 */
	public ProtocolServer(int port) {
		this.port = port;
	}

	 public void run() throws Exception {
	        EventLoopGroup bossGroup = new NioEventLoopGroup();
	        EventLoopGroup workerGroup = new NioEventLoopGroup();
	        try {
	            ServerBootstrap b = new ServerBootstrap();
	            b.group(bossGroup, workerGroup)
	             .channel(NioServerSocketChannel.class)
	             .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
	                 @Override
	                 public void initChannel(SocketChannel ch) throws Exception {
	 					ch.pipeline().addLast("decoder",
								new ProtocolDecoder(Constants.MAX_FRAME_LENGTH,
										Constants.LENGTH_FIELD_OFFSET,Constants.LENGTH_FIELD_LENGTH,
										Constants.LENGTH_ADJUSTMENT, Constants.INITIAL_BYTES_TO_STRIP));
	                     ch.pipeline().addLast("encoder", new ProtocolEncoder());
	                     ch.pipeline().addLast(new ProtocolServerHandler());
	                 }
	             })
	             .option(ChannelOption.SO_BACKLOG, 128)
	             .childOption(ChannelOption.SO_KEEPALIVE, true);

	            // 绑定端口，开始接收进来的连接
	            ChannelFuture f = b.bind(port).sync();
	            
	    		System.out.println("Server start listen at " + port );
	    		
	            // 等待服务器  socket 关闭 。
	            // 在这个例子中，这不会发生，但你可以优雅地关闭你的服务器。
	            f.channel().closeFuture().sync();
	            

	        } finally {
	            workerGroup.shutdownGracefully();
	            bossGroup.shutdownGracefully();
	        }
	    }
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = Constants.PORT;
        }
        new ProtocolServer(port).run();
	}

}
