package com.risque.api.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.risque.api.model.Note;
import com.risque.api.model.Patient;
import com.risque.api.model.Risque;

@Service
public class RisqueService {
	
	@Autowired
	private InfoService infoService;
	
	@Autowired 
	private NoteService noteService;
	private static final List<String> triggers = List.of(
	        "Hémoglobine A1C", "Microalbumine", "Taille", "Poids",
	        "Fumeur", "Fumeuse", "Anormal", "Cholestérol", "Vertiges",
	        "Rechute", "Réaction", "Anticorps"
	    );

	public Risque evaluateRisk(Integer id) {
		Patient patient = infoService.findById(id); 
		List<Note> notes = noteService.getNoteByPatient(id);
		
        int age = Period.between(LocalDate.parse(patient.getBirthday()), LocalDate.now()).getYears();
        int triggerCount = countTriggers(notes);

        if (triggerCount == 0) return Risque.None;

        if (triggerCount >= 8 && age > 30) return Risque.EarlyOnset;
        if (triggerCount >= 7 && age < 30 && patient.getGender().equals("F")) return Risque.EarlyOnset;
        if (triggerCount >= 5 && age < 30 && patient.getGender().equals("M")) return Risque.EarlyOnset;

        if ((age > 30 && triggerCount >= 6 && triggerCount <= 7)) return Risque.InDanger;
        if (age < 30 && patient.getGender().equals("F") && triggerCount >= 4) return Risque.InDanger;
        if (age < 30 && patient.getGender().equals("M") && triggerCount >= 3) return Risque.InDanger;

        if (triggerCount >= 2 && triggerCount <= 5 && age > 30) return Risque.Borderline;

        return Risque.None;
    }

    private int countTriggers(List<Note> notes) {
    	return (int) notes.stream()
    	        .mapToLong(note ->
    	            triggers.stream()
    	                .filter(trigger -> note.getContent().toLowerCase().contains(trigger.toLowerCase()))
    	                .count()
    	        ).sum();
    }
}
