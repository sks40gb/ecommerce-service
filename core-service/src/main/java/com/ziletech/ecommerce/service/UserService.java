package com.ziletech.ecommerce.service;

import dto.UserDTO;

public interface UserService {

    UserDTO saveUser(UserDTO userDTO);

    UserDTO findById(Long id);

    UserDTO findByName(String name);

    void delete(Long id);

    void update(UserDTO user);

    Iterable<UserDTO> findByAll();
}
