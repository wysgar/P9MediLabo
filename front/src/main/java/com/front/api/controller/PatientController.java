package com.front.api.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.front.api.model.Patient;
import com.front.api.service.NoteService;
import com.front.api.service.PatientService;
import com.front.api.service.RisqueService;

import jakarta.validation.Valid;

@Controller
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	@Autowired
	private NoteService noteService;
	@Autowired
	private RisqueService risqueService;
	
	@GetMapping("/patient/list")
    public String home(Model model, Principal principal)
    {
    	model.addAttribute("patients", patientService.getPatients());
    	model.addAttribute("remoteUser", principal.getName());
        return "patient/list";
    }
	
	@GetMapping("/patient/info/{id}")
    public String infoPatient(@PathVariable Integer id, Model model)
    {
    	model.addAttribute("patient", patientService.findById(id));
    	model.addAttribute("notes", noteService.getNoteByPatient(id));
    	model.addAttribute("risque", risqueService.getRisque(id));
        return "patient/info";
    }
	
	@GetMapping("/patient/add")
    public String addPatientForm(Patient patient) {
        return "patient/add";
    }

    @PostMapping("/patient/validate")
    public String validate(@Valid Patient patient, BindingResult result, Model model) {
    	if (!result.hasErrors()) {
    		patientService.save(patient);
            return "redirect:/patient/list";
        }
        return "patient/add";
    }
    
    @GetMapping("/patient/update/{id}")
    public String showUpdateForm(@PathVariable Integer id, Model model) {
    	Patient patient = patientService.findById(id);
        model.addAttribute("patient", patient);
        return "patient/update";
    }

    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable Integer id, Patient patient, BindingResult result, Model model) {
    	if (result.hasErrors()) {
            return "patient/update";
        }
    	patient.setId(id);
    	patientService.update(patient);
        return "redirect:/patient/info/" + id;
    }
    
    @GetMapping("/patient/delete/{id}")
    public String deletePatient(@PathVariable Integer id, Model model) {
    	patientService.delete(id);
        return "redirect:/patient/list";
    }
}
