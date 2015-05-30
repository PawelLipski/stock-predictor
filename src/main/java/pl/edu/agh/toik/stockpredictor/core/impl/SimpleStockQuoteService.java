/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core.impl;

import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.toik.stockpredictor.core.IStockQuoteService;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

/**
 *
 * @author Dariusz Hudziak
 */
@Service
public class SimpleStockQuoteService implements IStockQuoteService {

    @Override
    @Transactional
    public List<StockQuote> getStockQuotes(ListedCompany listedCompany, Date from, Date to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public List<StockQuote> getStockQuotes(ListedCompany listedCompany, int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void storeStockQuotes(List<StockQuote> list) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
