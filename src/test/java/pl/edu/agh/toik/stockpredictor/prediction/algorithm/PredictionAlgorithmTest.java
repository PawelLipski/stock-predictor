package pl.edu.agh.toik.stockpredictor.prediction.algorithm;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.toik.stockpredictor.prediction.algorithm.impl.SimplePredictionAlgorithm;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

import java.math.BigDecimal;
import org.joda.time.DateTime;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by grzegorz on 2015-06-08.
 */
public class PredictionAlgorithmTest {

    @Test
    public void simplePredictionAlgorithmTest_stockQuoteDoubled() {
        BigDecimal olderQuoteValue = new BigDecimal(1.44);
        BigDecimal newerQuoteValue = new BigDecimal(2.88);

        BigDecimal result = newerQuoteValue.add(newerQuoteValue.subtract(olderQuoteValue));

        StockQuote stockQuoteOlder = new StockQuote();
        stockQuoteOlder.setValue(olderQuoteValue);
        stockQuoteOlder.setDateAndTime(DateTime.now().minusDays(1).toDate());

        StockQuote stockQuoteNewer = new StockQuote();
        stockQuoteNewer.setValue(newerQuoteValue);
        stockQuoteNewer.setDateAndTime(DateTime.now().toDate());

        List<StockQuote> stockQuotes = new LinkedList<>();
        stockQuotes.add(stockQuoteOlder);
        stockQuotes.add(stockQuoteNewer);

        IPredictionAlgorithm predictionAlgorithm = new SimplePredictionAlgorithm();

        BigDecimal predictionResult = predictionAlgorithm.predict(stockQuotes);

        Assert.assertEquals(predictionResult, result);
    }
}
