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
public interface PermissionCfg extends GenericCfg {

    String QUERY_FIND_PERMISSION_THAT_USER_HAS_ASSIGNED = "finPermissionThatUserHasAssigned";
    String QUERY_FIND_MISSING_PERMISSION_UBO = "findMissingPermissionByUserByOffice";
    String QUERY_FIND_ENEBLED_PERMISSIONS = "findAllEnebledPermissions";
    String QUERY_FIND_PERMISSION_BY_TYPE_AND_STATUS = "findPermissionByTypeAndStatus";
    String QUERY_FIND_ALL_PERMISSIONS_BY_UBO = "findAllPermissionsByUBO";
    String QUERY_FIND_ALL_NOT_PERMISSIONS_BY_UBO = "findAllNotPermissionsByUBO";
    String QUERY_FIND_ALL_PERMISSION_BY_TYPE_AND_STATUS = "findAllPermissionByTypeAndStatus";

    String FIELD_PERMISSION = "permission";
    String FIELD_DESCRIPTION = "description";
    String FIELD_MENU_PATH = "menuPath";
    String FIELD_LEFT_TO_RIGHT = "leftToRightOrder";
    String FIELD_TOP_TO_BOTTOM = "topToBottomOrder";
    String FIELD_PERMISSION_STATUS = "permissionStatus";
    String FIELD_PERMISSION_TYPE = "permissionType";

}
