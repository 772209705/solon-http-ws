package com.example.funIM.common;

import lombok.extern.log4j.Log4j2;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;


@Log4j2
@Component(index = 0) //index 为顺序位（不加，则默认为0）
public class AppFilter implements Filter {

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        // 开始计时（用于计算响应时长）
        long start = System.currentTimeMillis();
        try {
            // 记录请求数据日志
            getRequestData(ctx);

            // 执行处理
            chain.doFilter(ctx);

            // 未处理设为404状态
            if (!ctx.getHandled()) {
                ctx.status(404);
            }

        } catch (Throwable e) {
            // 异常捕捉与控制（并标为500错误）
            ctx.status(500);
            log.error("{}", e);
        }

        // 获得接口响应时长
        long times = System.currentTimeMillis() - start;
        log.info("需要时间：" + times);
    }

    private void getRequestData(Context ctx) {
        log.info("IP: {}, 参数: {}, 路径: {} ", ctx.realIp(), ctx.paramsMap(), ctx.path());
    }


}  