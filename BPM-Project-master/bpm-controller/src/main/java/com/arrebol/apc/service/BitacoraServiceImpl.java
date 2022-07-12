/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.service;

import com.arrebol.apc.model.system.logs.Bitacora;
import com.arrebol.apc.repository.LazyDataModelDAORepository;
import javax.enterprise.context.RequestScoped;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
@RequestScoped
public class BitacoraServiceImpl extends LazyDataModelDAORepository<Bitacora> implements BitacoraService {

    @Override
    public boolean saveBitacora(Bitacora bitacora) {
        logger.debug("saveBitacora");
        return save(bitacora);
    }

    final Logger logger = LogManager.getLogger(getClass());
}
