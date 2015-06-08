package pl.edu.agh.toik.stockpredictor.prediction.service.impl;

import pl.edu.agh.toik.stockpredictor.core.ICoreStockQuoteReadService;
import pl.edu.agh.toik.stockpredictor.prediction.algorithm.IPredictionAlgorithm;
import pl.edu.agh.toik.stockpredictor.prediction.Prediction;
import pl.edu.agh.toik.stockpredictor.prediction.PredictionParams;
import pl.edu.agh.toik.stockpredictor.prediction.service.IPredictionService;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * Created by grzegorz on 2015-05-18.
 */
@Component
public class PredictionServiceImpl implements IPredictionService {

    private IPredictionAlgorithm predictionAlgorithm;

    public void setPredictionAlgorithm(IPredictionAlgorithm predictionAlgorithm) {
        this.predictionAlgorithm = predictionAlgorithm;
    }

    @Override
    public Prediction predict(ICoreStockQuoteReadService quoteReadService, PredictionParams params) {

        setPredictionAlgorithm(params.getPredicitionMethod().getAlgorithm());
        List<StockQuote> stockQuotes = quoteReadService.getLast(params.getListedCompany(), predictionAlgorithm.getNumberOfStocksRequired());

        Prediction prediction = new Prediction();

        BigDecimal predictionValue = predictionAlgorithm.predict(stockQuotes);

        prediction.setPrediction(predictionValue);
        prediction.setUpDown(predictionValue.compareTo(BigDecimal.ZERO) > 0 ? true : false);
        prediction.setStocks(stockQuotes);
        prediction.setPredictionMethod(params.getPredicitionMethod());

        //nie wiem gdzie to liczyć
        prediction.setProbability(-1.0);
        return prediction;
    }
}
