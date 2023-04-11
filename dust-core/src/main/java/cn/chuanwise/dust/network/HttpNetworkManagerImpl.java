package cn.chuanwise.dust.network;

import cn.chuanwise.dust.network.http.HttpNetworkConfiguration;
import cn.chuanwise.dust.network.http.HttpRequestHandler;
import com.google.common.base.Preconditions;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.net.InetSocketAddress;

public class HttpNetworkManagerImpl
    implements NetworkManager {
    
    private final HttpNetworkConfiguration configuration;
    
    public static class HttpServerInitializer
        extends ChannelInitializer<SocketChannel> {
        
        private static HttpServerInitializer INSTANCE = new HttpServerInitializer();
    
        public static HttpServerInitializer getInstance() {
            return INSTANCE;
        }
        
        private HttpServerInitializer() {
        }
    
        @Override
        protected void initChannel(SocketChannel channel) throws Exception {
            final ChannelPipeline pipeline = channel.pipeline();
    
            pipeline.addLast(new HttpServerCodec());
            pipeline.addLast("httpAggregator", new HttpObjectAggregator(512 * 1024));
            pipeline.addLast(new HttpRequestHandler());
        }
    }
    
    public HttpNetworkManagerImpl(HttpNetworkConfiguration configuration) {
        Preconditions.checkNotNull(configuration, "Network configuration is null!");
    
        this.configuration = configuration;
    }
    
    public void bind() throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        bootstrap.group(boss,work)
            .handler(new LoggingHandler(LogLevel.DEBUG))
            .channel(NioServerSocketChannel.class)
            .childHandler(HttpServerInitializer.getInstance());
    
        final InetSocketAddress serverAddress = configuration.getServerAddress();
        ChannelFuture f = bootstrap.bind(serverAddress).sync();
        System.out.println("server start up on port : " + serverAddress.getPort());
        f.channel().closeFuture().sync();
    }
    
    @Override
    public HttpNetworkConfiguration getConfiguration() {
        return configuration;
    }
}
