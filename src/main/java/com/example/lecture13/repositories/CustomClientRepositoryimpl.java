package com.example.lecture13.repositories;

import com.example.lecture13.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class CustomClientRepositoryimpl implements CustomClientRepository{
    @Autowired
    ClientRepository clientRepository;



    @Override
    public Map<String, Integer> top10ClientsByFirstName() {
       List<Client> clientList = clientRepository.findAll();
       Map<String, Integer> top10 = new TreeMap<>();
        for (Client client: clientList) {
            if (client.isPep()) {
                if (top10.containsKey(client.getFirstName())) {
                    top10.computeIfPresent(client.getFirstName(), (k, v) -> v + 1);
                } else {
                    top10.put(client.getFirstName(), 1);
                }
            }
            else{
                continue;
            }
        }

        return top10.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
