package edu.sjsu.cmpe275.lab2.service;

import edu.sjsu.cmpe275.lab2.dao.OrganizationDao;
import edu.sjsu.cmpe275.lab2.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiaoxiaoli on 11/2/15.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService{
    @Autowired
    OrganizationDao orgDao;

    public void save(Organization org){
        orgDao.saveOrUpdate(org);
    }

    public void update(Organization org){
        orgDao.saveOrUpdate(org);
    }

    public void delete(Organization org){
        orgDao.delete(org);
    }

    public Organization findByOrgId(int orgId){
        return orgDao.findByOrgId(orgId);
    }
}
