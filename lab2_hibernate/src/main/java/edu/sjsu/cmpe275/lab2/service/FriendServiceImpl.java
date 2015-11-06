package edu.sjsu.cmpe275.lab2.service;

import edu.sjsu.cmpe275.lab2.controller.status.ResourceNotFoundException;
import edu.sjsu.cmpe275.lab2.dao.PersonDao;
import edu.sjsu.cmpe275.lab2.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by xiaoxiaoli on 11/5/15.
 */
@Service
public class FriendServiceImpl implements FriendService {
    @Autowired
    PersonDao personDao;

    @Transactional
    public void formFriendRelationship(int id1, int id2){
        Person p1 = personDao.findByPersonId(id1);
        Person p2 = personDao.findByPersonId(id2);
        if(p1==null||p2==null){
            throw new ResourceNotFoundException();
        }
        try{
            List<Person> friendList = p1.getFriends();
            friendList.add(p2);
            p1.setFriends(friendList);
            personDao.updateFriend(p1);
        }
        catch (RuntimeException e){
            throw e;
        }

    }
    @Transactional
    public void removeFriendRelationship(int id1, int id2){
        Person p1 = personDao.findByPersonId(id1);
        Person p2 = personDao.findByPersonId(id2);
        if(p1==null||p2==null){
            throw new ResourceNotFoundException();
        }
        try{
            List<Person> friendList = p1.getFriends();
            if((friendList.remove(p2))==false){
                throw new ResourceNotFoundException();
            }
            p1.setFriends(friendList);
            personDao.updateFriend(p1);
        }
        catch (RuntimeException e){
            throw e;
        }
    }
}
