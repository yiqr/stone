package com.stone.app.modules.sys.service.impl;

import com.stone.app.modules.sys.repositories.SysPersistentTokenRepository;
import com.stone.app.modules.sys.service.ISysPersistentTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author rose
 * @date 2022-11-19 18:14
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SysPersistentTokenService implements ISysPersistentTokenService {

    private final SysPersistentTokenRepository sysPersistentTokenRepository;

    @Override
    public SysPersistentTokenRepository getRepository() {
        return sysPersistentTokenRepository;
    }
}
