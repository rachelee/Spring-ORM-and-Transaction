package edu.sjsu.cmpe275.lab2.service;


import edu.sjsu.cmpe275.lab2.model.Stock;


public interface StockService {

    void save(Stock stock);
    void update(Stock stock);
    void delete(Stock stock);
    Stock findByStockCode(String stockCode);
}