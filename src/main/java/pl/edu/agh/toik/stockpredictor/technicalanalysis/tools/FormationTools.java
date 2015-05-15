package pl.edu.agh.toik.stockpredictor.technicalanalysis.tools;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.CandleColor;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.CandleType;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Formation;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.agh.toik.stockpredictor.technicalanalysis.tools.CandleTools.isGreaterThan;
import static pl.edu.agh.toik.stockpredictor.technicalanalysis.tools.CandleTools.isLowerThan;

/**
 * Created by Krzysztof Kicinger on 2015-05-10.
 */
public class FormationTools {

    public static List<Formation> getFormations(List<Candle> candles) {
        //TODO: IMPLIEMENT IT!!!! :D :D :D
        return new ArrayList<Formation>();
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
