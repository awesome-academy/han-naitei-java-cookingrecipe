package com.cookingrecipe.service.category;

import java.util.List;

import com.cookingrecipe.entity.Category;
import com.cookingrecipe.entity.Recipe;
import com.cookingrecipe.model.category.PureCategory;

public interface ICategoryService {
	List<PureCategory> findAll() throws Exception;
	void update(Category cat, Recipe recipe);
	Category getById(Integer id);
}
