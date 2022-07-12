/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.system.office;

import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.constance.OfficeCfg;
import com.arrebol.apc.model.enums.OfficeStatus;
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
public class OfficeController implements Serializable{
    
    /**
     *
     * Searching all roles.
     *
     * @return
     */
    public List<Office> fillOfficeDatatable() {

        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(OfficeCfg.FIELD_OFFICE_STATUS, OfficeStatus.ENEBLED));

        return genericEntityRepository.xmlQueryAPCEntities(Office.class, OfficeCfg.QUERY_FIND_ALL_OFFICES_ACTIVES, parameters);
    }
    
    /**
     *
     * @param office
     * @return boolean
     */
    public boolean saveOffice(Office office) {
        logger.debug("saveOffice");
        boolean success = genericEntityRepository.insertAPCEntity(office);

        return success;
    }
    
    /**
     *
     * @param office
     * @return boolean
     */
    public boolean updateByOfficeId(Office office) {
        logger.debug("updateByOfficeId");

        return genericEntityRepository.updateAPCEntity(office);
    }
    
    /**
     *
     * @param status
     * @param officeIdToUpdate
     * @return
     */
    public boolean updateOfficeByStatus(OfficeStatus status, String officeIdToUpdate, String lastUpdatedBy) {
        logger.debug("updateOfficeByStatus");

        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(OfficeCfg.FIELD_OFFICE_STATUS, status));
        parameters.add(new ModelParameter(OfficeCfg.FIELD_LAST_UPDATED_BY, lastUpdatedBy));
        parameters.add(new ModelParameter(OfficeCfg.FIELD_LAST_UPDATED_ON, new Date()));
        parameters.add(new ModelParameter(OfficeCfg.FIELD_ID, officeIdToUpdate));

        return genericEntityRepository.xmlUpdateOrDeleteAPCEntity(OfficeCfg.UPDATE_OFFICES_BY_ID, parameters);
    }
    
    final Logger logger = LogManager.getLogger(OfficeController.class);
    private final GenericEntityRepository genericEntityRepository;

    public OfficeController() {
        this.genericEntityRepository = new GenericEntityRepository();
    }
}
