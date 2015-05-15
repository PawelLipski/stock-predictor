package pl.edu.agh.toik.stockpredictor.core;

import java.util.Date;
import java.util.List;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

public interface ICoreStockQuoteReadService {
	List<StockQuote> getStockQuotes(ListedCompany listedCompany, Date day);
}
