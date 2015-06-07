/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.toik.stockpredictor.core.ICandleService;
import pl.edu.agh.toik.stockpredictor.core.dao.CandleDAO;
import pl.edu.agh.toik.stockpredictor.core.dao.CompanyDAO;
import pl.edu.agh.toik.stockpredictor.core.dao.DAOFactory;
import pl.edu.agh.toik.stockpredictor.core.persistence.model.CandleEntity;
import pl.edu.agh.toik.stockpredictor.core.persistence.model.CompanyEntity;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;

/**
 *
 * @author Dariusz Hudziak
 */
@Service
public class SimpleCandleService implements ICandleService {

    @Autowired
    private DAOFactory factory;
    public void setDAOFactory(DAOFactory dao) {
        this.factory = dao;
    }
    
    @Override
    @Transactional
    public List<Candle> getCandlesFor(ListedCompany le, Date from, Date to) {
        CandleDAO dao = factory.newCandleDAO();
        
        List<CandleEntity> entities = dao.listCandlesFor(le.getShortName(), from, to);
        List<Candle> result = new ArrayList<>(entities.size());
        
        for(CandleEntity ce : entities){
            result.add(ce.toCandle());
        }
        
        return result;
    }

    @Override
    @Transactional
    public void store(List<Candle> lc) {
       
        CandleDAO candleDAO = factory.newCandleDAO();
        CompanyDAO companyDAO = factory.newCompanyDAO();
        
        for(Candle c : lc) {
            
           candleDAO.writeCandles(
            new CandleEntity(
                    companyDAO.findCompany(c.getListedCompany().getShortName()),
                    c.getMaxPrice(),
                    c.getMinPrice(),
                    c.getOpeningPrice(),
                    c.getClosingPrice(),
                    c.getDay()
            ));
            
        }
        
    }
    
    
}
