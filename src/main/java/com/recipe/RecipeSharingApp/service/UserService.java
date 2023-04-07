package com.recipe.RecipeSharingApp.service;

import com.recipe.RecipeSharingApp.entities.User;
import com.recipe.RecipeSharingApp.payload.UserDto;

import java.util.List;

public interface UserService {

    List<UserDto> getAllUsers();

	UserDto getById(long id);

	String deleteById(long id);

	UserDto updateById(long id, UserDto userDto);
}
