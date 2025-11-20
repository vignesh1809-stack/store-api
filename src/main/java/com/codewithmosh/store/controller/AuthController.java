package com.codewithmosh.store.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codewithmosh.store.dto.LoginUserDto;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> loginUser(
        @Valid@RequestBody LoginUserDto request
    ){ 

      authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
      );
        
      return ResponseEntity.ok().build();

       



        
       

    }
 
    
}
