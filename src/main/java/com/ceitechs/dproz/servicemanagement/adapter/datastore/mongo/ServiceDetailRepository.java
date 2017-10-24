package com.ceitechs.dproz.servicemanagement.adapter.datastore.mongo;

import com.ceitechs.dproz.servicemanagement.domain.ServiceDetail;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author iddymagohe on 10/23/17.
 */
public interface ServiceDetailRepository extends MongoRepository<ServiceDetail, String> {
}
