package com.macro.mall.tiny.modules.task.mapper;

import com.macro.mall.tiny.modules.task.model.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2024-03-16
 */
public interface CustomerMapper extends BaseMapper<Customer> {

    @Transactional
    int insertBatch(@Param("customers") List<Customer> customers);


    List<Long> getIdsByPhoneList(List<String> phoneList);
}
