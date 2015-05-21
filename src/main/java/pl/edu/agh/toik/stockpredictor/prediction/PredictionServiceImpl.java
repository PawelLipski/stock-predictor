package pl.edu.agh.toik.stockpredictor.prediction;

import org.springframework.stereotype.Component;

import pl.edu.agh.toik.stockpredictor.core.ICoreStockQuoteReadService;

@Component
public class PredictionServiceImpl implements IPredictionService {
	@Override
	public Prediction predict(ICoreStockQuoteReadService quoteReadService,
			PredictionParams params) {
		// TODO Auto-generated method stub
		return null;
	}
}
