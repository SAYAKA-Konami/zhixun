package com.macro.mall.tiny.modules.task.mapper;

import com.macro.mall.tiny.modules.task.model.TaskCustomer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2024-03-13
 */
public interface TaskCustomerMapper extends BaseMapper<TaskCustomer> {

    @Select("select customer_id from task_customer where task_id = #{taskID}")
    List<Long> getCustomerIdsByTaskID(Long taskID);
}
