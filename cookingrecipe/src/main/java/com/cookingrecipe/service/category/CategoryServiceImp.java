package com.cookingrecipe.service.category;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cookingrecipe.dao.interfaces.ICategoryDAO;
import com.cookingrecipe.entity.Category;
import com.cookingrecipe.entity.Recipe;
import com.cookingrecipe.model.category.PureCategory;

@Service(value = "categoryServiceImp")
@Transactional(readOnly = true)
public class CategoryServiceImp implements ICategoryService{
	
	private static Logger log = LoggerFactory.getLogger(CategoryServiceImp.class);
	@Autowired
	private ICategoryDAO categoryDAO;

	@Override
	public List<PureCategory> findAll() throws Exception{
		List<Category> categories;
		try {
			categories = categoryDAO.findAll();
			if (categories == null || categories.isEmpty())
				return null;
			List<PureCategory> pureCategories = new ArrayList<PureCategory>();
			for (Category c : categories) {
				PureCategory pureCategory = new PureCategory();
				BeanUtils.copyProperties(c, pureCategory);
				pureCategories.add(pureCategory);
			}
			return pureCategories;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}

	@Transactional(readOnly = false)
	@Override
	public void update(Category cat, Recipe recipe) {
		try {
			log.info("Processing update in CategoryServiceImp...");
			categoryDAO.update(cat, recipe);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public Category getById(Integer id) {
		log.info("Processing getbyId in CategoryServiceImp...");
		Category cat = new Category();
		try {
			cat = categoryDAO.getById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cat;
	}
}
