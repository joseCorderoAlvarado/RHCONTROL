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
import com.arrebol.apc.model.loan.LoanFeeNotification;
import java.io.Serializable;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoanFeeNotificationRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param notification
     * @return
     * @throws Exception
     */
    public boolean saveLoanFeeNotification(LoanFeeNotification notification) throws Exception {
        logger.debug("saveLoanFeeNotification");

        try {
            return save(notification);
        } catch (Exception e) {
            logger.error("saveLoanFeeNotification", e);
            throw e;
        }
    }

    /**
     *
     * @param clazz
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public Long countNotificationByLoanId(Class clazz, String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("countNotificationByLoanId");

        try {
            return (Long) createNamedQueryUniqueResult(clazz, xmlQuery, parameters);
        } catch (Exception e) {
            logger.error("countNotificationByLoanId", e);
            throw e;
        }
    }

    private static final long serialVersionUID = -7172367255017070335L;
    final Logger logger = LogManager.getLogger(LoanFeeNotificationRepository.class);
}
