package com.example.shopservicespring.Repos;


import com.example.shopservicespring.Model.Product;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepo {

    //DECLARATION
    private List<Product> products;

    //CONSTRUCTOR
    public ProductRepo(List<Product> productList) {

        this.products = productList;
    }


    //METHODS
    public List<Product> listProducts() {
        return products;
    }

    public Optional<Product> getProduct(String id) {
        Optional optional = Optional.ofNullable(products.contains(id));

        if (optional.isPresent()) {
            for (Product product : products) {
                if (product.getId().equals(id)) {
                    return Optional.of(product);
                }
            }
        }
        return Optional.empty();
    }

    public List<Product> getProducts() {
        return products;

    }
}
