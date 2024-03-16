package com.macro.mall.tiny.modules.task.service;

import com.macro.mall.tiny.modules.task.dto.CustomerDto;
import com.macro.mall.tiny.modules.task.model.Customer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


public interface CustomerService extends IService<Customer> {
    /**
     * excel 导入
     * @return 导入失败的数据
     */
    List<CustomerDto> batchInsertCustomerByExcel(List<CustomerDto> customerDtoList);

    /**
     *  批量导出
     */
    List<CustomerDto> batchExportCustomer(List<Long> customerIds);

}
