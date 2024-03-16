package com.macro.mall.tiny.modules.task.dto.mapper;

import com.macro.mall.tiny.modules.task.dto.CustomerDto;
import com.macro.mall.tiny.modules.task.model.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerDTOMapper {
    CustomerDTOMapper INSTANCE = Mappers.getMapper(CustomerDTOMapper.class);

    Customer customerDtoToCustomer(CustomerDto customerDto);
}
