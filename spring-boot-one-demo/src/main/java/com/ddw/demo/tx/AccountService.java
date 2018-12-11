package com.ddw.demo.tx;

import org.springframework.stereotype.Service;


public interface AccountService {

    public void transfer(String from, String to, Double money);

    public void transfer1(String from, String to, Double money);

    public void transfer2(String from, String to, Double money);
}
