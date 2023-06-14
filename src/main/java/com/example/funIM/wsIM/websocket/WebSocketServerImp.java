package com.example.funIM.wsIM.websocket;

import lombok.SneakyThrows;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.ServerEndpoint;
import org.noear.solon.core.message.Listener;
import org.noear.solon.core.message.Message;
import org.noear.solon.core.message.Session;
import org.redisson.api.RedissonClient;

import java.util.concurrent.ConcurrentHashMap;


@ServerEndpoint(path = "/ws")
public class WebSocketServerImp implements Listener {

    public static ConcurrentHashMap<Object, Session> userChannelMap = new ConcurrentHashMap<>(16);

    @Inject
    RedissonClient redissonClient;

    @Override
    public void onOpen(Session session) {
        String token = session.param("token");
        String userId = redissonClient.getBucket(token).get().toString();
        System.out.println(userId);
        userChannelMap.put(userId, session);

    }

    @Override
    public void onMessage(Session session, Message message) {
        message.setHandled(true); //设为true，则不进入mvc路由
        String token = session.param("token");
        System.out.println(token);
        Session uSession = userChannelMap.get(token);
        uSession.send(message.bodyAsString());

    }

    @SneakyThrows
    @Override
    public void onClose(Session session) {
        System.out.println("断开连接");
        String token = session.param("token");
        String user = redissonClient.getBucket(token).get().toString();
        session.close();
        userChannelMap.remove(user);

        for (Session uSession : userChannelMap.values()) {
            uSession.send("用户：" + user + " 离开聊天室");
        }
    }

}