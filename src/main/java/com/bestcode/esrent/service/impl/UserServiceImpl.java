package com.bestcode.esrent.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.bestcode.esrent.entity.Role;
import com.bestcode.esrent.entity.User;
import com.bestcode.esrent.repository.RoleRepository;
import com.bestcode.esrent.repository.UserRepository;
import com.bestcode.esrent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

/**
 * @author xch
 * @create 2018-07-16 23:23
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User findUserByName(String username) {
        User user = userRepository.findByName(username);
        if (user == null) {
            return null;
        }
        List<Role> roles = roleRepository.findRolesByUserId(user.getId());
        if (roles == null || roles.isEmpty()) {
            throw new DisabledException("权限非法");
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        roles.forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        user.setAuthorityList(authorities);
        return user;
    }
}
