package org.yzh.framework.session;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFutureListener;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhihao.ye (1527621790@qq.com)
 * @home http://gitee.com/yezhihao/jt-server
 */
public enum SessionManager {

    Instance;

    public static SessionManager getInstance() {
        return Instance;
    }

    private Map<String, Session> terminalIdMap = new ConcurrentHashMap<>();

    private ChannelFutureListener remover = future -> {
        Session session = future.channel().attr(Session.KEY).get();
        if (session != null)
            terminalIdMap.remove(session.getTerminalId());
    };

    public Session get(String terminalId) {
        return terminalIdMap.get(terminalId);
    }

    protected void put(String terminalId, Session session) {
        Channel channel = session.channel;
        boolean added = terminalIdMap.putIfAbsent(terminalId, session) == null;
        if (added) {
            channel.closeFuture().addListener(remover);
        }
    }
}