package com.cookingrecipe.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cookingrecipe.entity.Recipe;
import com.cookingrecipe.model.category.PureCategory;
import com.cookingrecipe.model.recipe.RecipeRequest;
import com.cookingrecipe.model.comment.CommentRequest;
import com.cookingrecipe.model.recipe.RecipeResponse;
import com.cookingrecipe.service.category.ICategoryService;
import com.cookingrecipe.service.recipe.IRecipeService;

@Controller
@RequestMapping(path = "/recipes")
@Validated
public class RecipeController {
	
	@Autowired
	@Qualifier(value="recipeServiceImp")
	private IRecipeService recipeService;
	
	@Autowired
	@Qualifier(value = "categoryServiceImp")
	private ICategoryService categoryService;

	@Autowired
	private MessageSource messageSource;
	
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
	public String create(@ModelAttribute("recipe") @Validated RecipeRequest p,
						BindingResult bindingResult,
						@RequestParam(value="cats", required=false) ArrayList<String> cats,
						@RequestParam(value="addstep", required = false) ArrayList<String> steps,
						Model model,
						Locale locale) throws Throwable{
		if (bindingResult.hasErrors()) {
			System.out.println(bindingResult);
			return "recipeForm";
		}
		if (steps == null || steps.size() == 0) {
			String[] args = {};
			model.addAttribute("stepsError", messageSource.getMessage("error.notNull.recipe.steps", args , locale));
			return "recipeForm";
		}
		Recipe r = new Recipe();
		if(recipeService.createRecipe(r,p, cats, steps)!=null) {
			
			model.addAttribute("message", "Create recipe successfull!");
		}else {
			model.addAttribute("message", "Create recipe failed!");
		}
		return "redirect:/recipes/" + r.getId();
	}
//	
//	@ExceptionHandler(Throwable.class)
//	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
//	public String stepErrorHandler(Throwable throwable, Model model) {
//		System.out.println(throwable.getMessage());
//		model.addAttribute("stepError", throwable.getMessage());
//		return "recipeForm";
//	}
}
