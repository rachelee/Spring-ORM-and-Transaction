package edu.sjsu.cmpe275.lab2.service;

import edu.sjsu.cmpe275.lab2.model.Shop;

public interface ShopService {

    public Shop create(Shop shop);
//    public Shop delete(int id);
//    public List<Shop> findAll();
//    public Shop update(Shop shop);
    public Shop findById(int id);

}