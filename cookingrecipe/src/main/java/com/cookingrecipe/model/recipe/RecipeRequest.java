package com.cookingrecipe.model.recipe;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.NumberFormat;

import com.cookingrecipe.entity.Step;
import com.cookingrecipe.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeRequest {

	private Integer id;

	@NotBlank(message="{error.notBlank.recipe.name}")
	private String name;

	@NumberFormat
	private Integer time;

	private String image;

//	@NotNull(message = "{error.notNull.recipe.steps}")
	private List<Step> steps;
	
	@NotBlank(message="{error.notBlank.recipe.ingredient}")
	private String ingredient;

//	@NotNull
	private User user;


}
