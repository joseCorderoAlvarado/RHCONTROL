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
public interface PeopleCfg extends GenericCfg {

    String UPDATE_PEOPLE_BY_STATUS = "updateByPeopleId";
    String UPDATE_PEOPLE_TYPE_BY_STATUS = "updateTypeByPeopleId";
    String UPDATE_ROUTE_BY_PEOPLE = "updateRouteByPeopleId";
    String UPDATE_PEOPLE_BY_CLASSIFICATION = "updateByPeopleClassification";
    String QUERY_FIND_ALL_CUSTOMER = "findAllCustomerPeople";
    String QUERY_FIND_ALL_ENDORSEMENT = "findAllEndorsementPeople";
    String QUERY_FIND_PEOPLE_BY_ID = "findPeopleById";
    String QUERY_UPDATE_PHONE_HOME_BY_PEOPLE_ID = "updatePhoneHomeByPeopleId";

    String FIELD_PEOPLE_TYPE = "peopleType";
    String FIELD_ACTIVE_STATUS = "activeStatus";
    String FIELD_OFFICE = "office";
    String FIELD_ROUTE = "routeCtlg";
    String FIELD_CLASSIFICATION = "classification";
    String FIELD_PHONE_HOME = "phoneHome";
}
