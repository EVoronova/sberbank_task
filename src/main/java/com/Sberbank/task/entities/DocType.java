package com.Sberbank.task.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "DOC_TYPE")
public class DocType {
    @Id
    private String name;

    public DocType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocType docType = (DocType) o;
        return Objects.equals(name, docType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
