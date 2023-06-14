package com.example.funIM.mapper;

import com.example.funIM.model.DemoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DemoMapper {
    DemoVO DemoQuery(Long id);

    List<DemoVO> DemoQueryALL();
}
