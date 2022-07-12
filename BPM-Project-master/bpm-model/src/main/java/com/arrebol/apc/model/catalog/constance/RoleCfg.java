/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.catalog.constance;

import com.arrebol.apc.model.core.constance.GenericCfg;

/**
 *
 * @author Oscar Armando Vargas Cardenas <oscar.vargas@arrebol.com.mx>
 */
public interface RoleCfg extends GenericCfg {
    
    String UPDATE_ROLE_BY_STATUS = "updateByRoleId";
    String QUERY_FIND_ALL_ROLES = "findAllRoles";
    
    String FIELD_NAME = "role";
    String FIELD_ACTIVE_STATUS = "activeStatus";
  
}
