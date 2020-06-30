package com.alessio.jpa.restController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alessio.jpa.model.Ingredient;
import com.alessio.jpa.model.Recipe;
import com.alessio.jpa.repository.RecipeRepository;

@RestController
public class ControllerRecipe {

	private RecipeRepository recipeRepository;
	
	public ControllerRecipe(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}
	
	@GetMapping({"","/"})
	public List<Recipe> allRecipe() {
		return recipeRepository.findAll();
	}
	
	@GetMapping("/insert")
	public void addRecipe() {
		Ingredient ingredient = new Ingredient("ingredient-"+Math.random(), 10);
		Recipe newRecipe = new Recipe("Recipe-"+Math.random(), null); //contructor without recipe arg
		newRecipe.getIngredients().add(ingredient);
		ingredient.setRecipe(newRecipe);
		recipeRepository.save(newRecipe);
		System.out.println(newRecipe);
	}
}
