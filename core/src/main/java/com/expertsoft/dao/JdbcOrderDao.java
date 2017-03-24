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

    private Order fillOrder(long id, ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(id);
        order.setSubtotal(rs.getBigDecimal("subTotal"));
        order.setDeliveryPrice(rs.getBigDecimal("deliveryPrice"));
        order.setFirstName(rs.getString("firstName"));
        order.setLastName(rs.getString("lastName"));
        order.setDeliveryAddress(rs.getString("deliveryAddress"));
        order.setContactPhoneNo(rs.getString("contactPhoneNo"));
        return order;
    }

    private Phone fillPhone(ResultSet rs) throws SQLException {
        Phone phone = new Phone();
        phone.setId(rs.getLong("pId"));
        phone.setModel(rs.getString("model"));
        phone.setColor(rs.getString("color"));
        phone.setDisplaySize(rs.getInt("displaySize"));
        phone.setPrice(rs.getBigDecimal("price"));
        return phone;
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
    public JdbcOrderDao(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Order get(long id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id", id);
        String query = "select " +
                "O.id, O.subtotal, O.deliveryPrice, O.firstName, O.lastName, O.deliveryAddress, O.contactPhoneNo, " +
                "I.id as iId, I.quantity, I.pId, " +
                "P.model, P.color, P.displaySize, P.price " +
                "from Orders as O " +
                "inner join OrderItems as I on I.oId=O.id " +
                "inner join Phones as P on I.pId=P.id where O.id = :id";
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
        JdbcOperations operations = jdbcOperations.getJdbcOperations();
        SimpleJdbcInsert insert = new SimpleJdbcInsert((JdbcTemplate)operations)
                .withTableName("Orders")
                .usingColumns("subtotal", "deliveryPrice", "firstName",
                              "lastName", "deliveryAddress", "contactPhoneNo")
                .usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("subtotal", order.getSubtotal());
        parameters.put("deliveryPrice", order.getDeliveryPrice());
        parameters.put("firstName", order.getFirstName());
        parameters.put("lastName", order.getLastName());
        parameters.put("deliveryAddress", order.getDeliveryAddress());
        parameters.put("contactPhoneNo", order.getContactPhoneNo());
        long id = insert.executeAndReturnKey(parameters).longValue();
        insert = new SimpleJdbcInsert((JdbcTemplate)operations)
                .withTableName("OrderItems")
                .usingColumns("pId", "oId", "quantity");
        List<Map<String, ?>> list = new ArrayList<>(order.getOrderItems().size());
        for (OrderItem item : order.getOrderItems()) {
            parameters = new HashMap<>();
            parameters.put("pId", item.getPhone().getId());
            parameters.put("oId", id);
            parameters.put("quantity", item.getQuantity());
            list.add(parameters);
        }
        insert.executeBatch(list.toArray(new HashMap[list.size()]));
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
                "I.id as iId, I.quantity, I.pId, " +
                "P.model, P.color, P.displaySize, P.price " +
                "from Orders as O " +
                "inner join OrderItems as I on I.oId=O.id " +
                "inner join Phones as P on I.pId=P.id;";
        return jdbcOperations.query(query, rs -> {
            Map<Long, Order> orderMap = new HashMap<>();
            Order order;
            while (rs.next()) {
                long id = rs.getLong("id");
                order = orderMap.get(id);
                if(order == null){
                    order = fillOrder(id, rs);
                    orderMap.put(id, order);
                }
                addOrderItem(order, rs);
            }
            return new ArrayList<>(orderMap.values());
        });
    }
}
