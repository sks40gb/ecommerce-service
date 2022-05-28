package com.ziletech.ecommerce.service.impl;

import com.ziletech.ecommerce.entity.Role;
import com.ziletech.ecommerce.repository.RoleRepository;
import com.ziletech.ecommerce.repository.UserRepository;
import com.ziletech.ecommerce.service.RoleService;
import dto.RoleDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;


    @Override
    public RoleDTO save(RoleDTO roleDTO) {
        Role role = new Role();
        role.setId(roleDTO.getId());
        role.setRole(roleDTO.getRole());
        roleRepository.save(role);
        return roleDTO;
    }

    @Override
    public void update(RoleDTO roleDTO) {
        Role role = roleRepository.findById(roleDTO.getId()).orElse(null);
        if (role == null) {
            throw new EntityNotFoundException("Role is not found for given id  " + role);
        }
        role.setRole(roleDTO.getRole());
        roleRepository.save(role);
    }

    @Override
    public void delete(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null) {
            throw new EntityNotFoundException("ID is not found for these  " + id);
        }
        roleRepository.delete(role);
    }

    @Override
    public RoleDTO findById(Long id) {
        Role role = roleRepository.findById(id).orElse(null);
        if (role == null) {
            throw new EntityNotFoundException("Id is not found for these " + id);
        }
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(role.getId());
        roleDTO.setRole(role.getRole());

        return roleDTO;
    }

    @Override
    public List<RoleDTO> findByAll() {
        List<RoleDTO> roleList = new ArrayList<>();
        for (Role role : roleRepository.findAll()) {
            RoleDTO RoleDTO = new RoleDTO();
            RoleDTO.setId(role.getId());
            RoleDTO.setRole(role.getRole());
            roleList.add(RoleDTO);
        }
        return roleList;
    }


}
