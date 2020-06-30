package com.alessio.jpa.dto;

import com.alessio.jpa.model.Category;

public class CategoryDTO {
	private Long id;
	private String description;
	
	public static CategoryDTO toCategoryDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setDescription(category.getDescription());
		categoryDTO.setId(category.getId());
		return categoryDTO;
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
}
