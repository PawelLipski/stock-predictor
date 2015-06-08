package pl.edu.agh.toik.stockpredictor.core;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.edu.agh.toik.stockpredictor.datacollection.StockQuoteCollector;
import pl.edu.agh.toik.stockpredictor.prediction.service.IPredictionService;

import pl.edu.agh.toik.stockpredictor.prediction.Prediction;
import pl.edu.agh.toik.stockpredictor.prediction.PredictionParams;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.chart.CandlestickChart;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.service.ITechnicalAnalysisService;

@Component
public class Core implements ICoreCandlestickChartService,
		ICorePredictionService, ICoreStockQuoteReadService,
		ICoreStockQuoteWriteService {

        private IStockQuoteService quoteService;
        @Autowired
        public void setQuoteService(IStockQuoteService quoteService) {
            this.quoteService = quoteService;
        }
        
        private ICandleService candleService;
        @Autowired
        public void setCandleService(ICandleService candleService) {
            this.candleService = candleService;
        }
        
	private ITechnicalAnalysisService analysisService;
        @Autowired
	public void setAnalysisService(ITechnicalAnalysisService analysisService) {
		this.analysisService = analysisService;		
	}
        
	private IPredictionService predService;
	@Autowired
	public void setPredService(IPredictionService predService) {
		this.predService = predService;
	}

        
        private static StockQuoteCollector collector;
        private static Thread collectorThread;
        
        
        @PostConstruct
        public void start() {
            collector = new StockQuoteCollector(5,this);
            collector.addStockQuote("POM");
            collector.addStockQuote("M");
            collectorThread = new Thread(collector);
            collectorThread.setDaemon(true);
            collectorThread.start();
        }
        
        @PreDestroy
        public void stop(){
            stopDataCollection();
        }
        
        public static void stopDataCollection(){
            if(collectorThread !=null && collectorThread.isAlive()){
                collectorThread.interrupt();
                collectorThread=null;
            }
        }
	

	@Override
	public CandlestickChart getCandlestickChart(ListedCompany listedCompany, 
                                                    Date dayFrom, 
                                                    Date dayTo)
                                                    throws ChartUnavailable {				
		
//		Candle candle1 = new Candle(listedCompany, new BigDecimal("100"), new BigDecimal("50"), 
//				new BigDecimal("60"), new BigDecimal("90"), dayFrom); 		
//		Candle candle2 = new Candle(listedCompany, new BigDecimal("200"), new BigDecimal("100"), 
//				new BigDecimal("120"), new BigDecimal("180"), dayFrom);
//		
//		List<Candle> candles = new ArrayList<Candle>();
//		candles.add(candle1);
//		candles.add(candle2);
//				
//		Formation formation = new Formation(dayFrom, dayTo, FormationType.BEARISH_KICKER_PATTERN, candles);
//		
//		List<Formation> formations = new ArrayList<Formation>();		
//		formations.add(formation);
//		
//		CandlestickChart chart = new CandlestickChart();
//		chart.setListedCompany(listedCompany);
//		chart.setStartDay(new Date(115, 4, 10));
//		chart.setEndDay(new Date(115, 4, 15));
//		chart.setCandles(candles);
//		chart.setFormations(new ArrayList<Formation>());
//		return chart;
		
		
		List<Candle> candles = candleService.getCandlesFor(listedCompany, dayFrom, dayTo);
                
                
                if(candles.size() > 0) {
                    Candle c = candles.get(0);
                    dayFrom = c.getDay();
                } 
                
                if(dayFrom.compareTo(dayTo)==-1)
                {
                    List<Candle> missingCandles = null;
                    
                    missingCandles = 
                            analysisService.getCandles(
                                    quoteService.getStockQuotes(listedCompany, 
                                                                dayFrom, 
                                                                dayTo));
                    candleService.store(candles);
                    
                    if( missingCandles.size() > 0 ){
                        candleService.store(candles);
                        candles.addAll(missingCandles);
                    }
                    
                }
            
                if( candles.size() < 1 ) {
                    throw new ChartUnavailable("Not enough data available");
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
		quoteService.storeStockQuotes(shareData);
	}

        public List<StockQuote> getStockQuotes(ListedCompany listedCompany, Date from, Date to)
        {
            return quoteService.getStockQuotes(listedCompany, from, to);
        }
        public List<StockQuote> getLast(ListedCompany listedCompany,int n)
        {
            return quoteService.getStockQuotes(listedCompany, n);
        }

    @Override
    public List<ListedCompany> getCompanies() {
        return quoteService.getCompanies();
    }
        
        
        


}
