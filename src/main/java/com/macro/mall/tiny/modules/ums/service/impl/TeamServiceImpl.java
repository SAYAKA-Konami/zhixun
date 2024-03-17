package com.macro.mall.tiny.modules.ums.service.impl;

import com.macro.mall.tiny.modules.ums.model.Group;
import com.macro.mall.tiny.modules.ums.model.Team;
import com.macro.mall.tiny.modules.ums.mapper.TeamMapper;
import com.macro.mall.tiny.modules.ums.service.TeamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import org.apache.commons.collections4.CollectionUtils;
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
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService {
    @Resource
    private GroupServiceImpl groupService;
    @Override
    public List<String> findBelongUserName(Long id) {
        List<Group> byTeamId = groupService.findByTeamId(id);
        if (CollectionUtils.isEmpty(byTeamId)) {
            return null;
        }
        List<Long> groupIds = byTeamId.stream().map(Group::getId).toList();
        return groupService.findBelongUserIds(groupIds);
    }

    @Override
    public String getRoleName() {
        return "owner";
    }
}
