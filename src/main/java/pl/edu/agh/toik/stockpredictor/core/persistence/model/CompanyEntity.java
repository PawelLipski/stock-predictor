/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core.persistence.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import pl.edu.agh.toik.stockpredictor.technicalanalysis.domain.ListedCompany;


/**
 *
 * @author Dariusz Hudziak 2015-05-18
 */
@Entity
@Table(name = "TBL_COMPANY")
public class CompanyEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "FLD__ID")
    private int id;
    @Column(name = "FLD_NAME",length = 50,nullable = true)
    private String name;
    @Column(name = "FLD_SHORT_NAME",length = 10,nullable = false)
    private String shortName;

    public CompanyEntity() {
    }
    
    public CompanyEntity(ListedCompany company) {
        setName(company.getName());
        setShortName(company.getShortName());
    }
    
    
    public CompanyEntity(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    
    public ListedCompany toListedCompany(){
        return new ListedCompany(getName(),getShortName());
    }
}
