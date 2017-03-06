package com.expertsoft.dao;

import com.expertsoft.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public class JdbcPhoneDao implements PhoneDao {
    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcPhoneDao(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Phone get(Long id) {
        return jdbcOperations.queryForObject(
            "SELECT * from Phones where id = ?;",
            (rs, rowNum) -> {
                Phone phone = new Phone(
                rs.getString("model"),
                rs.getString("color"),
                rs.getInt("displaySize"),
                rs.getBigDecimal("price"));
                phone.setId(rs.getLong("id"));
                return phone;
            },
            id);
    }

    @Override
    public void save(Phone phone) {
        jdbcOperations.update(
            "INSERT INTO Phones (model, color, displaySize, price) VALUES (?, ?, ?, ?);",
            phone.getModel(),
            phone.getColor(),
            phone.getDisplaySize(),
            phone.getPrice()
        );
    }

    @Override
    public List<Phone> findAll() {
        return jdbcOperations.query("SELECT * FROM Phones;",
                new BeanPropertyRowMapper(Phone.class));
    }

    @Override
    public void delete(Long id) {
        jdbcOperations.update(
            "DELETE FROM Phones WHERE id = ?",
            id
        );
    }
}
