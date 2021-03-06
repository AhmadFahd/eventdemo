package com.ahmadfahd.ServicesImplementation;

import com.ahmadfahd.enums.ROLES;
import com.ahmadfahd.Services.UserServices;
import com.ahmadfahd.dto.ObjectMapperUtils;
import com.ahmadfahd.dto.UserGetDto;
import com.ahmadfahd.dto.UsersDTO;
import com.ahmadfahd.entity.RolesEntity;
import com.ahmadfahd.entity.UsersEntity;
import com.ahmadfahd.repository.RolesRepository;
import com.ahmadfahd.repository.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServicesImp implements UserServices {

    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RolesRepository rolesRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserGetDto> getAllUsers() {
        List<UsersEntity> usersEntities = usersRepository.findAll();
        List<UserGetDto> usersDTOS = ObjectMapperUtils.mapAll(usersEntities, UserGetDto.class);

        return usersDTOS;

    }

    @Override
    public List<UserGetDto> findAllPresent() {
        List<UsersEntity> usersEntities = usersRepository.findByEnabledIsTrue();
        List<UserGetDto> usersDTOS = ObjectMapperUtils.mapAll(usersEntities, UserGetDto.class);
        return usersDTOS;
    }
    @Override
    public List<UserGetDto> findAllDisable() {
        List<UsersEntity> usersEntities = usersRepository.findByEnabledFalse();
        List<UserGetDto> usersDTOS = ObjectMapperUtils.mapAll(usersEntities, UserGetDto.class);
        return usersDTOS;
    }

    @Override
    public UserGetDto findById(Long userid) {
//        if (usersRepository.findById(userid).isPresent()) {
        UsersEntity usersEntity = usersRepository.findByIdAndEnabledTrue(userid).get();
        UserGetDto userGetDto = modelMapper.map(usersEntity, UserGetDto.class);
        return userGetDto;

    }

    // FIXME: 11/19/2018
    @Override
    public void addUser(UsersDTO usersDTO, String role) {
        if (usersRepository.existsByUsername(usersDTO.getUsername())) {
            throw new RuntimeException(usersDTO.getUsername() + " is Already exist!");
        } else if (usersRepository.existsByEmail(usersDTO.getEmail())) {
            throw new RuntimeException(usersDTO.getEmail() + " is Already exist!");
        }
        UsersEntity usersEntity = modelMapper.map(usersDTO, UsersEntity.class);
        usersEntity.setPassword(new BCryptPasswordEncoder().encode(usersDTO.getPassword()));
        usersEntity.setEnabled(true);
        RolesEntity rolesEntity = new RolesEntity();
        rolesEntity.setRoleName(role);
        rolesEntity.setUser(usersEntity);
        usersRepository.save(usersEntity);
        rolesRepository.save(rolesEntity);
    }

    @Override
    public void updateUser(UsersDTO usersDTO, Long userid) {

        if (usersRepository.findById(userid).isPresent()) {
            UsersEntity usersEntity;
            usersEntity = modelMapper.map(usersDTO, UsersEntity.class);
            usersEntity.setId(userid);
            usersEntity.setEnabled(usersRepository.findById(userid).get().isEnabled());
            usersEntity.setPassword(new BCryptPasswordEncoder().encode(usersDTO.getPassword()));
            usersRepository.saveAndFlush(usersEntity);
        }
    }

    @Override
    public void enableUser(Long userid, boolean b) {
        if (usersRepository.findById(userid).isPresent()) {
            UsersEntity usersEntity = usersRepository.findById(userid).get();
            usersEntity.setEnabled(b);
            usersRepository.save(usersEntity);
        }
    }

    @Override
    public UsersDTO findByUsername(String username) {
        UsersEntity usersEntity = usersRepository.findByUsername(username);
        UsersDTO usersDTO = modelMapper.map(usersEntity, UsersDTO.class);
        return usersDTO;
    }

    @Override
    public UsersDTO findByEmail(String email) {
        UsersEntity usersEntity = usersRepository.findByEmail(email);
        UsersDTO usersDTO = modelMapper.map(usersEntity, UsersDTO.class);
        return usersDTO;
    }

    @Override
    public UsersDTO findByUsernameAndEnabledTrue(String username) {
        UsersEntity usersEntity = usersRepository.findByUsernameAndEnabledTrue(username);
        UsersDTO usersDTO = modelMapper.map(usersEntity, UsersDTO.class);
        return usersDTO;
    }

    @Override
    public boolean isActiveUser(String username) {
        return usersRepository.existsByUsernameAndEnabledTrue(username);
    }
    @Override
    public boolean isActiveUserByEmail(String email) {
        return usersRepository.existsByEmailAndEnabledTrue(email);
    }

    @Override
    public boolean isUser(String username) {
        return usersRepository.existsByUsername(username);
    }

    @Override
    public boolean isUserByEmail(String email){
        return usersRepository.existsByEmail(email);
    }


    @Override
    public List<UserGetDto> findAllOrganizers() {
        List<UsersEntity> usersEntities = usersRepository.findByRolesRoleNameNotContains(ROLES.ROLE_ADMIN.toString());
        List<UsersEntity> filtered = new ArrayList<>();
        for (UsersEntity usersEntity : usersEntities) {
            if (!usersEntity.getRoles().contains(rolesRepository.findByUserAndRoleName(usersEntity, ROLES.ROLE_ADMIN.toString()))) {
                filtered.add(usersEntity);
            }
        }
        List<UserGetDto> userGetDtos = ObjectMapperUtils.mapAll(filtered, UserGetDto.class);
        return userGetDtos;

    }
}
