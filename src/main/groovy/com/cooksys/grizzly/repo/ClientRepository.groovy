package com.cooksys.grizzly.repo

import com.cooksys.grizzly.model.Client
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Created by ft4 on 2016-12-30.
 */
interface ClientRepository extends MongoRepository<Client, String> {




}
