package com.stone.app.modules.sys.service.impl;

import com.stone.app.modules.sys.entity.SysUser;
import com.stone.app.modules.sys.repositories.SysUserRepository;
import com.stone.app.modules.sys.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author rose
 * @date 2022-11-19 9:38
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysUserServiceImpl implements ISysUserService {

    private final SysUserRepository sysUserRepository;


    @Override
    public SysUserRepository getRepository() {
        return sysUserRepository;
    }

    @Override
    public SysUser getUserByLoginName(String loginName) {
        Optional<SysUser> optionalSysUser = sysUserRepository.findByLoginName(loginName);
        return optionalSysUser.isEmpty() ? null : optionalSysUser.get();
    }
}
