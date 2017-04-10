package com.expertsoft.dao;

import com.expertsoft.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class JdbcPhoneDao implements PhoneDao {
    private NamedParameterJdbcOperations jdbcOperations;

    @Autowired
    public JdbcPhoneDao(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Phone get(long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        return jdbcOperations.queryForObject(
                "SELECT * from Phones where id = :id;", parameterSource,
                new BeanPropertyRowMapper<>(Phone.class));
    }

    @Override
    public void save(Phone phone) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        parameterSource.addValue("model", phone.getModel());
        parameterSource.addValue("color", phone.getColor());
        parameterSource.addValue("displaySize", phone.getDisplaySize());
        parameterSource.addValue("price", phone.getPrice());
        parameterSource.addValue("length", phone.getPrice());
        parameterSource.addValue("width", phone.getPrice());
        parameterSource.addValue("camera", phone.getPrice());
        jdbcOperations.update("INSERT INTO Phones (model, color, displaySize, price, length, width, camera) VALUES (:model, " +
                ":color, :displaySize, :price, :length, :width, :camera);", parameterSource, keyHolder);
        phone.setId(keyHolder.getKey().longValue());
    }

    @Override
    public void delete(long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcOperations.update(
                "DELETE FROM Phones WHERE id = :id",
                parameterSource
        );
    }

    @Override
    public List<Phone> findAll() {
        return jdbcOperations.query("SELECT * FROM Phones;",
                new BeanPropertyRowMapper<>(Phone.class));
    }
}
