package com.alessio.jpa.dto;

import com.alessio.jpa.model.Ingredient;

public class IngredientDTO {
	private Long id;
	private String description;
	private int amount;
	
	public static IngredientDTO toIngredientDTO(Ingredient ingredient) {
		IngredientDTO ingredientDTO = new IngredientDTO();
		ingredientDTO.setAmount(ingredient.getAmount());
		ingredientDTO.setDescription(ingredient.getDescription());
		ingredientDTO.setId(ingredient.getId());
		return ingredientDTO;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
}
