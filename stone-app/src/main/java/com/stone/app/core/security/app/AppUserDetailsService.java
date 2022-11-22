package com.stone.app.core.security.app;

import com.stone.app.core.security.StoneUserDetailsService;
import com.stone.app.core.security.jwt.JwtUser;
import com.stone.app.modules.sys.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 登陆逻辑
 *
 * @author rose
 * @date 2022-11-19 9:08
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AppUserDetailsService implements StoneUserDetailsService {

    private final ISysUserService ISysUserService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        SysUser user = sysUserService.getUserByLoginName(username);
//        if (Objects.isNull(user)) {
//            throw new UsernameNotFoundException(username);
//        }
//        return AppUser.of(user);

        //暂时写死 admin
        if (!"admin".equals(username)) {
            throw new UsernameNotFoundException("用户名或密码不存在");
        }
        List<GrantedAuthority> list = new ArrayList<>();
        //设置admin角色 角色前面加 ROLE_
        list.add(new SimpleGrantedAuthority("ROLE_admin"));
        return new JwtUser(
                username,
                passwordEncoder.encode("123"),
                list
        );
    }

}
