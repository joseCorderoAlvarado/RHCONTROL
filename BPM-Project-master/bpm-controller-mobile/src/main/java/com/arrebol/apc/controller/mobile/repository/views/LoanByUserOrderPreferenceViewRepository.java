/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.views;

import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.views.LoanByUserOrderPreferenceView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoanByUserOrderPreferenceViewRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public List<LoanByUserOrderPreferenceView> findAllLoanByUserOrderPreference(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("findAllLoanByUserOrderPreference");
        List<LoanByUserOrderPreferenceView> results = new ArrayList<>();

        try {
            results = createNamedQueryResultList(
                    LoanByUserOrderPreferenceView.class,
                    xmlQuery,
                    parameters
            );
        } catch (Exception e) {
            logger.error("findAllLoanByUserOrderPreference", e);
            throw e;
        }

        return results;
    }

    public boolean updateQuery(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("updateQuery");
        try {
            return updateCreateNamedQuery(xmlQuery, parameters);
        } catch (Exception e) {
            logger.error("updateQuery", e);
            throw e;
        }
    }

    private static final long serialVersionUID = -5747654537116880219L;
    final Logger logger = LogManager.getLogger(LoanByUserOrderPreferenceViewRepository.class);
}
