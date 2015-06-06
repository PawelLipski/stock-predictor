package pl.edu.agh.toik.stockpredictor.technicalanalysis.domain;

import java.util.Date;
import java.util.List;

import org.hamcrest.core.IsInstanceOf;

/**
 * Class represents formation which was adjusted to candles in particular
 * period.
 *
 * Created by Krzysztof Kicinger on 2015-05-10.
 */
public class Formation {

	/**
	 * Day which formation starts in
	 */
	private final Date startDay;
	/**
	 * Day which formation ends in
	 */
	private final Date endDay;
	/**
	 * Type of formation
	 */
	private final FormationType type;
	/**
	 * List of candles for which particular formation is adjusted
	 */
	private final List<Candle> candles;

	public Formation(Date startDay, Date endDay, FormationType type,
			List<Candle> candles) {
		this.startDay = startDay;
		this.endDay = endDay;
		this.type = type;
		this.candles = candles;
	}

	public Date getStartDay() {
		return startDay;
	}

	public Date getEndDay() {
		return endDay;
	}

	public FormationType getType() {
		return type;
	}

	public List<Candle> getCandles() {
		return candles;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Formation)) {
			return false;
		}
		Formation f = (Formation) o;
		if (this.startDay.equals(f.startDay) && this.endDay.equals(f.endDay)
				&& this.type.equals(f.type) && this.candles.equals(f.candles)) {
			return true;
		}
		return false;
	}

}
