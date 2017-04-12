package com.expertsoft.dao;

import com.expertsoft.model.Order;
import com.expertsoft.model.OrderItem;
import com.expertsoft.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class JdbcOrderDao implements OrderDao {
    private NamedParameterJdbcOperations jdbcOperations;
    private PhoneDao phoneDao;

    private Order fillOrder(long id, ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(id);
        order.setOrderItems(new ArrayList<>());
        order.setSubtotal(rs.getBigDecimal("subTotal"));
        order.setDeliveryPrice(rs.getBigDecimal("deliveryPrice"));
        order.setFirstName(rs.getString("firstName"));
        order.setLastName(rs.getString("lastName"));
        order.setDeliveryAddress(rs.getString("deliveryAddress"));
        order.setContactPhoneNo(rs.getString("contactPhoneNo"));
        return order;
    }

    private Phone fillPhone(ResultSet rs) throws SQLException {
        return phoneDao.get(rs.getLong("pId"));
    }

    private OrderItem fillOrderItem(Phone phone, Order order, ResultSet rs) throws SQLException {
        OrderItem item = new OrderItem();
        item.setId(rs.getLong("iId"));
        item.setPhone(phone);
        item.setOrder(order);
        item.setQuantity(rs.getLong("quantity"));
        return item;
    }

    private void addOrderItem(Order order, ResultSet rs) throws SQLException {
        Phone phone = fillPhone(rs);
        OrderItem item = fillOrderItem(phone, order, rs);
        order.getOrderItems().add(item);
    }

    @Autowired
    public JdbcOrderDao(NamedParameterJdbcOperations jdbcOperations, PhoneDao phoneDao) {
        this.jdbcOperations = jdbcOperations;
        this.phoneDao = phoneDao;
    }

    @Override
    public Order get(long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        String query = "select " +
                "O.id, O.subtotal, O.deliveryPrice, O.firstName, O.lastName, O.deliveryAddress, O.contactPhoneNo, " +
                "I.id as iId, I.quantity, I.pId " +
                "from Orders as O " +
                "inner join OrderItems as I on I.oId=O.id " +
                "where O.id = :id";
        return jdbcOperations.query(query, parameterSource, rs -> {
            Order order = null;
            while (rs.next()) {
                if(order == null){
                    order = fillOrder(id, rs);
                }
                addOrderItem(order, rs);
            }
            return order;
        });
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public void save(Order order) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        parameterSource.addValue("subtotal", order.getSubtotal());
        parameterSource.addValue("deliveryPrice", order.getDeliveryPrice());
        parameterSource.addValue("firstName", order.getFirstName());
        parameterSource.addValue("lastName", order.getLastName());
        parameterSource.addValue("deliveryAddress", order.getDeliveryAddress());
        parameterSource.addValue("contactPhoneNo", order.getContactPhoneNo());
        jdbcOperations.update("INSERT INTO Orders (subtotal, deliveryPrice, firstName, lastName, deliveryAddress, contactPhoneNo) " +
                "VALUES (:subtotal,  :deliveryPrice, :firstName, :lastName, :deliveryAddress, :contactPhoneNo);", parameterSource, keyHolder);
        long id = keyHolder.getKey().longValue();
        order.setId(id);
        MapSqlParameterSource[] batchArgs = new MapSqlParameterSource[order.getOrderItems().size()];
        for (int i = 0, j = order.getOrderItems().size(); i < j; i++) {
            batchArgs[i] = new MapSqlParameterSource();
            OrderItem item = order.getOrderItems().get(i);
            batchArgs[i].addValue("pId", item.getPhone().getId());
            batchArgs[i].addValue("oId", id);
            batchArgs[i].addValue("quantity", item.getQuantity());
        }
        jdbcOperations.batchUpdate("INSERT INTO OrderItems (pId, oId, quantity) VALUES(:pId, :oId, :quantity);", batchArgs);
    }

    @Override
    @Transactional
    public void delete(long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        jdbcOperations.update(
                "DELETE FROM OrderItems WHERE oId = :id",
                parameterSource
        );
        jdbcOperations.update(
                "DELETE FROM Orders WHERE id = :id",
                parameterSource
        );
    }

    @Override
    public List<Order> findAll() {
        String query = "select " +
                "O.id, O.subtotal, O.deliveryPrice, O.firstName, O.lastName, O.deliveryAddress, O.contactPhoneNo, " +
                "I.id as iId, I.quantity, I.pId " +
                "from Orders as O " +
                "inner join OrderItems as I on I.oId=O.id";
        return jdbcOperations.query(query, rs -> {
            List<Order> list = new ArrayList<>();
            int currentIndex = -1;
            Order order;
            while (rs.next()) {
                long id = rs.getLong("id");
                if (currentIndex == -1 || list.get(currentIndex).getId() != id) {
                    order = fillOrder(id, rs);
                    list.add(order);
                    currentIndex++;
                } else {
                    order = list.get(currentIndex);
                }
                addOrderItem(order, rs);
            }
            return list;
        });
    }
}
