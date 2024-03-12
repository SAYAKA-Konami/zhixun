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
 * @since 2024-03-13
 */
@Getter
@Setter
@Schema(name = "Task", description = "任务")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "父任务")
    private Long parentId;

    private String name;

    @Schema(description = "备注")
    private String mark;

    @Schema(description = "-2 强制结束-1 完成0 未开始 1 暂停 2 进行中")
    private Integer state;

    @Schema(description = "接听的客户数量")
    private Integer success;

    @Schema(description = "未能拨通电话的客户数量")
    private Integer miss;

    @Schema(description = "选择挂断的客户数量")
    private Integer failed;

    @Schema(description = "谁创建的任务")
    private Long createBy;

    private Date createTime;

    private Date updateTime;
}
