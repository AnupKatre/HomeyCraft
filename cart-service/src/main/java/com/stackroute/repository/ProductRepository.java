package com.stackroute.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String>{
}
