package com.babyspeak.speechtracker.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue
    private int id;

    @Column(name="timestamp")
    @CreationTimestamp
    private Date timestamp;

    @NotBlank
    @Size(min = 3, max = 100, message = "Name must be between 3 and 100 characters")
    private String name;



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimestamp() {
        return timestamp;
    }




    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
