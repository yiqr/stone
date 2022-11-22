package com.stone.app.core.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author rose
 * @date 2022-11-19 15:09
 */
public interface StoneUserDetailsService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

//    StoneUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException;

}
