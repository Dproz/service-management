package com.ceitechs.dproz.servicemanagement.domain;

import java.util.List;

/**
 * @author iddymagohe on 11/3/17.
 */
public interface ServicesService {

    List<ServiceDetail> retrieveServicesByCategoryAndLanguage(String categoryId, String language);

    List<ServiceDetail> retrieveServicesByKeywordAndLanguage(String keyword, String language);

    List<ServiceCategory> retrieveCategories();
}


