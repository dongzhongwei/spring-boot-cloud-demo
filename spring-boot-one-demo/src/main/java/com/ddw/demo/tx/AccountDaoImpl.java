package com.ddw.demo.tx;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDaoImpl implements AccountDao {

    private final JdbcTemplate jdbcTemplate;

    public AccountDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void outMoney(String from, Double money) {
        jdbcTemplate.update("update account set money = money - ? where name = ?", money, from);
    }

    @Override
    public void inMoney(String to, Double money) {
        jdbcTemplate.update("update account set money = money + ? where name = ?", money,to);
    }
}
