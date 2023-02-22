package com.stackroute.repository;

import com.stackroute.model.PaymentDto;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PaymentRepository extends MongoRepository<PaymentDto,String> {
}
