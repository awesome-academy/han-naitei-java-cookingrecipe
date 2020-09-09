package com.cookingrecipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cookingrecipe.model.recipe.RecipeResponse;
import com.cookingrecipe.service.recipe.IRecipeService;

import com.cookingrecipe.model.category.PureCategory;
import com.cookingrecipe.service.category.ICategoryService;


@Controller
public class BaseController {
	
	@Autowired
	private IRecipeService recipeService;
	
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping(value = "/")
	public String index(Model model) {
		List<RecipeResponse> recipes = recipeService.findAll();
		List<PureCategory> categories = categoryService.findAll();
		model.addAttribute("recipes", recipes);
		model.addAttribute("categories", categories);

		return "home";
	}

}
