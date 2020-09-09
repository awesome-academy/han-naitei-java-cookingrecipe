package com.cookingrecipe.model.recipe;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;
import com.cookingrecipe.entity.Category;
import com.cookingrecipe.entity.Comment;
import com.cookingrecipe.entity.Recipe;
import com.cookingrecipe.entity.Step;
import com.cookingrecipe.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipeResponse {

	private Integer id;

	private String name;

	private Integer time;

	private String image;

	private Integer likes;

	private List<Step> steps;

	private String ingredient;

	private User user;

	private List<Comment> comments;

	private Set<Category> categories;
	
	private Date createdAt;
	
	public RecipeResponse() {}

	@Override
	public String toString() {
		String step = "";
		String cmts = "";
		for (Step s : steps) {
			step += s.getContent();
		}
		for (Comment c : comments) {
			cmts += c.getContent();
		}
		return "RecipeResponse [id=" + id + ", name=" + name + ", time=" + time + ", image=" + image + ", likes="
				+ likes + ", steps=" + step + ", ingredient=" + ingredient + ", user=" + user + ", comments="
				+ cmts + ", categories=" + categories + "]";
	}
	
}
