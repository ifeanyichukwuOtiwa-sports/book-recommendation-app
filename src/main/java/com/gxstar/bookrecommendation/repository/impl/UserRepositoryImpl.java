package com.gxstar.bookrecommendation.repository.impl;

import com.gxstar.bookrecommendation.model.AccessRole;
import com.gxstar.bookrecommendation.model.Role;
import com.gxstar.bookrecommendation.model.User;
import com.gxstar.bookrecommendation.repository.api.UserRepository;
import com.gxstar.bookrecommendation.repository.api.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final NamedParameterJdbcOperations jdbcOperator;

    @Override
    public Boolean existsByEmail(final String email) {
        final String sql = """
                SELECT
                    IF(COUNT(*) > 0, TRUE, FALSE)
                FROM user u
                WHERE u.email = :email
                """;
        final SqlParameterSource param = new MapSqlParameterSource("email", email);
        return jdbcOperator.queryForObject(sql, param, Boolean.class);
    }

    @Override
    public Boolean existsByUsername(final String username) {
        final String sql = """
                SELECT
                    IF(COUNT(*) > 0, TRUE, FALSE)
                FROM user u
                WHERE u.username = :username
                """;
        final SqlParameterSource param = new MapSqlParameterSource(Field.USERNAME.fieldName, username);
        return jdbcOperator.queryForObject(sql, param, Boolean.class);
    }

    @Override
    public Optional<UserDto> findByUsername(final String username) {

        final String sql = """
                SELECT first_name, last_name, email, username, name
                FROM user u
                INNER JOIN access_role a ON u.access_role_id = a.id
                WHERE u.username = :username
                """;
        final SqlParameterSource param = new MapSqlParameterSource(Field.USERNAME.fieldName, username);
        return jdbcOperator.query(sql, param, userDtoRowMapper()).stream().findAny();
    }

    @Override
    public Optional<UserDto> findByUserNameAndPassword(final String username, final String password) {
        final String sql = """
                SELECT first_name, last_name, email, username, name
                FROM user u
                INNER JOIN access_role a ON u.access_role_id = a.id
                WHERE u.username = :username AND u.password = :password
                """;
        final SqlParameterSource param = new MapSqlParameterSource(Field.USERNAME.fieldName, username)
                .addValue(Field.PASSWORD.fieldName, password);
        return jdbcOperator.query(sql, param, userDtoRowMapper()).stream().findAny();
    }

    @Override
    public UUID save(final UserDto user) {
        final String sql = """
                    INSERT INTO
                    """;
    }

    @Override
    public UserDto findByUserUuid(final UUID userUuid) {
        return null;
    }

    @Override
    public boolean updateByUsername(final String username, final String firstName, final String lastName, final String email) {
        return false;
    }

    @Override
    public boolean deleteByUsername(final String username) {
        return false;
    }

    @Override
    public boolean deleteById(final UUID id) {
        return false;
    }

    private RowMapper<UserDto> userDtoRowMapper() {
        return (rs, rn) -> new UserDto(
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString(Field.USERNAME.fieldName),
                rs.getString("email"),
                rs.getString("name")
        );
    }

    @RequiredArgsConstructor
    enum Field {
        USERNAME("username"), PASSWORD("password");
        private final String fieldName;
    }
}
