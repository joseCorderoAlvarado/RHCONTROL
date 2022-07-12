/*
 * Arrebol Consultancy copyright
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller;

import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.core.HumanResource;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.User;
import com.arrebol.apc.model.core.constance.HumanResourceCfg;
import com.arrebol.apc.model.core.constance.UserCfg;
import com.arrebol.apc.model.enums.HumanResourceStatus;
import com.arrebol.apc.repository.GenericEntityRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class GenericController implements Serializable{

    /**
     *
     * Searching all user actives by office.
     *
     * @param officeId
     * @return
     */
    public List<User> getAllUsersByOffice(String officeId) {
        logger.debug("getAllUsersByOffice");
        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(UserCfg.FIELD_OFFICE, new Office(officeId)));

        return genericEntityRepository.xmlQueryAPCEntities(User.class, UserCfg.QUERY_FIND_ALL_USERS_COMPLETE, parameters);
    }
    
    /**
     *
     * Searching all human resources actives by office.
     *
     * @param officeId
     * @return
     */
    public List<HumanResource> getAllHRByOffice(String officeId) {
        logger.debug("getAllHRByOffice");
        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(HumanResourceCfg.FIELD_OFFICE, new Office(officeId)));
        parameters.add(new ModelParameter(HumanResourceCfg.FIELD_HR_STATUS, HumanResourceStatus.ENEBLED));

        return genericEntityRepository.xmlQueryAPCEntities(HumanResource.class, HumanResourceCfg.QUERY_FIND_ALL_HR_BY_OFFICE, parameters);
    }
    
    final Logger logger = LogManager.getLogger(GenericController.class);
    private final GenericEntityRepository genericEntityRepository;

    public GenericController() {
        this.genericEntityRepository = new GenericEntityRepository();
    }
}
