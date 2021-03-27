package dev.mvvasilev.irc.server;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import dev.mvvasilev.irc.server.handler.IRCServerHandler;
import dev.mvvasilev.irc.server.handler.IRCServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import org.apache.commons.cli.CommandLine;

@Singleton
public class IRCServer {

    @Inject
    CommandLine commandLine;

    private Integer port;

    private Integer sslPort;

    private Boolean sslEnabled;

    @Inject
    public IRCServer(CommandLine commandLine) {
        port = Integer.valueOf(commandLine.getOptionValue("port", "6667"));
        sslPort = Integer.valueOf(commandLine.getOptionValue("ssl-port", "7667"));
        sslEnabled = Boolean.valueOf(commandLine.getOptionValue("ssl-enabled", "false"));
    }

    public void start() throws Exception {
        SslContext sslCtx;

        if (sslEnabled) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new IRCServerInitializer(sslCtx))
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            bootstrap.bind(port).sync().channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
