package pl.edu.agh.toik.stockpredictor.prediction.algorithm;

import pl.edu.agh.toik.stockpredictor.prediction.Prediction;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by grzegorz on 2015-06-08.
 */
public interface IPredictionAlgorithm {

    BigDecimal predict(List<StockQuote> stockQuotes);
    int getNumberOfStocksRequired();
}
