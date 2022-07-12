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
public interface ExpenseCompanyCfg extends GenericCfg {

    String QUERY_FIND_ALL_EXPENSE_COMPANY_BY_OFFICE = "findAllExpenseCompanyByOffice";
    String QUERY_FIND_ALL_EXPENSE_COMPANY_BY_OFFICE_BETWEEN_DATES = "findAllExpenseCompanyByOfficeBetweenDates";
    String QUERY_UPDATE_EXPENSE_COMPANY_BY_STATUS = "updateExpenseCompanyByStatus";

    String FIELD_OFFICE = "office";
    String FIELD_ACTIVE_STATUS = "activeStatus";
    String FIELD_EXPENSE_COMPANY_TYPE = "expenseCompanyType";

}
