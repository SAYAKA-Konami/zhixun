package com.macro.mall.tiny.modules.upload.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(of = "id")
@TableName("customer")
@Schema(title = "Customer对象", description = "客户信息")
public class Customer {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @Schema(title = "名称")
    private String name;
    private String phone;
    private String city;
    private String province;
    @Schema(title = "是原来的系统上的“运营商”一栏")
    private String source;
    @Schema(title = "记录是谁导入的")
    private Long importedBy;
    @Schema(title = "可能的归属有：客户公海、单个客服")
    private Long belong;
    @Schema(title = "用户类型。")
    private String category;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;
}
