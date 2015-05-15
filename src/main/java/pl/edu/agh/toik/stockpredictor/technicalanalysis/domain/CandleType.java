package pl.edu.agh.toik.stockpredictor.technicalanalysis.domain;

/**
 * Enumeration represents candle type based on stock prises.
 *
 * Created by Krzysztof Kicinger on 2015-05-10.
 */
public enum CandleType {

    /**
     * Candle with corps that is greater than particular ratio (0.7);
     */
    LONG_CORPS,
    SHORT_CORPS,
    /**
     * Candle with black corps and opening price lower than middle point price.
     */
    UPPER_SHADOW,
    /**
     * Candle with white corps and opening price higher than middle point price.
     */
    LOWER_SHADOW,
    /**
     * Candle whose opening and closing prices equal to max and min prices;
     */
    MARUBOZU,
    /**
     * Candle whose opening and closing prices are on the opposite side of middle point price (max + min / 2)
     */
    REEL,
    /**
     * Candle with white corps and closing price equal to maximum price.
     */
    HAMMER,
    /**
     * Candle with black corps and closing price equal to minimum price.
     */
    SHOOTING_STAR,
    /**
     * When opening and closing prices are equal.
     */
    DOJI,
    /**
     *
     */
    NOT_DEFINED

}
