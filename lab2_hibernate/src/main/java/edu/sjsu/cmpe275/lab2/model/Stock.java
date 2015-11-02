package edu.sjsu.cmpe275.lab2.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="stock")
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name="STOCK_ID")
    private Long stockId;

    @Column(name="STOCK_CODE", nullable = false)
    private String stockCode;

    @Column(name="STOCK_NAME", nullable = false)
    private String stockName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }
}