/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core.dao.impl.hbm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.toik.stockpredictor.core.dao.StockQuoteDAO;
import pl.edu.agh.toik.stockpredictor.core.persistence.model.StockQuoteEntity;

/**
 *
 * @author Dariusz Hudziak
 */

public class StockQuoteDAOImpl implements StockQuoteDAO {
    
    private final SessionFactory factory;

    public StockQuoteDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<StockQuoteEntity> listRecentQuotes(String companyName, int n) {
        Session s = factory.getCurrentSession();
      
        
        return
        s.createCriteria(StockQuoteEntity.class,"sq")
         .createAlias("sq.company", "c")
         .add(Restrictions.eq("c.shortName", companyName))
         .addOrder(Order.desc("sq.dateTime"))
         .setMaxResults(n)
         .list();
    }

    @Override
    public List<StockQuoteEntity> listStockQuotes(String companyName, Date from, Date to) {
        Session s = factory.getCurrentSession();
        
        return
        s.createCriteria(StockQuoteEntity.class,"sq")
         .createAlias("sq.company", "c")
         .add(Restrictions.eq("c.shortName", companyName))
         .add(Restrictions.between("sq.dateTime", from, to))
         .list();
    }

    @Override
    public void store(List<StockQuoteEntity> list) {
        Session s = factory.getCurrentSession();
        
        for(StockQuoteEntity e : list) {
            s.persist(e);
        }
    }
    
    @Override
    public void dropOlderThan(Date date) {
        Session s = factory.getCurrentSession();
        Query q = s.createQuery("delete from StockQuoteEntity where dateTime < :date");
        q.setParameter("date", date);
        q.executeUpdate();
    }
}