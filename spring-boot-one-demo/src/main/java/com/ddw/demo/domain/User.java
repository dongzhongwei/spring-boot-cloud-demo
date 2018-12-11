package com.ddw.demo.domain;


import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Getter
@Setter
//@SuperBuilder
//@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User{

    private Long id;

    private Name name;

    private Date birthday;


//    public User(Integer age, String sex, Long id, Name name, Date birthday) {
//        super(age, sex);
//        this.id = id;
//        this.name = name;
//        this.birthday = birthday;
//    }



    public static void main(String[] args) {
//        User.builder()
//        User user = User.builder().id(1L).age(18).sex("男").birthday(new Date()).build();
//        System.out.println(user);
//        System.out.println(user.getAge());
//        System.out.println(user.getId());
    }

}
