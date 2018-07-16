package com.bestcode.esrent.service;

import com.bestcode.esrent.entity.User;

/**
 * @author xch
 * @create 2018-07-16 23:21
 **/
public interface UserService {

    User findUserByName(String username);
}
