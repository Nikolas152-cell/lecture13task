package com.example.lecture13.repositories;

import com.example.lecture13.models.Client;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface CustomClientRepository {
    Map<String, Integer> top10ClientsByFirstName();
}
