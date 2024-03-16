package com.macro.mall.tiny.security.util;

import com.macro.mall.tiny.domain.AdminUserDetails;
import com.macro.mall.tiny.modules.ums.service.impl.UmsAdminServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityUtils {
    /**
     * 获取当前登录的用户的用户ID
     * @return  返回的实体类是从数据库中查出来的。所以包含全部信息。
     * @see UmsAdminServiceImpl#login
     * @see UmsAdminServiceImpl#loadUserByUsername
     * @see UmsAdminServiceImpl#getAdminByUsername
     */
    public static Optional<AdminUserDetails> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return Optional.empty();
        }
        AdminUserDetails current = (AdminUserDetails) authentication.getPrincipal();
        if (current == null) {
            return Optional.empty();
        }
        return Optional.of(current);
    }

    public static long getCurrentUserId() {
        Optional<AdminUserDetails> opUser = getCurrentUser();
        return opUser.map(AdminUserDetails::getId).orElse(AdminUserDetails.ILLEGAL_ID);
    }

    public static String getCurrentUserName(){
        Optional<AdminUserDetails> opUser = getCurrentUser();
        return opUser.map(AdminUserDetails::getUsername).orElse(StringUtils.EMPTY);
    }
}
