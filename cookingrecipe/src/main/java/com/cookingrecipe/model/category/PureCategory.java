package com.cookingrecipe.model.category;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PureCategory {
	private Integer id;
	
	@NotNull
	private String type;

}
