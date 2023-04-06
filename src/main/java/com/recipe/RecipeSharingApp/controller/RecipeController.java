package com.recipe.RecipeSharingApp.controller;

import com.recipe.RecipeSharingApp.entities.Recipe;
import com.recipe.RecipeSharingApp.payload.RecipeDto;
import com.recipe.RecipeSharingApp.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/recipe")
public class RecipeController {


    public final RecipeService recipeService;



    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;

    }


    @PostMapping
    // @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
    public ResponseEntity<RecipeDto> saveRecipe(@RequestBody RecipeDto recipeDto, Principal principal) {
        return new ResponseEntity<>(recipeService.addRecipe(recipeDto, principal), HttpStatus.OK);

    }

    @GetMapping
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        return new ResponseEntity<>(recipeService.getAllRecipes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDto> getById(@PathVariable("id") long id) {
           return new ResponseEntity<>(recipeService.getById(id),HttpStatus.OK);
    }


    @PutMapping("/{id}")
    public ResponseEntity<RecipeDto>  updateById(@PathVariable("id") long id,@RequestBody RecipeDto recipeDto,Principal principal ) throws Exception {

        return new ResponseEntity<>(recipeService.updateById(id,recipeDto,principal),HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id,Principal principal){
        return new ResponseEntity<>(recipeService.deleteById(id,principal),HttpStatus.OK);

    }

}
