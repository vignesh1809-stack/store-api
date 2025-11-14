package com.codewithmosh.store.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.codewithmosh.store.dto.CreateUserRequest;
import com.codewithmosh.store.dto.UpdateUserRequest;
import com.codewithmosh.store.dto.UserDto;
import com.codewithmosh.store.entities.User;

@Mapper(componentModel ="spring")
public interface UserMapper {
    UserDto todto(User user);


    User toUser(CreateUserRequest request);

    void updateUser(UpdateUserRequest request ,@MappingTarget User user);



    
    
}
