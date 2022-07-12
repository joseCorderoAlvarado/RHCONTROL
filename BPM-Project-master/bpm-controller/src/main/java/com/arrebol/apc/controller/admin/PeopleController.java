/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.admin;

import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.catalog.People;

import com.arrebol.apc.model.catalog.constance.PeopleCfg;
import com.arrebol.apc.model.enums.ActiveStatus;
import com.arrebol.apc.model.enums.PeopleType;
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
public class PeopleController implements Serializable{
    
    final Logger logger = LogManager.getLogger(PeopleController.class);
    private final GenericEntityRepository genericEntityRepository;
    
    /**
     *
     * @param people
     * @return boolean
     */
    public boolean savePeople(People people) {
        logger.debug("savePeopleController");
        boolean success = genericEntityRepository.insertAPCEntity(people);

        return success;
    }
    
    /**
     *
     * @param people
     * @return boolean
     */
    public boolean updateByPeopleId(People people) {
        logger.debug("updateByPeopleId");

        return genericEntityRepository.updateAPCEntity(people);
    }
    
    /**
     *
     * @param status
     * @param peopleIdToUpdate
     * @param lastUpdatedBy
     * @return
     */
    public boolean updatePeopleByStatus(ActiveStatus status, String peopleIdToUpdate, String lastUpdatedBy) {
        logger.debug("updatePeopleByStatus");

        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(PeopleCfg.FIELD_ACTIVE_STATUS, status));
        parameters.add(new ModelParameter(PeopleCfg.FIELD_LAST_UPDATED_BY, lastUpdatedBy));
        parameters.add(new ModelParameter(PeopleCfg.FIELD_LAST_UPDATED_ON, new Date()));
        parameters.add(new ModelParameter(PeopleCfg.FIELD_ID, peopleIdToUpdate));

        return genericEntityRepository.xmlUpdateOrDeleteAPCEntity(PeopleCfg.UPDATE_PEOPLE_BY_STATUS, parameters);
    }
    
    /**
     *
     * @param type
     * @param peopleIdToUpdate
     * @param lastUpdatedBy
     * @return
     */
    public boolean updatePeopleTypeById(PeopleType type, String peopleIdToUpdate, String lastUpdatedBy) {
        logger.debug("updatePeopleTypeById");

        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(PeopleCfg.FIELD_PEOPLE_TYPE, type));
        parameters.add(new ModelParameter(PeopleCfg.FIELD_LAST_UPDATED_BY, lastUpdatedBy));
        parameters.add(new ModelParameter(PeopleCfg.FIELD_LAST_UPDATED_ON, new Date()));
        parameters.add(new ModelParameter(PeopleCfg.FIELD_ID, peopleIdToUpdate));

        return genericEntityRepository.xmlUpdateOrDeleteAPCEntity(PeopleCfg.UPDATE_PEOPLE_TYPE_BY_STATUS, parameters);
    }
    
    /**
     *
     * Searching people.
     *
     * @param peopleId
     * @return
     */
    public People findPeopleById(String peopleId) {
        logger.debug("findPeopleById");

        return (People) genericEntityRepository.selectAPCEntityById(People.class, peopleId);
    }

    public PeopleController() {
        this.genericEntityRepository = new GenericEntityRepository();
    }
    
}
