package edu.sjsu.cmpe275.lab2.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
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

    @ManyToOne
    @JoinColumn(name="organization_id")
    private Organization org;


    @JoinTable(
            name="friendship",
            joinColumns = {@JoinColumn(name="person_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name="friend_id", referencedColumnName = "id")}
    )
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Person> friends =new ArrayList<>();
    // constructors, setters, getters, etc

    @ManyToMany(mappedBy = "friends")
    @JsonBackReference
    private List<Person> inverseFriends = new ArrayList<>();


    public Person() {
    }
    public Person(String firstname, String lastname, String email) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }

    public int getId() {
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

    @JsonProperty("friends")
    public List<Integer> getFriendList(){
        List<Integer> res = new ArrayList<>();
        for(Person person:friends){
            res.add((int) person.getId());
        }
        for(Person person:inverseFriends)
            res.add((int) person.getId());
        return res;
    }

    public List<Person> getInverseFriends() {
        return inverseFriends;
    }

    public void setInverseFriends(List<Person> inverseFriends) {
        this.inverseFriends = inverseFriends;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Person person = (Person) obj;
        if(this.id == person.getId()){
            return true;
        }

        return false;
    }
}


