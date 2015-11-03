package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.model.Organization;

/**
 * Created by xiaoxiaoli on 11/2/15.
 */
public interface OrganizationDao {
    void saveOrUpdate(Organization org);
    void delete(Organization org);
    Organization findByOrgId(int orgId);
}
