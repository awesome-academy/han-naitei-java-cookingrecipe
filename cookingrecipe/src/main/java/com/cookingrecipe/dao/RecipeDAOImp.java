package com.cookingrecipe.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cookingrecipe.dao.interfaces.IRecipeDAO;
import com.cookingrecipe.entity.Recipe;

public class RecipeDAOImp extends GenericDAOImp<Recipe, Integer> implements IRecipeDAO {

	private static Logger log = LoggerFactory.getLogger(RecipeDAOImp.class);
	public RecipeDAOImp() {
		super(Recipe.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Recipe getRecipeDetails(Integer id) {
		Session ss = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="SELECT r FROM Recipe r inner join fetch r.steps WHERE r.id=:id";
		Query<Recipe> query = ss.createQuery(hql, Recipe.class).setParameter("id", id);
		Recipe recipe = query.uniqueResult();
		
		hql = "SELECT r FROM Recipe r left join fetch r.comments WHERE r.id=:id";
		query = ss.createQuery(hql, Recipe.class)
				.setParameter("id", recipe.getId());
		recipe = query.uniqueResult();
		hql = "SELECT r FROM Recipe r left join fetch r.categories WHERE r.id=:id";
		query = ss.createQuery(hql, Recipe.class)
				.setParameter("id", recipe.getId());
		recipe = query.uniqueResult();
		return recipe;
	}

	@Override
	public void createRecipe(Recipe recipe) {
		log.info("Processing in RecipeDAOImp...");
		getHibernateTemplate().save(recipe);
	}

	@Override
	public List<Recipe> searchByName(String name) {
		Session ss = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="select distinct r from Recipe r where lower(r.name) LIKE lower(:name)";
		Query<Recipe> query = ss.createQuery(hql, Recipe.class).setParameter("name", "%"+name+"%");
		List<Recipe> recipes = query.getResultList();
		
		return recipes;
	}

	@Override
	public List<Recipe> searchByCategory(Integer id) {
		Session ss = getHibernateTemplate().getSessionFactory().getCurrentSession();
		String hql ="SELECT distinct r FROM Recipe r inner join r.categories c  WHERE c.id = :idCategory";
		Query<Recipe> query = ss.createQuery(hql, Recipe.class).setParameter("idCategory", id);
		List<Recipe> recipes = query.getResultList();
		
		return recipes;
	}
}
