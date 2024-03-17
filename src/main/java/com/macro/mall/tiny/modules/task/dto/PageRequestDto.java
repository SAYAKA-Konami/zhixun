package com.macro.mall.tiny.modules.task.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Setter;

@Setter
@Schema(name = "PageRequestDto", description = "分页查询请求参数")
public class PageRequestDto {
    @Schema(description = "页码", example = "1")
    private Integer pageNum;

    @Min(value = 1, message = "每页数量最小为1")
    private Integer pageSize;

    public Integer getPageNum() {
        return pageNum == null ? 1 : pageNum < 1 ? 1 : pageNum;
    }

    public Integer getPageSize() {
        return pageSize == null ? 5 : pageSize < 1 ? 5 : pageSize;
    }
}
