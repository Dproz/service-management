package com.ceitechs.dproz.servicemanagement.adapter.datastore.mongo;

import com.ceitechs.dproz.servicemanagement.domain.ServiceCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


/**
 * @author iddymagohe on 10/23/17.
 */
public interface CategoryRepository extends MongoRepository<ServiceCategory, Long> {
    List<ServiceCategory> findByDisciplineIgnoreCase(String discipline);
}
