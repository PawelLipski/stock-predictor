package pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Krzysztof Kicinger on 2015-05-14.
 */
public class ShareData {

    private ListedCompany listedCompany;
    private Date date;
    private BigDecimal value;

    public ShareData() {
    }

    public ShareData(ListedCompany listedCompany, Date date, BigDecimal value) {
        this.listedCompany = listedCompany;
        this.date = date;
        this.value = value;
    }

    public ListedCompany getListedCompany() {
        return listedCompany;
    }

    public void setListedCompany(ListedCompany listedCompany) {
        this.listedCompany = listedCompany;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
