package com.ddw.demo;

import com.ddw.demo.domain.User;
import com.ddw.demo.repository.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

/**
 *
 * @author ddw
 * @version 1.0
 * @date 2019-03-28 14:52
 * @Description
 */
public class Demo {

    public static void main(String[] args) throws Exception {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        UserMapper userMapper = sqlSessionFactory.openSession().getMapper(UserMapper.class);

        User user = userMapper.findById(141L);

        System.out.println(user.getTelephone());
    }
}
