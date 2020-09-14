package com.cookingrecipe.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cookingrecipe.dao.user.UserDao;
import com.cookingrecipe.entity.User;
import com.cookingrecipe.model.user.UserRequest;

@Service
public class UserService {
	@Autowired
	UserDao userDao;
	
	@Transactional (readOnly = false)
	public void createAccount(UserRequest p) throws Exception{
		try {
			userDao.createAccount(p.convertUser());			
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}

	}
	
	public User getById(Integer id) throws Exception{
		return userDao.getById(id);
	}
}
