package com.cookingrecipe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

import com.cookingrecipe.model.user.UserRequest;
import com.cookingrecipe.service.user.UserService;

import com.sun.istack.logging.Logger;

@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@RequestMapping(value =  "/signup")
	  public String index(Model model) {
		model.addAttribute("newuser", new UserRequest());
	    return "signup";
	  }
	
	@PostMapping(value = "/users")
	public String create(@ModelAttribute("newuser") UserRequest p, Model model) {
	 this.userService.createAccount(p);
	 return "redirect:/signup";
	}
	
	@RequestMapping(value =  "/users/{userId}")
	  public String updateProfile(Model model) {
	    return "updateProfile";
	  }

}
