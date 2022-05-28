package com.ziletech.ecommerce.service;

import dto.RoleDTO;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {


    RoleDTO save(RoleDTO roleDTO);

    void update(RoleDTO roleDTO);

    void delete(Long id);

    RoleDTO findById(Long id);

    Iterable<RoleDTO> findByAll();


}
