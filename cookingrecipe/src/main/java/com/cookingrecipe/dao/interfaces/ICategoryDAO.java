package com.cookingrecipe.dao.interfaces;

import com.cookingrecipe.entity.Category;
import com.cookingrecipe.entity.Recipe;

public interface ICategoryDAO extends IGenericDAO<Category, Integer> {
	void update(Category cat, Recipe recipe);
	Category getById(Integer id);
}
