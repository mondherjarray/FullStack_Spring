package com.formation.spring.Services;

import com.formation.spring.Services.Impl.UserServiceImpl;
import com.formation.spring.Shared.DTO.UserDTO;

public interface UserService {

    UserDTO createUser (UserDTO userDTO);
}
