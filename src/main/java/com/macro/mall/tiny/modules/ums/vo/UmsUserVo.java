package com.macro.mall.tiny.modules.ums.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Schema(title = "用户", description = "用户表")
public class UmsUserVo {
    private Long id;

    private String username;

    @Schema(title = "头像")
    private String icon;

    @Schema(title = "邮箱")
    private String email;

    @Schema(title = "昵称")
    private String phone;

    @Schema(title = "备注信息")
    private String note;

    @Schema(title = "创建时间")
    private Date createTime;

    @Schema(title = "帐号启用状态：0->禁用；1->启用")
    private Integer status;

}
