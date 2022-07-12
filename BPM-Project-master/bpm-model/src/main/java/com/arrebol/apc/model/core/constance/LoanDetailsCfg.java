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
public interface LoanDetailsCfg extends GenericCfg {

    String QUERY_FIND_LOAN_DETAILS_BY_LOAN = "findLoanDetailsByLoan";
    String QUERY_FIND_FEES_TO_PAY_BY_LOAN_ID = "findFeesToPayByLoanId";
    String QUERY_FIND_ALL_FEES_BY_LOAN_ID = "findAllFeesByLoanId";
    String QUERY_UPDATE_PAID_FEES_STATUS_IN_LOAN_DETAILS_IDS = "updatePaidFeesStatusInLoanDetailIds";

    String FIELD_ID_LOAN = "loan";

}
