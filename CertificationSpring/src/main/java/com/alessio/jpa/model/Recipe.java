package com.alessio.jpa.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Recipe {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;
	@OneToOne(cascade = CascadeType.ALL) // se eliminiamo la Recipe eliminiamo anche notes
	private Notes notes;
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Ingredient> ingredients = new HashSet<>();
	@ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "recipe_category",
		joinColumns = @JoinColumn(name = "recipe_id"),
		inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories;
	
	public Recipe() {}
	public Recipe(String name, Notes notes) {
		super();
		this.notes = notes;
		this.name = name;
	}
	public Recipe(String name, Notes notes, Set<Ingredient> ingredients) {
		this.name = name;
		this.notes = notes;
		this.ingredients = ingredients;
	}
	
	public Recipe(String name, Notes notes, Set<Ingredient> ingredients, Set<Category> categories) {
		super();
		this.name = name;
		this.notes = notes;
		this.ingredients = ingredients;
		this.categories = categories;
	}
	
	@Override
	public String toString() {
		return "Recipe [id=" + id + ", name=" + name + ", notes=" + notes + ", ingredients=" + ingredients
				+ ", categories=" + categories + "]";
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Notes getNotes() {
		return notes;
	}
	public void setNotes(Notes notes) {
		this.notes = notes;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(Set<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public Set<Category> getCategories() {
		return categories;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
}
