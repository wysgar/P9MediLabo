package com.note.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.note.api.model.Note;
import com.note.api.repository.NoteRepository;

@Service
public class NoteService {
	
	@Autowired
	private NoteRepository noteRepository;

	public List<Note> getNoteByPatient(Integer id) {
		return noteRepository.findByPatientId(id);
	}

	public Note saveNote(Note note) {
		return noteRepository.save(note);
	}

}
