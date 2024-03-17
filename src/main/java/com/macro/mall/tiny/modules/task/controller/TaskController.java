package com.macro.mall.tiny.modules.task.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.task.dto.PageRequestDto;
import com.macro.mall.tiny.modules.task.model.Task;
import com.macro.mall.tiny.modules.task.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author macro
 * @since 2024-03-13
 */
@RestController
@RequestMapping("/admin/task")
@Tag(name = "TaskController",description = "任务管理")
public class TaskController {
    @Resource
    private TaskService taskService;

    @PostMapping("/getAll")
    @Operation(summary = "获取所有任务")
    public CommonResult<CommonPage<Task>> getAllTask(@RequestBody PageRequestDto pageRequestDto){
        Page<Task> page = Page.of(pageRequestDto.getPageNum(), pageRequestDto.getPageSize());
        Page<Task> ret = taskService.page(page);
        CommonPage<Task> result = CommonPage.restPage(ret);
        return CommonResult.success(result);
    }

}
