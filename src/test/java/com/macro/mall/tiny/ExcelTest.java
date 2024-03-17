package com.macro.mall.tiny;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.macro.mall.tiny.modules.task.dto.CustomerDto;
import com.macro.mall.tiny.modules.task.vo.CustomerVo;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import java.util.Collections;

public class ExcelTest {

    @Test
    public void writeExcel(){
        ClassPathResource resource = new ClassPathResource("resources");
//        String path = "/Users/wunan/nan/java/mall-tiny/src/main/resources/";
//        String fileName = path + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        String fileName = resource.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, CustomerDto.class)
//                .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                .sheet("模板")
                .doWrite(() -> {
                    // 分页查询数据
                    return Collections.singleton(new CustomerVo());
                });

    }
}
