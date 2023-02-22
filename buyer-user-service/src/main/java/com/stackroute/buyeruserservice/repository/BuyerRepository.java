package com.stackroute.buyeruserservice.repository;

import com.stackroute.buyeruserservice.model.Buyer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BuyerRepository extends MongoRepository<Buyer, String> {
    Optional<Buyer> findByBuyerEmail(String customerEmail);

}
