package com.macro.mall.tiny.modules.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Schema(name = "GetCustomerByTaskIDDto", description = "根据任务ID获取客户信息")
public class GetCustomerByTaskIDDto extends PageRequestDto{
    @Schema(description = "任务ID")
    private Long taskID;
}
