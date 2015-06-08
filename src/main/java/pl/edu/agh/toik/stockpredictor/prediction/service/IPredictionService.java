package pl.edu.agh.toik.stockpredictor.prediction.service;

import pl.edu.agh.toik.stockpredictor.core.ICoreStockQuoteReadService;
import pl.edu.agh.toik.stockpredictor.prediction.Prediction;
import pl.edu.agh.toik.stockpredictor.prediction.PredictionParams;

public interface IPredictionService {
	Prediction predict(ICoreStockQuoteReadService quoteReadService, PredictionParams params);
}
