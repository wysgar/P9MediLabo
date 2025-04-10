package com.note.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.note.api.model.Note;
import com.note.api.repository.NoteRepository;
import com.note.api.service.NoteService;

@Configuration
@RestController
@RequestMapping("/note")
public class NoteController {

	@Autowired
	private NoteService noteService;
	
	@GetMapping("/{id}")
	public List<Note> getNoteByPatient(@PathVariable Integer id) {
		return noteService.getNoteByPatient(id);
	}
	
	@PostMapping
	private ResponseEntity<Object> addNote(@RequestBody Note note) {
		Note noteAdded = noteService.saveNote(note);
		if (Objects.isNull(noteAdded)) {
			return ResponseEntity.noContent().build();
		}
		
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(noteAdded.getId())
				.toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@Bean
    CommandLineRunner initDatabase(NoteRepository noteRepository) {
	    return args -> {
	        if (noteRepository.count() == 0) {
	            Note note = new Note();
	            note.setPatientId(1);
	            note.setPatient("TestNone");
	            note.setContent("Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé");
	            
	            Note note2 = new Note();
	            note2.setPatientId(2);
	            note2.setPatient("TestBorderline");
	            note2.setContent("Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement");
	            
	            Note note3 = new Note();
	            note3.setPatientId(2);
	            note3.setPatient("TestBorderline");
	            note3.setContent("Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être anormale");
	            
	            Note note4 = new Note();
	            note4.setPatientId(3);
	            note4.setPatient("TestInDanger");
	            note4.setContent("Le patient déclare qu'il fume depuis peu");
	            
	            Note note5 = new Note();
	            note5.setPatientId(3);
	            note5.setPatient("TestInDanger");
	            note5.setContent("Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d’apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé");
	            
	            Note note6 = new Note();
	            note6.setPatientId(4);
	            note6.setPatient("TestEarlyOnset");
	            note6.setContent("Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments");
	            
	            Note note7 = new Note();
	            note7.setPatientId(4);
	            note7.setPatient("TestEarlyOnset");
	            note7.setContent("Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps");
	            
	            Note note8 = new Note();
	            note8.setPatientId(4);
	            note8.setPatient("TestEarlyOnset");
	            note8.setContent("Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé");
	            
	            Note note9 = new Note();
	            note9.setPatientId(4);
	            note9.setPatient("TestEarlyOnset");
	            note9.setContent("Taille, Poids, Cholestérol, Vertige et Réaction");

	            noteRepository.save(note);
	            noteRepository.save(note2);
	            noteRepository.save(note3);
	            noteRepository.save(note4);
	            noteRepository.save(note5);
	            noteRepository.save(note6);
	            noteRepository.save(note7);
	            noteRepository.save(note8);
	            noteRepository.save(note9);

	            System.out.println("Notes Created");
	        } else {
	            System.out.println("Notes already exist, skipping initialization.");
	        }
	    };
	}
}
