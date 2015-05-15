package pl.edu.agh.toik.stockpredictor.core;

import pl.edu.agh.toik.stockpredictor.prediction.Prediction;
import pl.edu.agh.toik.stockpredictor.prediction.PredictionParams;

public interface ICorePredictionService {
	Prediction getPrediction(PredictionParams params);
}
