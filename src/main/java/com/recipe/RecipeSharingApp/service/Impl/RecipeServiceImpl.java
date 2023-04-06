package com.recipe.RecipeSharingApp.service.Impl;

import com.recipe.RecipeSharingApp.entities.Difficulty;
import com.recipe.RecipeSharingApp.entities.Recipe;
import com.recipe.RecipeSharingApp.entities.User;
import com.recipe.RecipeSharingApp.exception.ResourceNotFoundException;
import com.recipe.RecipeSharingApp.payload.RecipeDto;
import com.recipe.RecipeSharingApp.repository.DifficultyRepository;
import com.recipe.RecipeSharingApp.repository.RecipeRepository;
import com.recipe.RecipeSharingApp.repository.UserRepository;
import com.recipe.RecipeSharingApp.security.CustomUserDetail;
import com.recipe.RecipeSharingApp.service.RecipeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class RecipeServiceImpl implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DifficultyRepository difficultyRepository;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();

    }


    @Override
    public RecipeDto addRecipe(RecipeDto recipeDto, Principal principal) {

        Recipe recipe = mapToEntity(recipeDto);

        Difficulty difficulty = difficultyRepository.findByDifficultyLevel(recipe.getDifficultyLevel().getDifficultyLevel())
                                                    .orElseThrow(()-> new ResourceNotFoundException("Difficulty","difficultyLevel",recipe.getDifficultyLevel().getDifficultyLevel()));
        recipe.setDifficultyLevel(difficulty);


        User user = userRepository.findByUsernameOrEmail(principal.getName(), principal.getName())
                                  .orElseThrow(()->new ResourceNotFoundException("User","Username",principal.getName()));
        recipe.setUser(user);

        user.getRecipes().add(recipe);
        difficulty.getRecipes().add(recipe);


        return mapToDto(recipeRepository.save(recipe));
    }

    @Override
    public RecipeDto getById(long id) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", id));
        return mapToDto(recipe);
    }

    @Override
    public String deleteById(long id, Principal principal) {
        CustomUserDetail customUserDetail = (CustomUserDetail) ((Authentication) principal).getPrincipal();
        long userId = customUserDetail.getId();

        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", id));

        if (userId == recipe.getUser().getId()) {
            recipeRepository.delete(recipe);
            return "Deleted Recipe";
        }
        return "User is not the owner";

    }

    @Override
    public RecipeDto updateById(long id,RecipeDto recipeDto, Principal principal) throws Exception {
        CustomUserDetail customUserDetail = (CustomUserDetail) ((Authentication) principal).getPrincipal();
        long userId = customUserDetail.getId();

        Recipe recipe = recipeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Recipe", "id", id));
        if (userId == recipe.getUser().getId()) {
            Recipe recipe1 = mapToEntity(recipeDto);
            recipe.setTitle(recipe1.getTitle());
            recipe.setIngredients(recipe1.getIngredients());
            recipe.setDirections(recipe1.getDirections());
            recipe.setCookTime(recipe1.getCookTime());
            recipe.setCuisine(recipe1.getCuisine());
            recipe.setDifficultyLevel(recipe1.getDifficultyLevel());

            return  mapToDto(recipeRepository.save(recipe));

        }
        throw  new Exception("Access Denied");

    }


    private Recipe mapToEntity(RecipeDto recipeDto) {
        return modelMapper.map(recipeDto, Recipe.class);
    }

    private RecipeDto mapToDto(Recipe recipe) {
        return modelMapper.map(recipe, RecipeDto.class);
    }


}
