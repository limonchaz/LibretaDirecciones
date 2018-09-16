package com.practice.eon.crud.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.practice.eon.crud.model.Coworker;
import com.practice.eon.crud.model.Relative;
import com.practice.eon.crud.repository.CoworkerRepository;
import com.practice.eon.crud.repository.RelativeRepository;

@Controller
public class MenuCtrl {
 
	@Autowired
	private RelativeRepository relativeRepository;
	
	@Autowired
	private CoworkerRepository coworkerRepository;
	
    @GetMapping("/")
    public String homePage(Model model) {
    	model.addAttribute("relativeList",relativeRepository.findAll());
    	model.addAttribute("coworkerList",coworkerRepository.findAll());
        return "home";
    }
    
    @GetMapping("/relativeForm")
    public String relative(Model model) {
    	model.addAttribute("relative", new Relative());
        return "addRelative";
    }
    
    @GetMapping("/coworkerForm")
    public String coworker(Model model) {
    	model.addAttribute("coworkerInfo", new Coworker());
    	return "addCoworker";
    }
}
