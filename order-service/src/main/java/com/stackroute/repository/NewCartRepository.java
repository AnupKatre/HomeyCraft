package com.stackroute.repository;

import com.stackroute.model.NewCartDto;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NewCartRepository extends MongoRepository<NewCartDto,String> {
    Optional<NewCartDto> findByCustomerEmail(String customerEmail);
}
