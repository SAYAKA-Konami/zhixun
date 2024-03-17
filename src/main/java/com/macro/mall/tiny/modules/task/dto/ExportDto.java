package com.macro.mall.tiny.modules.task.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExportDto {
    @NotNull
    private List<Long> customerIds;
}
