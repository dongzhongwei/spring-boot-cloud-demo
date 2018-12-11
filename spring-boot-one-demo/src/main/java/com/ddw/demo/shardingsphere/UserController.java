package com.ddw.demo.shardingsphere;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("shardingUser")
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public User createUser(String name, Integer age){
        User user = new User();
        user.setFirstName(name);
        user.setAge(age);
        return  userRepository.save(user);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id){
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
        mapperFactory.classMap(User.class, UserDTO.class)
                .field("age", "age1")
                .byDefault()
                .register();
        return  userRepository.findOne(id);
    }

//    @GetMapping
//    public List<UserDTO> getAll(){
//        return  userRepository.find();
//    }
}
