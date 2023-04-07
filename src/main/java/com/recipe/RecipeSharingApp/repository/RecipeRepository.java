package com.recipe.RecipeSharingApp.repository;

import com.recipe.RecipeSharingApp.entities.Recipe;
import com.recipe.RecipeSharingApp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe,Long> {
    @Override
    Optional<Recipe> findById(Long aLong);

    List<Recipe> findByUser(User user);


}
