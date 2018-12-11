package com.ddw.demo.tx;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {

    public final AccountDao accountDao;

    public AccountServiceImpl(AccountDao accountDao) {
        this.accountDao = accountDao;
    }


    @Override
//    @Transactional
    //propagation_required
    //propagation_required
    public void transfer(String from, String to, Double money) {
       transfer1(from,to,money);
//       int d = 1/0;
       transfer2(from,to,money);


    }

    @Override
    @Transactional(readOnly = true)
    public void transfer1(String from, String to, Double money) {
        accountDao.outMoney(from, money);
        accountDao.inMoney(to, money);
    }

    @Override
    @Transactional(readOnly = true)
    public void transfer2(String from, String to, Double money) {
        accountDao.outMoney(from, money);
        accountDao.inMoney(to, money);
    }
}
