/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.views;

import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.model.views.TotalCashByCurdateView;
import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class TotalCashByCurdateViewRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param id
     * @return
     * @throws Exception
     */
    public TotalCashByCurdateView findById(String id) throws Exception {
        logger.debug("findById");
        try {
            return (TotalCashByCurdateView) findAPCEntity(TotalCashByCurdateView.class, id);
        } catch (Exception e) {
            logger.error("findById", e);
            throw e;
        }
    }

    private static final long serialVersionUID = 7691052895894112231L;
    final Logger logger = LogManager.getLogger(TotalCashByCurdateViewRepository.class);
}
