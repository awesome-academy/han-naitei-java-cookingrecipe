package com.cookingrecipe.service.recipe;

import java.util.ArrayList;
import java.util.List;

import com.cookingrecipe.entity.Recipe;
import com.cookingrecipe.model.recipe.RecipeRequest;
import com.cookingrecipe.model.recipe.RecipeResponse;

public interface IRecipeService {
	List<RecipeResponse> findAll() throws Exception;
	RecipeResponse getById(Integer id) throws Exception;
	Recipe createRecipe(Recipe r, RecipeRequest p, ArrayList<String> cats, ArrayList<String> steps) throws Exception;
	Recipe getRecipeById(Integer id);
	List<RecipeResponse> searchByName(String name);
	List<RecipeResponse> searchByCategory(Integer idCat);
}
