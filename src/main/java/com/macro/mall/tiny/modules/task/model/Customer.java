package com.macro.mall.tiny.modules.task.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author macro
 * @since 2024-03-16
 */
@Getter
@Setter
@Schema(name = "Customer", description = "")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private String phone;

    private String city;

    private String province;

    @Schema(description = "是原有系统上的“运营商”一栏")
    private String source;

    @Schema(description = "记录是谁导入的")
    private String importedBy;

    @Schema(description = "可能的归属有：客户公海、单个客服")
    private Long belong;

    private String category;

    private Date createTime;

    private Date updateTime;
}
