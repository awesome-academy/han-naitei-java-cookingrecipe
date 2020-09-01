package com.cookingrecipe.model.user;

import com.cookingrecipe.entity.Role;
import com.cookingrecipe.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
	private Integer id;
	private String fullname;
	private String email;
	private String password;
	private String confirmPassword;
	private Role role;
	
	public UserRequest(String fullname, String email, String password, String confirmPassword) {
		super();
		this.fullname = fullname;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.role = Role.USER;
	}
	
	public UserRequest() {
		this.role=Role.USER;
	}
	
	public User convertUser() {
		User user = new User();
		user.setFullname(this.fullname);
		user.setMail(this.email);
		user.setPassword(this.password);
		return user;
	}
	
	
}
