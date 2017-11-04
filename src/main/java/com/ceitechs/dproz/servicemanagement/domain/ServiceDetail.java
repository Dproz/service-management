package com.ceitechs.dproz.servicemanagement.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * @author iddymagohe on 10/23/17.
 */

@Getter
@Setter
@Document(collection = "services")
@TypeAlias("services")
public class ServiceDetail {

    @Id
    private String serviceId;

    @Indexed
    private long serviceNumber;

    @Indexed
    private String lang = "en";

    @DBRef
    private ServiceCategory category;

    @Indexed
    private String serviceDescription;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceDetail that = (ServiceDetail) o;
        return serviceNumber == that.serviceNumber &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(serviceNumber, category);
    }
}
