package com.gxstar.bookrecommendation.repository.impl;

import com.gxstar.bookrecommendation.repository.api.BookRepository;
import com.gxstar.bookrecommendation.repository.api.dto.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Slf4j
@RequiredArgsConstructor
public class BookRepositoryImpl implements BookRepository {
    private final NamedParameterJdbcOperations jdbcOperator;
    @Override
    public List<BookDto> findBooksByAuthorId(final Long authorId) {
        final String sql = """
                SELECT b.*, a.name as name
                FROM book b
                INNER JOIN author a ON b.author_id = a.id
                WHERE author_id = :authorId
                
                """;
        final SqlParameterSource param = new MapSqlParameterSource("authorId", authorId);
        return jdbcOperator.query(sql, param, bookDtoRowMapper());
    }

    private static RowMapper<BookDto> bookDtoRowMapper() {
        return (rs, rowNum) -> BookDto.of(
                rs.getLong("id"),
                rs.getString("title"),
                rs.getString("isbn"),
                String.valueOf(rs.getDouble("review_count")),
                rs.getBigDecimal("rating"),
                rs.getString("name")
        );
    }


    @Override
    public Optional<BookDto> findBookByTitle(final String title) {
        final String sql = """
                SELECT b.*, a.name as name
                FROM book b
                INNER JOIN author a ON b.author_id = a.id
                WHERE b.title = :title
                """;
        final SqlParameterSource param = new MapSqlParameterSource("title", title);
        return jdbcOperator.query(sql, param, bookDtoRowMapper()).stream().findAny();
    }

    @Override
    public Optional<BookDto> findBookByIsbn(final String isbn) {
        final String sql = """
                SELECT b.*, a.name as name
                FROM book b
                INNER JOIN author a ON b.author_id = a.id
                WHERE b.isbn = :isbn
                """;
        final SqlParameterSource param = new MapSqlParameterSource("isbn", isbn);
        return jdbcOperator.query(sql, param, bookDtoRowMapper()).stream().findAny();
    }
}
