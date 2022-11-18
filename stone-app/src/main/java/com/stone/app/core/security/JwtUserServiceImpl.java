package com.stone.app.core.security;

import com.stone.app.core.security.jwt.JwtUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录逻辑
 *
 * @author rose
 * @date 2022-11-17 22:50
 */
@Component
public class JwtUserServiceImpl implements UserDetailsService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        //暂时写死 admin
        if (!"admin".equals(name)) {
            throw new UsernameNotFoundException("用户名或密码不存在");
        }
        List<GrantedAuthority> list = new ArrayList<>();
        //设置admin角色 角色前面加 ROLE_
        list.add(new SimpleGrantedAuthority("ROLE_admin"));
        return new JwtUser(
                name,
                passwordEncoder.encode("123"),
                list
        );
    }
}
