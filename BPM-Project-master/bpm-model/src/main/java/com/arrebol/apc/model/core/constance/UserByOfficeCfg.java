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
public interface UserByOfficeCfg extends GenericCfg {

    String QUERY_FIND_USER_LOGGED = "findIdsFromUserLogged";
    String QUERY_FIND_USERS_IN_OFFICE_IN_STATUSES = "findUsersInOfficeInStatuses";
    String QUERY_FIND_ID_OF_USER_BY_OFFICE = "findIdOfUserByOffice";

    String FIELD_USER = "user";
    String FIELD_OFFICE = "office";

    String PARAM_USER_BY_OFFICE_ID = "userByOfficeId";

}
