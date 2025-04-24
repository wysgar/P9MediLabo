package com.risque.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.risque.api.model.Risque;
import com.risque.api.service.RisqueService;

@RestController
@RequestMapping("/risque")
public class RisqueController {
	
	@Autowired
	private RisqueService risqueService;

	@GetMapping("/{id}")
	public String getRisque(@PathVariable Integer id) {
		Risque evaluateRisk = risqueService.evaluateRisk(id);
		
		switch(evaluateRisk) {
	      case None:
	    	  return "None";
	      case Borderline:
	    	  return "Borderline";
	      case InDanger:
	    	  return "In Danger";
	      case EarlyOnset:
	    	  return "Early onset";
	    }
		
		return "None";
	}
}
