package com.macro.mall.tiny.modules.customer.service.impl;

import com.macro.mall.tiny.modules.customer.model.Customer;
import com.macro.mall.tiny.modules.customer.mapper.CustomerMapper;
import com.macro.mall.tiny.modules.customer.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author macro
 * @since 2024-03-12
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}
