package com.roncoo.eshop.inventory.controller;

import com.roncoo.eshop.inventory.model.User;
import com.roncoo.eshop.inventory.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("getUserInfo")
    public User getUserInfo(){
        User user = userService.findUserInfo();
        return user;
    }


    @GetMapping("getCacheUserInfo")
    public User getCacheUserInfo(){
        User user = userService.getCacheUserInfo();
        return user;
    }
}
