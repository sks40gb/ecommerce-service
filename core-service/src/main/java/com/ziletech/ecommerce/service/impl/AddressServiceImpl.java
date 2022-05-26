package com.ziletech.ecommerce.service.impl;

import com.ziletech.ecommerce.entity.Address;
import com.ziletech.ecommerce.entity.User;
import com.ziletech.ecommerce.repository.AddressRepository;
import com.ziletech.ecommerce.repository.UserRepository;
import com.ziletech.ecommerce.service.AddressService;
import dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public AddressDTO saveUserAddress(AddressDTO addressDTO, Long userId) {

        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new EntityNotFoundException("user is not found for given id  " + user);
        }
        Address address = new Address();
        address.setApartment(addressDTO.getApartment());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPinCode(addressDTO.getPinCode());
        address.setUser(user);
        addressRepository.save(address);
        return addressDTO;
    }

    @Override
    public void updateUserAddress(AddressDTO addressDTO) {
        Address address = addressRepository.findById(addressDTO.getId()).orElse(null);
        if (address == null) {
            throw new EntityNotFoundException("user is not found for given id  " + address);
        }
        address.setId(addressDTO.getId());
        address.setApartment(addressDTO.getApartment());
        address.setStreet(addressDTO.getStreet());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setPinCode(addressDTO.getPinCode());
        addressRepository.save(address);

    }

    @Override
    public List<AddressDTO> findAllAddress(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            throw new EntityNotFoundException("user is not found for given id  " + user);
        }

        List<AddressDTO> addressDTOList = new ArrayList<>();
        for (Address address : user.getAddresses()) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setId(address.getId());
            addressDTO.setApartment(address.getApartment());
            addressDTO.setStreet(address.getStreet());
            addressDTO.setCity(address.getCity());
            addressDTO.setState(address.getState());
            addressDTO.setPinCode(address.getPinCode());
            addressDTOList.add(addressDTO);
        }

        return addressDTOList;
    }

    @Override
    public void delete(long id) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address == null) {
            throw new EntityNotFoundException("ID is not found for these  " + id);
        }
        addressRepository.delete(address);
    }

    @Override
    public AddressDTO findById(Long id) {
        Address address = addressRepository.findById(id).orElse(null);
        if (address == null) {
            throw new EntityNotFoundException("Id is not found for these " + id);
        }
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(address.getId());
        addressDTO.setApartment(address.getApartment());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setCity(address.getCity());
        addressDTO.setState(address.getState());
        addressDTO.setPinCode(address.getPinCode());
        return addressDTO;
    }

}
