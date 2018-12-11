package com.ddw.demo.jpa;

import com.ddw.demo.custom.repository.MyRepository;
import com.ddw.demo.shardingsphere.User;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends MyRepository<User, Integer> {

}