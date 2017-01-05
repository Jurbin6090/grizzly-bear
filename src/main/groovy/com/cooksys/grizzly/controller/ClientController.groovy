package com.cooksys.grizzly.controller

import com.cooksys.grizzly.model.Client
import com.cooksys.grizzly.repo.ClientRepository
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * Created by ft4 on 2017-01-05.
 */
@RestController
@CrossOrigin
@RequestMapping('/client')
class ClientController {

    private final ClientRepository clientRepository

    ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository
    }

    @PostMapping
    Client createClient(@RequestBody Client client) {
        clientRepository.save(client)
    }

    @GetMapping('/{id}')
    Client readClient(@PathVariable('id') String id) {
        clientRepository.findOne(id)
    }

    @PatchMapping
    Client patchClient(@RequestBody Client client) {
        def existing = clientRepository.findOne(client.id)

        def existMap = existing.properties.findAll{it.key != 'class'}
        def clientMap = client.properties.findAll{it.key != 'class'}

        def merged = new Client(existMap + clientMap)

        def result = clientRepository.save(merged)

        if(existing.id != result.id)
            clientRepository.delete(existing.id)

        return result
    }

    @PutMapping
    Client putClient(@RequestBody Client client) {
        clientRepository.save(client)
    }

    @DeleteMapping('/{id}')
    void deleteClient(@PathVariable('id') String id) {
        clientRepository.delete(id)
    }

    @GetMapping
    List<Client> readAllClients() {
        clientRepository.findAll()
    }

}
