package com.ceitechs.dproz.servicemanagement.adapter.datastore.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

/**
 * @author iddymagohe on 10/23/17.
 */
public interface CategoryRepository extends MongoRepository<Service, String> {
}
