package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.controller.status.InvalidParameterException;
import edu.sjsu.cmpe275.lab2.controller.status.ResourceNotFoundException;
import edu.sjsu.cmpe275.lab2.model.Organization;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by xiaoxiaoli on 11/2/15.
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao{
    @Autowired
    private SessionFactory sessionFactory;

    public int saveOrUpdate(Organization org){
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            if(org.getId()!=0){
                Organization oldOrg = findByOrgId(org.getId());
                if(oldOrg==null){
                    return -1;
                }
            }
            session.saveOrUpdate(org);
            tx.commit();
        }catch(RuntimeException e) {
            tx.rollback();
            throw e;
        }
        finally{
            session.close();
        }
        return 0;
    }

    public void delete(Organization org){
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Organization toDelete = findByOrgId(org.getId());
            if(toDelete==null){
                throw new ResourceNotFoundException();
            }
            org.setName(toDelete.getName());
            org.setAddress(toDelete.getAddress());
            org.setDescription(toDelete.getDescription());
            session.delete(toDelete);
            tx.commit();
        }catch(RuntimeException e) {
            if(tx!=null)
                tx.rollback();
            e.printStackTrace();
            throw new InvalidParameterException();
        }
        finally{
            session.close();
        }
    }


    public Organization findByOrgId(int orgId){
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        Organization org;
        try {
            tx.begin();
            org = (Organization)session.get(Organization.class, orgId);
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
        return org;
    }

}
