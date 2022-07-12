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
 * @author Oscar Armando Vargas Cardenas <oscar.vargas@arrebol.com.mx>
 */
public interface ClosingDayCfg extends GenericCfg {

    String QUERY_FIND_ALL_CLOSING_DAY_BY_OFFICE = "findAllClosingDayByOffice";
    String QUERY_FIND_ALL_CLOSING_DAY_BY_OFFICE_BETWEEN_DATES = "findAllClosingDayByOfficeBetweenCreatedOn";
    String QUERY_UPDATE_CLOSING_DAY_BY_STATUS = "updateClosingDayByStatus";
    String QUERY_FIND_TOTAL_BY_USER = "findAllTotalCashByCurdateByUserId";
    String QUERY_FIND_TOTAL_BY_OFFICE = "findAllTotalCashByCurdateByOffice";
    String QUERY_FIND_TOTAL_DASHBOARD_BY_OFFICE = "findAllTotalCashByCurdateDashboardByOffice";
    String QUERY_FIND_ALL_CLOSING_DAY_BY_CURDATE = "findAllViewTotalClosingDayByCurdateByOffice";
    String QUERY_COUNT_CLOSING_DATE_BY_USER_AND_OFFICE = "countClosingDateByUserAndOffice";
    String QUERY_SUM_CLOSING_DAY_BY_CURDATE_AND_OFFICE = "findSumClosingDayByCurdate";

    String FIELD_USER = "user";
    String FIELD_OFFICE = "office";
    String FIELD_ACTIVE_STATUS = "activeStatus";
    String FIELD_VIEW_OFFICE = "idOffice";
    String FIELD_CREATED_ON = "createdOn";

}
