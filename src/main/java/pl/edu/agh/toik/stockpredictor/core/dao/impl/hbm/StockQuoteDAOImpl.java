/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core.dao.impl.hbm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public List<StockQuote> getQuotesFor(ListedCompany lcomp, Date fromDay, Date toDay) {
        
      Transaction t =  factory.getCurrentSession().beginTransaction();
        
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

    @Override
    public void storeStockQuotes(List<StockQuote> lsq) {
        
        Session session = factory.getCurrentSession();
        
        
        for(StockQuote sq  : lsq) {
            Transaction t =  session.beginTransaction();
            session.persist(StockQuoteEntity.from(sq, session));
            t.commit();
        }
        
    }
    
    
    
}
