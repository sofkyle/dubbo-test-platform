package com.kc.dtp.remoting;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.remoting.ChannelHandler;
import org.apache.dubbo.remoting.RemotingException;
import org.apache.dubbo.remoting.transport.netty4.NettyClient;

/**
 * @author: Kyle
 */
public class Netty4Invoker {
    private final NettyClient nettyClient;

    public Netty4Invoker(final URL url, final ChannelHandler handler) throws RemotingException {
        nettyClient = new NettyClient(url, handler);
    }
}
