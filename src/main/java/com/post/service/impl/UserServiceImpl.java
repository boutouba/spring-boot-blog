package com.post.service.impl;

import com.post.dto.UserDto;
import com.post.entity.Role;
import com.post.entity.User;
import com.post.exception.UserAlreadyExistsException;
import com.post.repository.RoleRepository;
import com.post.repository.UserRepository;
import com.post.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        User u = userRepository.findByEmail(userDto.getEmail());
        if(u != null) {
            throw new UserAlreadyExistsException("User already exists!!");
        }
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null){
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
        user = userRepository.findByEmail(userDto.getEmail());
        UserDto dto = this.mapToUserDto(user);
        return dto;
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    private UserDto mapToUserDto(User user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist(){
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    public UserDto deleteUser(Long id) {

        User user = userRepository.findById(id).orElse(null);
        UserDto udto = this.mapToUserDto(user);
        if(user != null) {
            user.getRoles().clear();
            userRepository.delete(user);
        }
        return udto;

    }
}