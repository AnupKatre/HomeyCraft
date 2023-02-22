package com.stackroute.controller;

import com.stackroute.model.Order;
import com.stackroute.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

    private ResponseEntity responseEntity;
    @Autowired
    OrderServiceImpl orderService;

//    @PostMapping("/addOrder")
//    public ResponseEntity<String>createOrder(@RequestBody Order order) {
//        order.setId(orderService.getSequenceNumber(Order.SEQUENCE_NAME));
//        return orderService.createOrder(order);
//    }

    @GetMapping("/getOrder/{customerEmail}")
    public ResponseEntity<Order> getOrderByEmail(@PathVariable("customerEmail") String email) {
        return orderService.getOrderByEmail(email) ;
    }
    @GetMapping("/getOrders")
    public ResponseEntity<?> getAllOrders() {
        responseEntity= new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
        return responseEntity;
    }

    @PutMapping("/updateOrder")
    public ResponseEntity<String> updateOrder(@RequestBody Order order) {
       return orderService.updateOrder(order);
    }

    @DeleteMapping("/deleteOrder/{customerEmail}")
    public ResponseEntity<?> deleteOrderByEmail(@PathVariable("customerEmail") String email) {
        return orderService.deleteOrderByEmail(email);

    }
    @DeleteMapping("/deleteAllOrders")
    public ResponseEntity<?> deleteAll(Order order) {
        return orderService.deleteAll(order);
    }



}
