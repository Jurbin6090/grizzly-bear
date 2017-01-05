package com.cooksys.grizzly.model

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Created by ft4 on 2017-01-03.
 */
@Document
class Project {

    String id

    @Indexed String clientId

    @Indexed String name

    String workOrder

    Address address

    Contact manager

}
