package edu.sjsu.cmpe275.lab2;

import edu.sjsu.cmpe275.lab2.model.Stock;
import edu.sjsu.cmpe275.lab2.service.StockService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application
{
    public static void main( String[] args )
    {
        ApplicationContext appContext =
                new ClassPathXmlApplicationContext("spring-config.xml");

        StockService stockBo = (StockService)appContext.getBean("stockService");

        /** insert **/
        Stock stock = new Stock();
        stock.setStockCode("7668");
        stock.setStockName("HAIO");
        stockBo.save(stock);

        /** select **/
        Stock stock2 = stockBo.findByStockCode("7668");
        System.out.println(stock2);

        /** update **/
        stock2.setStockName("HAIO-1");
        stockBo.update(stock2);

        /** delete **/
        stockBo.delete(stock2);

        System.out.println("Done");
    }
}