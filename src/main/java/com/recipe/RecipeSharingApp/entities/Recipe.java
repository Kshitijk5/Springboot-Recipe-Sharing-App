package com.recipe.RecipeSharingApp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.yaml.snakeyaml.events.Event;

import java.time.Duration;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Recipe {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private long id;
     private String title;

     private List<String> ingredients;
     private List<String> Directions;

     private Duration cookTime;

     private String cuisine;
     @ManyToOne
     @JoinColumn(name="difficulty_level_id",nullable = false)
     private Difficulty difficultyLevel;

     @ManyToOne
     @JoinColumn(name="user_id",nullable = false)
     private User user;

}
