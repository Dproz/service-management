package com.ceitechs.dproz.servicemanagement.adapter.datastore.mongo;

import com.ceitechs.dproz.servicemanagement.domain.ServiceCategory;
import com.ceitechs.dproz.servicemanagement.domain.ServiceDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;


/**
 * @author iddymagohe on 10/23/17.
 */
public interface ServiceRepository extends MongoRepository<ServiceDetail, String>, QueryByExampleExecutor<ServiceDetail> {
    List<ServiceDetail> findByCategoryAndLang(ServiceCategory category, String lang);
}
