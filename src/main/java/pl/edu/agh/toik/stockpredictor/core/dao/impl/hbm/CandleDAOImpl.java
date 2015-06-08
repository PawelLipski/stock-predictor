/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core.dao.impl.hbm;

import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.edu.agh.toik.stockpredictor.core.dao.CandleDAO;
import pl.edu.agh.toik.stockpredictor.core.persistence.model.CandleEntity;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;

/**
 *
 * @author Dariusz Hudziak
 */

public class CandleDAOImpl implements CandleDAO {

    private SessionFactory factory;

    public CandleDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public List<CandleEntity> listCandlesFor(String company, Date fromDay, Date toDay) {
        return factory.getCurrentSession()
                      .createCriteria(CandleEntity.class,"ce")
                      .createAlias("ce.company", "c")
                      .add(Restrictions.and(Restrictions.eq("c.shortName", company),
                                            Restrictions.between("ce.day", fromDay, toDay)))
                      .list();
    }
    
    @Override
    public void writeCandles(CandleEntity ce) {
        factory.getCurrentSession().persist(ce);
    }
    
    
   
}
