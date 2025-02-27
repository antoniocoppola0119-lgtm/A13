package com.groom.manvsclass.controller.authentication;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthenticatedAdminRepository extends MongoRepository<AuthenticatedAdmin, String> {

    AuthenticatedAdmin findByAuthToken(String authToken);
}
