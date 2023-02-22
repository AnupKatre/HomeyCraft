package com.stackroute.repository;

import com.stackroute.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;


import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends MongoRepository<Product, String>{

   // @Query(value="{ 'productName' : ?0 }")
   Product findByProductName(String productName);
  
    Boolean existsByProductName(String productName);
    Boolean existsByProductId(String productId);

	Product findBySellerEmail(String sellerEmail);

	

}
