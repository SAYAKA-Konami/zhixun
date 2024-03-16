package com.macro.mall.tiny.modules.task.dto;

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
    private String name;
    @Schema(description = "用户手机号")
    @Size(min = 11, max = 11, message = "手机号长度必须为11位")
    private String phone;
    @Schema(description = "用户城市")
    private String city;
    @Schema(description = "用户省份")
    private String province;

    @Schema(description = "是原有系统上的“运营商”一栏")
    private String source;
    @NotNull
    @Schema(description = "记录是谁导入的")
    private Long importedBy;
    @NotNull
    @Schema(description = "可能的归属有：客户公海、单个客服")
    private Long belong;
    @NotNull
    private String category;
}
