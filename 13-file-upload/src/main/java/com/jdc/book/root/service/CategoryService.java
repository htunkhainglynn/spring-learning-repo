package com.jdc.book.root.service;

import java.util.List;
import java.util.Optional;

import com.jdc.book.root.dto.Category;

public interface CategoryService {

	public List<Category> getAllCategories();

	public Optional<Category> findById(int id);
}
