package edu.sjsu.cmpe275.lab2.service;

/**
 * Created by xiaoxiaoli on 11/5/15.
 */
public interface FriendService {
    void formFriendRelationship(int id1, int id2);
    void removeFriendRelationship(int id1, int id2);
}
