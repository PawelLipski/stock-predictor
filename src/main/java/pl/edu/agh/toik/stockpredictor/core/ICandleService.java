/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core;

import java.util.Date;
import java.util.List;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;

/**
 *
 * @author Dariusz Hudziak
 */
public interface ICandleService {
    List<Candle> getCandlesFor(ListedCompany le,Date from,Date to);
    void store(List<Candle> lc);
}
