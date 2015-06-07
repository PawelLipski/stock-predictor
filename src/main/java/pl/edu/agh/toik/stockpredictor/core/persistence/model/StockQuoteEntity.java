/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core.persistence.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.serializer.StockQuote;

/**
 *
 * @author uriel
 */
@Entity
@Table(name = "TBL_STOCK_QUOTE")
public class StockQuoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="FLD__ID")
    private int id;
 
    @ManyToOne
    private CompanyEntity company;
    @Column(name="FLD_DATE_TIME",nullable = false)
    private Date dateTime;
    @Column(name="FLD_VALUE",nullable = false)
    private BigDecimal value;


    public StockQuoteEntity() {
    }

    public StockQuoteEntity(CompanyEntity company, Date dateTime, BigDecimal value) {
        this.company = company;
        this.dateTime = dateTime;
        this.value = value;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
    
    public StockQuote toStockQuote(){
        return new StockQuote(
        new ListedCompany(company.getName(),company.getShortName()),
                          getDateTime(),
                          getValue());
    }
}
