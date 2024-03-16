package com.macro.mall.tiny.modules.task.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("task_customer")
@Schema(name = "TaskCustomer", description = "用户任务关联表")
public class TaskCustomer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     *  主键 (taskId, customerId)
     */
    private Long customerId;

    private Long taskId;

    private Date createTime;

    private Date updateTime;
}
