package com.recipe.RecipeSharingApp.payload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.recipe.RecipeSharingApp.entities.Difficulty;
import com.recipe.RecipeSharingApp.entities.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RecipeDto {
    private long id;
    private String title;

    private List<String> ingredients;
    private List<String> directions;

    private Duration cookTime;

    private String cuisine;

    private Difficulty difficultyLevel;



    private User user;
}
