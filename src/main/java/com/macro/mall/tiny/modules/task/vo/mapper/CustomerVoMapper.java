package com.macro.mall.tiny.modules.task.vo.mapper;

import com.macro.mall.tiny.modules.task.model.Customer;
import com.macro.mall.tiny.modules.task.vo.CustomerVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerVoMapper {
    CustomerVoMapper INSTANCE = Mappers.getMapper(CustomerVoMapper.class);

    CustomerVo customerToCustomerVo(Customer customer);
}
