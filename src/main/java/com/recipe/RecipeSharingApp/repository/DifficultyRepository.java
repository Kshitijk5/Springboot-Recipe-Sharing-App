package com.recipe.RecipeSharingApp.repository;

import com.recipe.RecipeSharingApp.entities.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DifficultyRepository extends JpaRepository<Difficulty,Long> {
}
