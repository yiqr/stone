package com.stone.app.core.security.app.filter;

import com.stone.app.core.security.app.AppUserDetailsService;
import com.stone.app.core.security.jwt.JwtUtils;
import com.stone.app.core.security.jwt.TokenClaims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author rose
 * @date 2022-11-19 10:58
 */
public class AppAuthTokenFilter extends OncePerRequestFilter {
    private final Set<RequestMatcher> matches;
    private final AppUserDetailsService userDetailsService;

    private final JwtUtils jwtUtils;

    private static final String HEADER_STRING = "Authorization";


    public AppAuthTokenFilter(JwtUtils jwtUtils, AppUserDetailsService userDetailsService, RequestMatcher... requestMatcher) {
        this.matches = new HashSet<>(Arrays.asList(requestMatcher));
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 匹配需要 token
        if (this.matches(request)) {
            String header = request.getHeader(HEADER_STRING);
            //解析token
            TokenClaims tokenClaims = jwtUtils.analysisToken(header);


            filterChain.doFilter(request, response);
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private boolean matches(HttpServletRequest request) {
        for (RequestMatcher matcher : this.matches) {
            if (matcher.matches(request)) {
                return true;
            }
        }
        return false;
    }
}
