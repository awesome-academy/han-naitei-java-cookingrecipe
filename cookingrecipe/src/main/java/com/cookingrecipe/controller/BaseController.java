package com.cookingrecipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cookingrecipe.model.recipe.RecipeResponse;
import com.cookingrecipe.service.recipe.IRecipeService;

@Controller
public class BaseController {
	
	@Autowired
	private IRecipeService recipeService;
	
	@GetMapping(value = "/")
	public String index(Model model) throws Exception {
		List<RecipeResponse> recipes = recipeService.findAll();
		model.addAttribute("recipes", recipes);
		return "home";
	}

}
