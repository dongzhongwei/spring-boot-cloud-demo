package com.ddw.demo.shardingsphere;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
 
    UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );
 
    @Mapping(source ="age", target = "age1")
    UserDTO carToCarDto(User user);
}