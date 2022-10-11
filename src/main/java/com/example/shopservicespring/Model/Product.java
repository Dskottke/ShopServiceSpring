package com.example.shopservicespring.Model;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Data
public class Product {
    //DECLARATION
    private String id;
    private String name;

    //CONSTRUCTOR
    public Product() {
    }

    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }

    //METHODS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }



}
