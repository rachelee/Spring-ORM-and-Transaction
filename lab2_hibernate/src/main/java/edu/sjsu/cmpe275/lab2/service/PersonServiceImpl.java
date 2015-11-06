package edu.sjsu.cmpe275.lab2.service;

import edu.sjsu.cmpe275.lab2.dao.OrganizationDao;
import edu.sjsu.cmpe275.lab2.dao.PersonDao;
import edu.sjsu.cmpe275.lab2.model.Organization;
import edu.sjsu.cmpe275.lab2.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by xiaoxiaoli on 11/4/15.
 */
@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    PersonDao personDao;

    @Autowired
    OrganizationDao organizationDao;

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

    public void delete(Person person){
        personDao.delete(person);
    }

    public Person findByPersonId(int personId){
        return personDao.findByPersonId(personId);
    }
}
