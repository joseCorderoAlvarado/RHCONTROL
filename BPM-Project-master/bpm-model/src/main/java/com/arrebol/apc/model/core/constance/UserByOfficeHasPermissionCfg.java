/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.core.constance;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public interface UserByOfficeHasPermissionCfg extends GenericCfg {

    String XML_QUERY_DELETE_PERMISSION_BY_USER_ID_AND_OFFICE_ID = "deletePermissionByUserIdAndOfficeId";
    String QUERY_DELETE_ALL_PERMISSION_BY_UBO = "deleteAllPermissionByUBO";

    String FIELD_USER_BY_OFFICE = "userByOffice";
}
