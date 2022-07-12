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
public interface LoanTypeCfg extends GenericCfg {

    String QUERY_FIND_ALL_LOAN_TYPE_BY_OFFICE = "findAllLoanTypeByOffice";
    String QUERY_FIND_NEW_CREDIT_LINE_BY_LOAN_ID = "findNewCreditLineByLoanId";

    String QUERY_FIND_ALL_DATA_LOAN_TYPE_BY_OFFICE = "findAllDataLoanTypeByOffice";
    
    String FIELD_OFFICE = "office";

    String PARAM_LOAN = "loan";
    String PARAM_LOAN_ID = "loanId";
}
