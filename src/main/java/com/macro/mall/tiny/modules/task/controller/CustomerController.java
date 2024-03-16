package com.macro.mall.tiny.modules.task.controller;

import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.common.api.ResultCode;
import com.macro.mall.tiny.modules.task.dto.CustomerDto;
import com.macro.mall.tiny.modules.task.service.CustomerService;
import com.macro.mall.tiny.modules.task.vo.TaskVo;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

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

    @Resource
    private CustomerService customerService;

    @PostMapping("/upload")
    @Operation(summary = "上传客户信息")
    public CommonResult<List<CustomerDto>> uploadCustomerInfo(@Validated @RequestBody TaskVo taskVo,
                                                              @RequestParam MultipartFile file){
        Optional<List<CustomerDto>> failedRet = customerService.parseExcelAndSave(file, taskVo);
        if (failedRet.isEmpty()) {
            return CommonResult.failed(CommonResult.SERVER_ERROR);
        }
        List<CustomerDto> failedInsert = failedRet.get();
        if (failedInsert.isEmpty()) {
            return CommonResult.success(failedInsert);
        }else{
            return CommonResult.failed(ResultCode.FAILED, "部分数据插入失败", failedInsert);
        }
    }


}
