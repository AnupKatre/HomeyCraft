package com.stackroute.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.model.User;


@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
