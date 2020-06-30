package com.alessio.jpa.dto;

import com.alessio.jpa.model.Notes;

public class NotesDTO {
	private String id;
	private String recipeNotes;

	public static NotesDTO toNotesDTO(Notes notes) {
		NotesDTO notesDTO = new NotesDTO();
		notesDTO.setId(notes.getId());
		notesDTO.setRecipeNotes(notes.getRecipeNotes());
		return notesDTO;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRecipeNotes() {
		return recipeNotes;
	}
	public void setRecipeNotes(String recipeNotes) {
		this.recipeNotes = recipeNotes;
	}
}
