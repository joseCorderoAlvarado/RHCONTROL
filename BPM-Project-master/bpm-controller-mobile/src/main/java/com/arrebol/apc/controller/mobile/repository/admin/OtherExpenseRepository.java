/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.admin;

import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.model.admin.OtherExpense;
import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class OtherExpenseRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param otherExpense
     * @return
     * @throws Exception
     */
    public boolean saveOtherExpense(OtherExpense otherExpense) throws Exception {
        logger.debug("addOtherExpense");
        try {
            return save(otherExpense);
        } catch (Exception e) {
            logger.error("addOtherExpense", e);
            throw e;
        }
    }

    private static final long serialVersionUID = -8510072805726896888L;
    final Logger logger = LogManager.getLogger(OtherExpenseRepository.class);

}
