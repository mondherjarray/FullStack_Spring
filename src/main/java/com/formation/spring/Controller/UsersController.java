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
    @PostMapping()
    public UserResponse createUser (UserRequest userRequest){

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userRequest, userDTO);

        UserEntity userCheck = userRepository.findByEmail(userDTO.getEmail());
        if(userCheck != null ) throw  new RuntimeException("User is Exist");

        userDTO.setUserId(utils.generateStringId(32));
        userDTO.setEncryptPassword(bCryptPasswordEncoder.encode(userRequest.getPassword()));


        UserDTO createUser = userService.createUser(userDTO);
     UserResponse userResponse = new UserResponse();
     BeanUtils.copyProperties(createUser, userResponse);
        return userResponse ;
    }
}
