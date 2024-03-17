package com.macro.mall.tiny.modules.task.service;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.macro.mall.tiny.common.exception.ApiException;
import com.macro.mall.tiny.domain.CustomerDetails;
import com.macro.mall.tiny.modules.task.dto.CustomerDto;
import com.macro.mall.tiny.modules.task.dto.GetCustomerByTaskIDDto;
import com.macro.mall.tiny.modules.task.dto.mapper.CustomerDTOMapper;
import com.macro.mall.tiny.modules.task.model.Customer;
import com.macro.mall.tiny.modules.task.mapper.CustomerMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macro.mall.tiny.modules.task.vo.CustomerVo;
import com.macro.mall.tiny.modules.task.vo.mapper.CustomerVoMapper;
import com.macro.mall.tiny.modules.ums.model.UmsRole;
import com.macro.mall.tiny.modules.ums.service.FindBelongUser;
import com.macro.mall.tiny.modules.ums.service.UmsAdminService;
import com.macro.mall.tiny.security.util.SecurityUtils;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

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
    @Resource
    private TaskCustomerService taskCustomerService;
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private List<FindBelongUser> findBelongUserList;

    private Map<String, FindBelongUser> roleName2FindFunction = new HashMap<>();

    @PostConstruct
    public void preHandle(){
        for (FindBelongUser fun : findBelongUserList) {
            roleName2FindFunction.put(fun.getRoleName(), fun);
        }
        // 管理员可以直接查看所有...
        roleName2FindFunction.put("admin", new FindBelongUser() {});
    }

    public Optional<List<CustomerDto>> parseExcelAndSave(MultipartFile file, String taskName){
        List<String> phoneList = new ArrayList<>();
        var customerDetails = new CustomerDetails(customerMapper, CustomerDTOMapper.INSTANCE, phoneList);
        try {
            EasyExcel.read(file.getInputStream(), CustomerDto.class, customerDetails).sheet().doRead();
        }catch (Exception e){
            log.error("Parse excel failed", e);
            return Optional.empty();
        }
        List<Long> ids = customerMapper.getIdsByPhoneList(phoneList);
        taskService.insertTaskAndTaskCustomer(ids, taskName);
        return Optional.of(customerDetails.getFailedDataList());
    }


    public List<CustomerVo> getCustomerList(Integer pageNum, Integer pageSize) {
        long currentUserId = SecurityUtils.getCurrentUserId();
        List<UmsRole> roleList = umsAdminService.getRoleList(currentUserId);
        if (roleList.isEmpty()) {
            log.error("用户没有角色! userId: {}", currentUserId);
            throw new ApiException("用户没有角色");
        }
        UmsRole umsRole = roleList.get(0);
        String role = umsRole.getName();
        FindBelongUser findBelongUserMethod = roleName2FindFunction.get(role);
        List<String> userNameList = findBelongUserMethod.findBelongUserName(currentUserId);
        int offsetSize = (pageNum - 1) * pageSize;
        List<Customer> customers = customerMapper.queryByImported(userNameList, pageSize, offsetSize);
        List<CustomerVo> ret = customers.stream().map(CustomerVoMapper.INSTANCE::customerToCustomerVo).toList();
        return ret;
    }

    public List<CustomerVo> pageByTaskId(GetCustomerByTaskIDDto dto) {
        List<Long> customerIds = taskCustomerService.getCustomerIdsByTaskID(dto.getTaskID());
        if (customerIds.isEmpty()) {
            return Collections.emptyList();
        }
        List<Customer> customers = this.listByIds(customerIds);
        return customers.stream().map(CustomerVoMapper.INSTANCE::customerToCustomerVo).toList();
    }
}
