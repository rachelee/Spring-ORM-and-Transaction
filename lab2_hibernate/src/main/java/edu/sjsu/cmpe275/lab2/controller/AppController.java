package edu.sjsu.cmpe275.lab2.controller;

import edu.sjsu.cmpe275.lab2.controller.status.ResourceNotFoundException;
import edu.sjsu.cmpe275.lab2.controller.status.InvalidParameterException;
import edu.sjsu.cmpe275.lab2.model.Address;
import edu.sjsu.cmpe275.lab2.model.Organization;
import edu.sjsu.cmpe275.lab2.model.Person;
import edu.sjsu.cmpe275.lab2.service.FriendService;
import edu.sjsu.cmpe275.lab2.service.OrganizationService;
import edu.sjsu.cmpe275.lab2.service.PersonService;
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
    private PersonService personService;

    @Autowired
    private OrganizationService orgService;

    @Autowired
    FriendService friendService;


    @RequestMapping("org/hello")
    @ResponseBody
    public String greeting() {
        return "Hello World";
    }


    @RequestMapping(value="/person", method = RequestMethod.POST)
    @ResponseBody
    public Person createPerson(@RequestParam(value="firstname", required=true) String firstName,
                                  @RequestParam(value="lastname", required=true) String lastName,
                                  @RequestParam(value="email", required=true) String email,
                                  @RequestParam(value="description", required=false) String description,
                                  @RequestParam(value="street", required=false) String street,
                                  @RequestParam(value="city", required=false) String city,
                                  @RequestParam(value="state", required=false) String state,
                                  @RequestParam(value="zip", required=false) String zip,
                                  @RequestParam(value="orgId", required=false) Integer orgId) {

        if(firstName == ""||lastName ==""||email==""){
            throw new InvalidParameterException();
        }
        Person person = new Person(firstName, lastName, email);

        if(description!=null){
            person.setDescription(description);
        }
        if(orgId != null) {
            Organization org = new Organization();
            org.setId(orgId);
            person.setOrg(org);
        }

        Address address = new Address(street, city, state, zip);
        person.setAddress(address);

        personService.save(person);

        return person;
    }

    @RequestMapping(value="/person/{id}", method = RequestMethod.GET, produces={"application/xml", "application/json"})
    @ResponseBody
    public Person getPerson(@PathVariable int id) {

        Person person = personService.findByPersonId(id);
        if(person==null){
            throw new ResourceNotFoundException();
        }

        return person;
    }
    @RequestMapping(value="/person/{id}", method = RequestMethod.GET)
    public String getPerson(@PathVariable int id, Model model) {

        Person person = personService.findByPersonId(id);
        if(person==null){
            throw new ResourceNotFoundException();
        }
        model.addAttribute("id", person.getId());
        model.addAttribute("firstname", person.getFirstname());
        model.addAttribute("lastname", person.getLastname());
        model.addAttribute("email", person.getEmail());
        model.addAttribute("description", person.getDescription());
        model.addAttribute("address", person.getAddress());
        model.addAttribute("organization", person.getOrg());
        return "person";
    }

    @RequestMapping(value="/person/{personId}", method = RequestMethod.POST)
    @ResponseBody
    public Person updatePerson(@PathVariable int personId,
                               @RequestParam(value="firstname", required=true) String firstname,
                               @RequestParam(value="lastname", required=true) String lastname,
                               @RequestParam(value="email", required=true) String email,
                               @RequestParam(value="description", required=false) String description,
                               @RequestParam(value="street", required=false) String street,
                               @RequestParam(value="city", required=false) String city,
                               @RequestParam(value="state", required=false) String state,
                               @RequestParam(value="zip", required=false) String zip,
                               @RequestParam(value="orgId", required=false) Integer orgId) {

        if(firstname == ""||lastname==""||email==""){
            throw new InvalidParameterException();
        }

        Person person = new Person(firstname, lastname, email);
        person.setId(personId);
        person.setDescription(description);
        Address address = new Address(street, city, state, zip);
        person.setAddress(address);
        if(orgId != null) {
            Organization org = new Organization();
            org.setId(orgId);
            person.setOrg(org);
        }

        personService.update(person);

        return person;
    }

    @RequestMapping(value="/person/{personId}", method = RequestMethod.DELETE)
    @ResponseBody
    public Person deletePerson(@PathVariable int personId) {
        Person person = new Person();
        person.setId(personId);
        personService.delete(person);
        return person;
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

        Organization org = new Organization(name);
        org.setId(orgId);
        org.setDescription(description);
        Address address = new Address(street, city, state, zip);
        org.setAddress(address);

        int error = orgService.update(org);
        if(error == -1){
            throw new ResourceNotFoundException();
        }


        return org;
    }

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

    @RequestMapping(value="friends/{id1}/{id2}", method = RequestMethod.PUT )
    @ResponseBody
    public String addFriend(@PathVariable int id1, @PathVariable int id2){
        friendService.formFriendRelationship(id1, id2);
        String res = Integer.toString(id1)+" and "+Integer.toString(id2)+" are friends now!";
        return res;
    }

    @RequestMapping(value="friends/{id1}/{id2}", method = RequestMethod.DELETE )
    @ResponseBody
    public String removeFriend(@PathVariable int id1, @PathVariable int id2){
        friendService.removeFriendRelationship(id1, id2);
        String res = Integer.toString(id1)+" and "+Integer.toString(id2)+" are not friends now!";
        return res;
    }


}
