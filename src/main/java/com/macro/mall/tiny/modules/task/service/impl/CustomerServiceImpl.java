package com.macro.mall.tiny.modules.task.service.impl;

import com.macro.mall.tiny.modules.task.dto.CustomerDto;
import com.macro.mall.tiny.modules.task.model.Customer;
import com.macro.mall.tiny.modules.task.mapper.CustomerMapper;
import com.macro.mall.tiny.modules.task.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author macro
 * @since 2024-03-13
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public List<CustomerDto> batchInsertCustomerByExcel(List<CustomerDto> customerDtoList) {
        return null;
    }

    @Override
    public List<CustomerDto> batchExportCustomer(List<Long> customerIds) {
        return null;
    }
}
