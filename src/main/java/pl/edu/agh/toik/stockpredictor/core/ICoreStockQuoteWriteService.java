package pl.edu.agh.toik.stockpredictor.core;

import java.util.List;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

public interface ICoreStockQuoteWriteService {
	void storeStockQuotes(List<StockQuote> shareData);
}
