package com.stackroute.repository;

import com.stackroute.model.NewCartDto;
import com.stackroute.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface OrderRepository extends MongoRepository<Order, String> {
    Optional<Order> findByBuyerEmail(String customerEmail);
    void deleteByBuyerEmail(String customerEmail);

}
