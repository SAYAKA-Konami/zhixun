package com.macro.mall.tiny.modules.task.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = {"name", "phone"})
public class CustomerVo {
    @ExcelProperty("任务名称")
    private String TaskName;
    @ExcelProperty("姓名")
    private String name;
    @ExcelProperty("手机号")
    private String phone;
    @ExcelProperty("城市")
    private String city;
    @ExcelProperty("省份")
    private String province;
    @ExcelProperty("来源")
    private String source;
    @ExcelProperty("导入人")
    private Long importedBy;
    @ExcelProperty("归属")
    private Long belong;
    @ExcelProperty("类别")
    private String category;
}
