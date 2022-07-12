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
public interface StableGeneralBoxCfg extends GenericCfg {

    String QUERY_FIND_ALL_STABLE_GENERAL_BOX_BY_OFFICE = "findAllStableGeneralBoxByOffice";
    String QUERY_FIND_ALL_STABLE_GENERAL_BOX_BY_OFFICE_BETWEEN_DATES = "findAllStableGeneralBoxByOfficeBetweenDates";
    String QUERY_UPDATE_STABLE_GENERAL_BOX_BY_STATUS = "updateStableGeneralBoxByStatus";
    String QUERY_COUNT_STABLE_GENERAL_BOX_BY_OFFICE_AND_DATE = "countStableGeneralBoxByOfficeAndDate";

    String QUERY_MAX_STABLE_GENERAL_BOX_BY_OFFICE = "verifyStableGeneralBoxCreatedByOffice";
    String QUERY_MAX_CLOSING_DAY_BY_OFFICE = "verifyClosingDayCreatedByOffice";
    
    String QUERY_SUM_PENDING_CLOSING_BY_OFFICE = "sumAdvancesUserDailyByOffice";

    String FIELD_OFFICE = "office";
    String FIELD_OFFICE_VIEW = "idOffice";
    String FIELD_ACTIVE_STATUS = "activeStatus";
    String FIELD_CREATEDON = "createdOn";

}
