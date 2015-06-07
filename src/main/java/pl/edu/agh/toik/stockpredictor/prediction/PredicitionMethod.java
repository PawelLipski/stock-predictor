package pl.edu.agh.toik.stockpredictor.prediction;

import pl.edu.agh.toik.stockpredictor.prediction.algorithm.IPredictionAlgorithm;
import pl.edu.agh.toik.stockpredictor.prediction.algorithm.impl.ComplexPredictionAlgorithm;
import pl.edu.agh.toik.stockpredictor.prediction.algorithm.impl.SimplePredictionAlgorithm;

public enum PredicitionMethod {
    PM1("Prymitywna metoda polegająca na porównaniu dwóch ostatnich kursów akcji.", new SimplePredictionAlgorithm()),
    PM2("Złożona metoda", new ComplexPredictionAlgorithm());

    private String description;
    private IPredictionAlgorithm algorithm;

    PredicitionMethod(String description, IPredictionAlgorithm predictionAlgorithm) {
        this.description = description;
        this.algorithm = predictionAlgorithm;
    }

    public String getDescription() {
        return this.description;
    }

    public IPredictionAlgorithm getAlgorithm() {
        return this.algorithm;
    }
}
