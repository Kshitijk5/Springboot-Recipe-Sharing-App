package com.recipe.RecipeSharingApp.entities;

import jakarta.persistence.*;
import lombok.*;
import org.yaml.snakeyaml.events.Event;

import java.time.Duration;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
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
