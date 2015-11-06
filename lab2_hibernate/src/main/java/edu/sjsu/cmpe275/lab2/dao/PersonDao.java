package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.model.Person;

/**
 * Created by xiaoxiaoli on 11/4/15.
 */
public interface PersonDao {
    void saveOrUpdate(Person person);
    void delete(Person person);
    Person findByPersonId(int personId);
    void updateFriend(Person person);
}
