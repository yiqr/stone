package com.stone.app.core.security.service.impl;

import com.stone.app.core.security.service.IAuthUserService;
import com.stone.app.modules.sys.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author rose
 * @date 2022-11-19 15:33
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthUserService implements IAuthUserService {

    private final ISysUserService ISysUserService;
    private final PasswordEncoder passwordEncoder;


}
