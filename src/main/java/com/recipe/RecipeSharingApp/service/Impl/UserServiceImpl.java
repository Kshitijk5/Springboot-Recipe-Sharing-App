package com.recipe.RecipeSharingApp.service.Impl;

import com.recipe.RecipeSharingApp.entities.Recipe;
import com.recipe.RecipeSharingApp.entities.User;
import com.recipe.RecipeSharingApp.exception.ResourceNotFoundException;
import com.recipe.RecipeSharingApp.repository.RecipeRepository;
import com.recipe.RecipeSharingApp.repository.UserRepository;
import com.recipe.RecipeSharingApp.service.UserService;
import com.recipe.RecipeSharingApp.payload.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ModelMapper modelMapper;



    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto getById(long id) {
        return mapToDto(userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id)));

    }

    @Override
    public String deleteById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        List<Recipe> userRecipe  = recipeRepository.findByUser(user);
        for ( Recipe recipe: userRecipe) {
            recipe.setUser(null);
            recipeRepository.save(recipe);
        }

        userRepository.delete(user);
        return "User deleted";

    }

    @Override
    public UserDto updateById(long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));

        User tempUser = mapToEntity(userDto);

        user.setUsername(tempUser.getUsername());
        user.setPassword(passwordEncoder.encode(tempUser.getPassword()));
        user.setEmail(tempUser.getEmail());

        return mapToDto(userRepository.save(user));
    }

    private User mapToEntity(com.recipe.RecipeSharingApp.payload.UserDto dto) {
        return modelMapper.map(dto, User.class);
    }

    private UserDto mapToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

}
