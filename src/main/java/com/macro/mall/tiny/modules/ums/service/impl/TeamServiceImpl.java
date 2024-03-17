package com.macro.mall.tiny.modules.ums.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.macro.mall.tiny.common.exception.ApiException;
import com.macro.mall.tiny.modules.ums.mapper.TeamMapper;
import com.macro.mall.tiny.modules.ums.model.Group;
import com.macro.mall.tiny.modules.ums.model.Team;
import com.macro.mall.tiny.modules.ums.service.TeamService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
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
@Slf4j
@Service
public class TeamServiceImpl extends ServiceImpl<TeamMapper, Team> implements TeamService {
    @Resource
    private GroupServiceImpl groupService;
    @Override
    public List<String> findBelongUserName(Long userId) {
        QueryWrapper<Team> wrapper = new QueryWrapper<Team>().eq("owner", userId);
        Team team = baseMapper.selectOne(wrapper);
        if (team == null) {
            log.error("查不到该团队的信息！ userId:{}", userId);
            throw new ApiException("查不到该团队长的团队信息");
        }
        List<Group> byTeamId = groupService.findByTeamId(team.getId());
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
