package com.example.shopservicespring.Response;

import com.example.shopservicespring.Model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {


    private Order orders ;

}
