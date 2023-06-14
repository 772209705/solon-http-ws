package com.example.funIM.common;

import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.solon.core.handle.Result;
import org.noear.solon.core.route.PathLimiter;
import org.noear.solon.core.route.PathRule;
import org.noear.solon.core.route.RouterInterceptor;
import org.noear.solon.core.route.RouterInterceptorChain;

@Component
public class AdminInterceptorImpl implements RouterInterceptor, PathLimiter {

    @Override
    public PathRule pathRule() {
        return new PathRule().include("/**")
                //
                .exclude("/v1/hello")
                // 登录
                .exclude("/v1/login");
    }

    @Override
    public void doIntercept(Context ctx, Handler mainHandler, RouterInterceptorChain chain) throws Throwable {
        // 检查是否登录
        if(ctx.path().startsWith("/v1/")){
            chain.doIntercept(ctx, mainHandler);
        }
        else{
           ctx.render(Result.failure(401,"账号未登录"));
        }
    }
}