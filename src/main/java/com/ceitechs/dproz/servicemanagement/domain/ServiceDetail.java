package com.ceitechs.dproz.servicemanagement.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author iddymagohe on 10/23/17.
 */

@Getter
@Setter
@Document(collection = "services")
public class ServiceDetail {

    @Id
    private String serviceId;

    @Indexed
    private long serviceNumber;

    @Indexed
    private String lang = "en";

    @Transient
    private ServiceCategory category;

    @Indexed
    private long categoryNumber;

    @TextIndexed
    private String serviceDescription;

}
