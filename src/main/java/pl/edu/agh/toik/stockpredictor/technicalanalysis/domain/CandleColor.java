package pl.edu.agh.toik.stockpredictor.technicalanalysis.domain;

/**
 * Enumeration represents candle stick color.
 *
 * Created by Krzysztof Kicinger on 2015-05-10.
 */
public enum CandleColor {

    /**
     * Candle has black corps when opening price is greater than closing price.
     */
    BLACK,
    /**
     * Candle has white corps when closing price is greater than opening price.
     */
    WHITE,
    /**
     * Candle corps color is not defined when closing and opening price are equal.
     */
    NOT_DEFINED

}
