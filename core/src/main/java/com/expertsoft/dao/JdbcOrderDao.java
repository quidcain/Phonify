package com.expertsoft.dao;

import com.expertsoft.model.Order;
import com.expertsoft.model.OrderItem;
import com.expertsoft.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Repository
public class JdbcOrderDao implements OrderDao {
    private JdbcOperations jdbcOperations;

    @Autowired
    public JdbcOrderDao(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public Order get(long id) {
        String query = "select " +
                "O.id, O.subtotal, O.deliveryPrice, O.firstName, O.lastName, O.deliveryAddress, O.contactPhoneNo, " +
                "I.id as iId, I.quantity, I.pId, " +
                "P.model, P.color, P.displaySize, P.price " +
                "from Orders as O " +
                "inner join OrderItems as I on I.oId=O.id " +
                "inner join Phones as P on I.pId=P.id where O.id = ?";
        return jdbcOperations.query(query, rs -> {
            Order order = null;
            while (rs.next()) {
                if(order == null){
                    BigDecimal subTotal = rs.getBigDecimal("subTotal");
                    BigDecimal deliveryPrice = rs.getBigDecimal("deliveryPrice");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String deliveryAddress = rs.getString("deliveryAddress");
                    String contactPhoneNo = rs.getString("contactPhoneNo");
                    order = new Order(
                            new ArrayList<>(), subTotal, deliveryPrice, firstName,
                            lastName, deliveryAddress, contactPhoneNo
                    );
                    order.setId(id);
                }
                Phone phone = new Phone(
                        rs.getString("model"),
                        rs.getString("color"),
                        rs.getInt("displaySize"),
                        rs.getBigDecimal("price"));
                phone.setId(rs.getInt("pId"));
                OrderItem item = new OrderItem(phone, order, rs.getLong("quantity"));
                item.setId(rs.getInt("iId"));
                order.getOrderItems().add(item);
            }
            return order;
        }, id);
    }

    @Override
    @Transactional
    @SuppressWarnings("unchecked")
    public void save(Order order) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert((JdbcTemplate) jdbcOperations)
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
        insert = new SimpleJdbcInsert((JdbcTemplate) jdbcOperations)
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
        jdbcOperations.update(
                "DELETE FROM OrderItems WHERE oId = ?",
                id
        );
        jdbcOperations.update(
                "delete from Orders where id = ?",
                id
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
            Map<Integer, Order> orderMap = new HashMap<>();
            Order order;
            OrderItem item;
            while (rs.next()) {
                int id = rs.getInt("id");
                order = orderMap.get(id);
                if(order == null){
                    BigDecimal subTotal = rs.getBigDecimal("subTotal");
                    BigDecimal deliveryPrice = rs.getBigDecimal("deliveryPrice");
                    String firstName = rs.getString("firstName");
                    String lastName = rs.getString("lastName");
                    String deliveryAddress = rs.getString("deliveryAddress");
                    String contactPhoneNo = rs.getString("contactPhoneNo");
                    order = new Order(
                            new ArrayList<>(), subTotal, deliveryPrice, firstName,
                            lastName, deliveryAddress, contactPhoneNo
                            );
                    order.setId(id);
                    orderMap.put(id, order);
                }
                Phone phone = new Phone(
                        rs.getString("model"),
                        rs.getString("color"),
                        rs.getInt("displaySize"),
                        rs.getBigDecimal("price"));
                phone.setId(rs.getInt("pId"));
                item = new OrderItem(
                        phone,
                        order,
                        rs.getLong("quantity"));
                item.setId(rs.getInt("iId"));
                order.getOrderItems().add(item);
            }
            return new ArrayList<>(orderMap.values());
        });
    }
}
