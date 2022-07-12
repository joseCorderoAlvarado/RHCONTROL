/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.otherexpense;

import com.arrebol.apc.controller.mobile.repository.admin.OtherExpenseRepository;
import com.arrebol.apc.model.admin.OtherExpense;
import java.io.Serializable;
import java.math.BigDecimal;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class OtherExpenseController implements Serializable {

    /**
     *
     * @param officeId
     * @param userId
     * @param expense
     * @param description
     * @return
     * @throws Exception
     */
    public boolean addOtherExpense(
            String officeId,
            String userId,
            Double expense,
            String description) throws Exception {
        logger.debug("addOtherExpense");
        try {
            OtherExpense otherExpense = new OtherExpense(officeId, userId, new BigDecimal(expense), description);

            return otherExpenseRepository.saveOtherExpense(otherExpense);
        } catch (Exception e) {
            logger.error("addOtherExpense", e);
            throw e;
        }
    }

    private static final long serialVersionUID = -1748077572747436181L;

    final Logger logger = LogManager.getLogger(OtherExpenseController.class);

    private final OtherExpenseRepository otherExpenseRepository;

    public OtherExpenseController() {
        this.otherExpenseRepository = new OtherExpenseRepository();
    }

}
