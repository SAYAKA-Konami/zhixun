package com.macro.mall.tiny.modules.task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.macro.mall.tiny.modules.task.model.Customer;
import com.macro.mall.tiny.modules.task.mapper.CustomerMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author macro
 * @since 2024-03-13
 */
@Service
public class CustomerService extends ServiceImpl<CustomerMapper, Customer> implements IService<Customer> {
    @Autowired
    private CustomerMapper customerMapper;

}
