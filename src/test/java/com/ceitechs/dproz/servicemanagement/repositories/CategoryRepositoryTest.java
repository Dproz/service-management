package com.ceitechs.dproz.servicemanagement.repositories;

import com.ceitechs.dproz.servicemanagement.AbstractDprozServiceIntegrationTest;
import com.ceitechs.dproz.servicemanagement.domain.ServiceCategory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author iddymagohe on 10/24/17.
 */
public class CategoryRepositoryTest extends AbstractDprozServiceIntegrationTest {


    private List<ServiceCategory> createCategories() {
        List<ServiceCategory> cats = new ArrayList<>();

        ServiceCategory s = new ServiceCategory();
        s.setCategoryDescription("Fences described");
        s.setCategoryName("Fences");
        s.setCategoryNumber(1L);
        s.setDiscipline("Housing Construction");



        return cats;
    }
}
