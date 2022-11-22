package com.stone.app.modules.sys.service;

import com.stone.app.core.jpa.service.DomainService;
import com.stone.app.modules.sys.entity.SysPersistentToken;
import com.stone.app.modules.sys.repositories.SysPersistentTokenRepository;

/**
 * @author rose
 * @date 2022-11-19 18:13
 */
public interface ISysPersistentTokenService extends DomainService<SysPersistentToken, SysPersistentTokenRepository> {
}
