/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.service;

import com.arrebol.apc.controller.util.HibernateUtil;
import com.arrebol.apc.model.core.User;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@RequestScoped
public class GenericValidationServiceImpl implements GenericValidationService {

    @Override
    public Date lastStableSmallBoxByDate(User user) {
        logger.info("lastStableSmallBoxByDate");

        Date success = null;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            String query = "SELECT cd.createdOn FROM StableSmallBox cd "
                    + "WHERE DATE(cd.createdOn) <= CURDATE() AND cd.activeStatus = 'ENEBLED' "
                    + "order by cd.createdOn DESC";

            success = (Date) session.createQuery(query).setMaxResults(1)
                    .uniqueResult();

            transaction.commit();

            logger.info("Total of closing day found: ");
        } catch (HibernateException e) {
            logger.error("Can not find stable small box by created on", e);
            if (null != transaction) {
                transaction.rollback();
            }
        } catch (Exception e) {
            logger.error("Method lastStableSmallBoxByDate()", e);
            if (null != transaction) {
                transaction.rollback();
            }
        }

        return success;
    }

    @Override
    public boolean existStableSmallBoxByCreatedOn(Date date) {
        logger.info("existStableSmallBoxByCreatedOn");
        boolean success = true;
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            String query = "SELECT ssb.id FROM APC_STABLE_SMALL_BOX ssb "
                    + "WHERE DATE(ssb.created_on) = DATE(:date) AND ssb.active_status = 'ENEBLED'";

            List<String> records = session.createSQLQuery(query).setParameter("date", date).getResultList();

            if (records.isEmpty()) {
                success = false;
            }

            transaction.commit();
        } catch (Exception e) {
            logger.error("existStableSmallBoxByCreatedOn", e);
            if (null != transaction) {
                transaction.rollback();
            }
        }
        return success;
    }

    final Logger logger = LogManager.getLogger(getClass());

}
