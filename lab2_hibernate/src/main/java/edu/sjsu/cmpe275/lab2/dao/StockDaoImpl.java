package edu.sjsu.cmpe275.lab2.dao;

import edu.sjsu.cmpe275.lab2.model.Stock;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by xiaoxiaoli on 10/31/15.
 */


public class StockDaoImpl implements StockDao{

    @Autowired
    private SessionFactory sessionFactory;

    public StockDaoImpl(){
        Configuration configuration = new Configuration().configure();
        sessionFactory=configuration.buildSessionFactory();
    }
    public void saveOrUpdate(Stock stock){
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            session.saveOrUpdate(stock);
            tx.commit();
        }catch(RuntimeException e) {
            tx.rollback();
            ;
            throw e;
        }
        finally{
            session.close();
        }
    }

    public void delete(long stockId){
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        try {
            tx.begin();
            Stock stock = session.get(Stock.class, stockId);
            session.delete(stock);
            tx.commit();
        }catch(RuntimeException e) {
            if(tx!=null)
                tx.rollback();
            e.printStackTrace();

            throw e;
        }
        finally{
            session.close();
        }
    }


    public Stock findByStockCode(String stockCode){
        Session session = sessionFactory.openSession();
        Transaction tx = session.getTransaction();
        Stock stock;
        try {
            tx.begin();
            stock = session.get(Stock.class, stockCode);
            tx.commit();
        }catch(RuntimeException e) {
            if(tx!=null)
                tx.rollback();
            e.printStackTrace();

            throw e;
        }
        finally{
            session.close();
        }
        return stock;
    }


}
