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
public interface LoanByUserCfg extends GenericCfg {

    String QUERY_UPDATE_ORDER_IN_LIST = "updateOrderInList";
    String QUERY_UPDATE_LOAN_BY_USER_BY_LOAND_ID = "updateLoandByUserByLoanId";
    String QUERY_UPDATE_LOAN_BY_USER_BU_USER_ID = "updateLoandByUserByUserId";
    String QUERY_FIND_LOAN_BY_USER_BY_LOAND_ID = "findLoandByUserByLoanId";
    String QUERY_CHANGE_LOANS_BETWEEN_USERS_IN_LOAN_IDS = "changeLoansBetweenUsersInLoanIds";

    String FIELD_ORDER_IN_LIST = "orderInList";
    String FIELD_LOAN = "loan";
    String FIELD_LOAN_BY_USER_STATUS = "loanByUserStatus";
    String FIELD_USER = "user";

    String PARAMS_LOAN = "loans";
}
