package com.ddw.demo.repository;

import com.ddw.demo.domain.User;

public interface UserMapper {
    User findById(Long id);
}
