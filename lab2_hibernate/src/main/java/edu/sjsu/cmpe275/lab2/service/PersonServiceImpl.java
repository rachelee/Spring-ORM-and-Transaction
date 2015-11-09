package edu.sjsu.cmpe275.lab2.service;

import edu.sjsu.cmpe275.lab2.controller.status.ResourceNotFoundException;
import edu.sjsu.cmpe275.lab2.dao.OrganizationDao;
import edu.sjsu.cmpe275.lab2.dao.PersonDao;
import edu.sjsu.cmpe275.lab2.model.Organization;
import edu.sjsu.cmpe275.lab2.model.Person;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaoxiaoli on 11/4/15.
 */
@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    PersonDao personDao;

    @Autowired
    OrganizationDao organizationDao;

    @Autowired
    FriendService friendService;

    @Transactional
    public void save(Person person){
        Organization  org = person.getOrg();
        if(org!=null){
            person.setOrg(organizationDao.findByOrgId(org.getId()));
        }
        personDao.saveOrUpdate(person);
    }

    @Transactional
    public void update(Person person){
        int orgId = person.getOrg().getId();
        if(orgId!=0){
            person.setOrg(organizationDao.findByOrgId(orgId));
        }
        personDao.saveOrUpdate(person);
    }

    @Transactional
    public void delete(Person person){
        Person toDelete = findByPersonId(person.getId());
        person.setFirstname(toDelete.getFirstname());
        person.setLastname(toDelete.getLastname());
        person.setEmail(toDelete.getEmail());
        person.setDescription(toDelete.getDescription());
        person.setAddress(toDelete.getAddress());
        person.setOrg(toDelete.getOrg());
        Hibernate.initialize(toDelete.getFriends());
        Hibernate.initialize(toDelete.getInverseFriends());
        List<Person> friendList1 = toDelete.getFriends();
        List<Person> friendList2 = toDelete.getInverseFriends();
        for(Person p:friendList1){
            friendService.removeFriendRelationship(person.getId(), p.getId());
        }
        for(Person p:friendList2){
            friendService.removeFriendRelationship(person.getId(), p.getId());
        }
        if(toDelete==null){
            throw new ResourceNotFoundException();
        }

        personDao.delete(toDelete);
    }

    public Person findByPersonId(int personId){
        return personDao.findByPersonId(personId);
    }
}
