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
public interface LoanByUserViewCfg extends GenericCfg {

    /**
     *
     */
    String QUERY_FIND_ALL_LOAN_BY_USER_ID_BY_ORDER_LIST = "findAllLoansByUserIdOrderByOrderList";
    
    /**
     * 
     */
    String QUERY_FIND_ALL_LOAN_BY_USER_ID_BY_CUSTOMER_NAME = "findAllLoansByUserIdOrderByCustomerName";

    /**
     *
     */
    String FIELD_USER_ID = "userId";

    /**
     *
     */
    String FIELD_ORDER_IN_LIST = "orderInList";

    /**
     *
     */
    String FIELD_CUSTOMER_NAME = "customerName";

    /**
     *
     */
    String PARAM_PREFERENCE_ORDER = "preferenceOrder";
}
