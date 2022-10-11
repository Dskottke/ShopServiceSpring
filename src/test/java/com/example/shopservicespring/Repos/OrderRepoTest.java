package com.example.shopservicespring.Repos;


import com.example.shopservicespring.Model.Order;
import com.example.shopservicespring.Model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderRepoTest {

    @Test
    @DisplayName("getOrder should return the Order by id ")
    void getOrderByid() {
        //GIVEN
        List<Product> productList = new ArrayList<>(
                List.of(new Product("1","testProduct1"),
                        new Product("2","testProduct2")));

        Order testOrder = new Order("1",productList);

        Map<String,Order> ordersMap = new HashMap<>(Map.of(testOrder.getId(),testOrder));

        OrderRepo testOrderRepo = new OrderRepo(ordersMap);
        //WHEN
        Order actual = testOrderRepo.getOrder("1");
        Order expected =testOrder;
        //THEN
        assertEquals(expected,actual);
    }

    @Test
    @DisplayName("addOrder should add an order into the orderList")
    void addOrder() {
       //GIVEN
        String testId = "1";
        OrderRepo orderRepo = new OrderRepo();

        List<Product> productList = new ArrayList<>(List.of(
                        new Product(testId,"testProduct1")));

        Order testOrder = new Order("1",productList);

        //WHEN
        orderRepo.addOrder(testOrder);
        Order actual = orderRepo.getOrder(testId);
        Order expected = testOrder;
        //THEN
        assertEquals(expected,actual);
    }
}