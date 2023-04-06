package com.recipe.RecipeSharingApp.service;

import com.recipe.RecipeSharingApp.entities.Recipe;
import com.recipe.RecipeSharingApp.payload.RecipeDto;

import java.security.Principal;
import java.util.List;

public interface RecipeService {

    List<Recipe> getAllRecipes();



    RecipeDto addRecipe(RecipeDto recipeDto, Principal principal);

    RecipeDto getById(long id);

    String deleteById(long id,Principal principal);

    RecipeDto updateById(long id,RecipeDto recipeDto, Principal principal) throws Exception;
}
