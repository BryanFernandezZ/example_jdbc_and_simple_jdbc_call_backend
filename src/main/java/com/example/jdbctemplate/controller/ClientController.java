package com.example.jdbctemplate.controller;

import com.example.jdbctemplate.dto.request.ClientRequestDto;
import com.example.jdbctemplate.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @RequestMapping(path = "/getAll", method = RequestMethod.GET)
    private ResponseEntity<?> getAllClients() {
        return ResponseEntity.ok(clientService.getClients());
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    private ResponseEntity<?> saveClient(@RequestBody ClientRequestDto clientRequestDto) {
        clientService.saveClient(clientRequestDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
    private ResponseEntity<?> updateClient(@PathVariable("id") Integer clientId, @RequestBody ClientRequestDto clientRequestDto) {
        clientService.updateClient(clientId, clientRequestDto);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
    private ResponseEntity<Void> deleteClient(@PathVariable("id") Integer clientId) {
        clientService.deleteClient(clientId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
