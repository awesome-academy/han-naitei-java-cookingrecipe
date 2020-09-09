package com.cookingrecipe.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import lombok.Data;

@Entity
@Table(name = "Recipes")
@Data
public class Recipe {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(nullable = false)
	private String name;

	private Integer time;

	private String image;

	private Integer likes = 0;

	@OneToMany(mappedBy = "recipe")
	private List<Step> steps;

	@Column(columnDefinition="TEXT")
	private String ingredient;

	@ManyToOne
	private User user;

	@OneToMany(mappedBy = "recipe")
	private List<Comment> comments;

	@ManyToMany
	private Set<Category> categories;

	@CreationTimestamp
	private Date createdAt;
	
	@UpdateTimestamp
	private Date updatedAt;

	public Recipe() {
		super();
		this.likes=0;
		this.steps= new ArrayList<>();
		this.categories=new HashSet<>();
		this.comments= new ArrayList<>();
	}
}
