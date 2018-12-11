package com.ddw.demo.io.netty;

import com.ddw.demo.io.Calculator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = ByteBuf.class.cast(msg);

        byte[] req = new byte[in.readableBytes()];
        in.readBytes(req);
        String body = new String(req, "UTF-8");
        System.out.println("收到客户端消息:" + body);
        String result = null;
        try {
            result = Calculator.cal(body).toString();
        } catch (Exception e) {
            result = "错误的表达式：" + e.getMessage();
        }
        ctx.write(Unpooled.copiedBuffer(result.getBytes()));
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
