package pl.edu.agh.toik.stockpredictor.technicalanalysis.service;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.chart.CandlestickChart;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

import java.util.List;

/**
 * Created by Krzysztof Kicinger on 2015-05-14.
 */
public interface ITechnicalAnalysisService {

    public List<Candle> getCandles(List<StockQuote> shareData);

    public CandlestickChart createCandlestickChart(ListedCompany listedCompany, List<Candle> candles);

}
