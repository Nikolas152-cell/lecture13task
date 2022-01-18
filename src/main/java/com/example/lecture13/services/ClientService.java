package com.example.lecture13.services;

import com.example.lecture13.models.Client;
import com.example.lecture13.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;

    public List<Client> findByFullName(String fullName)
    {
        return clientRepository.findClientsByFullName(fullName);
    }

    public Integer countClientsByFirstName(String firstName)
    {
        return clientRepository.countClientsByFirstName(firstName);
    }
    public List<Client> findClientsByFirstNameAndPep(String firstName, Boolean isPep){
        return clientRepository.findClientsByFirstNameAndPepIsTrue(firstName, isPep);
    }
}
