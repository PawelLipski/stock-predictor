package pl.edu.agh.toik.stockpredictor.technicalanalysis.tools;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.CandleColor;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.CandleType;

import java.math.BigDecimal;

/**
 * Created by Krzysztof Kicinger on 2015-05-10.
 */
public class CandleTools {

    private static final int SCALE = 4;
    private static final BigDecimal TWO = new BigDecimal("2.0");
    private static final BigDecimal LONG_CORPS_RATIO = new BigDecimal("0.7");
    private static final BigDecimal REEL_RATIO = new BigDecimal("0.3");

    public static CandleColor getCandleColor(Candle candle) {
        return getCandleColor(candle.getOpeningPrice(), candle.getClosingPrice());
    }

    public static CandleColor getCandleColor(BigDecimal openingPrice, BigDecimal closingPrice) {
        if(isGreaterThan(openingPrice, closingPrice)) {
            return CandleColor.BLACK;
        } else if(isLowerThan(openingPrice, closingPrice)) {
            return CandleColor.WHITE;
        }
        return CandleColor.NOT_DEFINED;
    }

    public static CandleType getCandleType(Candle candle) {
        return getCandleType(candle.getOpeningPrice(), candle.getClosingPrice(), candle.getMaxPrice(), candle.getMinPrice());
    }

    public static CandleType getCandleType(BigDecimal openingPrice, BigDecimal closingPrice, BigDecimal maxPrice, BigDecimal minPrice) {
        if(isMarubozu(openingPrice, closingPrice, maxPrice, minPrice)) {
            return CandleType.MARUBOZU;
        } else if(isDoji(openingPrice, closingPrice)) {
            return CandleType.DOJI;
        } else if(isShootingStar(openingPrice, closingPrice, maxPrice, minPrice)) {
            return CandleType.SHOOTING_STAR;
        } else if(isHammer(openingPrice, closingPrice, maxPrice, minPrice)) {
            return CandleType.HAMMER;
        } else if(isLongCorps(openingPrice, closingPrice, maxPrice, minPrice)) {
            return CandleType.LONG_CORPS;
        } else if(isReelCorps(openingPrice, closingPrice, maxPrice, minPrice)) {
            return CandleType.REEL;
        } else if(isLowerShadow(openingPrice, closingPrice, maxPrice, minPrice)) {
            return CandleType.LOWER_SHADOW;
        } else if(isUpperShadow(openingPrice, closingPrice, maxPrice, minPrice)) {
            return CandleType.UPPER_SHADOW;
        }
        return CandleType.NOT_DEFINED;
    }

    private static boolean isMarubozu(BigDecimal openingPrice, BigDecimal closingPrice, BigDecimal maxPrice, BigDecimal minPrice) {
        return (isEqual(openingPrice, maxPrice) && isEqual(closingPrice, minPrice)) ||
               (isEqual(closingPrice, maxPrice) && isEqual(openingPrice, minPrice));
    }

    private static boolean isDoji(BigDecimal openingPrice, BigDecimal closingPrice) {
        return isEqual(openingPrice, closingPrice);
    }

    private static boolean isShootingStar(BigDecimal openingPrice, BigDecimal closingPrice, BigDecimal maxPrice, BigDecimal minPrice) {
        return  isEqual(closingPrice, minPrice) &&
                !isEqual(openingPrice, maxPrice) &&
                isGreaterThan(openingPrice, closingPrice);
    }

    private static boolean isHammer(BigDecimal openingPrice, BigDecimal closingPrice, BigDecimal maxPrice, BigDecimal minPrice) {
        return  isEqual(closingPrice, maxPrice) &&
                !isEqual(openingPrice, minPrice) &&
                isLowerThan(openingPrice, closingPrice);
    }

    private static boolean isLongCorps(BigDecimal openingPrice, BigDecimal closingPrice, BigDecimal maxPrice, BigDecimal minPrice) {
        BigDecimal corpsHeight = openingPrice.subtract(closingPrice).abs();
        BigDecimal candleHeight = maxPrice.subtract(minPrice).abs();
        BigDecimal divided = new BigDecimal(corpsHeight.doubleValue()/candleHeight.doubleValue());
        return  isGreaterThan(divided, LONG_CORPS_RATIO) &&
                !isEqual(openingPrice, minPrice) && !isEqual(openingPrice, maxPrice) &&
                !isEqual(closingPrice, minPrice) && !isEqual(closingPrice, maxPrice);
    }

    private static boolean isReelCorps(BigDecimal openingPrice, BigDecimal closingPrice, BigDecimal maxPrice, BigDecimal minPrice) {
        BigDecimal corpsHeight = openingPrice.subtract(closingPrice).abs();
        BigDecimal candleHeight = maxPrice.subtract(minPrice).abs();
        BigDecimal middlePoint = getMiddlePointPrice(maxPrice, minPrice);
        BigDecimal divied = new BigDecimal(corpsHeight.doubleValue()/candleHeight.doubleValue());
        return isLowerThan(divied, REEL_RATIO) &&
                ((isGreaterThan(openingPrice, middlePoint) && isLowerThan(closingPrice, middlePoint)) ||
                 (isGreaterThan(closingPrice, middlePoint) && isLowerThan(openingPrice, middlePoint)));
    }

    private static boolean isLowerShadow(BigDecimal openingPrice, BigDecimal closingPrice, BigDecimal maxPrice, BigDecimal minPrice) {
        return  isGreaterThan(maxPrice, closingPrice) &&
                isGreaterThan(closingPrice, openingPrice) &&
                isGreaterThan(openingPrice, getMiddlePointPrice(maxPrice, minPrice));
    }

    private static boolean isUpperShadow(BigDecimal openingPrice, BigDecimal closingPrice, BigDecimal maxPrice, BigDecimal minPrice) {
        return  isLowerThan(minPrice, closingPrice) &&
                isLowerThan(closingPrice, openingPrice) &&
                isLowerThan(openingPrice, getMiddlePointPrice(maxPrice, minPrice));
    }

    public static boolean isEqual(BigDecimal firstArg, BigDecimal secondArg) {
        firstArg.setScale(SCALE, BigDecimal.ROUND_HALF_UP);
        firstArg.setScale(SCALE, BigDecimal.ROUND_HALF_UP);
        return firstArg.equals(secondArg);
    }

    public static boolean isGreaterThan(BigDecimal firstArg, BigDecimal secondArg) {
        firstArg.setScale(SCALE, BigDecimal.ROUND_HALF_UP);
        firstArg.setScale(SCALE, BigDecimal.ROUND_HALF_UP);
        return firstArg.compareTo(secondArg) > 0;
    }

    public static boolean isLowerThan(BigDecimal firstArg, BigDecimal secondArg) {
        firstArg.setScale(SCALE, BigDecimal.ROUND_HALF_UP);
        firstArg.setScale(SCALE, BigDecimal.ROUND_HALF_UP);
        return firstArg.compareTo(secondArg) < 0;
    }

    private static BigDecimal getMiddlePointPrice(BigDecimal maxPrice, BigDecimal minPrice) {
        return maxPrice.add(minPrice).abs().divide(TWO);
    }

}
