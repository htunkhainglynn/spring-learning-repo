package com.jdc.book.root.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.jdc.book.root.dto.Book;
import com.jdc.book.root.dto.Category;

@Component
public class BookRowMapper implements RowMapper<Book>{

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setCategory(new Category());
        book.getCategory().setId(rs.getInt("categoryId"));
        book.getCategory().setName(rs.getString("categoryName"));
        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setPrice(rs.getInt("price"));
        book.setRemark(rs.getString("remark"));
        return book;
    }

}

