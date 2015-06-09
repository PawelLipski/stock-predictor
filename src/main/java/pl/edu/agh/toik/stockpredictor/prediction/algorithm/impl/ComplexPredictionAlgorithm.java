package pl.edu.agh.toik.stockpredictor.prediction.algorithm.impl;

import pl.edu.agh.toik.stockpredictor.prediction.algorithm.IPredictionAlgorithm;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

import java.math.BigDecimal;
import java.util.List;

/**
 * Algorytm korzysta z modelu liniowego Holta wygladzania wykladniczego ciagow
 * czasowych.
 */
public class ComplexPredictionAlgorithm implements IPredictionAlgorithm {

	private static final int STOCKS_REQUIRED = 20;

	private final BigDecimal alfa = new BigDecimal("0.75");
	private final BigDecimal alfaSubtracted = new BigDecimal(1).subtract(alfa);
	private final BigDecimal beta = new BigDecimal("0.023");
	private final BigDecimal betaSubtracted = new BigDecimal(1).subtract(beta);

	@Override
	public BigDecimal predict(List<StockQuote> stockQuotes) {
		BigDecimal f1 = stockQuotes.get(STOCKS_REQUIRED - 1).getValue();
		BigDecimal f2 = stockQuotes.get(STOCKS_REQUIRED - 2).getValue();
		BigDecimal s = f2.subtract(f1);
		for (int i = STOCKS_REQUIRED - 3; i >= 0; i--) {
			f1 = f2;
			f2 = stockQuotes.get(i).getValue().multiply(alfa)
					.add(alfaSubtracted.multiply(f2.add(s)));
			s = beta.multiply(f2.subtract(f1)).add(betaSubtracted.multiply(s));
		}
		return f2.add(s);
	}

	public int getNumberOfStocksRequired() {
		return STOCKS_REQUIRED;
	}
}
