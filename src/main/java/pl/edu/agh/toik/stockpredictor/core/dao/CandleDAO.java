/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core.dao;

import java.util.Date;
import java.util.List;
import pl.edu.agh.toik.stockpredictor.core.persistence.model.CandleEntity;

/**
 *
 * @author uriel
 */
public interface CandleDAO {
    List<CandleEntity> listCandlesFor(String company, Date fromDay, Date toDay);
    void writeCandles(CandleEntity ce);
}
