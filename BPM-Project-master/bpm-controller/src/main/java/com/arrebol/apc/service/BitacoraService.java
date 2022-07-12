/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.service;

import com.arrebol.apc.model.system.logs.Bitacora;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public interface BitacoraService {

    /**
     *
     * @param bitacora
     * @return
     */
    boolean saveBitacora(Bitacora bitacora);
}
