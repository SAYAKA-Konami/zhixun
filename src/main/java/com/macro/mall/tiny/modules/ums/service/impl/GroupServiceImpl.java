package com.macro.mall.tiny.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.macro.mall.tiny.modules.ums.mapper.UmsAdminMapper;
import com.macro.mall.tiny.modules.ums.model.Group;
import com.macro.mall.tiny.modules.ums.mapper.GroupMapper;
import com.macro.mall.tiny.modules.ums.model.UmsAdmin;
import com.macro.mall.tiny.modules.ums.service.GroupService;
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
 * @since 2024-03-17
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {
    @Autowired
    private UmsAdminMapper umsAdminMapper;
    @Autowired
    private GroupMapper groupMapper;
    @Override
    public List<String> findBelongUserName(Long userId) {
        List<UmsAdmin> groupUsers = groupMapper.getFuckableByLeaderId(userId);
        return groupUsers.stream().map(UmsAdmin::getUsername).toList();
    }

    @Override
    public String getRoleName() {
        return "leader";
    }

    public List<String> findBelongUserIds(List<Long> groupIds){
        List<String> userByGroupIds = umsAdminMapper.getUserNameByGroupIds(groupIds);
        return userByGroupIds;
    }

    public List<Group> findByTeamId(Long teamId){
        QueryWrapper<Group> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Group::getTeamId, teamId);
        List<Group> groups = baseMapper.selectList(wrapper);
        return groups;
    }
}
