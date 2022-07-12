/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.model.admin.constance;

import com.arrebol.apc.model.core.constance.GenericCfg;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public interface StableSmallBoxCfg extends GenericCfg {
    
    String QUERY_FIND_ALL_STABLE_SMALL_BOX_BY_OFFICE = "findAllStableSmallBoxByOffice";
    String QUERY_FIND_ALL_STABLE_SMALL_BOX_BY_OFFICE_BETWEEN_DATES = "findAllStableSmallBoxByOfficeBetweenDates";
    String QUERY_UPDATE_STABLE_SMALL_BOX_BY_STATUS = "updateStableSmallBoxByStatus";
    String QUERY_COUNT_STABLE_SMALL_BOX_BY_OFFICE_AND_DATE = "countStableSmallBoxByOfficeAndDate";
    
    String QUERY_SUM_PENDING_CLOSING_BY_OFFICE = "sumAdvancesUserDailyByOffice";
    
    String FIELD_OFFICE = "office";
    String FIELD_OFFICE_VIEW = "idOffice";
    String FIELD_ACTIVE_STATUS = "activeStatus";
    String FIELD_CREATEDON = "createdOn";

}
