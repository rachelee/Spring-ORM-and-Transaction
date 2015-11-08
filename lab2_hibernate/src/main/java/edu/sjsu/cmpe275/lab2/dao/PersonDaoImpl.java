package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.controller.status.ResourceNotFoundException;
import edu.sjsu.cmpe275.lab2.model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by xiaoxiaoli on 11/4/15.
 */

@Repository
public class PersonDaoImpl implements PersonDao{
    @Autowired
    private SessionFactory sessionFactory;

    public void saveOrUpdate(Person person){
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            if(person.getId()!=0){
                Person oldPerson = findByPersonId(person.getId());
                if(oldPerson==null){
                    throw new ResourceNotFoundException();
                }
                person.setFriends(oldPerson.getFriends());
            }
            session.saveOrUpdate(person);
            tx.commit();
        }catch(RuntimeException e) {
            tx.rollback();
            throw e;
        }
        finally{
            session.close();
        }
    }

    public void delete(Person person){
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Person toDelete = findByPersonId(person.getId());
            person.setFirstname(toDelete.getFirstname());
            person.setLastname(toDelete.getLastname());
            person.setEmail(toDelete.getEmail());
            person.setDescription(toDelete.getDescription());
            person.setAddress(toDelete.getAddress());
            person.setOrg(toDelete.getOrg());
            List<Person> friendList = toDelete.getInverseFriends();
            for(Person p:friendList){
                List<Person> list = p.getFriends();
                list.remove(toDelete);
                p.setFriends(list);
                updateFriend(p);
            }
            if(toDelete==null){
                throw new ResourceNotFoundException();
            }

            session.delete(toDelete);
            tx.commit();
        }catch(RuntimeException e) {
            if(tx!=null)
                tx.rollback();
            e.printStackTrace();

            throw e;
        } finally{
            session.close();
        }
    }


    public Person findByPersonId(int personId){
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        Person person;
        try {
            tx.begin();
            person = (Person)session.get(Person.class, personId);
            if(person==null){
                throw new ResourceNotFoundException();
            }
            Hibernate.initialize(person.getFriends());
            Hibernate.initialize(person.getInverseFriends());
//            for (Person p : person.getFriends()) {
//                p.setFriends(null);
//            }
//
//            for (Person p : person.getInverseFriends()) {
//                p.setFriends(null);
//            }
            tx.commit();
        }catch(RuntimeException e) {
            if(tx!=null)
                tx.rollback();
            e.printStackTrace();

            throw e;
        }
        finally{
            session.close();
        }
        return person;
    }

    public void updateFriend(Person person){
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.update(person);
            tx.commit();
        }catch(RuntimeException e) {
            tx.rollback();
        }
        finally{
            session.close();
        }
    }



}
