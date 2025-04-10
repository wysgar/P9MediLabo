package com.front.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.front.api.model.Note;
import com.front.api.model.Patient;
import com.front.api.service.NoteService;
import com.front.api.service.PatientService;

@Controller
@RequestMapping("/note")
public class NoteController {
	
	@Autowired
	private NoteService noteService;
	@Autowired 
	private PatientService patientService;
	
	@GetMapping("/add")
    public String addPatientForm(Note note, Integer patientId, Model model) {
		Patient patient = patientService.findById(patientId);
		note.setPatientId(patientId);
		note.setPatient(patient.getLastName() + " " + patient.getFirstName());
        return "note/add";
    }

    @PostMapping("/validate")
    public String validate(Note note, BindingResult result, Model model) {
    	if (!result.hasErrors()) {
    		noteService.save(note);
            return "redirect:/patient/info/" + note.getPatientId();
        }
        return "note/add";
    }
}
