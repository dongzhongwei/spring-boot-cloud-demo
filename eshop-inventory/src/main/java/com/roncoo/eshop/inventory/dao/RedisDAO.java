package com.roncoo.eshop.inventory.dao;

public interface RedisDAO {

    void set(String key, String value);

    void get(String key);
}
