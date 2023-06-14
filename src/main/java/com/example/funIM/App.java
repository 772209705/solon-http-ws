package com.example.funIM;

import org.noear.solon.Solon;
import org.noear.solon.annotation.SolonMain;
import org.noear.solon.web.cors.CrossFilter;

@SolonMain
public class App {
    public static void main(String[] args) {
        Solon.start(App.class, args, app->{
            // 启用 WebSocket 服务
            app.enableWebSocket(true);

            // 跨域 增加全局处理（用过滤器模式）//对静态资源亦有效 //加-1 优先级更高
            app.filter(-1,
                    new CrossFilter()
                            .allowedOrigins("*")
                            .allowedMethods("*")
                            .allowedHeaders("*")
                            .exposedHeaders("*")
                            .allowCredentials(true)
                        );
        });
    }
}