package edu.sjsu.cmpe275.lab2.dao;

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

    public void saveOrUpdate(Organization org){
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.saveOrUpdate(org);
            tx.commit();
        }catch(RuntimeException e) {
            tx.rollback();
            ;
            throw e;
        }
        finally{
            session.close();
        }
    }

    public void delete(Organization org){
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.delete(org);
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
