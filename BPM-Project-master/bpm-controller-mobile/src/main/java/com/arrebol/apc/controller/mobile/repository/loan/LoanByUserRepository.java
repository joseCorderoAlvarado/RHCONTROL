/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.loan;

import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.model.loan.LoanByUser;
import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoanByUserRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param loanByUser
     * @return
     * @throws Exception
     */
    public boolean saveLoanByUser(LoanByUser loanByUser) throws Exception {
        logger.debug("saveLoanByUser");
        try {
            return save(loanByUser);
        } catch (Exception e) {
            logger.error("saveLoanByUser", e);
            throw e;
        }
    }

    private static final long serialVersionUID = 7523456759588286540L;
    final Logger logger = LogManager.getLogger(LoanByUserRepository.class);
}
