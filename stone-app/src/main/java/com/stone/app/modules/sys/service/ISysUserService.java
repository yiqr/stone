package com.stone.app.modules.sys.service;

import com.stone.app.core.jpa.service.DomainService;
import com.stone.app.modules.sys.entity.SysUser;
import com.stone.app.modules.sys.repositories.SysUserRepository;

/**
 * @author rose
 * @date 2022-11-19 9:30
 */
public interface ISysUserService extends DomainService<SysUser, SysUserRepository> {

    /**
     * 根据登陆名获取用户
     *
     * @param loginName 登录名
     * @return
     */
    SysUser getUserByLoginName(String loginName);
}
