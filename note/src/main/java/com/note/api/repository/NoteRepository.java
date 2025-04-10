package com.note.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.note.api.model.Note;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
	public List<Note> findByPatientId(Integer patientId);
}
