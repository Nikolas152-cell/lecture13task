package com.example.lecture13.controllers;

import com.example.lecture13.repositories.CustomClientRepository;
import com.example.lecture13.services.ClientService;
import com.example.lecture13.models.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CustomClientRepository customClientRepository;


    @GetMapping("/")
    public String[] homePage()
    {
        String welcomeText = "This is home page " +
                "1.You can find client by his full name using url:/find?fullName, " +
                "where full name your input " +
                "2.You can find out top ten first names and their quantity in db " +
                "by url: /top10";
        String[] lines = welcomeText.split("\r\n");
        return lines;
    }

    @GetMapping("/find")
    public String index(Model model, @RequestParam(value = "fullName", defaultValue = "" +
            "Єгор Євгенович Пристрома") String fullName)
    {
        //model.addAttribute("client");
        //List<Client> clientList = clientRepository.findAll();
        /*for (Client client: clientList) {
            System.out.println(client.getLastName());
            System.out.println(client.getFirstName());
            System.out.println(client.getId());
        }*/
        if(clientService.findByFullName(fullName) == null)
        {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "client not found"
            );
        }
        List<Client> clients = clientService.findByFullName(fullName);
        Map<String, List<String>> mapForClients = new HashMap<>();
        for (Client client: clients) {
            List<String> listForAttributes = new ArrayList<>();
            listForAttributes.add("Is pep " + client.isPep());
            listForAttributes.add("Is died " + client.isDied());
            mapForClients.put(client.getFullName(), listForAttributes);
        }

        return mapForClients.toString();
    }

    @GetMapping("/count")
    public String count(@RequestParam("firstName") String firstName)
    {
        return "Количество человек с именем " + firstName + ": " +
                clientService.countClientsByFirstName(firstName).toString();
    }

    @GetMapping("/topTen")
    public String topTen(@RequestParam("firstName") String firstName)
    {
        return "Количество человек с именем " + firstName + ": " +
                clientService.findClientsByFirstNameAndPep(firstName, true).toString();
    }

    @GetMapping("/topten")
    public String topTenFirstNames()
    {
        return customClientRepository.top10ClientsByFirstName().toString();
    }



}
