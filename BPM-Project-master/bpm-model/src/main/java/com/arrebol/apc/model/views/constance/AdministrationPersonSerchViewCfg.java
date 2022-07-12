/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.views.constance;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public interface AdministrationPersonSerchViewCfg {

    String QUERY_FIND_PERSON_BY_TYPE_ROUTE_OFFICE = "findPersonByPersonTypeAndRouteAndOffice";
    String QUERY_LIKE_PERSON_NAME_IN_PERSON_TYPE_IN_ROUTES_AND_OFFICE = "likePersonNameInPersonTypeInRoutesAndOffice";
    String QUERY_LIKE_PERSON_NAME_IN_PERSON_TYPE_ALL_ROUTES_BY_OFFICE = "likePersonNameInPersonTypeAllRoutesByOffice";

    String FIELD_PEOPLE_TYPE = "peopleType";
    String FIELD_ID_ROUTE = "idRoute";
    String FIELD_ID_OFFICE = "idOffice";
    String FIELD_PERSON_SEARCH = "personSearch";
}
