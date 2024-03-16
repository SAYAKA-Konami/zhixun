package com.macro.mall.tiny.security.util;

import com.macro.mall.tiny.modules.ums.service.impl.UmsAdminServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {
    /**
     * 获取当前登录的用户的用户名
     * @return  返回的实体类是从数据库中查出来的。所以包含全部信息。
     * @see UmsAdminServiceImpl#login
     * @see UmsAdminServiceImpl#loadUserByUsername
     * @see UmsAdminServiceImpl#getAdminByUsername
     */
    public static String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return StringUtils.EMPTY;
        }
        UserDetails current = (UserDetails) authentication.getPrincipal();
        return current.getUsername();
    }
}
