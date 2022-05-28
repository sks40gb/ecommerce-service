package com.ziletech.ecommerce.service;

import dto.AddressDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {

    AddressDTO saveUserAddress(AddressDTO addressDTO, Long userId);

    void updateUserAddress(AddressDTO addressDTO);

    List<AddressDTO> findAllAddress(Long userId);

    void delete(long id);

    AddressDTO findById(Long id);
}
