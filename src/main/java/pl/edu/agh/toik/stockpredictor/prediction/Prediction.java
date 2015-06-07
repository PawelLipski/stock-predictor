package pl.edu.agh.toik.stockpredictor.prediction;

import java.math.BigDecimal;
import java.util.List;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

public class Prediction {
	
	private List<StockQuote /*???*/ > stocks;
	private PredicitionMethod predictionMethod;
    private boolean /*???*/ upDown;
    private BigDecimal prediction;
    private double probability; // optional: negative when the method doesn't provide probability for the result
	
    public List<StockQuote> getStocks() {
		return stocks;
	}
	public void setStocks(List<StockQuote> stocks) {
		this.stocks = stocks;
	}
	
	public PredicitionMethod getPredictionMethod() {
		return predictionMethod;
	}
	public void setPredictionMethod(PredicitionMethod predictionMethod) {
		this.predictionMethod = predictionMethod;
	}
	
	public boolean isUpDown() {
		return upDown;
	}
	public void setUpDown(boolean upDown) {
		this.upDown = upDown;
	}
	
	public BigDecimal getPrediction() {
		return prediction;
	}
	public void setPrediction(BigDecimal prediction) {
		this.prediction = prediction;
	}
	
	public double getProbability() {
		return probability;
	}
	public void setProbability(double probability) {
		this.probability = probability;
	}	
}
