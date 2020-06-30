package com.alessio.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alessio.jpa.model.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, Long>{

}
