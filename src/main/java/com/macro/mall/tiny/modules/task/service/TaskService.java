package com.macro.mall.tiny.modules.task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.macro.mall.tiny.domain.AdminUserDetails;
import com.macro.mall.tiny.domain.TaskDetails;
import com.macro.mall.tiny.modules.task.mapper.TaskCustomerMapper;
import com.macro.mall.tiny.modules.task.model.Task;
import com.macro.mall.tiny.modules.task.mapper.TaskMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macro.mall.tiny.modules.task.model.TaskCustomer;
import com.macro.mall.tiny.modules.task.vo.TaskVo;
import com.macro.mall.tiny.security.util.SecurityUtils;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author macro
 * @since 2024-03-13
 */
@Service
public class TaskService extends ServiceImpl<TaskMapper, Task> implements IService<Task> {
    @Autowired
    private  TaskMapper taskMapper;
    @Resource
    private TaskCustomerService taskCustomerService;

    @Transactional
    public void insertTaskAndTaskCustomer(List<Long> customerIds, TaskVo taskVo){
        long currentUserId = SecurityUtils.getCurrentUserId();
        if (currentUserId == AdminUserDetails.ILLEGAL_ID) {
            log.error("Can not get user.");
        }
        Task newTask = TaskDetails.initialTask(currentUserId);
        newTask.setName(taskVo.getTaskName());
        TaskDetails taskDetails = new TaskDetails(newTask);
        List<TaskCustomer> taskCustomers = taskDetails.buildRelation(customerIds);
        this.save(newTask);
        taskCustomerService.saveBatch(taskCustomers);
    }


}
