/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.dashboard;

import com.arrebol.apc.repository.GenericEntityRepository;
import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Oscar Armando Vargas Cardenas <oscar.vargas@arrebol.com.mx>
 */
public class DashboardController implements Serializable {
    final Logger logger = LogManager.getLogger(DashboardController.class);
    private final GenericEntityRepository genericEntityRepository;

    public DashboardController() {
        this.genericEntityRepository = new GenericEntityRepository();
    }

}
