package pl.edu.agh.toik.stockpredictor.core;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.agh.toik.stockpredictor.core.dao.CandleDAO;
import pl.edu.agh.toik.stockpredictor.core.dao.StockQuoteDAO;
import pl.edu.agh.toik.stockpredictor.prediction.IPredictionService;

import pl.edu.agh.toik.stockpredictor.prediction.Prediction;
import pl.edu.agh.toik.stockpredictor.prediction.PredictionParams;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.chart.CandlestickChart;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Formation;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.FormationType;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.service.ITechnicalAnalysisService;

@Component
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
		
		Candle candle1 = new Candle(listedCompany, new BigDecimal("100"), new BigDecimal("50"), 
				new BigDecimal("60"), new BigDecimal("90"), dayFrom); 		
		Candle candle2 = new Candle(listedCompany, new BigDecimal("200"), new BigDecimal("100"), 
				new BigDecimal("120"), new BigDecimal("180"), dayFrom);
		
		List<Candle> candles = new ArrayList<Candle>();
		candles.add(candle1);
		candles.add(candle2);
				
		Formation formation = new Formation(dayFrom, dayTo, FormationType.BEARISH_KICKER_PATTERN, candles);
		
		List<Formation> formations = new ArrayList<Formation>();		
		formations.add(formation);
		
		CandlestickChart chart = new CandlestickChart();
		chart.setListedCompany(listedCompany);
		chart.setStartDay(new Date(115, 4, 10));
		chart.setEndDay(new Date(115, 4, 15));
		chart.setCandles(candles);
		chart.setFormations(new ArrayList<Formation>());
		return chart;
		
		/*
		TODO: odkomentowac jak DAO bedzie zaimplementowane
		 
		List<Candle> candles;

		if (daoCandle.allCandlesPresent(listedCompany, dayTo, dayTo)) {
			candles = daoCandle.listCandlesFor(listedCompany, dayTo, dayTo);
		} else {
			candles = analysisService.getCandles(daoStock.getQuotesFor(listedCompany, dayFrom, dayTo));
			daoCandle.writeCandles(candles);
		}

		CandlestickChart chart = analysisService.createCandlestickChart(listedCompany, candles);
		return chart;
		*/

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

        public List<StockQuote> getStockQuotes(ListedCompany listedCompany, Date from, Date to)
        {
            return daoStock.getQuotesFor(listedCompany, from, to);
        }
        public List<StockQuote> getLast(ListedCompany listedCompany,int n)
        {
            return null;
           // return daoStock.getQuotesFor(listedCompany, day, day);
        }
        


}
