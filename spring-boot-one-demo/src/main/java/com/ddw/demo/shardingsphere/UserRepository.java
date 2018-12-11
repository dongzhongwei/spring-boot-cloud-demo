package com.ddw.demo.shardingsphere;

import com.ddw.demo.annotation.NativeSql;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for the User entity.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


//    @Query(value = "select name,age from user")
//    List<UserDTO> find();
}
