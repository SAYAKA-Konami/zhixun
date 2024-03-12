package com.macro.mall.tiny.modules.task.service.impl;

import com.macro.mall.tiny.modules.task.model.Task;
import com.macro.mall.tiny.modules.task.mapper.TaskMapper;
import com.macro.mall.tiny.modules.task.service.TaskService;
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
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements TaskService {

}
