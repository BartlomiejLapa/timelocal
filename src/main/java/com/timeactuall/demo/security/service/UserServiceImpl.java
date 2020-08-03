package com.timeactuall.demo.security.service;

import com.timeactuall.demo.security.model.Role;
import com.timeactuall.demo.security.model.User;
import com.timeactuall.demo.security.repository.RoleRepository;
import com.timeactuall.demo.security.repository.UserRepository;
import com.timeactuall.demo.security.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    public User save(UserRegistrationDto registrationDto) {
        User user = new User();
        user.setEmail(registrationDto.getEmail());
        user.setUsername(registrationDto.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
//        user.setRoles(Arrays.asList(new Role("USER_ROLE")));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        return userRepository.save(user);
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = userRepository.findByEmail(email);
//        if (user == null){
//            throw new UsernameNotFoundException("invalid user name or password");
//        }
//        return new org.springframework.security.core.userdetails.User (
//                user.getEmail(),
//                user.getPassword(),
//                mapRolesToAuthorities(user.getRoles()));
//    }

//    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection< Role > roles) {
//        return roles.stream()
//                .map(role -> new SimpleGrantedAuthority(role.getName()))
//                .collect(Collectors.toList());
//    }
}
