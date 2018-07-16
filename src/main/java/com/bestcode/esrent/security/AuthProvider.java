package com.bestcode.esrent.security;

import com.bestcode.esrent.entity.User;
import com.bestcode.esrent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @author xch
 * @create 2018-07-16 23:14
 **/
public class AuthProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    private final Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String inputPassword = (String) authentication.getCredentials();
        User user = userService.findUserByName(username);
        if (user == null) {
            throw new AuthenticationCredentialsNotFoundException("未找到该用户");
        }
        if (this.passwordEncoder.isPasswordValid(user.getPassword(), inputPassword, user.getId())) {
            return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        }
        throw new BadCredentialsException("用户名或密码错误");
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
