package com.stackroute.selleruserservice.repository;
import com.stackroute.selleruserservice.model.Seller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerRepository extends MongoRepository<Seller, String> {

    Optional<Seller> findBySellerEmail(String email);
}