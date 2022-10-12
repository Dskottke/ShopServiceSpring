package com.example.shopservicespring.Controller;

import com.example.shopservicespring.Model.Product;
import com.example.shopservicespring.Shop.ShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    private final ShopService shopService;

    @Autowired
    public ProductController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping
    public List<Product> getProducts(@RequestParam Optional<String> name) {
        if (name.isPresent()) {
            return shopService.searchProducts(name.get());
        }
        return shopService.getProducts();
    }

    @GetMapping("{id}")
    public Product getProductBy(@PathVariable String id) {
        return shopService.getProduct(id);
    }
}
