package com.alessio.jpa.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.alessio.jpa.model.Recipe;

public class RecipeDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	private NotesDTO notes;
	private Set<IngredientDTO> ingredients = new HashSet<>();
	private Set<CategoryDTO> categories;
	
	public static RecipeDTO toRecipeDTO(Recipe recipe) {
		RecipeDTO recipeDTO = new RecipeDTO();
		// categories
		Set<CategoryDTO> categoriesDTO = new HashSet<>();
		if (recipe.getCategories() != null) {
			recipe.getCategories().forEach(category -> {
				categoriesDTO.add(CategoryDTO.toCategoryDTO(category));
			});
		}
		recipeDTO.setCategories(categoriesDTO);
		recipeDTO.setId(recipe.getId());
		// ingredients
		Set<IngredientDTO> ingredientsDTO = new HashSet<>();
		if (recipe.getIngredients() != null) {
			recipe.getIngredients().forEach(ingredient -> {
				ingredientsDTO.add(IngredientDTO.toIngredientDTO(ingredient));
			});
		}
		recipeDTO.setIngredients(ingredientsDTO);
		recipeDTO.setName(recipe.getName());
		if (recipe.getNotes() != null) {
			recipeDTO.setNotes(NotesDTO.toNotesDTO(recipe.getNotes()));
		}
		return recipeDTO;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public NotesDTO getNotes() {
		return notes;
	}
	public void setNotes(NotesDTO notes) {
		this.notes = notes;
	}
	public Set<IngredientDTO> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Set<IngredientDTO> ingredients) {
		this.ingredients = ingredients;
	}
	public Set<CategoryDTO> getCategories() {
		return categories;
	}
	public void setCategories(Set<CategoryDTO> categories) {
		this.categories = categories;
	}
}
