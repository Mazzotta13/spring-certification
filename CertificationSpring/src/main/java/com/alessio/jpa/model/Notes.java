package com.alessio.jpa.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
public class Notes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	@OneToOne // no cascade perchè se eliminiamo una nota non vogliamo eliminare le Recipe
	private Recipe recipe;
	@Lob // entry di grandi dimensioni (maggiori di 256 caratteri)
	private String recipeNotes;
	
	@Override
	public String toString() {
		return "Notes [id=" + id + ", recipe=" + recipe + ", recipeNotes=" + recipeNotes + "]";
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	public String getRecipeNotes() {
		return recipeNotes;
	}
	public void setRecipeNotes(String recipeNotes) {
		this.recipeNotes = recipeNotes;
	}
}
