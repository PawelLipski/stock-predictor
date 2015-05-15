package pl.edu.agh.toik.stockpredictor.technicalanalysis.domain;

/**
 * Class represents a company whose shares are bought and sold on a stock market.
 *
 * Created by Krzysztof Kicinger on 2015-05-14.
 */
public class ListedCompany {

    private String name;
    private String shortName;

    public ListedCompany() {
    }

    public ListedCompany(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

}
