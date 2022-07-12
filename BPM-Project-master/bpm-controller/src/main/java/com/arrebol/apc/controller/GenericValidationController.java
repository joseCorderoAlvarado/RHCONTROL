/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller;

import com.arrebol.apc.controller.util.ConnectionManager;
import java.io.Serializable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Oscar Armando Vargas Cardenas <oscar.vargas@arrebol.com.mx>
 */
public class GenericValidationController extends ConnectionManager implements Serializable{

    final Logger logger = LogManager.getLogger(GenericValidationController.class);
    private static final long serialVersionUID = -6732331411572526429L;
    
}
