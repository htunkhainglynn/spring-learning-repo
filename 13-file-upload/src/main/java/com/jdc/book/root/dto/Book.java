package com.jdc.book.root.dto;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class Book {

	private int id;
	
	@NotBlank(message = "{book.title.notBlank}")
	private String title;
	@NotBlank(message = "{book.author.notBlank}")
	private String author;
	@NotNull(message = "{book.category.notNull}")
	private Category category;
	@Min(value = 300, message = "{book.price.minimum}")
	private int price;
	private String remark;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Book(int id, String title, String author, Category category, int price, String remark) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.category = category;
		this.price = price;
		this.remark = remark;
	}
	public Book() {};
	
	public Map<String, Object> getInsertParams() {
		Map<String, Object> params = new HashMap<>();
		params.put("title", title);
		params.put("author", author);
		params.put("category_id", category.getId());
		params.put("price", price);
		params.put("remark", remark);
		return params;
	}
	
	public Map<String, Object> getUpdateParams() {
		Map<String, Object> params = new HashMap<>(getInsertParams());
		params.put("id", id);
		return params;
	}
}
