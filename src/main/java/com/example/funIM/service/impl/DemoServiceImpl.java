package com.example.funIM.service.impl;

import com.example.funIM.common.AppResult;
import com.example.funIM.mapper.DemoMapper;
import com.example.funIM.model.DemoVO;
import com.example.funIM.service.DemoService;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.ProxyComponent;
import org.noear.solon.data.annotation.Tran;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

@Log4j2
@ProxyComponent
public class DemoServiceImpl implements DemoService {

    @Db
    DemoMapper demoMapper;

    @Inject
    RedissonClient redissonClient;

    @Tran
    public AppResult<DemoVO> getNote(Long noteId) {
        // 数据库
        DemoVO demoModel = demoMapper.DemoQuery(noteId);
        // redis
        RBucket<DemoVO> bucket = redissonClient.getBucket("myBucket");

        bucket.set(demoModel);
        DemoVO storedObject = bucket.get();

        System.out.println(storedObject);

        redissonClient.getBucket("demo").set("dsfhaskjf你好");
        String demo = redissonClient.getBucket("demo").get().toString();
        System.out.println(demo);

        return AppResult.success(storedObject,"成功");
    }
}
