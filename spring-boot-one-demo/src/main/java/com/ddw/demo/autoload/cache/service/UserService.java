package com.ddw.demo.autoload.cache.service;


import com.ddw.demo.autoload.cache.condition.UserCondition;
import com.ddw.demo.autoload.cache.entity.UserDO;

import java.util.List;

public interface UserService {

    UserDO getUserById(Long userId);

    // @Cache(expire = 600, key = "'userid-list-' + @@hash(#args[0])")
    List<UserDO> listByCondition(UserCondition condition);

    // @CacheDeleteTransactional
    Long register(UserDO user);

    UserDO doLogin(String name, String password);

    void updateUser(UserDO user);

    void deleteUserById(Long userId);
}
