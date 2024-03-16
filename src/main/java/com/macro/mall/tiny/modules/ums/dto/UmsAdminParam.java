package com.macro.mall.tiny.modules.ums.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

/**
 * 用户登录参数
 * Created by macro on 2018/4/26.
 */
@Getter
@Setter
public class UmsAdminParam {
    @NotEmpty
    @Schema(title = "用户名", required = true)
    private String username;
    @NotEmpty
    @Schema(title = "密码", required = true)
    private String password;
    @Schema(title = "用户头像")
    private String icon;
    @Email
    @Schema(title = "邮箱")
    private String email;
    @Schema(title = "用户昵称")
    @Size(min = 11, max = 11, message = "手机号码长度不正确")
    private String phone;
    @Schema(title = "备注")
    private String note;
}
