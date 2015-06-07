/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.agh.toik.stockpredictor.core.dao.impl.hbm;

import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import pl.edu.agh.toik.stockpredictor.core.dao.CompanyDAO;
import pl.edu.agh.toik.stockpredictor.core.persistence.model.CompanyEntity;

/**
 *
 * @author uriel
 */
public class CompanyDAOImpl implements CompanyDAO  {

    private final SessionFactory factory;

    public CompanyDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }
   
    @Override
    public CompanyEntity findCompany(String shortName) {
        List<CompanyEntity> cel =
        factory.getCurrentSession()
               .createCriteria(CompanyEntity.class)
               .add(Restrictions.eq("shortName", shortName))
               .list();
        return cel.size() > 0 ? cel.get(0) : null;
    }

    @Override
    public void store(CompanyEntity ce) {
       factory.getCurrentSession().persist(ce);
    }
}
