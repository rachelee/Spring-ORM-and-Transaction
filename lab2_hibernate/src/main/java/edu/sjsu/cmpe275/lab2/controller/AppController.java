package edu.sjsu.cmpe275.lab2.controller;

import edu.sjsu.cmpe275.lab2.controller.status.ResourceNotFoundException;
import edu.sjsu.cmpe275.lab2.controller.status.InvalidParameterException;
import edu.sjsu.cmpe275.lab2.model.Address;
import edu.sjsu.cmpe275.lab2.model.Organization;
import edu.sjsu.cmpe275.lab2.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



/**
 * Created by xiaoxiaoli on 11/2/15.
 */
@Controller
public class AppController {
    @Autowired
    private OrganizationService orgService;

    @RequestMapping("org/hello")
    @ResponseBody
    public String greeting() {
        return "Hello World";
    }

    @RequestMapping(value="/org", method = RequestMethod.POST)
    @ResponseBody
    public Organization createOrg(@RequestParam(value="name", required=true) String name,
                          @RequestParam(value="description", required=false) String description,
                          @RequestParam(value="street", required=false) String street,
                          @RequestParam(value="city", required=false) String city,
                          @RequestParam(value="state", required=false) String state,
                          @RequestParam(value="zip", required=false) String zip) {

        if(name == ""){
            throw new InvalidParameterException();
        }

        Organization org = new Organization(name);
        if(description!=null){
            org.setDescription(description);
        }
        Address address = new Address(street, city, state, zip);
        org.setAddress(address);

        orgService.save(org);


        return org;
    }

    @RequestMapping(value="/org/{orgId}", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseBody
    public Organization getOrg(@PathVariable int orgId) {

        Organization org = orgService.findByOrgId(orgId);
        if(org==null){
            throw new ResourceNotFoundException();
        }

        return org;
    }
    @RequestMapping(value="/org/{orgId}", method = RequestMethod.GET)
    public String getOrg(@PathVariable int orgId, Model model) {

        Organization org = orgService.findByOrgId(orgId);
        if(org==null){
            throw new ResourceNotFoundException();
        }
        model.addAttribute("id", org.getId());
        model.addAttribute("name", org.getName());
        model.addAttribute("description", org.getDescription());
        model.addAttribute("address", org.getAddress());
        return "organization";
    }

    @RequestMapping(value="/org/{orgId}", method = RequestMethod.POST)
    @ResponseBody
    public Organization updateOrg(@PathVariable int orgId,
                                  @RequestParam(value="name", required=true) String name,
                                  @RequestParam(value="description", required=false) String description,
                                  @RequestParam(value="street", required=false) String street,
                                  @RequestParam(value="city", required=false) String city,
                                  @RequestParam(value="state", required=false) String state,
                                  @RequestParam(value="zip", required=false) String zip) {

        if(name == ""){
            throw new InvalidParameterException();
        }

        Organization org = orgService.findByOrgId(orgId);
        if(org==null){
            throw new ResourceNotFoundException();
        }
        org.setName(name);
        org.setDescription(description);
        Address address = new Address(street, city, state, zip);
        org.setAddress(address);

        orgService.save(org);


        return org;
    }

    //TODO:If there is still any person belonging to this organization, return 400
    @RequestMapping(value="/org/{orgId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Organization deleteOrg(@PathVariable int orgId) {

        Organization org = orgService.findByOrgId(orgId);
        if(org==null){
            throw new ResourceNotFoundException();
        }

        orgService.delete(org);

        return org;
    }
}
