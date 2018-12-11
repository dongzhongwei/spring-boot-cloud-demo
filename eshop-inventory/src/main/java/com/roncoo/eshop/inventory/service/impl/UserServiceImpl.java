package com.roncoo.eshop.inventory.service.impl;

import com.roncoo.eshop.inventory.mapper.UserMapper;
import com.roncoo.eshop.inventory.model.User;
import com.roncoo.eshop.inventory.service.UserService;
import com.roncoo.eshop.inventory.utils.JsonUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final StringRedisTemplate stringRedisTemplate;

    public UserServiceImpl(UserMapper userMapper, StringRedisTemplate stringRedisTemplate) {
        this.userMapper = userMapper;
        this.stringRedisTemplate = stringRedisTemplate;
    }


    @Override
    public User findUserInfo() {
        return userMapper.findUserInfo();
    }

    @Override
    public User getCacheUserInfo() {
        final ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("cached_user_tom","{\"name\":\"tom\",\"age\":28}");

        final String userJson = valueOperations.get("cached_user_tom");

        return JsonUtils.fromJsonString(userJson, User.class);
    }
}
