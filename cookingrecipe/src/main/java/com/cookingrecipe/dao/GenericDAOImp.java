package com.cookingrecipe.dao;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.data.util.Pair;
import org.springframework.orm.hibernate5.HibernateTemplate;
import com.cookingrecipe.dao.interfaces.IGenericDAO;
import com.cookingrecipe.entity.Recipe;

public class GenericDAOImp<E, Id extends Serializable> implements IGenericDAO<E, Id> {
	private Class<E> entityClass;
	
	@Autowired
	private HibernateTemplate hibernateTemplate;

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public GenericDAOImp(Class<E> entityClass) {
		this.entityClass = entityClass;
	}
	
	public Class<E> getEntityClass(){
		return entityClass;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public E getById(Id id) {
		DetachedCriteria criteria = DetachedCriteria.forClass(entityClass);
		criteria.add(Restrictions.eq("id", id));
		return (E) DataAccessUtils.uniqueResult(hibernateTemplate.findByCriteria(criteria));
	}
	
	
	@Override
	public List<E> findAll() throws Exception {
		DetachedCriteria criteria = DetachedCriteria.forClass(getEntityClass());
		criteria.addOrder(Order.desc("updatedAt"));
		List<E> res = (List<E>) getHibernateTemplate().findByCriteria(criteria);
		return res;
	}

	@Override
	public E save(E entity) throws Exception {
		// TODO Auto-generated method stub
		getHibernateTemplate().saveOrUpdate(entity);
		getHibernateTemplate().flush();
		return entity;
	}

}
