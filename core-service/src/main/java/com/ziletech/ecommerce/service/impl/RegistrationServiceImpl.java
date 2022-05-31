package com.ziletech.ecommerce.service.impl;

import com.ziletech.ecommerce.entity.Role;
import com.ziletech.ecommerce.entity.User;
import com.ziletech.ecommerce.repository.RoleRepository;
import com.ziletech.ecommerce.repository.UserRepository;
import com.ziletech.ecommerce.service.RegistrationService;
import com.ziletech.ecommerce.service.userexception.EmailAlreadyExistException;
import dto.RoleDTO;
import dto.Type;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void register(UserDTO userDTO) {

        checkIfEmailIsExist(userDTO.getEmail());
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setContactNumber(userDTO.getContactNumber());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        List<Role> roleList = getRoles(userDTO);
        user.setRoles(roleList);
        userRepository.save(user);

    }

    private List<Role> getRoles(UserDTO userDTO) {
        List<Role> roleList = new ArrayList<>();
        Role role = roleRepository.findByRole(Type.USER.name());
        roleList.add(role);
        return roleList;
    }

    private void checkIfEmailIsExist(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            throw new EmailAlreadyExistException("email is already exist");
        }
    }
}
