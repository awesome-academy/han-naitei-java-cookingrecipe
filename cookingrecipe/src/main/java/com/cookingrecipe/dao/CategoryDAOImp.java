package com.cookingrecipe.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cookingrecipe.dao.interfaces.ICategoryDAO;
import com.cookingrecipe.entity.Category;
import com.cookingrecipe.entity.Recipe;

public class CategoryDAOImp extends GenericDAOImp<Category, Integer> implements ICategoryDAO{

	private static Logger log = LoggerFactory.getLogger(CategoryDAOImp.class);
	public CategoryDAOImp() {
		super(Category.class);
	}

	@Override
	public List<Category> findAll() throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(getEntityClass());
		criteria.addOrder(Order.asc("type"));
		List<Category> res = (List<Category>) getHibernateTemplate().findByCriteria(criteria);
		return res;
	}

	@Override
	public void update(Category cat, Recipe recipe){
		log.info("Processing update in CategoryDAOImp...");
		cat.getRecipes().add(recipe);
		getHibernateTemplate().saveOrUpdate(cat);
	}
	
}
