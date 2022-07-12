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
import com.arrebol.apc.model.views.LoanToDeliveryByCertifierView;
import java.io.Serializable;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoanToDeliveryByCertifierRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param clazz
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public List<LoanToDeliveryByCertifierView> findLoansByCertifier(Class clazz, String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("findLoansByCertifier");

        try {
            return createNamedQueryResultList(clazz, xmlQuery, parameters);
        } catch (Exception e) {
            logger.error("findLoansByCertifier", e);
            throw e;
        }
    }

    private static final long serialVersionUID = 1692607159278862158L;
    final Logger logger = LogManager.getLogger(LoanToDeliveryByCertifierRepository.class);
}
