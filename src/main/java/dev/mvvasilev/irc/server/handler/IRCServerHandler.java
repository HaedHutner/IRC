package dev.mvvasilev.irc.server.handler;

import com.google.inject.Inject;
import dev.mvvasilev.irc.server.IRCServer;
import dev.mvvasilev.irc.server.command.CommandService;
import dev.mvvasilev.irc.server.command.user.UserCommand;
import dev.mvvasilev.irc.server.command.user.UserCommandParser;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import java.net.InetAddress;

public class IRCServerHandler extends SimpleChannelInboundHandler<String> {

    @Inject
    CommandService commandService;

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.write("Welcome to " + InetAddress.getLocalHost().getHostName() + "!\r\n");
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        commandService.interpret(msg);

        System.out.println("Received: \"" + msg + "\". Echoing back");
        ctx.writeAndFlush(msg + System.lineSeparator() + "> ");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
