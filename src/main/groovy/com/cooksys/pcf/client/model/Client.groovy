package com.cooksys.pcf.client.model

import groovy.transform.ToString
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

/**
 * Created by ft4 on 2016-12-30.
 */
@Document
@ToString
class Client {

    String id

    @Indexed
    String name

    Address address

}
