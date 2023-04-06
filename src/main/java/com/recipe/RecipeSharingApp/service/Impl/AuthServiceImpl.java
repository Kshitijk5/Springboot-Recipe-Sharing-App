package com.recipe.RecipeSharingApp.service.Impl;

import com.recipe.RecipeSharingApp.dto.Login;
import com.recipe.RecipeSharingApp.dto.Register;
import com.recipe.RecipeSharingApp.entities.Role;
import com.recipe.RecipeSharingApp.entities.User;
import com.recipe.RecipeSharingApp.exception.RecipeAPIException;
import com.recipe.RecipeSharingApp.repository.RoleRepository;
import com.recipe.RecipeSharingApp.repository.UserRepository;
import com.recipe.RecipeSharingApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public String loginUser(Login loginDto) {


//        Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(loginDto.getUsernameOrEmail(), loginDto.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);

        return "User logged in ";

    }

    @Override
    public String registerUser(Register registerDto) {
        // add check for username exists in database
        if (userRepository.existsByUsername(registerDto.getUsername())) {

            throw new RecipeAPIException(HttpStatus.BAD_REQUEST, "Username is already exists!.");

        }

        // add check for email exists in database
        if (userRepository.existsByEmail(registerDto.getEmail())) {

            throw new RecipeAPIException(HttpStatus.BAD_REQUEST, "Email is already exists!.");
        }

        User newUser = new User();

        newUser.setUsername(registerDto.getUsername());
        newUser.setEmail(registerDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        newUser.setCreationDate(new Date());


        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        newUser.setRoles(roles);

        userRepository.save(newUser);
        return "User successfully registered";
    }
}
