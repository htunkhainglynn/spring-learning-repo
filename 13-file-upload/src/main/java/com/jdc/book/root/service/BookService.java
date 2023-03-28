package com.jdc.book.root.service;

import java.util.List;
import java.util.Optional;

import com.jdc.book.root.dto.Book;


public interface BookService {

	List<Book> search(Integer category, String keyword);

	Optional<Book> findById(int id);

	int save(Book book);

	List<Book> getAllBook();
}
