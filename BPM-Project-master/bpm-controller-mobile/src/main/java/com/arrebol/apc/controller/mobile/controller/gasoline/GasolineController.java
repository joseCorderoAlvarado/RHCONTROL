/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.controller.gasoline;

import com.arrebol.apc.controller.mobile.repository.gasoline.GasolineRepository;
import com.arrebol.apc.model.gasoline.Gasoline;
import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class GasolineController implements Serializable {

    /**
     *
     * @param gasoline
     * @return
     * @throws Exception
     */
    public boolean saveNewGasolinePayment(Gasoline gasoline) throws Exception {
        try {
            GasolineRepository gasolineRepository = new GasolineRepository();

            return gasolineRepository.saveNewGasolinePayment(gasoline);
        } catch (Exception e) {
            logger.error("addNewGasolineEntry", e);
            throw e;
        }
    }

    private static final long serialVersionUID = -5280895557294295000L;
    final Logger logger = LogManager.getLogger(getClass());
}
