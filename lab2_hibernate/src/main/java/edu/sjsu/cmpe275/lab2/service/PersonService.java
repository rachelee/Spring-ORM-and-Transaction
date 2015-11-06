package edu.sjsu.cmpe275.lab2.service;

import edu.sjsu.cmpe275.lab2.model.Person;

/**
 * Created by xiaoxiaoli on 11/4/15.
 */
public interface PersonService {
    void save(Person person);
    void update(Person person);
    void delete(Person person);
    Person findByPersonId(int personId);
}
