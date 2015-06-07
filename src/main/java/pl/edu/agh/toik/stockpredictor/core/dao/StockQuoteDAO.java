/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core.dao;

import java.util.Date;
import java.util.List;
import pl.edu.agh.toik.stockpredictor.core.persistence.model.StockQuoteEntity;


/**
 *
 * @author Dariusz Hudziak
 */
public interface StockQuoteDAO {
   List<StockQuoteEntity> listRecentQuotes(String companyName,int n);
   List<StockQuoteEntity> listStockQuotes(String companyName,Date from,Date to);
   void store(List<StockQuoteEntity> list);
   void dropOlderThan(Date date);
   
}
