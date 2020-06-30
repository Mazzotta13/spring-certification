package com.alessio.jpa;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.alessio.jpa.model.Category;
import com.alessio.jpa.model.Ingredient;
import com.alessio.jpa.model.Notes;
import com.alessio.jpa.model.Recipe;
import com.alessio.jpa.repository.CategoryRepository;
import com.alessio.jpa.repository.IngredientRepository;
import com.alessio.jpa.repository.RecipeRepository;

// remove autoconfiguration datasource
@SpringBootApplication
@ComponentScan(basePackages = "com.alessio.jpa")
public class DataApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(DataApplication.class, args);
	}
	
	@Autowired
	public RecipeRepository recipeRepository;
	@Autowired
	public IngredientRepository ingredientRepository;
	@Autowired
	public CategoryRepository categoryRepository;

	@Override
	public void run(String... args) throws Exception {
		// one to one reletionship
		Notes notes = new Notes();
		notes.setRecipeNotes("note ricetta 1...");
		recipeRepository.save(new Recipe("ricetta 1", notes ));
		// one to many reletionship
		// 1) Ingredient
		Ingredient ingredient = new Ingredient("description", 10);
		// 2) recipe
		Recipe recipe2 = new Recipe("ricetta 2", null);
		recipe2.getIngredients().add(ingredient);
		ingredient.setRecipe(recipe2);
		
		recipeRepository.save(recipe2);
//		ingredientRepository.findAll().forEach(ingredient -> System.out.println(ingredient));
		
		// many to many reletionship
		Set<Category> categories = new HashSet<>();
		categories.add(new Category("category description", null));
		recipeRepository.save(new Recipe("ricetta 3", null, null, categories));
		recipeRepository.findAll().forEach(recipe -> printRecipe(recipe));

	}
	
	private void printRecipe(Recipe recipe) {
		System.out.println(recipe);
		recipe.getCategories().forEach(category -> System.out.println("Category: "+category.getDescription()));
	}

}