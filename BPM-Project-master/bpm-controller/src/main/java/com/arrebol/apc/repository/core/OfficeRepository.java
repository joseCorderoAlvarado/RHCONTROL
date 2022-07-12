/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.repository.core;

import com.arrebol.apc.controller.util.HibernateUtil;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.constance.OfficeCfg;
import com.arrebol.apc.model.enums.OfficeStatus;
import com.arrebol.apc.repository.AbstractRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
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
public class OfficeRepository extends AbstractRepository implements Serializable {

    public List<Office> getAllActiveOffice() {
        logger.debug("getAllActiveOffice");

        List<Office> offices = null;
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            transaction = session.beginTransaction();

            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Office> query = builder.createQuery(Office.class);
            Root<Office> root = query.from(Office.class);
            Path<String> idPath = root.get(OfficeCfg.FIELD_ID);
            Path<String> permissionPath = root.get(OfficeCfg.FIELD_PERMISSION);

            query.select(builder.construct(Office.class, idPath, permissionPath));
            query.where(builder.equal(root.get(OfficeCfg.FIELD_OFFICE_STATUS), OfficeStatus.ENEBLED));
            query.orderBy(builder.asc(root.get(OfficeCfg.FIELD_PERMISSION)));

            offices = session.createQuery(query).getResultList();

            transaction.commit();
            logger.debug("Total of office: " + offices.size());
        } catch (HibernateException e) {
            logger.error("getAllActiveOffice Hibernate", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } catch (Exception e) {
            logger.error("Method getAllActiveOffice() ", e);
            if (null != transaction) {
                transaction.rollback();
            }
            throw e;
        } finally {
            if (null != session) {
                session.close();
            }
        }
        return offices;
    }

    /**
     *
     * @param xmlQueryTuple
     * @param parameters
     * @return
     */
    public List<Office> findAllOfficesByUser(String xmlQueryTuple, List<ModelParameter> parameters) {
        logger.debug("findAllOfficesByUser");

        List<Office> offices = new ArrayList<>();
        try {
            List<Tuple> tupleLst = xmlQueryTuple(xmlQueryTuple, parameters);

            tupleLst.forEach((tuple) -> {
                offices.add(new Office((String) tuple.get(0), (String) tuple.get(1)));
            });

            logger.debug("User has office " + offices.size());
        } catch (HibernateException e) {
            logger.error("findAllOfficeByUser hibernate", e);
        } catch (Exception e) {
            logger.error("Method findAllOfficeByUser() ", e);
        }
        return offices;
    }

    private static final long serialVersionUID = 1712329089116981172L;
    final Logger logger = LogManager.getLogger(OfficeRepository.class);
}
