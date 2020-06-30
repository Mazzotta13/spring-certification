package com.alessio.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alessio.jpa.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long>{

}
