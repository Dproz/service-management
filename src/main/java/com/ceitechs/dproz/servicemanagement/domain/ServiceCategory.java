package com.ceitechs.dproz.servicemanagement.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author  iddymagohe on 10/23/17.
 */
@Getter
@Setter
@Document(collection = "service-category")
public class ServiceCategory {

    @Id
    private String categoryId;

    private long categoryNumber;

    private String categoryName;

    private String categoryDescription;

    @Indexed
    private String discipline;

}
