/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core.impl;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import pl.edu.agh.toik.stockpredictor.core.IStockQuoteService;
import pl.edu.agh.toik.stockpredictor.core.dao.CompanyDAO;
import pl.edu.agh.toik.stockpredictor.core.dao.DAOFactory;
import pl.edu.agh.toik.stockpredictor.core.dao.StockQuoteDAO;
import pl.edu.agh.toik.stockpredictor.core.dao.impl.hbm.HibernateDAOFactory;
import pl.edu.agh.toik.stockpredictor.core.persistence.model.CompanyEntity;
import pl.edu.agh.toik.stockpredictor.core.persistence.model.StockQuoteEntity;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

/**
 *
 * @author Dariusz Hudziak
 */
@Service
public class SimpleStockQuoteService implements IStockQuoteService {

    private DAOFactory factory;
    @Autowired
    @Qualifier("hbmDAOFactory")
    public void setDAOFactory(DAOFactory dao) {
        this.factory = dao;
    }

    @Override
    @Transactional
    public List<ListedCompany> getCompanies() {
       List<CompanyEntity> entities = factory.newCompanyDAO().getCompanies();
       List<ListedCompany> result = new ArrayList<>();
       
       for(CompanyEntity ce : entities ) {
           result.add(ce.toListedCompany());
       }
       
       return result;
    }
    
    @Override
    @Transactional
    public List<StockQuote> getStockQuotes(ListedCompany listedCompany, Date from, Date to)
    {
      List<StockQuoteEntity> entities;
      List<StockQuote> result;
      StockQuoteDAO dao = factory.newStockQuoteDAO();
      
        SessionFactory sf  = ((HibernateDAOFactory)factory).getFactory();
        sf.getCurrentSession().createCriteria(StockQuoteEntity.class).list();
      
      entities = dao.listStockQuotes(listedCompany.getShortName(), from, to);
      result  = new ArrayList<>(entities.size());
      
      for(StockQuoteEntity entity : entities ){
          result.add(entity.toStockQuote());
      }
      
      return result;
    }

    @Override
    @Transactional
    public List<StockQuote> getStockQuotes(ListedCompany listedCompany, int n) {
       
      List<StockQuoteEntity> entities;
      List<StockQuote> result;
      StockQuoteDAO dao = factory.newStockQuoteDAO();
     
      entities = dao.listRecentQuotes(listedCompany.getShortName(), n);
      result  = new ArrayList<>(entities.size());
      
      for(StockQuoteEntity entity : entities ){
          result.add(entity.toStockQuote());
      }
      
      return result;
    }

    @Override
    @Transactional
    public void storeStockQuotes(List<StockQuote> list) {
       
       List<StockQuoteEntity> entties = new ArrayList<>(list.size());
       StockQuoteDAO quoteDAO = factory.newStockQuoteDAO();
       CompanyDAO comapnyDAO = factory.newCompanyDAO();
       
       for(StockQuote quote : list)
       {  
           CompanyEntity ce = comapnyDAO.findCompany(
                                quote.getListedCompany().getShortName());
           
           if(ce==null) {
               ce = new CompanyEntity(quote.getListedCompany());
               comapnyDAO.store(ce);
           }
           
           entties.add(
            new StockQuoteEntity(ce,
                                 quote.getDateAndTime(),
                                 quote.getValue()));   
       }
        
       
       quoteDAO.store(entties);
       quoteDAO.dropOlderThan(new Date(System.currentTimeMillis()-5184000000l));
    }
    
}
