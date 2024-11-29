package com.formation.spring.Controller;

import com.formation.spring.Entities.UserEntity;
import com.formation.spring.Reponses.UserResponse;
import com.formation.spring.Repositories.UserRepository;
import com.formation.spring.Requets.UserRequest;
import com.formation.spring.Services.UserService;
import com.formation.spring.Shared.DTO.UserDTO;
import com.formation.spring.Shared.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    Utils utils;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @GetMapping()
    public String getUser(){

        return "Api Get User is called" ;
    }
    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest userRequest){

        UserDTO userDto = new UserDTO();
        BeanUtils.copyProperties(userRequest, userDto);

        UserEntity checkUser = userRepository.findByEmail(userDto.getEmail());
        if(checkUser != null) throw new RuntimeException("User already exists");

        userDto.setUserId(utils.generateStringId(30));
        userDto.setEncryptPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));
        UserDTO createUser = userService.createUser(userDto);

        UserResponse userResponse = new UserResponse();
        BeanUtils.copyProperties(createUser, userResponse);

        return userResponse;
    }
}
