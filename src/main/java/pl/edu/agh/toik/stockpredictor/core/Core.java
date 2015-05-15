package pl.edu.agh.toik.stockpredictor.core;

import java.util.Date;
import java.util.List;

import pl.edu.agh.toik.stockpredictor.prediction.Prediction;
import pl.edu.agh.toik.stockpredictor.prediction.PredictionParams;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;


public class Core implements ICoreCandlestickChartService, ICorePredictionService, 
		ICoreStockQuoteReadService, ICoreStockQuoteWriteService {
	
	@Override
	public List<Candle> getCandlestickChart(ListedCompany listedCompany, Date dayFrom, Date dayTo) {
	 // Szyna sprawdza, czy ma już policzone świece dla wszystkich dni od X do Y.
	 // Jeśli nie:
	 //     szyna -> analizy: policz mi świece dla wszystkich dni od X do Y 
	 // public List<Candle> getCandles(List<StockQuote> shareData);
	 //     (nie przejmujemy się, że niektóre już mogły być policzone)
	 // zapisz te świece w bazie
	
	 // szyna -> analizy: masz tu candles, znajdź mi dla nich formacje -> formations 
	 // (tu już liczymy zawsze on-line - formacji NIGDY nie persystujemy)
	 // zwróć do GUI candles oraz formations
	 // public CandlestickChart createCandlestickChart(ListedCompany listedCompany, List<Candle> candles);
		return null;
	}

	@Override
	public Prediction getPrediction(PredictionParams params) {
		// TODO zaimplementuj jako: return predictionService.predict((ICoreStockQuoteReadService)this, params);
		return null;
	}

	@Override
	public void storeStockQuotes(List<StockQuote> shareData) {		
		// TODO: zapis do bazy
	}

	@Override
	public List<StockQuote> getStockQuotes(ListedCompany listedCompany, Date day) {
		// TODO: odczyt z bazy dla danej spolki i dnia
		return null;
	}
}
