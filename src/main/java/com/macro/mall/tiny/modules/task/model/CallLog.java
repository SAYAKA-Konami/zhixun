package com.macro.mall.tiny.modules.task.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("call_log")
@Schema(name = "CallLog", description = "通话记录")
public class CallLog implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "oss链接")
    private String audio;

    private Long taskId;

    private Long customerId;

    private Long agentId;

    private Date beginTime;

    private Date endTime;

    private String mark;

    private Date createTime;

    private Date updateTime;
}
