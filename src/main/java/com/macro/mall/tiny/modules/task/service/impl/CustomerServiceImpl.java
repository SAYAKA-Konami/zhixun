package com.macro.mall.tiny.modules.task.service.impl;

import com.macro.mall.tiny.modules.task.model.Customer;
import com.macro.mall.tiny.modules.task.mapper.CustomerMapper;
import com.macro.mall.tiny.modules.task.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
