package com.macro.mall.tiny.modules.ums.service;

import java.util.List;

/**
 * @apiNote 查找该集合下的所有用户
 * @author wu nan
 * @since  2024/3/17
 **/
public interface FindBelongUser {
    /**
     * @apiNote 查找该集合下的所有用户
     * @param id 集合id
     * @return 用户的ID集合
     */
    default List<String> findBelongUserName(Long userId){
        return null;
    }

    default String getRoleName(){
        return "admin";
    }
}
