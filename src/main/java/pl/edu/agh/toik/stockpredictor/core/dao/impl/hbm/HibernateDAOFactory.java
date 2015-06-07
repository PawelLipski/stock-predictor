/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core.dao.impl.hbm;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.toik.stockpredictor.core.dao.CandleDAO;
import pl.edu.agh.toik.stockpredictor.core.dao.CompanyDAO;
import pl.edu.agh.toik.stockpredictor.core.dao.DAOFactory;
import pl.edu.agh.toik.stockpredictor.core.dao.StockQuoteDAO;

/**
 *
 * @author Dariusz Hudziak
 */
public class HibernateDAOFactory implements DAOFactory {

    private SessionFactory factory;
    public void setFactory(SessionFactory factory) {
        this.factory = factory;
    }

    public SessionFactory getFactory() {
        return factory;
    }
           
    
    
    @Override
    public CompanyDAO newCompanyDAO() {
        return new CompanyDAOImpl(factory);
    }

    @Override
    public StockQuoteDAO newStockQuoteDAO() {
        return new StockQuoteDAOImpl(factory);
    }

    @Override
    public CandleDAO newCandleDAO() {
        return new CandleDAOImpl(factory);
    }
    
}
