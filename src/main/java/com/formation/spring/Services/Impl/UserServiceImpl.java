package com.formation.spring.Services.Impl;

import com.formation.spring.Entities.UserEntity;
import com.formation.spring.Repositories.UserRepository;
import com.formation.spring.Services.UserService;
import com.formation.spring.Shared.DTO.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDTO, userEntity);
        UserEntity createUser = userRepository.save(userEntity);
        UserDTO user = new UserDTO();
        BeanUtils.copyProperties(createUser, user);




        return user;
    }
}
