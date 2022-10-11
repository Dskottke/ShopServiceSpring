package com.example.shopservicespring.Model;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Data

public class Order {

    //DECLARATION
    private String id;
    private List<Product> products;

    //CONSTRUCTOR
    public Order() {
    }

    //METHODS
    public Order(String id, List<Product> products) {
        this.id = id;
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, products);
    }

    public List<Product> listProducts() {
        return products;
    }

}
