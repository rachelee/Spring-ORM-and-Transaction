package edu.sjsu.cmpe275.lab2.model;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by xiaoxiaoli on 10/30/15.
 */
@Entity
@Table(name="organization")
public class Organization {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    @Embedded
    private Address address;

    public Organization() {

    }
    public Organization(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
