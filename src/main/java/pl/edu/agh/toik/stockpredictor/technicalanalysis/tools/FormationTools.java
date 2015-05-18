package pl.edu.agh.toik.stockpredictor.technicalanalysis.tools;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.CandleColor;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.CandleType;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Formation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.FormationType;

import static pl.edu.agh.toik.stockpredictor.technicalanalysis.tools.CandleTools.isGreaterThan;
import static pl.edu.agh.toik.stockpredictor.technicalanalysis.tools.CandleTools.isLowerThan;

/**
 * Created by Krzysztof Kicinger on 2015-05-10.
 */
public class FormationTools {

public static List<Formation> getFormations(List<Candle> candles) {
        List<Formation> singleCandleFormations = getSingleCandlesFormations(candles);
        List<Formation> twoCandlesFormations = getTwoCandlesFormations(candles);
        List<Formation> fourCandlesFormations = getFourCandlesFormations(candles);
        LinkedList<Formation> formations = new LinkedList<Formation>();
        formations.addAll(singleCandleFormations);
        formations.addAll(twoCandlesFormations);
        formations.addAll(fourCandlesFormations);
        return formations;
    }

    private static List<Formation> getSingleCandlesFormations(
            List<Candle> candles) {
        LinkedList<Formation> formations = new LinkedList<Formation>();
        for (int i = 0; i < candles.size(); i++) {
            Candle candle = candles.get(i);
            if (isHossaReversion(candle)) {

                formations.add(new Formation(candle.getDate(),
                        candle.getDate(), FormationType.HOSSA_REVERSION,
                        new LinkedList<Candle>(Arrays.asList(candle))));
            } else if (isBessaReversion(candle)) {
                formations.add(new Formation(candle.getDate(),
                        candle.getDate(), FormationType.BESSA_REVERSION,
                        new LinkedList<Candle>(Arrays.asList(candle))));
            }
        }
        return formations;
    }

    private static List<Formation> getTwoCandlesFormations(List<Candle> candles) {
        LinkedList<Formation> formations = new LinkedList<Formation>();
        if (candles.size() < 2) {
            return formations;
        }
        ArrayList<Candle> current = new ArrayList<Candle>();
        current.add(candles.get(0));
        current.add(candles.get(1));
        for (int i = 2; i < candles.size() + 1; i++) {
            Candle previousCandle = current.get(0);
            Candle nextCandle = current.get(1);
            if (isEngulfingHossaPattern(previousCandle, nextCandle)) {
                formations.add(new Formation(previousCandle.getDate(),
                        nextCandle.getDate(),
                        FormationType.ENGULFING_HOSSA_PATTERN,
                        new LinkedList<Candle>(Arrays.asList(previousCandle,
                                nextCandle))));
            } else if (isEngulfingBessaPattern(previousCandle, nextCandle)) {
                formations.add(new Formation(previousCandle.getDate(),
                        nextCandle.getDate(),
                        FormationType.ENGULFING_BESSA_PATTERN,
                        new LinkedList<Candle>(Arrays.asList(previousCandle,
                                nextCandle))));
            }
            if (i < candles.size()) {
                current.set(0, current.get(1));
                current.set(1, candles.get(i));
            }
        }
        return formations;
    }

    private static List<Formation> getFourCandlesFormations(List<Candle> candles) {
        LinkedList<Formation> formations = new LinkedList<Formation>();
        if (candles.size() < 4) {
            return formations;
        }
        ArrayList<Candle> current = new ArrayList<Candle>();
        current.add(candles.get(0));
        current.add(candles.get(1));
        current.add(candles.get(2));
        current.add(candles.get(3));
        for (int i = 4; i < candles.size() + 1; i++) {
            Candle candle1 = current.get(0);
            Candle candle2 = current.get(1);
            Candle candle3 = current.get(2);
            Candle candle4 = current.get(3);
            if (isBullishKickerPattern(candle1, candle2, candle3, candle4)) {
                formations.add(new Formation(candle1.getDate(), candle4
                        .getDate(), FormationType.BULLISH_KICKER_PATTERN,
                        new LinkedList<Candle>(Arrays.asList(candle1, candle2,
                                candle3, candle4))));
            } else if (isBearishKickerPattern(candle1, candle2, candle3, candle4)) {
                formations.add(new Formation(candle1.getDate(), candle4
                        .getDate(), FormationType.BEARISH_KICKER_PATTERN,
                        new LinkedList<Candle>(Arrays.asList(candle1, candle2,
                                candle3, candle4))));
            }
            if (i < candles.size()) {
                current.set(0, current.get(1));
                current.set(1, current.get(2));
                current.set(2, current.get(3));
                current.set(3, candles.get(i));
            }
        }
        return formations;
    }

    private static boolean isHossaReversion(Candle candle) {
        return (candle.getType() == CandleType.LONG_CORPS && candle.getColor() == CandleColor.WHITE) ||
                candle.getType() == CandleType.HAMMER;
    }

    private static boolean isBessaReversion(Candle candle) {
        return (candle.getType() == CandleType.LONG_CORPS && candle.getColor() == CandleColor.BLACK) ||
                candle.getType() == CandleType.SHOOTING_STAR;
    }

    private static boolean isEngulfingBessaPattern(Candle previousCandle, Candle nextCandle) {
        return  previousCandle.getColor() == CandleColor.WHITE && nextCandle.getColor() == CandleColor.BLACK &&
                isLowerThan(previousCandle.getClosingPrice(), nextCandle.getOpeningPrice()) &&
                isGreaterThan(previousCandle.getOpeningPrice(), nextCandle.getClosingPrice());
    }

    private static boolean isEngulfingHossaPattern(Candle previousCandle, Candle nextCandle) {
        return  previousCandle.getColor() == CandleColor.BLACK && nextCandle.getColor() == CandleColor.WHITE &&
                isLowerThan(previousCandle.getOpeningPrice(), nextCandle.getClosingPrice()) &&
                isGreaterThan(previousCandle.getClosingPrice(), nextCandle.getOpeningPrice());
    }
    
    private static boolean isBullishKickerPattern(Candle candle1, Candle candle2, Candle candle3, Candle candle4) {
    	return	candle1.getColor() == CandleColor.BLACK && candle2.getColor() == CandleColor.BLACK &&
    			candle3.getColor() == CandleColor.BLACK && candle4.getColor() == CandleColor.WHITE &&
    			isGreaterThan(candle1.getClosingPrice(), candle2.getOpeningPrice()) &&
    			isGreaterThan(candle2.getClosingPrice(), candle3.getOpeningPrice()) &&
    			isLowerThan(candle3.getClosingPrice(), candle4.getOpeningPrice()) &&
    			isLowerThan(candle3.getOpeningPrice(), candle4.getClosingPrice()) &&
    			isGreaterThan(candle1.getOpeningPrice(), candle4.getClosingPrice());
    }
    
    private static boolean isBearishKickerPattern(Candle candle1, Candle candle2, Candle candle3, Candle candle4) {
    	return	candle1.getColor() == CandleColor.WHITE && candle2.getColor() == CandleColor.WHITE &&
    			candle3.getColor() == CandleColor.WHITE && candle4.getColor() == CandleColor.BLACK &&
    			isGreaterThan(candle1.getOpeningPrice(), candle2.getClosingPrice()) &&
    			isGreaterThan(candle2.getOpeningPrice(), candle3.getClosingPrice()) &&
    			isLowerThan(candle3.getOpeningPrice(), candle4.getClosingPrice()) &&
    			isLowerThan(candle3.getClosingPrice(), candle4.getOpeningPrice()) &&
    			isGreaterThan(candle1.getClosingPrice(), candle4.getOpeningPrice());
    }

}
