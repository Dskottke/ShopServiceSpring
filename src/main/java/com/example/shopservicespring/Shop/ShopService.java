package com.example.shopservicespring.Shop;


import com.example.shopservicespring.Model.Order;
import com.example.shopservicespring.Model.OrderStatus;
import com.example.shopservicespring.Model.Product;
import com.example.shopservicespring.Repos.OrderRepository;
import com.example.shopservicespring.Repos.ProductRepository;
import com.example.shopservicespring.Response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ShopService {

    private final OrderRepository orderRepo;
    private final ProductRepository productRepository;

    WebClient webClient = WebClient.create("https://my-json-server.typicode.com/Flooooooooooorian/OrderApi/");

    @Autowired
    public ShopService(OrderRepository orderRepo, ProductRepository productRepository) {
        this.orderRepo = orderRepo;
        this.productRepository = productRepository;
    }

    public List<Order> listOrders() {
        return orderRepo.listOrders();
    }

    public Order getOrderBy(String id) {
        Optional<Order> optionalOrder = orderRepo.getOrderBy(id);
        if (optionalOrder.isPresent()) {
            return optionalOrder.get();
        } else {
            throw new IllegalArgumentException("Product with ID " + id + " not found");
        }
    }

    public Order orderProducts(List<String> productIds) {
        List<Product> productsToOrder = new ArrayList<>();
        for (String productId : productIds) {
            Product productToAdd = getProductBy(productId);
            productsToOrder.add(productToAdd);
        }
        return orderRepo.addOrder(new Order(generateId(), productsToOrder));
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public void deleteOrder(String id) {
        Order order = getOrderBy(id);
        orderRepo.deleteOrder(order);
    }

    public List<Product> getProducts() {
        return productRepository.listProducts();
    }

    public List<Product> searchProducts(String name) {
        List<Product> products = new ArrayList<>();
        for (Product product : productRepository.listProducts()) {
            if (product.getName().contains(name)) {
                products.add(product);
            }
        }
        return products;
    }

    public Product getProductBy(String id) {
        Optional<Product> optionalProduct = productRepository.getProduct(id);
        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new IllegalArgumentException("Product with ID " + id + " not found");
        }
    }

    public Order MakeOrderFromApiById(String id) {
 Order response = Objects.requireNonNull(webClient.get().uri("orders/?id=" + id)
                        .retrieve()
                        .toEntity(Order.class)
                        .block())
                .getBody();
        return orderRepo.addOrder(response);
    }

    public List<Order> getOrderWithStatus(OrderStatus status) {


        List<Order> ordersWithStatus = orderRepo.listOrders().stream()
                .filter(order -> order.getOrderStatus().equals(status))
                .collect(Collectors.toList());

        return ordersWithStatus;
    }

}