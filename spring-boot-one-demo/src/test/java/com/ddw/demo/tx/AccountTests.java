package com.ddw.demo.tx;

import com.ddw.demo.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class AccountTests {

    @Autowired
    private AccountService accountService;

    @Test
    public void transferTest(){
        accountService.transfer("zs","ls",100.0);
    }
}
