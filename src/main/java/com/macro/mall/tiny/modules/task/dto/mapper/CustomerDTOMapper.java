package com.macro.mall.tiny.modules.task.dto.mapper;

import com.macro.mall.tiny.modules.task.dto.CustomerDto;
import com.macro.mall.tiny.modules.task.model.Customer;
import com.macro.mall.tiny.security.util.SecurityUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Date;

@Mapper
public interface CustomerDTOMapper {
    CustomerDTOMapper INSTANCE = Mappers.getMapper(CustomerDTOMapper.class);

    @Mapping(target = "importedBy", expression = "java(getImportedBy())")
    @Mapping(target = "belong", expression = "java(defaultBelong())")
    @Mapping(target = "createTime", expression = "java(createTime())")
    Customer customerDtoToCustomer(CustomerDto customerDto);

    default String getImportedBy(){
        return SecurityUtils.getCurrentUserName();
    }

    default long defaultBelong(){return -1L;}

    default Date createTime(){return new Date();}

    CustomerDto customerToCustomerDto(Customer customer);
}
