package com.cooksys.grizzly

import com.cooksys.grizzly.model.Client
import com.cooksys.grizzly.repo.ClientRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
@CrossOrigin
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

    @RequestMapping(path = "client", method = RequestMethod.PUT)
    Client putClient(@RequestBody Client client) {
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
