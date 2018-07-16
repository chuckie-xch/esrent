package com.bestcode.esrent.repository;

import java.util.List;

import com.bestcode.esrent.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xch
 * @create 2018-07-16 23:39
 **/
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findRolesByUserId(Long userId);
}
