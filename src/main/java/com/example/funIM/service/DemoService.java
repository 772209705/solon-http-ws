package com.example.funIM.service;

import com.example.funIM.common.AppResult;
import com.example.funIM.model.DemoVO;


public interface DemoService {

    AppResult<DemoVO> getNote(Long noteId);

}
