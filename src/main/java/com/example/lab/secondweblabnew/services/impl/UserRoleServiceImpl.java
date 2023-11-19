package com.example.lab.secondweblabnew.services.impl;

import com.example.lab.secondweblabnew.models.UserRole;
import com.example.lab.secondweblabnew.repositories.UserRoleRepository;
import com.example.lab.secondweblabnew.services.UserRoleService;
import com.example.lab.secondweblabnew.services.dtos.UserRoleDTO;
import com.example.lab.secondweblabnew.util.ValidationUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {
    private UserRoleRepository userRoleRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public UserRoleServiceImpl(ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Autowired
    public void setUserRoleRepository (UserRoleRepository userRoleRepository) { this.userRoleRepository = userRoleRepository; }

    @Override
    public void add(UserRoleDTO userRoleDTO) {
        if (!validationUtil.isValid(userRoleDTO))
        {
            this.validationUtil
                    .violations(userRoleDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        } else
        {
            try {
                this.userRoleRepository.saveAndFlush(this.modelMapper.map(userRoleDTO, UserRole.class));
            }
            catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }

    @Override
    public void update(String uuid, UserRoleDTO newUserRoleDTO) {
        if (!validationUtil.isValid(newUserRoleDTO))
        {
            this.validationUtil
                    .violations(newUserRoleDTO)
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }
        {
            try {
                Optional<UserRole> existingUserRole = userRoleRepository.findById(uuid);
                if (existingUserRole == null)
                    throw new EntityNotFoundException("");
                UserRole userRole = existingUserRole.get();
                modelMapper.map(newUserRoleDTO, userRole);
                userRoleRepository.saveAndFlush(userRole);
            }
            catch (Exception e) {
                System.out.println("Oops, something went wrong! :(");
            }
        }
    }

    @Override
    public void deleteByUuid(String uuid) {
        userRoleRepository.deleteById(uuid);
    }

    @Override
    public Optional<UserRole> findByUuid(String uuid) {
        return userRoleRepository.findById(uuid);
    }

    @Override
    public List<UserRole> getAll() {
        return userRoleRepository.findAll();
    }
}
