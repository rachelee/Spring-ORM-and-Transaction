package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.model.Stock;

public interface StockDao {
    void saveOrUpdate(Stock stock);
    void delete(long stockId);
    Stock findByStockCode(String stockCode);
}
