/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.system.employee;

import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.core.HumanResource;
import com.arrebol.apc.model.core.HumanResourceByOffice;
import com.arrebol.apc.model.core.Office;
import com.arrebol.apc.model.core.constance.HumanResourceByOfficeCfg;
import com.arrebol.apc.model.core.constance.HumanResourceCfg;
import com.arrebol.apc.model.enums.HumanResourceStatus;
import com.arrebol.apc.repository.GenericEntityRepository;
import com.arrebol.apc.repository.core.HumanResourceRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class EmployeeController implements Serializable {

    /**
     * Find where status EQUALS TO status.
     *
     * @param office
     * @param status
     * @param hrOwnerId Human resource id from user logged.
     * @return
     */
    public List<HumanResource> findEmployeesByType(Office office, HumanResourceStatus status, String hrOwnerId) {
        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(HumanResourceCfg.FIELD_OFFICE, office));
        parameters.add(new ModelParameter(HumanResourceCfg.FIELD_HR_STATUS, status));
        parameters.add(new ModelParameter(HumanResourceByOfficeCfg.HUMAN_RESOURCE, new HumanResource(hrOwnerId)));

        return genericEntityRepository.xmlQueryAPCEntities(HumanResource.class, HumanResourceCfg.QUERY_FIND_ALL_BY_STATUS, parameters);
    }

    /**
     * Find where status IN status.
     *
     * @param office
     * @param statusLst
     * @param hrOwnerId Human resource id from user logged.
     * @return
     */
    public List<HumanResource> findEmployeesInType(Office office, List<HumanResourceStatus> statusLst, String hrOwnerId) {
        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(HumanResourceCfg.FIELD_OFFICE, office));
        parameters.add(new ModelParameter(HumanResourceCfg.FIELD_HR_STATUS, statusLst));
        parameters.add(new ModelParameter(HumanResourceByOfficeCfg.HUMAN_RESOURCE, new HumanResource(hrOwnerId)));

        return genericEntityRepository.xmlQueryAPCEntities(HumanResource.class, HumanResourceCfg.QUERY_FIND_ALL_IN_STATUS, parameters);
    }

    /**
     * Save an entity.
     *
     * @param humanResourceByOffice
     * @param humanResource
     * @return
     */
    public boolean saveHRController(HumanResourceByOffice humanResourceByOffice, HumanResource humanResource) {
        logger.debug("saveHRController");

        boolean success = genericEntityRepository.insertAPCEntity(humanResource);

        if (success) {
            humanResourceByOffice.setHumanResource(new HumanResource(humanResource.getId()));
            success = genericEntityRepository.insertAPCEntity(humanResourceByOffice);
        }

        return success;
    }

    /**
     *
     * @param hr
     * @param updateAvatar
     * @return
     */
    public boolean updateByHumanResourceId(HumanResource hr, boolean updateAvatar) {
        logger.debug("updateByHumanResourceId");

        return humanResourceRepository.updateByHumanResourceId(hr, updateAvatar);
    }

    /**
     *
     * @param status
     * @param userIdToUpdate
     * @param lastUpdatedBy
     * @return
     */
    public boolean updateHRByStatus(HumanResourceStatus status, String userIdToUpdate, String lastUpdatedBy) {
        logger.debug("updateHRByStatus");

        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(HumanResourceCfg.FIELD_HR_STATUS, status));
        parameters.add(new ModelParameter(HumanResourceCfg.FIELD_LAST_UPDATED_BY, lastUpdatedBy));
        parameters.add(new ModelParameter(HumanResourceCfg.FIELD_LAST_UPDATED_ON, new Date()));
        parameters.add(new ModelParameter(HumanResourceCfg.FIELD_ID, userIdToUpdate));

        return genericEntityRepository.xmlUpdateOrDeleteAPCEntity(HumanResourceCfg.UPDATE_HR_BY_STATUS, parameters);
    }

    /**
     *
     * @param hrId
     * @return
     */
    public HumanResource findHumanResourceById(String hrId) {
        logger.debug("findHumanResourceById");

        return (HumanResource) genericEntityRepository.selectAPCEntityById(HumanResource.class, hrId);
    }

    private static final long serialVersionUID = 2527037592427439763L;
    final Logger logger = LogManager.getLogger(EmployeeController.class);

    private final GenericEntityRepository genericEntityRepository;
    private final HumanResourceRepository humanResourceRepository;

    public EmployeeController() {
        this.genericEntityRepository = new GenericEntityRepository();
        this.humanResourceRepository = new HumanResourceRepository();
    }

}
