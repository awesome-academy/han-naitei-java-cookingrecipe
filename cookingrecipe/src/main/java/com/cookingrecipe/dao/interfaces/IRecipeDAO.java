package com.cookingrecipe.dao.interfaces;

import com.cookingrecipe.comparator.RecipeComparator;
import com.cookingrecipe.entity.Recipe;

public interface IRecipeDAO extends IGenericDAO<Recipe, Integer>{
	Recipe getRecipeDetails(Integer id);
}