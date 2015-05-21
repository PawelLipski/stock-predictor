package pl.edu.agh.toik.stockpredictor.core;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.toik.stockpredictor.core.dao.CandleDAO;
import pl.edu.agh.toik.stockpredictor.core.dao.StockQuoteDAO;
import pl.edu.agh.toik.stockpredictor.prediction.IPredictionService;

import pl.edu.agh.toik.stockpredictor.prediction.Prediction;
import pl.edu.agh.toik.stockpredictor.prediction.PredictionParams;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.chart.CandlestickChart;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.service.ITechnicalAnalysisService;

public class Core implements ICoreCandlestickChartService,
		ICorePredictionService, ICoreStockQuoteReadService,
		ICoreStockQuoteWriteService {

	private CandleDAO daoCandle;
	private StockQuoteDAO daoStock;
	private ITechnicalAnalysisService analysisService;
	private IPredictionService predService;

	@Autowired
	public void setCandleDAO(CandleDAO candleDAO) {
		daoCandle = candleDAO;
	}

	@Autowired
	public void setStockQuoteDAO(StockQuoteDAO stockDAO) {
		daoStock = stockDAO;
	}
	
	@Autowired
	public void setAnalysisService(ITechnicalAnalysisService analysisService) {
		this.analysisService = analysisService;		
	}

	@Autowired
	public void setPredService(IPredictionService predService) {
		this.predService = predService;
	}

	public boolean candleDAOReady() {
		return daoCandle != null;
	}

	public boolean stockQuoteDAOReady() {
		return daoStock != null;
	}

	@Override
	public CandlestickChart getCandlestickChart(ListedCompany listedCompany, Date dayFrom, Date dayTo) {
		
		List<Candle> candles;

		if (daoCandle.allCandlesPresent(listedCompany, dayTo, dayTo)) {
			candles = daoCandle.listCandlesFor(listedCompany, dayTo, dayTo);
		} else {
			candles = analysisService.getCandles(daoStock.getQuotesFor(listedCompany, dayFrom, dayTo));
			daoCandle.writeCandles(candles);
		}

		CandlestickChart chart = analysisService.createCandlestickChart(listedCompany, candles);
		return chart;

		// Szyna sprawdza, czy ma już policzone świece dla wszystkich dni od X
		// do Y.
		// Jeśli nie:
		// szyna -> analizy: policz mi świece dla wszystkich dni od X do Y
		// public List<Candle> getCandles(List<StockQuote> shareData);
		// (nie przejmujemy się, że niektóre już mogły być policzone)
		// zapisz te świece w bazie

		// szyna -> analizy: masz tu candles, znajdź mi dla nich formacje ->
		// formations
		// (tu już liczymy zawsze on-line - formacji NIGDY nie persystujemy)
		// zwróć do GUI candles oraz formations
		// public CandlestickChart createCandlestickChart(ListedCompany
		// listedCompany, List<Candle> candles);

	}

	@Override
	public Prediction getPrediction(PredictionParams params) {
		// TODO zaimplementuj jako: return
		// predictionService.predict((ICoreStockQuoteReadService)this, params);
		return predService.predict(this, params);
	}

	@Override
	public void storeStockQuotes(List<StockQuote> shareData) {
		daoStock.storeStockQuotes(shareData);
	}

	@Override
	public List<StockQuote> getStockQuotes(ListedCompany listedCompany, Date day) {
		// TODO: odczyt z bazy dla danej spolki i dnia
		return daoStock.getQuotesFor(listedCompany, day, day);
	}


}
