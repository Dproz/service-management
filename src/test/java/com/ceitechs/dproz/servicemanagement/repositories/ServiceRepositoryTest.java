package com.ceitechs.dproz.servicemanagement.repositories;

import com.ceitechs.dproz.servicemanagement.AbstractDprozServiceIntegrationTest;
import com.ceitechs.dproz.servicemanagement.adapter.datastore.mongo.CategoryRepository;
import com.ceitechs.dproz.servicemanagement.adapter.datastore.mongo.ServiceRepository;
import com.ceitechs.dproz.servicemanagement.domain.ServiceCategory;
import com.ceitechs.dproz.servicemanagement.domain.ServiceDetail;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.*;

/**
 * @author iddymagohe on 10/24/17.
 */
public class ServiceRepositoryTest extends AbstractDprozServiceIntegrationTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ServiceRepository serviceDetailRepository;



    @Test
    public void createServiceDetailTest(){
        categoryRepository.deleteAll();
        serviceDetailRepository.deleteAll();
        ServiceDetail sd = createServiceDetail();
        categoryRepository.save(createCategory());
        ServiceDetail svd = serviceDetailRepository.save(sd);
        assertEquals("saved and about to be saved services, must be equal", sd, svd);
    }



    @Test
    public void createCategoryTest(){
        categoryRepository.deleteAll();
        ServiceCategory sc = createCategory();
        ServiceCategory svc = categoryRepository.save(sc);
        assertEquals("saved and about to be saved service-category, must be equal", sc, svc);
    }

    @Test
    public void findCategoriesByDisciplineTest(){
        categoryRepository.deleteAll();
        serviceDetailRepository.deleteAll();
        categoryRepository.save(createCategory());

       List<ServiceCategory> categoryList = categoryRepository.findByDisciplineIgnoreCase("HousIng construction");
       assertTrue(categoryList.size() > 0);
    }

    @Test
    public void findServiceDescriptionTest(){
        categoryRepository.deleteAll();
        serviceDetailRepository.deleteAll();
        categoryRepository.save(createCategory());
        ServiceDetail sd = createServiceDetail();
        ServiceDetail svd = serviceDetailRepository.save(sd);
        ServiceDetail sdsearch = new ServiceDetail();
        sdsearch.setServiceDescription("ins");

        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
                .withIgnorePaths("category", "serviceId","serviceNumber")
                .withMatcher("lang", exact().ignoreCase())
                .withMatcher("serviceDescription", contains().ignoreCase());
        List<ServiceDetail> serviceDetails = serviceDetailRepository.findAll(Example.of(sdsearch,exampleMatcher));

        assertTrue(serviceDetails.size() > 0);
    }



    private ServiceCategory createCategory() {
        ServiceCategory s = new ServiceCategory();
        s.setCategoryDescription("Fences described");
        s.setCategoryName("Fences");
        s.setCategoryId(1L);
        s.setDiscipline("Housing Construction");
        return s;
    }

    private ServiceDetail createServiceDetail(){
        ServiceDetail serviceDetail = new ServiceDetail();
        serviceDetail.setCategory(createCategory());
        serviceDetail.setServiceNumber(101L);
        serviceDetail.setServiceDescription("Wrought Iron Fence - Install");
        return serviceDetail;
    }
}
