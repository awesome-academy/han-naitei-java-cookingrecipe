package com.cookingrecipe.dao.interfaces;

import java.util.List;

import com.cookingrecipe.entity.Recipe;

public interface IRecipeDAO extends IGenericDAO<Recipe, Integer>{
	Recipe getRecipeDetails(Integer id);
	void createRecipe(Recipe recipe);
	List<Recipe> searchByName(String name);
	List<Recipe> searchByCategory(Integer idCat);
}
