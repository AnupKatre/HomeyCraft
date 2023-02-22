package com.stackroute.emailservice.repository;

import com.stackroute.emailservice.model.Buyer;
import com.stackroute.emailservice.model.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface SellerUserRepository extends MongoRepository<Seller, String> {
    public Optional<Seller> findBySellerName(String sellerName);
}
