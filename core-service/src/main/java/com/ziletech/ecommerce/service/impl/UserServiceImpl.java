package com.ziletech.ecommerce.service.impl;

import com.ziletech.ecommerce.entity.Address;
import com.ziletech.ecommerce.entity.Role;
import com.ziletech.ecommerce.entity.User;
import com.ziletech.ecommerce.repository.UserRepository;
import com.ziletech.ecommerce.service.UserService;
import dto.AddressDTO;
import dto.RoleDTO;
import dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {

        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setContactNumber(userDTO.getContactNumber());
        user.setPassword(userDTO.getPassword());
        List<Address> addresses = new ArrayList<>();
        for (AddressDTO addressDTO : userDTO.getAddresses()) {
            Address address = new Address();
            address.setId(addressDTO.getId());
            address.setApartment(addressDTO.getApartment());
            address.setStreet(addressDTO.getStreet());
            address.setCity(addressDTO.getCity());
            address.setState(addressDTO.getState());
            address.setPinCode(addressDTO.getPinCode());
            addresses.add(address);
        }
        user.setAddresses(addresses);
        user = userRepository.save(user);
        userDTO.setId(user.getId());
        return userDTO;
    }

    @Override
    public void update(UserDTO userDTO) {
        User user = userRepository.findById(userDTO.getId()).orElse(null);
        if (user == null) {
            throw new EntityNotFoundException("ID is not found for these  " + userDTO.getId());
        }
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setContactNumber(userDTO.getContactNumber());
        user.setPassword(userDTO.getPassword());
        List<Address> addresses = new ArrayList<>();
        for (AddressDTO addressDTO : userDTO.getAddresses()) {
            Address address = new Address();
            address.setId(addressDTO.getId());
            address.setApartment(addressDTO.getApartment());
            address.setStreet(addressDTO.getStreet());
            address.setCity(addressDTO.getCity());
            address.setState(addressDTO.getState());
            address.setPinCode(addressDTO.getPinCode());
            addresses.add(address);
        }
        user.setAddresses(addresses);
        userRepository.save(user);

    }

    @Override
    public List<UserDTO> findByAll() {
        List<UserDTO> userList = new ArrayList<>();
        for (User user : userRepository.findAll()) {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setEmail(user.getEmail());
            userDTO.setContactNumber(user.getContactNumber());
            userDTO.setPassword(user.getPassword());

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
            userDTO.setAddresses(addressDTOList);
            userList.add(userDTO);
        }
        return userList;
    }

    @Override
    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new EntityNotFoundException("Id is not found for these " + id);
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setContactNumber(user.getContactNumber());
        userDTO.setPassword(user.getPassword());

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
        userDTO.setAddresses(addressDTOList);
        return userDTO;
    }

    @Override
    public UserDTO findByName(String name) {
        User user = userRepository.findByFirstName(name);
        if (user == null) {
            throw new EntityNotFoundException("User is not found for these " + name);
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setContactNumber(user.getContactNumber());
        userDTO.setPassword(user.getPassword());
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
        userDTO.setAddresses(addressDTOList);
        return userDTO;
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new EntityNotFoundException("ID is not found for these  " + id);
        }
        userRepository.delete(user);
    }

}


