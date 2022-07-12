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
public interface LoanByRenovationCfg extends GenericCfg {

    String QUERY_FIND_LOAN_RENOVATION_BY_NEW_LOAN_ID = "findLoanRenovationByNewLoanId";
    String QUERY_UPDATE_LOAN_RENOVATION = "updateLoanRenovation";
    String QUERY_UPDATE_LOAN_RENOVATION_WEB = "updateLoanRenovationWeb";

    String FIELD_LOAN_NEW = "loanNew";
    String FIELD_LOAN_RENOVATION_STATUS = "loanRenovationStatus";
    String FIELD_COMMENTS = "comments";
}
