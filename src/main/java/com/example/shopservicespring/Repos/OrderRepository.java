package com.example.shopservicespring.Repos;

import com.example.shopservicespring.Model.Order;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class OrderRepository {
    private final List<Order> orders = new ArrayList<>();

    public List<Order> listOrders(){
        return orders;
    }

    public Optional<Order> getOrderBy(String id) {
        for (Order order : orders) {
            if (order.getId().equals(id)) {
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }

    public Order addOrder(Order newOrder){
        orders.add(newOrder);
        return newOrder;
    }

    public void deleteOrder(Order order) {
        orders.remove(order);
    }
}