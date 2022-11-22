package com.stone.app.core.security.app;

import com.google.common.collect.Lists;
import com.stone.app.commons.Constants;
import com.stone.app.modules.sys.entity.SysUser;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author rose
 * @date 2022-11-19 9:19
 */
@Data
public class AppUser implements UserDetails {

    private String userId;
    // 登陆账号
    private String loginName;
    // 密码
    private String password;
    // 是否被锁定/注销
    private boolean locked;

    private List<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }


    @Override
    public String getUsername() {
        return loginName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static AppUser of(SysUser sysUser) {
        AppUser appUser = new AppUser();
        appUser.setUserId(sysUser.getId());
        appUser.setLoginName(sysUser.getLoginName());
        appUser.setPassword(sysUser.getLoginPwd());
        appUser.setLocked(sysUser.isLocked());
        appUser.setAuthorities(Lists.newArrayList(new SimpleGrantedAuthority(Constants.ROLE_USER)));
        return appUser;
    }
}
