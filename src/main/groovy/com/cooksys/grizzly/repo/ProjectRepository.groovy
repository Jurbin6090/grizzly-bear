package com.cooksys.grizzly.repo

import com.cooksys.grizzly.model.Project
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * Created by ft4 on 2017-01-04.
 */
@Repository
interface ProjectRepository extends MongoRepository<Project, String> {
}
