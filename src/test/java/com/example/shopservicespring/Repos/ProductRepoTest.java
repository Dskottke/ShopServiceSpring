package com.example.shopservicespring.Repos;


import com.example.shopservicespring.Model.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductRepoTest {

    @Test
    @DisplayName("getProduct should return matching product by ID ")
    void getProductById(){
        //GIVEN
        List<Product> products = new ArrayList<>(List.of(
                new Product("1","apfel"),
                new Product("2","banane")));

        ProductRepo productRepo = new ProductRepo(products);

        //WHEN
        Optional <Product> actual = productRepo.getProduct("1");
        Product expected = products.get(0);
        //THEN
        assertEquals(expected,actual.get());
    }
    @Test
    @DisplayName("return Optional.empty by finding no match by id")
   void getProductByIdFailToFindId()
    {
        //GIVEN
        List<Product> products = new ArrayList<>(List.of(
                new Product("1","apfel"),
                new Product("2","banane")));

        ProductRepo productRepo = new ProductRepo(products);
        //WHEN

        Optional<Product> actual = productRepo.getProduct("3");
        Optional expected = Optional.empty();

        //THEN
        assertEquals(expected,actual);

    }


}