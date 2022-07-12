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
public interface LoanCfg extends GenericCfg {

    String QUERY_UPDATE_LOAN_BY_ID = "updateLoanById";
    String QUERY_UPDATE_LOAN_FROM_RENOVATION = "updateLoanFromRenovation";
    String QUERY_UPDATE_LOAN_FROM_WEB = "updateLoanFromWeb";
    String QUERY_FIND_LOAN_BY_CUSTOMER = "findLoansByCustomer";
    String QUERY_FIND_LOAN_JURIDICAL = "findLoansJuridical";
    String QUERY_FIND_LOAN_ZERO = "findLoansZero";
    String QUERY_FIND_LOAN_FINISHED = "findLoansFinished";
    String QUERY_FIND_LOAN_BY_ENDORSEMENT = "findLoansByEndorsement";
    String QUERY_UPDATE_LOAN_BY_ID_FROM_CERTIFIER_VIEW = "updateLoanByIdFromCertifiedView";
    String QUERY_UPDATE_LOAN_WITH_CREATED_ON_BY_ID_FROM_CERTIFIER_VIEW = "updateLoanWithCreatedOnByIdFromCertifiedView";
    String QUERY_UPDATE_AND_FINISH_LOAN_BY_ID = "updateAndFinishLoanById";
    String QUERY_UPDATE_DISCOUNT_AND_LOAN_BY_ID_FROM_CERTIFIER_VIEW = "updateDiscountAndLoanByIdFromCertifiedView";
    String QUERY_FIND_LOAN_DETAILS_CURDATE_BY_LOAN = "findLoanDetailsPaymentCurdateByLoan";
    String QUERY_DELETE_LOAN_DETAILS_CURDATE_BY_LOAN = "deleteLoanDetailsPaymentCurdateByLoan";
    String QUERY_SEARCH_PAYMENT_DETAILS = "searchPaymentDetails";
    String QUERY_UPDATE_ROUTE_BY_ID = "updateRouteFromLoan";
    String QUERY_FIND_LOAN_DETAILS_FEE_CURDATE_BY_LOAN = "findLoanDetailsFeeCurdateByLoan";
    String QUERY_DELETE_LOAN_DETAILS_FEE_CURDATE_BY_LOAN = "deleteLoanDetailsFeeCurdateByLoan";
    String QUERY_DELETE_LOAN_FEE_NOTIFICATION_CURDATE_BY_LOAN = "deleteLoanFeeNotificationCurdateByLoan";
    String QUERY_DELETE_LOAN_FEE_NOTIFICATION_BY_LOAN = "deleteLoanFeeNotificationByLoan";
    String QUERY_UPDATE_LOAN_BONUS_NEW_CUSTOMER = "updateBonusNewCustomer";
    String QUERY_FIND_LAST_REFERENCE_NUMBER_BY_LOAN = "findLastReferenceNumberByLoan";

    String QUERY_FIND_LOAN_BY_STATUS_PENDING = "findLoansByStatusPending";
    String QUERY_FIND_ALL_LOANS = "findAllLoans";
    String QUERY_FIND_ALL_LOANS_VIEW = "findAllLoansView";
    String QUERY_FIND_ALL_LOANS_VIEW_BY_START_AND_END_DATE = "findAllLoansViewByStartAndEndDate";
    String QUERY_FIND_LOAN_DETAILS_BY_ID = "findLoansDetailById";
    String FIELD_AMOUNT_PAID = "amountPaid";
    String FIELD_AMOUNT_TO_PAY = "amountToPay";
    String FIELD_LAST_REFERENCE_NUMBER = "lastReferenceNumber";
    String FIELD_LOAN_STATUS = "loanStatus";
    String FIELD_NEW_CUSTOMER = "newCustomer";
    String FIELD_CUSTOMER = "customer";
    String FIELD_ENDORSEMENT = "endorsement";
    String FIELD_CUSTOMER_OFFICE = "customer.office";
    String FIELD_DETAILS_LOAN = "loan";
    String FIELD_COMMENTS = "comments";
    String FIELD_ROUTE = "routeCtlg";
    String FIELD_CREATED_ON = "createdOn";
    String FIELD_USER = "idUser";

}
