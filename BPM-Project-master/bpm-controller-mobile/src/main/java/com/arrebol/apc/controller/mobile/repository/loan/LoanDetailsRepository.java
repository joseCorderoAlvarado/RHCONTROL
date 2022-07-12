/*
 * Arrebol Consultancy copyright.
 * 
 * This code belongs to Arrebol Consultancy
 * its use, redistribution or modification are prohibited 
 * without written authorization from Arrebol Consultancy.
 */
package com.arrebol.apc.controller.mobile.repository.loan;

import com.arrebol.apc.controller.mobile.repository.GenericRepository;
import com.arrebol.apc.controller.mobile.util.HibernateUtil;
import com.arrebol.apc.model.ModelParameter;
import com.arrebol.apc.model.enums.FeeStatus;
import com.arrebol.apc.model.loan.Loan;
import com.arrebol.apc.model.loan.LoanDetails;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Tuple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoanDetailsRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param details
     * @return
     * @throws Exception
     */
    public boolean saveLoanDetails(LoanDetails details) throws Exception {
        logger.debug("saveLoanDetails");
        try {
            return save(details);
        } catch (Exception e) {
            logger.error("saveLoanDetails", e);
            throw e;
        }
    }

    /**
     *
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public List<LoanDetails> findFeesToPayByLoanId(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("findFeesToPayByLoanId");

        List<LoanDetails> results = new ArrayList<>();
        try {
            List<Tuple> tuples = xmlQueryTuple(xmlQuery, parameters);

            tuples.forEach((tuple) -> {
                results.add(new LoanDetails(tuple.get("id", String.class), tuple.get("createdOn", Date.class), tuple.get("feeStatus", FeeStatus.class)));
            });
        } catch (Exception e) {
            logger.error("findFeesToPayByLoanId", e);
            throw e;
        }

        return results;
    }

    /**
     *
     * @param xmlQuery
     * @param parameters
     * @param loanDetails
     * @return
     * @throws Exception
     */
    public boolean updatePaidFeesStatusInLoanDetailIds(String xmlQuery, List<ModelParameter> parameters, LoanDetails loanDetails) throws Exception {
        boolean success = false;

        Session sssn = null;
        Transaction trnsctn = null;
        try {
            sssn = HibernateUtil.getSessionFactory().getCurrentSession();
            trnsctn = sssn.beginTransaction();

            Loan loan = sssn.get(Loan.class, loanDetails.getLoan().getId());

            if (null != parameters && !parameters.isEmpty()) {
                Query query = sssn.createNamedQuery(xmlQuery);
                parameters.forEach((param) -> {
                    query.setParameter(param.getParameter(), param.getValue());
                });
                query.executeUpdate();
                logger.debug("Query update executed");
            }
            BigDecimal totalFees = loan.getAmountPaid().add(loanDetails.getPaymentAmount());

            loanDetails.setReferenceNumber(loan.getLastReferenceNumber() + 1);

            sssn.save(loanDetails);

            loan.setAmountPaid(totalFees);
            loan.setLastReferenceNumber(loan.getLastReferenceNumber() + 1);
            loan.setLastUpdatedBy(loanDetails.getCreatedBy());
            loan.setLastUpdatedOn(new Date());

            sssn.update(loan);

            trnsctn.commit();

            logger.info("Entity updated");
            success = true;
        } catch (Exception e) {
            logger.error("Method updatePaidFeesStatusInLoanDetailIds() ", e);
            if (null != trnsctn) {
                trnsctn.rollback();
            }
            throw e;
        }
        /* finally {
            if (null != sssn) {
                sssn.close();
            }
        }*/
        return success;
    }

    private static final long serialVersionUID = -6088464996350747643L;
    final Logger logger = LogManager.getLogger(LoanDetailsRepository.class);
}
