package com.recipe.RecipeSharingApp.controller;

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
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping(value = {"/signin","/login"})
    public ResponseEntity<String> login(@RequestBody Login login){
        return new ResponseEntity<>(authService.loginUser(login),HttpStatus.OK);
    }

    @PostMapping(value={"/signup","/register"})
    public ResponseEntity<String> register(@RequestBody Register register){

        return new ResponseEntity<String>("User registered successfully!.",HttpStatus.OK);
    }


}
