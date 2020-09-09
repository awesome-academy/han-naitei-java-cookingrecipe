package com.cookingrecipe.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cookingrecipe.entity.Recipe;
import com.cookingrecipe.model.category.PureCategory;
import com.cookingrecipe.model.recipe.RecipeRequest;
import com.cookingrecipe.model.comment.CommentRequest;
import com.cookingrecipe.model.recipe.RecipeResponse;
import com.cookingrecipe.service.category.ICategoryService;
import com.cookingrecipe.service.recipe.IRecipeService;

@Controller
@RequestMapping(path = "/recipes")
public class RecipeController {
	
	@Autowired
	@Qualifier(value="recipeServiceImp")
	private IRecipeService recipeService;
	
	@Autowired
	@Qualifier(value = "categoryServiceImp")
	private ICategoryService categoryService;
	
	@GetMapping(path = "/{idRecipe}")
	public String show(@PathVariable Integer idRecipe, Model model) {
		RecipeResponse recipeResponse = recipeService.getById(idRecipe);
		model.addAttribute("recipe", recipeResponse);
		model.addAttribute("comment", new CommentRequest());
		return "recipeDetail";
	}
	
	@GetMapping("/create")
	public String create(Model model){
		List<PureCategory> categories = categoryService.findAll();
		model.addAttribute("categories", categories);
		model.addAttribute("recipe", new RecipeRequest());
		return "recipeForm";
	}
	
	@PostMapping("")
	public String create(@ModelAttribute("recipe") RecipeRequest p,
						@RequestParam(value="cats", required=false) ArrayList<String> cats,
						@RequestParam(value="addstep", required=false) ArrayList<String> steps,
						Model model) {
		Recipe r = new Recipe();
		if(recipeService.createRecipe(r,p, cats, steps)!=null) {
			model.addAttribute("message", "Create recipe successfull!");
		}else {
			model.addAttribute("message", "Create recipe failed!");
		}
		return "redirect:/recipes/" + r.getId();
	}
	
	@PostMapping(value="/search")
	public String searchByName(	Model model,
								@RequestParam(value="search", required = false) String name ) {
		return "redirect:/recipes/search/" + name;
	}
	
	@GetMapping("/search/{name}")
	public String resultSearch(	Model model,
								@PathVariable(value="name") String name) {
		List<RecipeResponse> recipes = recipeService.searchByName(name);
		model.addAttribute("recipes", recipes);
		model.addAttribute("count", recipes.size());
		return "resultSearch";
	}
}
