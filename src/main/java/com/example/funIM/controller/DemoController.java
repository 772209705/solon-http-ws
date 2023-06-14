package com.example.funIM.controller;


import com.example.funIM.common.AppResult;
import com.example.funIM.model.DemoVO;
import com.example.funIM.service.DemoService;
import org.noear.solon.annotation.*;

@Mapping("v1")
@Controller
public class DemoController {

    @Inject
    DemoService demoService;

    @Get
    @Mapping("/note")
    public AppResult<DemoVO> note(Long noteId) {
        return demoService.getNote(noteId);
    }
}