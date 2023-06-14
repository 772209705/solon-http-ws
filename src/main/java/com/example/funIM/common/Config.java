package com.example.funIM.common;

import com.zaxxer.hikari.HikariDataSource;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import org.redisson.api.RedissonClient;
import org.redisson.solon.RedissonSupplier;

import javax.sql.DataSource;

@Configuration
public class Config{
    // 同时支持 name 和 类型 两种方式注入（注入时没有name，即为按类型注入）
    //
    @Bean(value = "db1", typed = true)
    public DataSource dataSource(@Inject("${jdbc.db1}") HikariDataSource ds){
        return ds;
    }

    // Redis
    @Bean(value = "rdb1",typed = true)
    public RedissonClient redisClient(@Inject("${redisson.redis.db1}") RedissonSupplier supplier) {
        return supplier.get();
    }

}