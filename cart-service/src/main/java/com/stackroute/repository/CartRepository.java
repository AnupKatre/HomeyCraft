package com.stackroute.repository;

import com.stackroute.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart,String> {
    Optional<Cart> findByCustomerEmail(String customerEmail);
}
