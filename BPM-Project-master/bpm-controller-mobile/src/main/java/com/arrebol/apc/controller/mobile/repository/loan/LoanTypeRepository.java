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
import com.arrebol.apc.model.loan.LoanType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Tuple;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Carlos Janitzio Zavala Lopez <janitzio.zavala@arrebol.com.mx>
 */
public class LoanTypeRepository extends GenericRepository implements Serializable {

    /**
     *
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public List<LoanType> findAllLoanTypeByOffice(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("findAllLoanTypeByOffice");

        List<LoanType> results = new ArrayList<>();
        try {
            List<Tuple> tuples = xmlQueryTuple(xmlQuery, parameters);

            tuples.forEach((tuple) -> {
                results.add(new LoanType(tuple.get("id").toString(), (BigDecimal) tuple.get("payment")));
            });
        } catch (Exception e) {
            logger.error("findAllLoanTypeByOffice", e);
            throw e;
        }

        return results;
    }

    /**
     * Find loan type by id.
     *
     * @param id Identifycation number
     * @return
     * @throws Exception
     */
    public LoanType findLoanType(String id) throws Exception {
        logger.debug("findLoanType");
        try {
            return (LoanType) findAPCEntity(LoanType.class, id);
        } catch (Exception e) {
            logger.error("findLoanType", e);
            throw e;
        }
    }

    /**
     *
     * @param clazz
     * @param xmlQuery
     * @param parameters
     * @return
     * @throws Exception
     */
    public List<LoanType> findLoanTypes(Class clazz, String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("findLoanTypes");
        try {
            return createNamedQueryResultList(clazz, xmlQuery, parameters);
        } catch (Exception e) {
            logger.error("findLoanTypes", e);
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
    public List<LoanType> findIdAndPaymentLoans(String xmlQuery, List<ModelParameter> parameters) throws Exception {
        logger.debug("findIdAndPaymentLoans");

        List<LoanType> results = new ArrayList<>();
        try {
            List<Tuple> tuples = xmlQueryTuple(xmlQuery, parameters);

            tuples.forEach((tuple) -> {
                results.add(new LoanType(tuple.get("id").toString(), (BigDecimal) tuple.get("payment")));
            });
        } catch (Exception e) {
            logger.error("findIdAndPaymentLoans", e);
            throw e;
        }

        return results;
    }

    private static final long serialVersionUID = -5815645336646530766L;
    final Logger logger = LogManager.getLogger(LoanTypeRepository.class);
}
