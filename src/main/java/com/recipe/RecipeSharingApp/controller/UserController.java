package com.recipe.RecipeSharingApp.controller;

import com.recipe.RecipeSharingApp.entities.User;
import com.recipe.RecipeSharingApp.payload.UserDto;
import com.recipe.RecipeSharingApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getById(@PathVariable("id") long id){
    	return new ResponseEntity<>(userService.getById(id),HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id) {
    	return new ResponseEntity<>(userService.deleteById(id),HttpStatus.OK);
    }
    
    @PutMapping("/{id}")
    @PreAuthorize("#id == authentication.principal.id")
    public ResponseEntity<UserDto> updateById(@PathVariable("id")long id,@RequestBody UserDto userDto){
    	return new ResponseEntity<>(userService.updateById(id,userDto),HttpStatus.OK);
    }

}
