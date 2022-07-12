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
import com.arrebol.apc.model.views.LoanByUserView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoanByUserViewRepository extends GenericRepository implements Serializable {

    /**
     * 
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception 
     */
    public List<LoanByUserView> findAllLoansByUserId(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("findAllLoansByUserId");
        List<LoanByUserView> results = new ArrayList<>();
        
        try {
            results = createNamedQueryResultList(
                    LoanByUserView.class,
                    xmlQuery,
                    parameters
            );

        } catch (Exception e) {
            logger.error("findAllLoansByUserId", e);
            throw e;
        }

        return results;
    }

    private static final long serialVersionUID = 5669198954766725476L;
    final Logger logger = LogManager.getLogger(LoanByUserViewRepository.class);

}
