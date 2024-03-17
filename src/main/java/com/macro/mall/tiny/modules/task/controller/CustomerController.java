package com.macro.mall.tiny.modules.task.controller;

import com.alibaba.excel.EasyExcel;
import com.macro.mall.tiny.common.api.CommonPage;
import com.macro.mall.tiny.common.api.CommonResult;
import com.macro.mall.tiny.common.api.ResultCode;
import com.macro.mall.tiny.modules.task.dto.CustomerDto;
import com.macro.mall.tiny.modules.task.dto.ExportDto;
import com.macro.mall.tiny.modules.task.service.CustomerService;
import com.macro.mall.tiny.modules.task.vo.CustomerVo;
import com.macro.mall.tiny.modules.task.vo.TaskVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;
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
@Tag(name = "CustomerController",description = "客户信息管理")
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

    /**
     * 文件下载（失败了会返回一个有部分数据的Excel）
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link CustomerDto}
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("/download/stencil")
    @Operation(summary = "下载客户信息模板")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("模版", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), CustomerDto.class).sheet("模板").doWrite(Collections.singleton(new CustomerDto()));
    }


    @PostMapping("/export")
    @Operation(summary = "导出客户信息")
    public void export(@RequestBody ExportDto exportDto, HttpServletResponse response) throws IOException{
    }

    @GetMapping("/list")
    @Operation(summary = "获取客户信息列表")
    public CommonResult<CommonPage<CustomerVo>> getCustomerList(){
        List<CustomerDto> ret = customerService.getCustomerList();
        // 根据用户权限获取客户信息列表
        return CommonResult.success(null);
    }
}
