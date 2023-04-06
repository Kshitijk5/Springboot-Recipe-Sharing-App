package com.recipe.RecipeSharingApp.repository;

import com.recipe.RecipeSharingApp.entities.Difficulty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DifficultyRepository extends JpaRepository<Difficulty,Long> {

    Optional<Difficulty> findByDifficultyLevel(String diffcultyLevel);
}
