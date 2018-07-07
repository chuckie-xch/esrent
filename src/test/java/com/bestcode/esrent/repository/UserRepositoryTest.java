package com.bestcode.esrent.repository;

import static org.junit.Assert.*;

import com.bestcode.esrent.ApplicationTests;
import com.bestcode.esrent.entity.User;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryTest extends ApplicationTests{

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindOne() {
        User user = userRepository.findOne(2L);
        Assert.assertEquals("admin", user.getName());
    }
}