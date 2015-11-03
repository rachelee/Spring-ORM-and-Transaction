package edu.sjsu.cmpe275.lab2.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by xiaoxiaoli on 10/30/15.
 */
@Entity
@Table(name="Person")
public class Person {
    @Id
    @GeneratedValue
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String description;
    @Embedded
    private Address address;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="organization_id")
    private Organization org;

    @ManyToMany
    @JoinTable(
            name="friendship",
            joinColumns = {@JoinColumn(name="person_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="friend_id", referencedColumnName = "id")}
    )
    private List<Person> friends;
    // constructors, setters, getters, etc


    public Person(String firstname, String lastname, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Organization getOrg() {
        return org;
    }

    public void setOrg(Organization org) {
        this.org = org;
    }

    public List<Person> getFriends() {
        return friends;
    }

    public void setFriends(List<Person> friends) {
        this.friends = friends;
    }
}


