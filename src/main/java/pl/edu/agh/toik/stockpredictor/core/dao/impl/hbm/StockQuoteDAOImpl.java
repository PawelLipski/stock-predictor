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
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.toik.stockpredictor.core.dao.StockQuoteDAO;
import pl.edu.agh.toik.stockpredictor.core.persistence.model.StockQuoteEntity;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

/**
 *
 * @author uriel
 */
public class StockQuoteDAOImpl implements StockQuoteDAO {
    
    private SessionFactory factory;
    @Autowired
    public void setSessionFactory(SessionFactory sf){
        factory = sf;
    }

    @Override
    public List<StockQuoteEntity> listRecentQuotes(String companyName, int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<StockQuoteEntity> listStockQuotes(String companyName, Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    @Override
    public void store(List<StockQuoteEntity> list) {
        Session s = factory.getCurrentSession();
        
        for(StockQuoteEntity e : list) {
            s.persist(e.getCompany());
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
    
    
    

 
    public List<StockQuote> getQuotesFor(ListedCompany lcomp, Date fromDay, Date toDay) {
      
      Session s = factory.getCurrentSession();
      
      if(!s.isOpen()) {
          s = factory.openSession();
      }
        
      Transaction t =  s.beginTransaction();
        
       List<StockQuoteEntity> list = factory.getCurrentSession().
               createCriteria(StockQuoteEntity.class,"sq").
               createAlias("sq.company", "c").
               add(Restrictions.eq("c.shortName", lcomp.getShortName())).
               add(Restrictions.between("dateTime",fromDay, toDay))
               .list();
       List<StockQuote> res = new ArrayList(list.size());
       
       t.commit();
       
       for(StockQuoteEntity sqe : list) {
           res.add(sqe.toStockQuote());
       }
       
       return res;
    }

  
    public void storeStockQuotes(List<StockQuote> lsq) {
        
        
        
        
        for(StockQuote sq  : lsq) {
            Session session = factory.getCurrentSession();
                if(!session.isOpen()){
                    session = factory.openSession();
                }
            Transaction t =  session.beginTransaction();
            session.persist(StockQuoteEntity.from(sq, session));
            t.commit();
        }
        
        
        
    }
    
    
    
}
