package pl.edu.agh.toik.stockpredictor.prediction.algorithm.impl;

import pl.edu.agh.toik.stockpredictor.prediction.algorithm.IPredictionAlgorithm;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by grzegorz on 2015-06-08.
 */
public class ComplexPredictionAlgorithm implements IPredictionAlgorithm {

    //TODO: uzupełnić z ilu kursów algorytm korzysta
    private static final int STOCKS_REQUIRED = 0;

    @Override
    public BigDecimal predict(List<StockQuote> stockQuotes) {
        //TODO
        return null;
    }

    public int getNumberOfStocksRequired() {
        return STOCKS_REQUIRED;
    }
}
