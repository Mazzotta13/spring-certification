package com.alessio.jpa.model;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
	@ManyToMany(fetch = FetchType.EAGER,mappedBy = "categories")
	private Collection<Recipe> recipes;
	
	public Category() {}
	public Category(String description, Collection<Recipe> recipes) {
		this.description = description;
		this.recipes = recipes;
	}
	
//	@Override
//	public String toString() {
//		return "Category [id=" + id + ", description=" + description + ", recipes=" + recipes + "]";
//	}
	
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
	public Collection<Recipe> getRecipes() {
		return recipes;
	}
	public void setRecipes(Collection<Recipe> recipes) {
		this.recipes = recipes;
	}
}