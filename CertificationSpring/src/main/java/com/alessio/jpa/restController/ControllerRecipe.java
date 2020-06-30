package com.alessio.jpa.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alessio.jpa.dto.RecipeDTO;
import com.alessio.jpa.model.Ingredient;
import com.alessio.jpa.model.Notes;
import com.alessio.jpa.model.Recipe;
import com.alessio.jpa.repository.RecipeRepository;

@RestController
public class ControllerRecipe {

	private RecipeRepository recipeRepository;
	
	public ControllerRecipe(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}
	
	@GetMapping({"","/"})
	public List<RecipeDTO> allRecipe() {
		List<RecipeDTO> recipeDTOList = new ArrayList<>();
		recipeRepository.findAll().forEach(recipe -> {
			recipeDTOList.add(RecipeDTO.toRecipeDTO(recipe));
		});;
		return recipeDTOList;
	}
	
	@GetMapping("/insert")
	public RecipeDTO addRecipe() {
		Ingredient ingredient = new Ingredient("ingredient-"+Math.random(), 10);
		Notes notes = new Notes();
		notes.setRecipeNotes("recipe-notes-1");
		Recipe newRecipe = new Recipe("Recipe-"+Math.random(), notes);
		newRecipe.getNotes().setRecipe(newRecipe);
		newRecipe.getIngredients().add(ingredient);
		ingredient.setRecipe(newRecipe);
		newRecipe = recipeRepository.save(newRecipe);
		return RecipeDTO.toRecipeDTO(newRecipe);
	}
}
