package com.ceitechs.dproz.servicemanagement.services;

import com.ceitechs.dproz.servicemanagement.adapter.datastore.mongo.CategoryRepository;
import com.ceitechs.dproz.servicemanagement.adapter.datastore.mongo.ServiceRepository;
import com.ceitechs.dproz.servicemanagement.domain.ServiceCategory;
import com.ceitechs.dproz.servicemanagement.domain.ServiceDetail;
import com.ceitechs.dproz.servicemanagement.domain.ServicesService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

/**
 * @author iddymagohe on 11/3/17.
 */

@Service
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ServiceRepository serviceRepository;



    @Override
    public List<ServiceDetail> retrieveServicesByCategoryAndLanguage(String categoryId, String language) {
        List<ServiceDetail> serviceDetails = new ArrayList<>();

        Optional<ServiceCategory> category = categoryRepository.findById(Long.valueOf(categoryId));
        if (category.isPresent()) {
            serviceDetails = serviceRepository.findByCategoryAndLang(category.get(), StringUtils.isEmpty(language) ? "en" : language);
        }
        return serviceDetails;
    }

    @Override
    public List<ServiceDetail> retrieveServicesByKeywordAndLanguage(String keyword, String language) {
        ServiceDetail serviceDetail = new ServiceDetail();
        serviceDetail.setLang(StringUtils.isEmpty(language) ? "en" : language);
        serviceDetail.setServiceDescription(keyword);

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("category", "serviceId", "serviceNumber")
                .withMatcher("lang", exact().ignoreCase())
                .withMatcher("serviceDescription", contains().ignoreCase());

        return serviceRepository.findAll(Example.of(serviceDetail, exampleMatcher));
    }

    @Override
    public List<ServiceCategory> retrieveCategories() {
        return categoryRepository.findAll();
    }
}
