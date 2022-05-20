package com.ziletech.ecommerce.service;

import dto.UserDTO;

public interface UserService {

    UserDTO saveUser(UserDTO userDTO);

    UserDTO findById(Long id);
}
