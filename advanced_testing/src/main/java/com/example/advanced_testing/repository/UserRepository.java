package com.example.advanced_testing.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.example.advanced_testing.entity.User;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
