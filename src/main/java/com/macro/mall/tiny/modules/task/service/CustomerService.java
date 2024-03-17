package com.macro.mall.tiny.modules.task.service;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.macro.mall.tiny.common.exception.ApiException;
import com.macro.mall.tiny.domain.AdminUserDetails;
import com.macro.mall.tiny.domain.CustomerDetails;
import com.macro.mall.tiny.domain.TaskDetails;
import com.macro.mall.tiny.modules.task.dto.CustomerDto;
import com.macro.mall.tiny.modules.task.dto.mapper.CustomerDTOMapper;
import com.macro.mall.tiny.modules.task.model.Customer;
import com.macro.mall.tiny.modules.task.mapper.CustomerMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macro.mall.tiny.modules.task.model.Task;
import com.macro.mall.tiny.modules.task.vo.TaskVo;
import com.macro.mall.tiny.modules.ums.model.UmsAdmin;
import com.macro.mall.tiny.modules.ums.model.UmsRole;
import com.macro.mall.tiny.modules.ums.service.UmsAdminService;
import com.macro.mall.tiny.security.util.SecurityUtils;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.javassist.expr.NewArray;
import org.checkerframework.checker.units.qual.N;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
@Slf4j
public class CustomerService extends ServiceImpl<CustomerMapper, Customer> implements IService<Customer> {
    @Autowired
    private CustomerMapper customerMapper;
    @Resource
    private TaskService taskService;
    @Autowired
    private UmsAdminService umsAdminService;

    public Optional<List<CustomerDto>> parseExcelAndSave(MultipartFile file, TaskVo taskVo){
        List<String> phoneList = new ArrayList<>();
        var customerDetails = new CustomerDetails(customerMapper, CustomerDTOMapper.INSTANCE, phoneList);
        try {
            EasyExcel.read(file.getInputStream(), CustomerDto.class, customerDetails).sheet().doRead();
        }catch (Exception e){
            log.error("Parse excel failed", e);
            return Optional.empty();
        }
        List<Long> ids = customerMapper.getIdsByPhoneList(phoneList);
        taskService.insertTaskAndTaskCustomer(ids);
        return Optional.of(customerDetails.getFailedDataList());
    }


    public List<CustomerDto> getCustomerList() {
        long currentUserId = SecurityUtils.getCurrentUserId();
        List<UmsRole> roleList = umsAdminService.getRoleList(currentUserId);
        if (roleList.isEmpty()) {
            throw new ApiException("用户没有角色");
        }

        // TODO
        return null;
    }
}
