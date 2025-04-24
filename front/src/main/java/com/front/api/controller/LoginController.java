package com.front.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@GetMapping("login")
    public ModelAndView login(@RequestParam(required = false) String error) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        
        if (error != null) {
            mav.addObject("errorMessage", "Username or Password is invalid");
        }
        
        return mav;
    }
}
