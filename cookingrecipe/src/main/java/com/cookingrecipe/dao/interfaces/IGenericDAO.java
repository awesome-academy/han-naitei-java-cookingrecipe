package com.cookingrecipe.dao.interfaces;

import java.io.Serializable;
import java.util.List;
import java.util.Comparator;

public interface IGenericDAO<E, Id extends Serializable> {
	E getById(Id id);
	List<E> findAll() throws Exception;
}