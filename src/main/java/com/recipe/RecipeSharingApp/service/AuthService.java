package com.recipe.RecipeSharingApp.service;

import com.recipe.RecipeSharingApp.dto.Login;
import com.recipe.RecipeSharingApp.dto.Register;

public interface AuthService {

    String loginUser(Login loginDto);
    String registerUser(Register registerDto);
}
