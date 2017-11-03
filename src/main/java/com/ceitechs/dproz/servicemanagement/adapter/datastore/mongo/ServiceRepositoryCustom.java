package com.ceitechs.dproz.servicemanagement.adapter.datastore.mongo;

import com.ceitechs.dproz.servicemanagement.domain.ServiceDetail;
import org.apache.lucene.util.CollectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author iddymagohe on 11/2/17.
 */
public interface ServiceRepositoryCustom {

    List<ServiceDetail> findByserviceDescriptionAndLang(String description, String lang);
}

class ServiceRepositoryCustomImpl implements ServiceRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<ServiceDetail> findByserviceDescriptionAndLang(String description, String lang) {
        List<ServiceDetail> serviceDetails = new ArrayList<>();

        Query query = TextQuery.queryText(new TextCriteria().matchingPhrase(description).caseSensitive(false));
        serviceDetails = mongoTemplate.find(query, ServiceDetail.class);

        if (!CollectionUtils.isEmpty(serviceDetails)) {
            serviceDetails = serviceDetails.stream()
                    .filter(serviceDetail -> serviceDetail.getLang().equalsIgnoreCase(lang))
                    .collect(Collectors.toList());
        }

        //TODO re-write to using regular expression

        return serviceDetails;
    }
}
