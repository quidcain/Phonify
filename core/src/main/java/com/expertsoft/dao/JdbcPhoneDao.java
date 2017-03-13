package com.expertsoft.dao;

import com.expertsoft.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
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
            (rs, rowNum) -> {
                Phone phone = new Phone(
                rs.getString("model"),
                rs.getString("color"),
                rs.getInt("displaySize"),
                rs.getBigDecimal("price"));
                phone.setId(rs.getLong("id"));
                return phone;
            });
    }

    @Override
    public void save(Phone phone) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("model", phone.getModel());
        parameterSource.addValue("color", phone.getColor());
        parameterSource.addValue("displaySize", phone.getDisplaySize());
        parameterSource.addValue("price", phone.getPrice());
        jdbcOperations.update("INSERT INTO Phones (model, color, displaySize, price) VALUES (:model, " +
                ":color, :displaySize, :price);", parameterSource);
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
                    new BeanPropertyRowMapper(Phone.class));
    }
}
