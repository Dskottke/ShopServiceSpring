package com.example.shopservicespring.Shop;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class NoProductFoundException extends RuntimeException{

    //Exception for no match with id
    public NoProductFoundException() {

        super("found no product with this id ");
    }

}
