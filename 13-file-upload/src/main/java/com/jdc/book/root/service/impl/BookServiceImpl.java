package com.jdc.book.root.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jdc.book.root.dto.Book;
import com.jdc.book.root.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private SimpleJdbcInsert bookInsert;
	
	@Autowired
	private SimpleJdbcInsert categoryInsert;
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	@Autowired
	private RowMapper<Book> bookRowMapper;
	
	private static final String SELECT = """
			SELECT b.id, b.title, b.author, b.price, b.remark, 
			c.id categoryID, c.name categoryName FROM books b
			join category c on c.id = b.category_id WHERE 1 = 1
			""";
	
	private static final String UPDATE = """
            UPDATE books set title = :title, author = :author,
            price = :price, remark = :remark, category_id = :category_id
            where id = :id
            """;
	
	@PostConstruct
	private void init() {
		bookInsert.setTableName("books");
	}

	@Override
	public List<Book> search(Integer categoryId, String keyword) {
		StringBuffer sb = new StringBuffer(SELECT);
		Map<String, Object> params = new HashMap<String, Object>();
		
		if (null != categoryId) {
			sb.append(" and c.id = :categoryId");
			params.put("categoryId", categoryId);
		}
		
		if (StringUtils.hasLength(keyword)) {
			sb.append("""
					 and (
					lower(b.title) like :keyword
					or lower(b.author) like :keyword
					or lower(c.name) like :keyword
					)""");
			params.put("keyword", keyword.toLowerCase().concat("%"));
		}
		
		return template.query(sb.toString(), params, bookRowMapper);
	}


	@Override
	public int save(Book book) {
		if(book.getId() == 0) {
			String sql = "INSERT INTO books(title, author, category_id, price, remark) VALUES (?, ?, ?, ?, ?)";
		    KeyHolder keyHolder = new GeneratedKeyHolder();
		    int rowsAffected = bookInsert.getJdbcTemplate().update(new PreparedStatementCreator() {
		        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
		            PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
		            ps.setString(1, book.getTitle());
		            ps.setString(2, book.getAuthor());
		            ps.setInt(3, book.getCategory().getId());
		            ps.setInt(4, book.getPrice());
		            ps.setString(5, book.getRemark());
		            return ps;
		        }
		    }, keyHolder);
		    if (rowsAffected == 1) {
		        int id = keyHolder.getKey().intValue();
		        book.setId(id);
		        return id;
		    } else {
		        throw new RuntimeException("Failed to insert book into database");
		    }
		}
		template.update(UPDATE, book.getUpdateParams());
		return book.getId();
	}

	@Override
	public Optional<Book> findById(int id) {
	    return template.queryForStream(
	            SELECT.concat(" and b.id = :id"),
	            Map.of("id", id),
	            bookRowMapper).findFirst();
	}

	@Override
	public List<Book> getAllBook() {
		String sql = SELECT + " ORDER BY b.id DESC";
		return template.query(sql, bookRowMapper);
	}


}
