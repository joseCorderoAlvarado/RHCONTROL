/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.loan;

import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.loan.LoanDetails;
import com.arrebol.apc.model.loan.LoanFeeNotification;
import java.io.Serializable;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class AddAmountRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param notification
     * @param loanDetails
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public boolean saveNewAmount(
            LoanFeeNotification notification,
            LoanDetails loanDetails,
            String xmlQuery,
            List<ModelParameter> parameters) throws Exception {
        logger.debug("saveNewAmount");

        boolean success = false;
        int total = 0;
        
        try {
            openConnection();

            if (null != notification) {
                getSession().save(notification);
                logger.info("Notification saved: " + notification);
            }

            getSession().save(loanDetails);
            logger.info("Loan details saved: " + loanDetails);

            if (null != parameters && !parameters.isEmpty()) {
                Query query = getSession().createNamedQuery(xmlQuery);

                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });

                total = query.executeUpdate();
                logger.info("Query update executed");
            }

            if (total > 0) {
                flushAndClear();
                closeConnection();

                success = true;

                logger.info("Loan successfuly saved");
            } else {
                logger.error("Loan could not be added: " + loanDetails);
                rollback();
            }
        } catch (HibernateException e) {
            logger.error("Save Hibernate", e);
            rollback();
            throw e;
        } catch (Exception e) {
            logger.error("Method save() ", e);
            rollback();
            throw e;
        }
        return success;
    }

    private static final long serialVersionUID = 6466918195145358594L;
    final Logger logger = LogManager.getLogger(AddAmountRepository.class);
}
