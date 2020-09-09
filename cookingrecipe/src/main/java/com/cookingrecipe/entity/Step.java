package com.cookingrecipe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Steps")
@Getter
@Setter
@NoArgsConstructor
public class Step {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@NotNull
	@Column(nullable = false, columnDefinition="TEXT")
	private String content;

	private String image;

	@NotNull
	@ManyToOne
	private Recipe recipe;

	public Step(String content, Recipe recipe) {
		super();
		this.content = content;
		this.recipe = recipe;
	}

	
}
