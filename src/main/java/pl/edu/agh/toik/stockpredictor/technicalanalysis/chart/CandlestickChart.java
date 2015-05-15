package pl.edu.agh.toik.stockpredictor.technicalanalysis.chart;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Formation;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;

import java.util.Date;
import java.util.List;

/**
 * Class represents a financial chart used to describe price movements based on candles and formations.
 *
 * Created by Krzysztof Kicinger on 2015-05-14.
 */
public class CandlestickChart {

    private ListedCompany listedCompany;
    /**
     * List of candles that particular chart displays
     */
    private List<Candle> candles;
    /**
     * Formations adjusted to candles
     */
    private List<Formation> formations;
    /**
     * First date that chart contains data for (accurate to day)
     */
    private Date startDay;
    /**
     * Last date that chart contains data for (accurate to day)
     */
    private Date endDay;

    public CandlestickChart() {
    }

    public ListedCompany getListedCompany() {
        return listedCompany;
    }

    public void setListedCompany(ListedCompany listedCompany) {
        this.listedCompany = listedCompany;
    }

    public List<Candle> getCandles() {
        return candles;
    }

    public void setCandles(List<Candle> candles) {
        this.candles = candles;
    }

    public List<Formation> getFormations() {
        return formations;
    }

    public void setFormations(List<Formation> formations) {
        this.formations = formations;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

}
