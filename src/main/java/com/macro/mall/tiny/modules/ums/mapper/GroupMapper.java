package com.macro.mall.tiny.modules.ums.mapper;

import com.macro.mall.tiny.modules.ums.model.Group;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.macro.mall.tiny.modules.ums.model.UmsAdmin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author macro
 * @since 2024-03-17
 */
public interface GroupMapper extends BaseMapper<Group> {
    /**
     *  获取小组内的所有成员
     * @param leaderId 组长ID
     */
    List<UmsAdmin> getFuckableByLeaderId(@Param("leaderId")long leaderId);
}
