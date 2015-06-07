/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core.dao;

import java.util.List;
import pl.edu.agh.toik.stockpredictor.core.persistence.model.CompanyEntity;

/**
 *
 * @author Dariusz Hudziak
 */
public interface CompanyDAO {
    List<CompanyEntity> getCompanies();
    CompanyEntity findCompany(String shortName);
    void store(CompanyEntity ce);
}
