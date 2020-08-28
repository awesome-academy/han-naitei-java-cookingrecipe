package com.cookingrecipe.model.user;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserRequest {
	private Integer id;

	@NotBlank
	@Email
	@Column(nullable = false)
	private String mail;

	@NotBlank
	@Column(nullable = false)
	private String password;

	private String image;

	@NotNull
	@Column(nullable = false)
	private Integer role = 1;

}
