package com.cookingrecipe.model.recipe;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;
import com.cookingrecipe.entity.Category;
import com.cookingrecipe.entity.Comment;
import com.cookingrecipe.entity.Step;
import com.cookingrecipe.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeRequest {

	private Integer id;

	@NotBlank
	private String name;

	@NumberFormat
	private Integer time;

	private String image;

	@NotNull
	private List<Step> steps;
	
	@NotBlank
	private String ingredient;

	@NotNull
	private User user;

	private List<Category> categories;

}
