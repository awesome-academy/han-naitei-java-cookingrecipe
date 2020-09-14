package com.cookingrecipe.service.recipe;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.cookingrecipe.dao.interfaces.IRecipeDAO;
import com.cookingrecipe.entity.Category;
import com.cookingrecipe.entity.Recipe;
import com.cookingrecipe.entity.Step;
import com.cookingrecipe.entity.User;
import com.cookingrecipe.model.recipe.RecipeRequest;
import com.cookingrecipe.model.recipe.RecipeResponse;
import com.cookingrecipe.service.category.ICategoryService;
import com.cookingrecipe.service.step.IStepService;
import com.cookingrecipe.service.user.UserService;

@Service
@Transactional(readOnly = true)
public class RecipeServiceImp implements IRecipeService {

	private static Logger log = LoggerFactory.getLogger(RecipeServiceImp.class);

	@Autowired
	private IRecipeDAO recipeDAO;

	@Autowired
	private ICategoryService categoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private IStepService stepService;

	@Override
	public RecipeResponse getById(Integer id) throws Exception {
		try {
			Recipe recipe = recipeDAO.getRecipeDetails(id);
			if (recipe == null)
				throw new Exception("Recipe not found.");
			RecipeResponse recipeResponse = new RecipeResponse();
			BeanUtils.copyProperties(recipe, recipeResponse);
			return recipeResponse;
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(readOnly = false)
	@Override
	public Recipe createRecipe(Recipe r, RecipeRequest p, ArrayList<String> cats, ArrayList<String> steps) throws Exception {
		log.info("Processing in RecipeServiceImp...");
		try {
			log.info("Saving new recipe...");
			recipeDAO.createRecipe(convertToRecipe(r, p, cats, steps));
			log.info("Saved recipe successful!");
			for (Step step : r.getSteps()) {
				stepService.save(step);
			}

		} catch (Exception e) {
			log.error("Save recipe failed!", e);
			throw e;
		}
		return r;
	}

	private Recipe convertToRecipe(Recipe r, RecipeRequest recipe, ArrayList<String> cats, ArrayList<String> steps)
			throws Exception {
		log.info("Processing in RecipeServiceImp...");
		User u = userService.getById(2);
		if (u == null) {
			log.error("Error: User is not exist!");
		}

		r.setName(recipe.getName());
		r.setTime(recipe.getTime());
		r.setIngredient(recipe.getIngredient());
		r.setUser(u);
		for (String step : steps) {
			Step s = new Step(step, r);
			r.getSteps().add(s);
		}
		if (cats != null) {
			for (String cat : cats) {
				Category c = categoryService.getById(Integer.valueOf(cat));
				r.getCategories().add(c);
				categoryService.update(c, r);
			}
		}
		return r;
	}

	@Override
	public Recipe getRecipeById(Integer id) {
		Recipe result = new Recipe();
		try {
			result = recipeDAO.getById(id);

		} catch (Exception e) {
			log.error(null, e);
		}
		return result;
	}

	@Override
	public List<RecipeResponse> findAll() throws Exception {
		List<Recipe> recipes = new ArrayList<>();
		List<RecipeResponse> responses = new ArrayList<>();
		try {
			recipes = recipeDAO.findAll();
			for(Recipe recipe:recipes) {
				RecipeResponse recipeResponse = new RecipeResponse();
				BeanUtils.copyProperties(recipe, recipeResponse);
				responses.add(recipeResponse);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return responses;
	}

	@Override
	public List<RecipeResponse> searchByName(String name) {
		List<RecipeResponse> responses = new ArrayList<>();
		try {
			List<Recipe> recipes = recipeDAO.searchByName(name);
			for(Recipe recipe:recipes) {
				RecipeResponse recipeResponse = new RecipeResponse();
				BeanUtils.copyProperties(recipe, recipeResponse);
				responses.add(recipeResponse);
			}
			return responses;
			
		} catch (Exception e) {
			log.error("Error at function searchByName in RecipeServiceImp....", e);
			return null;
		}
	}

	@Override
	public List<RecipeResponse> searchByCategory(Integer idCat) {
		List<RecipeResponse> responses = new ArrayList<>();
		try {
			List<Recipe> recipes = recipeDAO.searchByCategory(idCat);
			for(Recipe recipe:recipes) {
				RecipeResponse recipeResponse = new RecipeResponse();
				BeanUtils.copyProperties(recipe, recipeResponse);
				responses.add(recipeResponse);
			}
			return responses;
			
		} catch (Exception e) {
			log.error("Error at function searchByCategory in RecipeServiceImp....", e);
			return null;
		}
	}
}
