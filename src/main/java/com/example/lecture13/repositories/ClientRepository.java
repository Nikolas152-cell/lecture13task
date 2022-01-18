package com.example.lecture13.repositories;

import com.example.lecture13.models.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ClientRepository extends MongoRepository<Client,String>{
    List<Client> findClientsByFullName(String fullName);
    List<Client> findClientsByFirstNameAndPepIsTrue(String firstName, boolean isPep);
    Integer countClientsByFirstName(String firstName);

}
