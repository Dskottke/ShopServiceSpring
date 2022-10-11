package com.example.shopservicespring.Controller;

import com.example.shopservicespring.Model.Order;
import com.example.shopservicespring.Model.Product;
import com.example.shopservicespring.Shop.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shopservice")

public class ShopController {

    private ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;}


    @GetMapping("product")

        public List<Product> getproducts(){
            return shopService.getProducts();
        }
    @GetMapping("product/{id}")

        public Product getproduct(@PathVariable String id){
        return shopService.getProduct(id);
    }
    @GetMapping("order")

    public List<Order> getOrder(){
        return shopService.getOrder();
    }
    @GetMapping("order/{id}")

    public Order getOrder(@PathVariable String id){
        return shopService.getOrderById(id);
    }


}




