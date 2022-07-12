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
public interface OtherExpenseCfg extends GenericCfg {

    String QUERY_FIND_ALL_OTHER_EXPENSE_BY_OFFICE = "findAllOtherExpensesByOffice";
    String QUERY_FIND_ALL_OTHER_EXPENSE_BY_OFFICE_BETWEEN_DATES = "findAllOtherExpensesByOfficeBetweenDates";

    String FIELD_OFFICE = "office";

}
