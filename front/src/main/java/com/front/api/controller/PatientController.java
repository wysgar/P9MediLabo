package com.front.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PatientController {

	
	
	@RequestMapping("/patient/list")
    public String home(Model model)
    {
    	model.addAttribute("patients", );
        return "patient/list";
    }
	
	@GetMapping("/patient/add")
    public String addPatientForm(Patient patient) {
        return "patient/add";
    }

    @PostMapping("/patient/validate")
    public String validate(Patient patient, BindingResult result, Model model) {
    	if (!result.hasErrors()) {
            return "redirect:/patient/list";
        }
        return "patient/add";
    }
    
    @GetMapping("/patient/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    	Patient patient;
        model.addAttribute("patient", patient);
        return "patient/update";
    }

    @PostMapping("/patient/update/{id}")
    public String updatePatient(@PathVariable("id") Integer id, Patient patient, BindingResult result, Model model) {
    	if (result.hasErrors()) {
            return "patient/update";
        }
    	
        return "redirect:/patient/list";
    }
}
