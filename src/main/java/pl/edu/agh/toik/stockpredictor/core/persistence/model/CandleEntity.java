/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core.persistence.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.Candle;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;


/**
 *
 * @author uriel
 */
@Entity
@Table(name = "TBL_CANDLE")
public class CandleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="FLD__ID")
    private int id;
    
    @ManyToOne
    @JoinColumn(name="FKF_COMPANY",nullable = false)
    private CompanyEntity company;
    
    @Column(name="FLD_MAX_PRICE",nullable = true)
    private BigDecimal maxPrice;
    
    @Column(name="FLD_MAIN_PRICE",nullable = true)
    private BigDecimal minPrice;
    
    @Column(name="FLD_OPENING_PRICE",nullable = true)
    private BigDecimal openingPrice;
    
    @Column(name="FLD_CLOSING_PRICE",nullable = true)
    private BigDecimal closingPrice;
    
    @Column(name="FLD_DAY")
    private Date day;
    
    public CandleEntity() {
    }
    
    public CandleEntity(CompanyEntity company,
                        BigDecimal maxPrice, 
                        BigDecimal minPrice, 
                        BigDecimal openingPrice, 
                        BigDecimal closingPrice, 
                        Date day) {
        this.company = company;
        this.maxPrice = maxPrice;
        this.minPrice = minPrice;
        this.openingPrice = openingPrice;
        this.closingPrice = closingPrice;
        this.day = day;
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

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public BigDecimal getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(BigDecimal openingPrice) {
        this.openingPrice = openingPrice;
    }

    public BigDecimal getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(BigDecimal closingPrice) {
        this.closingPrice = closingPrice;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }
    
    public Candle toCandle() {
        return new Candle(
        new ListedCompany(company.getName(),company.getShortName()),
        getMaxPrice(),
        getMinPrice(),
        getOpeningPrice(),
        getClosingPrice(),
        getDay());
    }
}
