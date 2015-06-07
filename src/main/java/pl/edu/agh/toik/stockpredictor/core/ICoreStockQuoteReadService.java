package pl.edu.agh.toik.stockpredictor.core;

import java.util.Date;
import java.util.List;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

public interface ICoreStockQuoteReadService {
        /**
         * 
         * @param listedCompany
         * @param from pełna data razem z czasem
         * @param to pełna data razem z czasem
         * @return 
         */
	List<StockQuote> getStockQuotes(ListedCompany listedCompany, Date from, Date to);
        List<StockQuote> getLast(ListedCompany listedCompany,int n);
        List<ListedCompany> getCompanies();
}
