package edu.sjsu.cmpe275.lab2.service;


import edu.sjsu.cmpe275.lab2.model.Organization;

/**
 * Created by xiaoxiaoli on 11/2/15.
 */
public interface OrganizationService {
    void save(Organization org);
    int update(Organization org);
    void delete(Organization org);
    Organization findByOrgId(int orgId);
}
