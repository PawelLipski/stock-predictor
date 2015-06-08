package pl.edu.agh.toik.stockpredictor.prediction.algorithm;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import pl.edu.agh.toik.stockpredictor.prediction.algorithm.impl.ComplexPredictionAlgorithm;
import pl.edu.agh.toik.stockpredictor.prediction.algorithm.impl.SimplePredictionAlgorithm;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

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
    
    @Test
    public void complexPredictionAlgorithmTest() {
    	List<String> list = new LinkedList<String>();
    	list.add("201.3755");
    	list.add("201.3654");
    	list.add("201.3654");
    	list.add("201.3600");
    	list.add("201.3688");
    	list.add("201.4076");
    	list.add("201.3600");
    	list.add("201.4024");
    	list.add("201.4029");
    	list.add("201.3500");
    	list.add("201.3003");
    	list.add("201.3373");
    	list.add("201.3422");
    	list.add("201.3429");
    	list.add("201.3090");
    	list.add("201.3168");
    	list.add("201.3198");
    	list.add("201.3045");
    	list.add("201.3223");
    	list.add("201.3000");
    	
    	BigDecimal result = new BigDecimal("201.29412");
    	
    	List<StockQuote> stockQuotes = new LinkedList<>();
    	for (String string : list) {
    		StockQuote stockQuote = new StockQuote();
    		stockQuote.setValue(new BigDecimal(string));
    		stockQuotes.add(stockQuote);
		}
    	
    	IPredictionAlgorithm predictionAlgorithm = new ComplexPredictionAlgorithm();
    	BigDecimal predictionResult = predictionAlgorithm.predict(stockQuotes).setScale(5, BigDecimal.ROUND_HALF_UP);;
    	
    	Assert.assertEquals(predictionResult, result);
    }
}
