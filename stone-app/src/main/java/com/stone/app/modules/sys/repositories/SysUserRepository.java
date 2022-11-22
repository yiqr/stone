package com.stone.app.modules.sys.repositories;

import com.stone.app.core.jpa.repositories.DomainRepository;
import com.stone.app.modules.sys.entity.SysUser;

import java.util.Optional;

public interface SysUserRepository extends DomainRepository<SysUser> {

    Optional<SysUser> findByLoginName(String loginName);
}
