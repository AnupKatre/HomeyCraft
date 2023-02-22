package com.stackroute.service;

import com.stackroute.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public interface OrderService {

    ResponseEntity<String>createOrder(Order order);
    List<Order> getAllOrders();

    ResponseEntity<Order> getOrderByEmail(String id);

    ResponseEntity<String> updateOrder(Order order);

    ResponseEntity<String> deleteOrderByEmail(String id);

    ResponseEntity<?>  deleteAll(Order order);
}


