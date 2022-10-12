package com.example.shopservicespring.Shop;

import org.springframework.stereotype.Service;


import com.example.shopservicespring.Model.Order;
import com.example.shopservicespring.Model.Product;
import com.example.shopservicespring.Repos.OrderRepo;
import com.example.shopservicespring.Repos.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
public class ShopService {
    //DECLARATION
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;

    //CONSTRUCTOR
    public ShopService(OrderRepo orderRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    //METHODS
    public Product getProduct(String id) {
        Optional<Product> product = productRepo.getProduct(id);

        if (product.isPresent()) {
            return product.get();
        }
        else throw new NoProductFoundException();
    }

    public List<Product> listProduct() {
        return productRepo.listProducts();
    }

    public Order orderProducts(List<String> productKeys) {

        List<Product> productsToOrder = new ArrayList<>();

        for (String key : productKeys) {

            Product product = getProduct(key);
            productsToOrder.add(product);

        }

        String id = UUID.randomUUID().toString();

        return orderRepo.addOrder(new Order(id, productsToOrder));
    }

    public List<Product> getProducts(){

        return productRepo.getProducts();

    }
    public List<Order> getOrder(){
        return orderRepo.listOrder();
    }
    public Order getOrderById(String id){
        return orderRepo.getOrder(id);
    }
    public void deleteOrder(String id) {
        Order order = getOrderById(id);
        orderRepo.deleteOrder(order);
    }

    public List<Product> searchProducts(String name){
        List<Product> products = new ArrayList<>();
        for (Product product :productRepo.listProducts()) {
            if (product.getName().contains(name)) {
                products.add(product);
            }
        }
        return products;
    }

    public List<Order> listOrder(){
        return orderRepo.listOrder();
    }



}
