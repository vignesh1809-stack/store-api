package com.codewithmosh.store.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.codewithmosh.store.dto.CreateUserRequest;
import com.codewithmosh.store.dto.UpdateUserRequest;
import com.codewithmosh.store.dto.UserDto;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.UserRepository;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UserController {


    private final UserRepository userRepository;

    private final  UserMapper userMapper;

    @GetMapping
    public List<UserDto> getAllUsers(){

        return userRepository.findAll()
                            .stream()
                            .map(userMapper::todto)
                            .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){

        var user = userRepository.findById(id).orElse(null);

        if (user == null){
            return ResponseEntity.notFound().build();
        }

        var userDto = userMapper.todto(user);

        return ResponseEntity.ok().body(userDto);

    }

    @PostMapping()
    public ResponseEntity<User>  createUser(
        @RequestBody CreateUserRequest request,
        UriComponentsBuilder uriBuilder
    ){

        var user=userMapper.toUser(request);

        userRepository.save(user);

        var uri=uriBuilder
            .path("/users/{id}")
            .buildAndExpand(user.getId())
            .toUri();

        return ResponseEntity.created(uri).body(user);

    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(
        @RequestBody UpdateUserRequest request,
        @PathVariable Long id
    ){

        var user = userRepository.findById(id).orElse(null);

        if (user == null){
            return ResponseEntity.notFound().build();
        }

        userMapper.updateUser(request, user);

        var updatedUser = userRepository.save(user);

        return ResponseEntity.ok(userMapper.todto(updatedUser));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
        @PathVariable Long id

    ){
         var user = userRepository.findById(id).orElse(null);

        if (user == null){
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);

        return ResponseEntity.noContent().build();


    }






    
    
}
