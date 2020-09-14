package com.cookingrecipe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cookingrecipe.model.recipe.RecipeResponse;
import com.cookingrecipe.service.category.ICategoryService;
import com.cookingrecipe.service.recipe.IRecipeService;

@Controller
public class CategoryController {

	@Autowired
	private IRecipeService recipeService;

	@Autowired
	private ICategoryService categoryService;

	@GetMapping("/categories/{id}")
	public String searchByCategory(Model model, @PathVariable(value = "id") Integer id) {
		List<RecipeResponse> recipes = recipeService.searchByCategory(id);
		model.addAttribute("recipes", recipes);
		model.addAttribute("count", recipes.size());
		model.addAttribute("category", categoryService.getById(id).getType());

		return "resultCategory";
	}
}
