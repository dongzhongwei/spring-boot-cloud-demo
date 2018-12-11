package com.roncoo.eshop.inventory.service;

import com.roncoo.eshop.inventory.model.User;

public interface UserService {

    User findUserInfo();

    User getCacheUserInfo();
}
