package pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Krzysztof Kicinger on 2015-05-14.
 */
public class StockQuote {

    private ListedCompany listedCompany;
    private Date dateAndTime;
    private BigDecimal value;

    public StockQuote() {
    }

    public StockQuote(ListedCompany listedCompany, Date dateAndTime, BigDecimal value) {
        this.listedCompany = listedCompany;
        this.dateAndTime = dateAndTime;
        this.value = value;
    }

    public ListedCompany getListedCompany() {
        return listedCompany;
    }

    public void setListedCompany(ListedCompany listedCompany) {
        this.listedCompany = listedCompany;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

	public Date getDateAndTime() {
		return dateAndTime;
	}

	public void setDateAndTime(Date dateAndTime) {
		this.dateAndTime = dateAndTime;
	}
}
