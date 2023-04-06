package com.recipe.RecipeSharingApp.controller;

import com.recipe.RecipeSharingApp.payload.Login;
import com.recipe.RecipeSharingApp.payload.Register;
import com.recipe.RecipeSharingApp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping(value = {"/signin", "/login"})
    public ResponseEntity<String> login(@RequestBody Login login) {
        return new ResponseEntity<>(authService.loginUser(login), HttpStatus.OK);
    }

    @PostMapping(value = {"/signup", "/register"})
    public ResponseEntity<String> register(
            @RequestBody Register register,
            @RequestParam(value = "file", required = false) MultipartFile profilePic
    ) {

        return new ResponseEntity<String>(authService.registerUser(register), HttpStatus.OK);
    }


}
