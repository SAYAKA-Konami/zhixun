package com.macro.mall.tiny.modules.task.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(title = "导入客户信息",description = "任务参数")
public class TaskVo {
    @NotNull
    @Schema(description = "任务名称")
    private String taskName;
}
