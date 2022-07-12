/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.repository;

import com.arrebol.apc.controller.util.HibernateUtil;
import com.arrebol.apc.model.ModelParameter;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 * @param <T>
 */
public abstract class AutoCompleteDAORepository<T> extends AbstractRepository {

    /**
     *
     * @param clazz
     * @param hbmQuery
     * @param parameters
     * @return
     */
    public List<T> searchLike(Class clazz, String hbmQuery, List<ModelParameter> parameters) {
        List<T> results;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hbmQuery, clazz);
            if (null != parameters) {
                parameters.forEach(param -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });
            }
            query.setMaxResults(10);
            results = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            logger.error("searchLike from class: " + clazz.getName(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            results = new ArrayList<>();
        }
        return results;
    }
}
