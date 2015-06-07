package pl.edu.agh.toik.stockpredictor.prediction.algorithm.impl;

import pl.edu.agh.toik.stockpredictor.prediction.algorithm.IPredictionAlgorithm;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by grzegorz on 2015-06-08.
 */
public class SimplePredictionAlgorithm implements IPredictionAlgorithm {

    private static final int STOCKS_REQUIRED = 2;

    @Override
    public BigDecimal predict(List<StockQuote> stockQuotes) {
        StockQuote older = stockQuotes.get(0);
        StockQuote newer = stockQuotes.get(1);

        //nie jestem pewien co do posortowania kursów w liście po datach
        if (older.getDateAndTime().after(newer.getDateAndTime())) {
            StockQuote temp = older;
            older = newer;
            newer = temp;
        }

        return newer.getValue().add(newer.getValue().subtract(older.getValue()));
    }

    public int getNumberOfStocksRequired() {
        return STOCKS_REQUIRED;
    }
}
