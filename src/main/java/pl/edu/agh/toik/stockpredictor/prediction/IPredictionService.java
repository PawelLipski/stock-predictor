package pl.edu.agh.toik.stockpredictor.prediction;

import pl.edu.agh.toik.stockpredictor.core.ICoreStockQuoteReadService;

public interface IPredictionService {
	Prediction predict(ICoreStockQuoteReadService quoteReadService, PredictionParams params);
}
