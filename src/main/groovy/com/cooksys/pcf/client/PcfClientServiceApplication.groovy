package com.cooksys.pcf.client

import com.cooksys.pcf.client.model.Client
import com.cooksys.pcf.client.repo.ClientRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class PcfClientServiceApplication {

    private static final def log = LoggerFactory.getLogger(PcfClientServiceApplication.class)
    private final ClientRepository clientRepository

    static void main(String[] args) {
		SpringApplication.run PcfClientServiceApplication, args
	}

    PcfClientServiceApplication(ClientRepository clientRepository) {
        this.clientRepository = clientRepository
    }

    @RequestMapping(path = "client", method = RequestMethod.POST)
    void createClient(@RequestBody Client client) {
        log.info('Received client: {}', client)

        clientRepository.save(client)
    }

	@RequestMapping(path = "client/{id}", method = RequestMethod.GET)
    Client readClient(@PathVariable('id') String id) {
        clientRepository.findOne(id)
    }

    @RequestMapping(path = "client", method = RequestMethod.PATCH)
    Client updateClient(@RequestBody Client client) {
        clientRepository.save(client)
    }

    @RequestMapping(path = "client/{id}", method = RequestMethod.DELETE)
    void deleteClient(@PathVariable('id') String id) {
        clientRepository.delete(id)
    }

    @RequestMapping(path = "client", method = RequestMethod.GET)
    List<Client> readAllClients() {
        clientRepository.findAll()
    }

}
