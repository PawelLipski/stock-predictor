package pl.edu.agh.toik.stockpredictor.prediction;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;

import java.util.Date;

public class PredictionParams {
	private Date dateFrom;
    private Date dateTo;
    private ListedCompany listedCompany;
    private PredicitionMethod predicitionMethod;

    
    
    
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public ListedCompany getListedCompany() {
        return listedCompany;
    }

    public void setListedCompany(ListedCompany listedCompany) {
        this.listedCompany = listedCompany;
    }

    public PredicitionMethod getPredicitionMethod() {
        return predicitionMethod;
    }

    public void setPredicitionMethod(PredicitionMethod predicitionMethod) {
        this.predicitionMethod = predicitionMethod;
    }
}
