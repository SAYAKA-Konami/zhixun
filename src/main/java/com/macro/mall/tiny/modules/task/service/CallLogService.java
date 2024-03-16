package com.macro.mall.tiny.modules.task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.macro.mall.tiny.modules.task.model.CallLog;
import com.macro.mall.tiny.modules.task.mapper.CallLogMapper;
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
public class CallLogService extends ServiceImpl<CallLogMapper, CallLog> implements IService<CallLog> {

}
