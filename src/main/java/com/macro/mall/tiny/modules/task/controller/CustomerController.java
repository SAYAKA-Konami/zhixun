package com.macro.mall.tiny.modules.task.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.modules.task.dto.CustomerDto;
import com.macro.mall.tiny.modules.task.vo.TaskVo;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author macro
 * @since 2024-03-13
 */
@RestController
@RequestMapping("/task/customer")
public class CustomerController {



    @PostMapping("/upload")
    public CommonResult<List<CustomerDto>> uploadCustomerInfo(@Validated @RequestBody TaskVo taskVo,
                                                              @RequestParam MultipartFile file){
        return null;
    }
}
