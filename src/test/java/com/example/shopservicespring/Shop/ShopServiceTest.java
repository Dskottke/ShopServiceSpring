package com.example.shopservicespring.Shop;

import com.example.shopservicespring.Model.Order;
import com.example.shopservicespring.Model.Product;
import com.example.shopservicespring.Repos.OrderRepo;
import com.example.shopservicespring.Repos.ProductRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class ShopServiceTest {

    @Test
    @DisplayName("shopservice getProduct should match with productRepo getProduct")

    void getProductbyId(){
        //GIVEN

        List<Product> productList = new ArrayList<>(List.of(
                        new Product("1","testProduct1"),
                        new Product("2","testProduct2"))
        );
        Order testOrder = new Order("1",productList);

        ProductRepo productRepo = new ProductRepo(productList);
        Map<String,Order> ordersMap = new HashMap<>(Map.of(testOrder.getId(),testOrder));
        OrderRepo testOrderRepo = new OrderRepo(ordersMap);
        ShopService shopService = new ShopService(testOrderRepo,productRepo);

        //WHEN
        Product actual = shopService.getProduct("1");
        Product expected =productRepo.getProduct("1").get();

        //THEN
        assertEquals(expected,actual);

    }

    @Test
    @DisplayName("the Order from testShopService should match with the expected Order")
        void orderProductsByKeys(){
        //GIVEN
        List<Product> productsToOrder = new ArrayList<>(List.of(
               new Product("1","Testproduct1"),
               new Product("2","Testproduct2"),
               new Product("3","Testproduct3"))
        );

        OrderRepo testOrderRepo = new OrderRepo();
        ProductRepo testProductRepo = new ProductRepo(productsToOrder);
        ShopService testShopService = new ShopService(testOrderRepo,testProductRepo);

        //WHEN
        List<String> productKeysForOrder = new ArrayList<>(List.of("1","2"));

        Order actual = testShopService.orderProducts(productKeysForOrder);
        Order expected = new Order("2",List.of(
                new Product("1","Testproduct1"),
                new Product("2","Testproduct2")
        ));
        //THEN
        assertEquals(expected.listProducts(),actual.listProducts());
        }

    @Test
    @DisplayName("Should throw an noProductsFoundException")
    void orderProductsByFalseKey(){
        //GIVEN
        List<Product> productsToOrder = new ArrayList<>(List.of(
                new Product("1","Testproduct1"),
                new Product("2","Testproduct2"),
                new Product("3","Testproduct3"))
        );

        OrderRepo testOrderRepo = new OrderRepo();
        ProductRepo testProductRepo = new ProductRepo(productsToOrder);
        ShopService testShopService = new ShopService(testOrderRepo,testProductRepo);


        //WHEN
        List<String> productKeysForOrder = new ArrayList<>(List.of("1","4"));
        try{
        testShopService.orderProducts(productKeysForOrder);
            fail();
        }
        //THEN
        catch (NoProductFoundException e){
            assertEquals("found no product with this id ",e.getMessage());

        }


    }


    }

