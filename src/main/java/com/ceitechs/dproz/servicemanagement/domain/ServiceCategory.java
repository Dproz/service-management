package com.ceitechs.dproz.servicemanagement.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

/**
 * @author  iddymagohe on 10/23/17.
 */
@Getter
@Setter
@Document(collection = "service-category")
@TypeAlias("service-category")
public class ServiceCategory {

    @Id
    private long categoryId;

    private String categoryName;

    private String categoryDescription;

    @Indexed
    private String discipline;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ServiceCategory that = (ServiceCategory) o;
        return Objects.equals(categoryName.toLowerCase(), that.categoryName.toLowerCase()) &&
                Objects.equals(discipline.toLowerCase(), that.discipline.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoryName, discipline);
    }
}
