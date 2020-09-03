package com.cookingrecipe.service.recipe;

import java.util.ArrayList;

import com.cookingrecipe.entity.Recipe;
import com.cookingrecipe.model.recipe.RecipeRequest;
import com.cookingrecipe.model.recipe.RecipeResponse;

public interface IRecipeService {
	RecipeResponse getById(Integer id);
	Recipe createRecipe(Recipe r, RecipeRequest p, ArrayList<String> cats, ArrayList<String> steps);
	Recipe getRecipeById(Integer id);
}
