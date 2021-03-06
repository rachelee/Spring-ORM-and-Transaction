package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.controller.status.ResourceNotFoundException;
import edu.sjsu.cmpe275.lab2.model.Person;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
            session.delete(person);
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
