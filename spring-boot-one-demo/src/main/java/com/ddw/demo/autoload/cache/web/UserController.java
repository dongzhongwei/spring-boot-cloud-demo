package com.ddw.demo.autoload.cache.web;

import com.ddw.demo.autoload.cache.condition.UserCondition;
import com.ddw.demo.autoload.cache.entity.UserDO;
import com.ddw.demo.autoload.cache.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping()
    public List<UserDO> list() {
        return userService.listByCondition(new UserCondition());
    }

    @GetMapping("/{id}")
    public UserDO detail(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/add")
    public UserDO add() {
        UserDO user = new UserDO();
        user.setName("name_" + System.currentTimeMillis());
        user.setPassword("11111");
        userService.register(user);
        return user;
    }

    @GetMapping("/update/{id}")
    public void update(@PathVariable Long id) {
        UserDO user = new UserDO();
        user.setId(id);
        user.setName("name:" + id);
        userService.updateUser(user);
    }

}
