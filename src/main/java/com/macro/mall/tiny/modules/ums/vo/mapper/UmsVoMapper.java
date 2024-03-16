package com.macro.mall.tiny.modules.ums.vo.mapper;

import com.macro.mall.tiny.modules.ums.model.UmsAdmin;
import com.macro.mall.tiny.modules.ums.vo.UmsUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UmsVoMapper {
    UmsVoMapper INSTANCE = Mappers.getMapper(UmsVoMapper.class);

    UmsUserVo umsAdminParamToUmsUserVo(UmsAdmin umsAdmin);
}
