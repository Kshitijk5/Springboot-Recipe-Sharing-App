package com.recipe.RecipeSharingApp.service;

import com.recipe.RecipeSharingApp.payload.Login;
import com.recipe.RecipeSharingApp.payload.Register;

public interface AuthService {

    String loginUser(Login loginDto);
    String registerUser(Register registerDto);
}
