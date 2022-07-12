/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller;

import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.admin.constance.BitacoraCfg;
import com.arrebol.apc.model.admin.constance.GoalCfg;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.system.logs.Bitacora;
import com.arrebol.apc.repository.GenericEntityRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Oscar Armando Vargas Cardenas <oscar.vargas@arrebol.com.mx>
 */
public class BitacoraController implements Serializable{
    
        /**
     *
     * Searching all bitacora by office.
     *
     * @param officeId
     * @return
     */
    public List<Bitacora> fillBitacoraDatatable(String officeId) {
        logger.debug("fillBitacoraDatatable");
        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(BitacoraCfg.FIELD_OFFICE, new Office(officeId)));

        return genericEntityRepository.xmlQueryAPCEntities(Bitacora.class, BitacoraCfg.QUERY_FIND_ALL_BITACORA_BY_OFFICE, parameters);
    }
    
    public List<Bitacora> fillBitacoraDatatableByDate(String officeId, Date startDate, Date endDate) {
        logger.debug("fillBitacoraDatatableByDate");
        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(BitacoraCfg.FIELD_OFFICE, new Office(officeId)));
        parameters.add(new ModelParameter(GoalCfg.PARAM_START_DATE, startDate));
        parameters.add(new ModelParameter(GoalCfg.PARAM_END_DATE, endDate));

        return genericEntityRepository.xmlQueryAPCEntities(Bitacora.class, BitacoraCfg.QUERY_FIND_ALL_BITACORA_BY_OFFICE_DATE, parameters);
    }
    
     /**
     *
     * @param bitacora
     * @return boolean
     */
    public boolean saveBitacora(Bitacora bitacora) {
        logger.debug("saveBitacora");
        boolean success = genericEntityRepository.insertAPCEntity(bitacora);

        return success;
    
    }   
    
    final Logger logger = LogManager.getLogger(BitacoraController.class);
    private final GenericEntityRepository genericEntityRepository;

    public BitacoraController() {
        this.genericEntityRepository = new GenericEntityRepository();
    }
}
