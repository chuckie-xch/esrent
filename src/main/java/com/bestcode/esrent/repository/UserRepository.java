package com.bestcode.esrent.repository;

import com.bestcode.esrent.entity.User;
import org.springframework.data.repository.CrudRepository;

/**
 * @author xch
 * @create 2018-07-07 22:38
 **/
public interface UserRepository extends CrudRepository<User, Long> {

}
