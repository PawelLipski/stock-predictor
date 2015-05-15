package pl.edu.agh.toik.stockpredictor.technicalanalysis.domain;

import java.util.Date;
import java.util.List;

/**
 * Class represents formation which was adjusted to candles in particular period.
 *
 * Created by Krzysztof Kicinger on 2015-05-10.
 */
public class Formation {

    /**
     * Day which formation starts in
     */
    private final Date startDate;
    /**
     * Day which formation ends in
     */
    private final Date endDate;
    /**
     * Type of formation
     */
    private final FormationType type;
    /**
     * List of candles for which particular formation is adjusted
     */
    private final List<Candle> candles;

    public Formation(Date startDate, Date endDate, FormationType type, List<Candle> candles) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.candles = candles;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public FormationType getType() {
        return type;
    }

    public List<Candle> getCandles() {
        return candles;
    }

}
