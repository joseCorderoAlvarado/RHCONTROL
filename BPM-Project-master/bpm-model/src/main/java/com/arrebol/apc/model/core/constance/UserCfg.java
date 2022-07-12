/*
 * Arrebol Consultancy copyright
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.core.constance;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public interface UserCfg extends GenericCfg {

    /**
     * Queries
     */
    String QUERY_FIND_ALL_USERS_BY_OFFICE = "findAllUsersByOffice";
    String QUERY_IS_USERNAME_AVAILABLE = "isUsernameAvailable";
    String QUERY_FIND_ALL_USERS_COMPLETE = "findAllUsersCompleteByOffice";
    String QUERY_UPDATE_PASSWORD_BY_USER_ID = "updatePasswordByUserId";
    String QUERY_UPDATE_CERTIFIER_AND_USER_TYPE_BY_ID = "updateCertifierAndUserTypeById";
    String QUERY_UPDATE_USER_NAME_BY_USER_ID = "updateUserNameById";
    String QUERY_UPDATE_USER_STATUS_BY_USER_ID = "updateUserStatusById";
    String QUERY_VERIFY_USER_STATUS_BY_ID = "verifyUserStatusById";

    /**
     * Field
     */
    String FIELD_USER_NAME = "userName";
    String FIELD_PASSWORD = "password";
    String FIELD_HUMAN_RESOURCE = "humanResource";
    String FIELD_USER_STATUS = "userStatus";
    String FIELD_OFFICE = "office";
    String FIELD_CERTIFIER = "certifier";
    String FIELD_USER_TYPE = "userType";
}
