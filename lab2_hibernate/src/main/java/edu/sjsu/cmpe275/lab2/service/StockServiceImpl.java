package edu.sjsu.cmpe275.lab2.service;

import edu.sjsu.cmpe275.lab2.dao.StockDao;
import edu.sjsu.cmpe275.lab2.model.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl implements StockService{

    @Autowired
    StockDao stockDao;

    public void setStockDao(StockDao stockDao) {
        this.stockDao = stockDao;
    }

    public void save(Stock stock){
        stockDao.saveOrUpdate(stock);
    }

    public void update(Stock stock){
        stockDao.saveOrUpdate(stock);
    }

    public void delete(Stock stock){
        long stockId = stock.getStockId();
        stockDao.delete(stockId);
    }

    public Stock findByStockId(long stockId){
        return stockDao.findByStockId(stockId);
    }

}
