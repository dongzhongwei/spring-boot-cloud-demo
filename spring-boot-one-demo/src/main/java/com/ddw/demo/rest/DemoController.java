package com.ddw.demo.rest;

import com.ddw.demo.domain.Color;
import com.ddw.demo.jpa.MyUserRepository;
import com.ddw.demo.service.HelloWorldService;
import com.ddw.demo.shardingsphere.User;
import com.ddw.demo.shardingsphere.UserDTO;
import com.ddw.demo.shardingsphere.UserRepository;
import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
public class DemoController {

    private final HelloWorldService helloWorldService;

    private final MyUserRepository myUserRepository;


    public DemoController(HelloWorldService helloWorldService, MyUserRepository myUserRepository) {
        this.helloWorldService = helloWorldService;
        this.myUserRepository = myUserRepository;
    }

    @GetMapping("helloWorld")
    public String helloWorld(HttpServletRequest request, @RequestParam String name, @RequestParam Color color) throws Exception {
        Object obj = new Object();
        Thread.sleep(200);
        helloWorldService.cache();

        return name + "Hello,World,this is code:"+ color;
    }

    @PostMapping("/myrepository/users")
    public String createUsers(@RequestBody List<User> users){
//        myUserRepository.save(users);
        return "OK";
    }

    @PutMapping("/myrepository/users")
    public String updateUsers(@RequestBody List<User> users){
        myUserRepository.updateInBatchById(users);
        return "OK";
    }

    @PostMapping("/myrepository/users/single")
    public User createUser(@RequestBody User user){
        return myUserRepository.save(user);
    }

    @GetMapping("/myrepository/users/dto")
    public List<UserDTO> createUser(){
        return myUserRepository.findAllByNativeSql(UserDTO.class,"select first_name as name,age from user",null);
    }

    @GetMapping("/myrepository/users")
    public Iterable<User> getUsers(@RequestParam String ids){
        List<Integer> idList = Lists.transform(Arrays.asList(ids.split(",")), input -> Integer.parseInt(input));
        return null;
    }

    @DeleteMapping("/myrepository/users")
    public Integer deleteUser(@RequestParam String ids){
        List<Integer> idList = Lists.transform(Arrays.asList(ids.split(",")), input -> Integer.parseInt(input));
        return myUserRepository.deleteInBatchById(idList);
    }
}
