/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.catalog;

import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.catalog.RoleCtlg;
import com.arrebol.apc.model.catalog.constance.RoleCfg;
import com.arrebol.apc.model.enums.ActiveStatus;
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
public class RoleController implements Serializable{
    
    /**
     *
     * Searching all roles.
     *
     * @return
     */
    public List<RoleCtlg> fillRolesDatatable() {

        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(RoleCfg.FIELD_ACTIVE_STATUS, ActiveStatus.ENEBLED));

        return genericEntityRepository.xmlQueryAPCEntities(RoleCtlg.class, RoleCfg.QUERY_FIND_ALL_ROLES, parameters);
    }
    
    /**
     *
     * @param role
     * @return boolean
     */
    public boolean saveRoles(RoleCtlg role) {
        logger.debug("saveRolesController");
        boolean success = genericEntityRepository.insertAPCEntity(role);

        return success;
    }
    
    /**
     *
     * @param role
     * @return boolean
     */
    public boolean updateByRoleId(RoleCtlg role) {
        logger.debug("updateByRoleId");

        return genericEntityRepository.updateAPCEntity(role);
    }
    
    /**
     *
     * @param status
     * @param roleIdToUpdate
     * @return
     */
    public boolean updateRoleByStatus(ActiveStatus status, String roleIdToUpdate, String lastUpdatedBy) {
        logger.debug("updateRoleByStatus");

        List<ModelParameter> parameters = new ArrayList<>();

        parameters.add(new ModelParameter(RoleCfg.FIELD_ACTIVE_STATUS, status));
        parameters.add(new ModelParameter(RoleCfg.FIELD_LAST_UPDATED_BY, lastUpdatedBy));
        parameters.add(new ModelParameter(RoleCfg.FIELD_LAST_UPDATED_ON, new Date()));
        parameters.add(new ModelParameter(RoleCfg.FIELD_ID, roleIdToUpdate));

        return genericEntityRepository.xmlUpdateOrDeleteAPCEntity(RoleCfg.UPDATE_ROLE_BY_STATUS, parameters);
    }

    
    final Logger logger = LogManager.getLogger(RoleController.class);
    private final GenericEntityRepository genericEntityRepository;

    public RoleController() {
        this.genericEntityRepository = new GenericEntityRepository();
    }
}
