/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core.dao.impl.hbm;

import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.edu.agh.toik.stockpredictor.core.dao.CandleDAO;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;

/**
 *
 * @author uriel
 */
public class CandleDAOImpl implements CandleDAO {

    private SessionFactory factory;
    
    @Autowired
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sf){
        factory = sf;
    }
    
    @Override
    public boolean allCandlesPresent(ListedCompany company, Date fromDay, Date toDay) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Candle> listCandlesFor(ListedCompany company, Date fromDay, Date toDay) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeCandles(List<Candle> candles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
