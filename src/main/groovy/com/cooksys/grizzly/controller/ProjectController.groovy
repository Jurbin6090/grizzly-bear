package com.cooksys.grizzly.controller

import com.cooksys.grizzly.model.Project
import com.cooksys.grizzly.repo.ProjectRepository
import org.springframework.web.bind.annotation.*

/**
 * Created by ft4 on 2017-01-05.
 */
@RestController
@CrossOrigin
@RequestMapping('/project')
class ProjectController {

    private final ProjectRepository repo

    ProjectController(ProjectRepository repo) {
        this.repo = repo
    }


    @GetMapping
    List<Project> getAllProjects() {
        repo.findAll()
    }

    @GetMapping('/{id}')
    Project getProject(@PathVariable("id") String id) {
        repo.findOne(id)
    }

    @PostMapping
    Project createProject(@RequestBody String project) {
        repo.save(project)
    }

    @PatchMapping
    Project patchClient(@RequestBody Project client) {
        def existing = repo.findOne(client.id)

        def existMap = existing.properties.findAll{it.key != 'class'}
        def clientMap = client.properties.findAll{it.key != 'class'}

        def merged = new Project(existMap + clientMap)

        def result = repo.save(merged)

        if(existing.id != result.id)
            repo.delete(existing.id)

        return result
    }

    @PutMapping
    Project putClient(@RequestBody Project client) {
        repo.save(client)
    }

    @DeleteMapping('/{id}')
    void deleteClient(@PathVariable('id') String id) {
        repo.delete(id)
    }


}
