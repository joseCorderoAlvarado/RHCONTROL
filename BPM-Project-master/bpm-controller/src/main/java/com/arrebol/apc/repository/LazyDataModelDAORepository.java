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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 * @param <T>
 */
public abstract class LazyDataModelDAORepository<T> extends AbstractRepository {

    /**
     *
     * @param hbmQuery
     * @param parameters
     * @return
     */
    public long lazyLoadingEntityCount(String hbmQuery, List<ModelParameter> parameters) {
        Long total;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();
            Query query = session.createQuery(hbmQuery, Long.class);
            if (null != parameters) {
                parameters.forEach(param -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });
            }
            total = (Long) query.getSingleResult();
            transaction.commit();
        } catch (Exception e) {
            logger.error("lazyLoadingEntityCount from hbmQuery: " + hbmQuery, e);
            if (null != transaction) {
                transaction.rollback();
            }
            total = 0l;
        }
        return total;
    }

    /**
     *
     * @param clazz
     * @param hbmQuery
     * @param parameters
     * @param first
     * @param pageSize
     * @return
     */
    public List<T> lazyLoadingEntityList(Class clazz, String hbmQuery, List<ModelParameter> parameters, int first, int pageSize) {
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
            query.setFirstResult(first);
            query.setMaxResults(pageSize);

            results = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            logger.error("lazyLoadingEntityList from class: " + clazz.getName(), e);
            if (null != transaction) {
                transaction.rollback();
            }
            results = new ArrayList<>();
        }
        return results;
    }

    final Logger logger = LogManager.getLogger(getClass());
}
