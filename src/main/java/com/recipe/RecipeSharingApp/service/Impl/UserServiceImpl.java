package com.recipe.RecipeSharingApp.service.Impl;

import com.recipe.RecipeSharingApp.entities.User;
import com.recipe.RecipeSharingApp.exception.ResourceNotFoundException;
import com.recipe.RecipeSharingApp.repository.UserRepository;
import com.recipe.RecipeSharingApp.service.UserService;
import com.recipe.RecipeSharingApp.payload.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getById(long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));

	}

	@Override
	public String deleteById(long id) {
		User user = getById(id);

		userRepository.delete(user);
		return "User deleted";

	}

	@Override
	public UserDto updateById(long id, UserDto userDto) {
		User user = mapToEntity(userDto);

		User tempUser = getById(id);

		tempUser.setUsername(userDto.getUsername());
		tempUser.setPassword(user.getPassword());
		tempUser.setEmail(user.getEmail());

//		userRepository.save(tempUser);

		return mapToDto(userRepository.save(tempUser));
	}

	private User mapToEntity(com.recipe.RecipeSharingApp.payload.UserDto dto) {
		return modelMapper.map(dto, User.class);
	}

	private UserDto mapToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}

}
