package com.formation.spring.Services;

import com.formation.spring.Services.Impl.UserServiceImpl;
import com.formation.spring.Shared.DTO.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDTO createUser (UserDTO userDTO);
    UserDTO getUser(String email);
}
