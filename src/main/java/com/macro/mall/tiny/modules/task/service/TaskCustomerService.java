package com.macro.mall.tiny.modules.task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.macro.mall.tiny.modules.task.model.TaskCustomer;
import com.macro.mall.tiny.modules.task.mapper.TaskCustomerMapper;
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
public class TaskCustomerService extends ServiceImpl<TaskCustomerMapper, TaskCustomer> implements IService<TaskCustomer> {

    @Autowired
    private TaskCustomerMapper mapper;

    public List<Long> getCustomerIdsByTaskID(Long taskID){
        return mapper.getCustomerIdsByTaskID(taskID);
    }

}
