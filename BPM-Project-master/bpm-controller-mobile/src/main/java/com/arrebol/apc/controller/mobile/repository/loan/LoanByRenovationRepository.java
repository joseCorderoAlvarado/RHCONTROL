/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.loan;

import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.core.User;
import com.arrebol.apc.model.core.constance.LoanByRenovationCfg;
import com.arrebol.apc.model.core.constance.LoanByUserCfg;
import com.arrebol.apc.model.core.constance.LoanCfg;
import com.arrebol.apc.model.enums.ComissionType;
import com.arrebol.apc.model.enums.LoanRenovationStatus;
import com.arrebol.apc.model.enums.LoanStatus;
import com.arrebol.apc.model.loan.Delivery;
import com.arrebol.apc.model.loan.Loan;
import com.arrebol.apc.model.loan.LoanByRenovation;
import com.arrebol.apc.model.loan.LoanDetails;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoanByRenovationRepository extends GenericRepository implements Serializable {

    private static final long serialVersionUID = 1357519239619447184L;
    final Logger logger = LogManager.getLogger(LoanByRenovationRepository.class);

    /**
     *
     * @param newLoanId
     * @return
     * @throws Exception
     */
    public LoanByRenovation findLoanRenovationByNewLoanId(String newLoanId) throws Exception {
        logger.debug("findLoanRenovationByNewLoanId");
        try {
            List<ModelParameter> parameters = new ArrayList<>();

            parameters.add(
                    new ModelParameter(LoanByRenovationCfg.FIELD_LOAN_NEW, new Loan(newLoanId))
            );

            return (LoanByRenovation) createNamedQueryUniqueResult(
                    LoanByRenovation.class,
                    LoanByRenovationCfg.QUERY_FIND_LOAN_RENOVATION_BY_NEW_LOAN_ID,
                    parameters);
        } catch (Exception e) {
            logger.error("findLoanRenovationByNewLoanId", e);
            throw e;
        }
    }

    /**
     *
     * @param loanByRenovation
     * @param userId
     * @param comments
     * @param action
     * @param amount
     * @param discount
     * @param loanDetails
     * @param totalAmountPaid
     * @param newLastReferenceNumber
     * @param comission
     * @return
     * @throws Exception
     */
    public boolean updateLoanRenovationFromCerfierView(
            LoanByRenovation loanByRenovation,
            String userId,
            String comments,
            boolean action,
            BigDecimal amount,
            BigDecimal discount,
            LoanDetails loanDetails,
            BigDecimal totalAmountPaid,
            Integer newLastReferenceNumber,
            ComissionType comission) throws Exception {
        logger.debug("updateLoanRenovationFromCerfierView");
        boolean success = false;
        try {
            openConnection();

            Date lastUpdatedOn = new Date();
            List<ModelParameter> parameters = new ArrayList<>();

            // Update loanByRenovation details in Renovation table
            parameters.add(new ModelParameter(LoanByRenovationCfg.FIELD_LOAN_RENOVATION_STATUS, action ? LoanRenovationStatus.APPROVED : LoanRenovationStatus.REJECTED));
            parameters.add(new ModelParameter(LoanByRenovationCfg.FIELD_COMMENTS, comments));
            parameters.add(new ModelParameter(LoanByRenovationCfg.FIELD_LAST_UPDATED_BY, userId));
            parameters.add(new ModelParameter(LoanByRenovationCfg.FIELD_LAST_UPDATED_ON, lastUpdatedOn));
            parameters.add(new ModelParameter(LoanByRenovationCfg.FIELD_ID, loanByRenovation.getId()));

            if (0 < executeQuery(LoanByRenovationCfg.QUERY_UPDATE_LOAN_RENOVATION, parameters)) {

                parameters.clear();

                // Update NEW loan details in Loan table
                parameters.add(new ModelParameter(LoanCfg.FIELD_LOAN_STATUS, action ? LoanStatus.APPROVED : LoanStatus.REJECTED));
                parameters.add(new ModelParameter(LoanCfg.FIELD_COMMENTS, comments));
                parameters.add(new ModelParameter(LoanCfg.FIELD_CREATED_ON, lastUpdatedOn));
                parameters.add(new ModelParameter(LoanCfg.FIELD_LAST_UPDATED_BY, userId));
                parameters.add(new ModelParameter(LoanCfg.FIELD_LAST_UPDATED_ON, lastUpdatedOn));
                parameters.add(new ModelParameter(LoanCfg.FIELD_ID, loanByRenovation.getLoanNew().getId()));

                if (0 < executeQuery(LoanCfg.QUERY_UPDATE_LOAN_WITH_CREATED_ON_BY_ID_FROM_CERTIFIER_VIEW, parameters)) {
                    parameters.clear();

                    // Update OLD loan details in Loan table
                    String commentsOldLoan = action ? "Crédito renovado" : "El certificador rechazo la renovación de este crédito";
                    String strQuery = LoanCfg.QUERY_UPDATE_LOAN_BY_ID_FROM_CERTIFIER_VIEW;

                    if (action && null != loanDetails && null != totalAmountPaid && null != newLastReferenceNumber) {
                        parameters.add(new ModelParameter(LoanCfg.FIELD_AMOUNT_PAID, totalAmountPaid));
                        parameters.add(new ModelParameter(LoanCfg.FIELD_LAST_REFERENCE_NUMBER, newLastReferenceNumber));

                        strQuery = LoanCfg.QUERY_UPDATE_DISCOUNT_AND_LOAN_BY_ID_FROM_CERTIFIER_VIEW;
                    }

                    parameters.add(new ModelParameter(LoanCfg.FIELD_LOAN_STATUS, action ? LoanStatus.FINISH : LoanStatus.APPROVED));
                    parameters.add(new ModelParameter(LoanCfg.FIELD_COMMENTS, commentsOldLoan));
                    parameters.add(new ModelParameter(LoanCfg.FIELD_LAST_UPDATED_BY, userId));
                    parameters.add(new ModelParameter(LoanCfg.FIELD_LAST_UPDATED_ON, lastUpdatedOn));
                    parameters.add(new ModelParameter(LoanCfg.FIELD_ID, loanByRenovation.getLoanOld().getId()));

                    if (0 < executeQuery(strQuery, parameters)) {

                        // Update OLD loanByUser details in Loan By User Table
                        List<ModelParameter> params = new ArrayList<>();

                        params.add(new ModelParameter(LoanByUserCfg.FIELD_LOAN, new Loan(loanByRenovation.getLoanOld().getId())));
                        params.add(new ModelParameter(LoanByUserCfg.FIELD_LOAN_BY_USER_STATUS, action ? LoanStatus.FINISH : LoanStatus.APPROVED));

                        Query query1 = getSession().createNamedQuery(LoanByUserCfg.QUERY_UPDATE_LOAN_BY_USER_BY_LOAND_ID);

                        params.forEach((param) -> {
                            query1.setParameter(param.getParameter(), param.getValue());
                        });

                        query1.executeUpdate();

                        // Update NEW loanByUser details in Loan By User Table
                        params.clear();

                        params.add(new ModelParameter(LoanByUserCfg.FIELD_LOAN, new Loan(loanByRenovation.getLoanNew().getId())));
                        params.add(new ModelParameter(LoanByUserCfg.FIELD_LOAN_BY_USER_STATUS, action ? LoanStatus.APPROVED : LoanStatus.REJECTED));

                        Query query2 = getSession().createNamedQuery(LoanByUserCfg.QUERY_UPDATE_LOAN_BY_USER_BY_LOAND_ID);

                        params.forEach((param) -> {
                            query2.setParameter(param.getParameter(), param.getValue());
                        });

                        query2.executeUpdate();

                        if (action) {
                            // Insert new loanDetails (discount) in Loan Details table
                            if (null != loanDetails) {
                                getSession().save(loanDetails);
                            }

                            // Insert delivery details (To know cash that certifier deliveried) in Delivery Table
                            Delivery delivery = new Delivery(
                                    new User(userId),
                                    new Loan(loanByRenovation.getLoanNew().getId()),
                                    amount,
                                    userId,
                                    new Date(),
                                    comission
                            );

                            getSession().save(delivery);
                        }

                        flushAndClear();
                        closeConnection();

                        logger.info("Certifier delivery the loan details: " + loanByRenovation);
                        success = true;
                    } else {
                        rollback();
                    }
                } else {
                    rollback();
                }
            } else {
                rollback();
            }
            return success;
        } catch (Exception e) {
            rollback();
            logger.error("updateLoanRenovationFromCerfierView", e);
            throw e;
        }
    }

    private int executeQuery(String xmlQuery, List<ModelParameter> parameters) {
        Query query = getSession().createNamedQuery(xmlQuery);

        parameters.forEach((param) -> {
            query.setParameter(param.getParameter(), param.getValue());
        });

        return query.executeUpdate();
    }
}
