package com.recipe.RecipeSharingApp.repository;

import com.recipe.RecipeSharingApp.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    @Override
    Optional<Recipe> findById(Long aLong);


}
