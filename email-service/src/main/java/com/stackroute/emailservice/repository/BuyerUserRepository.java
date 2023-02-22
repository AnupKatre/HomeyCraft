package com.stackroute.emailservice.repository;

import com.stackroute.emailservice.model.Buyer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface BuyerUserRepository extends MongoRepository<Buyer, String> {
    public Optional<Buyer> findByBuyerName(String buyerName);
}
