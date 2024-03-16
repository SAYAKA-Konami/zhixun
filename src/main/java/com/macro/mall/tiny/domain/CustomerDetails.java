package com.macro.mall.tiny.domain;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.excel.util.ListUtils;
import com.alibaba.fastjson2.JSON;
import com.macro.mall.tiny.modules.task.dto.CustomerDto;
import com.macro.mall.tiny.modules.task.dto.mapper.CustomerMapperStruct;
import com.macro.mall.tiny.modules.task.mapper.CustomerMapper;
import com.macro.mall.tiny.modules.task.model.Customer;
import com.macro.mall.tiny.modules.task.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Slf4j
public class CustomerDetails implements ReadListener<CustomerDto> {
    /**
     * 理论上来说这里应该是一个mapper。但是mybatis-plus的mapper不包含批量插入方法。
     */
    private final CustomerService service;
    /**
     *  负责VO <-- PO --> DTO
     */
    private final CustomerMapperStruct mapperStruct;

    /**
     * 每隔5条存储数据库，实际使用中可以100条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    /**
     * 缓存的数据
     */
    private List<Customer> cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);

    public CustomerDetails(CustomerService service, CustomerMapperStruct mapperStruct) {
        this.service = service;
        this.mapperStruct = mapperStruct;
    }

    public Customer getCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);
        return customer;
    }

    @Override
    public void invoke(CustomerDto data, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        Customer customer = mapperStruct.customerDtoToCustomer(data);
        cachedDataList.add(customer);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (cachedDataList.size() >= BATCH_COUNT) {
            service.saveBatch(cachedDataList);
            // 存储完成清理 list
            cachedDataList = ListUtils.newArrayListWithExpectedSize(BATCH_COUNT);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
