package pl.edu.agh.toik.stockpredictor.datacollection;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;

import javax.annotation.PostConstruct;

import pl.edu.agh.toik.stockpredictor.core.ICoreStockQuoteWriteService;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


public class StockQuoteCollector implements Runnable{

	
	private int updateRate = 5000;
	private CopyOnWriteArrayList<String> selectedServicesNames = new CopyOnWriteArrayList<String>();
	private StockQuoteConstantHolder constantHolder = new StockQuoteConstantHolder();
	private ICoreStockQuoteWriteService writeService;
	
	public StockQuoteCollector(ICoreStockQuoteWriteService writeService){
		this.writeService = writeService;
	}
	
	/**
	 * @param updateRate - time measured in minutes between each retive stockQuote from webservice
	 */
	public StockQuoteCollector(int updateRate, ICoreStockQuoteWriteService writeService){
		this.updateRate = 60 * 1000 * updateRate;
		this.writeService = writeService;
	}
	
	/**
	 * 
	 * @param stockQuoteName - short name of stock quote
	 */
	public void addStockQuote(String stockQuoteName){

		if(constantHolder.availableServiecesNames.contains(stockQuoteName)){
			selectedServicesNames.add(stockQuoteName);
		}else{
			throw new RuntimeException("Quote name " + stockQuoteName +" is not available.");
		}
	}
	
	/**
	 * 
	 * @param stockQuoteName - short name of stock quote
	 */
	public void removeStockQuote(String stockQuoteName){
		
		if(selectedServicesNames.contains(stockQuoteName)){
			selectedServicesNames.remove(stockQuoteName);
		}
	}
	
	/**
	 * 
	 * @param stockQuotesList - list containing shortNames of stockQuotes
	 */
	public void setStockQuoteList(CopyOnWriteArrayList<String> stockQuotesList){
		this.selectedServicesNames = stockQuotesList;
	}
	
	/**
	 * @param updateRate - time measured in minutes between each retive stockQuote from webservice
	 */
	public void setUpdateRate(int updateRate){
		this.updateRate = 60 * 1000 * updateRate;
	}
	
	private void getStockQuotes() 
			throws UnirestException, ParseException {

		List<StockQuote> dataToSend = new LinkedList<StockQuote>();
		
		for(String serviceName : selectedServicesNames){
			HttpResponse<String> response = Unirest.get("http://webservicex.net/StockQuote.asmx/GetQuote?symbol=" + serviceName).asString();
			if(response.getStatus() == 200){
				String body = response.getBody();
				Matcher nameMatcher = constantHolder.namePattern.matcher(body);
				Matcher shortNameMatcher = constantHolder.shortNamePattern.matcher(body);
				Matcher valueMatcher = constantHolder.valuePattern.matcher(body);
				Matcher dateMatcher = constantHolder.datePattern.matcher(body);
				
				nameMatcher.find();
				shortNameMatcher.find();
				valueMatcher.find();
				dateMatcher.find();
				
				Date date = (Date) constantHolder.dateFormatter.parse(dateMatcher.group(1) + " " + dateMatcher.group(2));
				//TODO make a class with instances of every listedCompany so that we don't have to create
				// instance of it every time we refresh the stockquotes
				ListedCompany listedCompany = new ListedCompany(nameMatcher.group(1), shortNameMatcher.group(1));
				
				StockQuote stockQuote = new StockQuote(listedCompany, date, new BigDecimal(valueMatcher.group(1)));
				
				dataToSend.add(stockQuote);
			}else{
				throw new RuntimeException("StockQuoteCollector GET (" + serviceName + ") method on webservice did not succeed.");
			}
		}
		System.out.println(dataToSend.size());
		writeService.storeStockQuotes(dataToSend);
	}
	
	@Override
	public void run() {
		while(true){
			try {
				getStockQuotes();
				Thread.sleep(updateRate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	//TODO this might be removed because it doesn't wait for spring beans to laod 
	// (then InitializingBean interface should be imlpemented
	@PostConstruct
	public void init(){
		//TODO add implementation of ICorStockQuoteWriteService
		//TODO let user select which stock quotes he wants to follow
		StockQuoteCollector collector = new StockQuoteCollector(new ICoreStockQuoteWriteService(){

			@Override
			public void storeStockQuotes(List<StockQuote> shareData) {
			}
			
		});
		collector.addStockQuote("POM");
		collector.addStockQuote("M");
		(new Thread(collector)).start();
	}

}
