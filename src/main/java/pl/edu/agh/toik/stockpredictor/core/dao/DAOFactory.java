/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core.dao;

/**
 *
 * @author uriel
 */
public interface DAOFactory {
    CompanyDAO newCompanyDAO();
    StockQuoteDAO newStockQuoteDAO();
    CandleDAO newCandleDAO();
}
