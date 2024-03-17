package com.macro.mall.tiny.modules.task.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"name", "phone"})
@Schema(name = "CustomerDto", description = "导入用户")
public class CustomerDto {
    @Schema(description = "用户姓名")
    @NotNull
    @ExcelProperty("姓名")
    private String name;

    @Schema(description = "用户手机号")
    @Size(min = 11, max = 11, message = "手机号长度必须为11位")
    @ExcelProperty("手机号")
    private String phone;

    @Schema(description = "用户城市")
    @ExcelProperty("城市")
    private String city;

    @ExcelProperty("省份")
    @Schema(description = "用户省份")
    private String province;

    @ExcelProperty("运营商")
    @Schema(description = "是原有系统上的“运营商”一栏")
    private String source;

    @ExcelProperty("归属")
    @NotNull
    @Schema(description = "可能的归属有：客户公海、单个客服")
    private Long belong;

    @ExcelProperty("类别")
    @NotNull
    private String category;
}
