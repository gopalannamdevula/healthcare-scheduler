package com.gopal.patient.repository;

import com.gopal.patient.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<Users,String> {
    Optional<Users> findByUsername(String username);
}
